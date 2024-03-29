package sportify.backend.api.service.matches;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sportify.backend.api.config.Constants;
import sportify.backend.api.config.RestEndPoints;
import sportify.backend.api.domain.matches.IplAllMatchesApi;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;
import sportify.backend.api.mapper.matches.IplAllMatchesApiMapper;
import sportify.backend.api.repository.matches.IplAllMatchesApiRepository;
import sportify.backend.api.service.CricketDataService;
import util.JavaApiClass.CommonUtil;
import util.JavaApiClass.iplAllMatches.IplAllMatches;
import util.JavaApiClass.iplAllMatches.Match;
import util.JavaApiClass.iplAllMatches.TeamInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IplAllMatchesApiServiceImpl implements IplAllMatchesApiService{
    @Autowired
    CricketDataService cricketDataService;
    @Autowired
    IplAllMatchesApiRepository iplAllMatchesApiRepository;
    @Override
    public List<IplAllMatchesApi> createEntity() throws Exception {
        IplAllMatches iplAllMatches = cricketDataService.fetchDataFromApi(IplAllMatches.class,Constants.IPL_ALL_MATCHES_ID, Constants.IPL_SERIES_INFO);
        List<Match> matchList = iplAllMatches.getData().getMatchList();

                    for (Match match : matchList) {
                        Optional<IplAllMatchesApiDto> existingMatchOpt = iplAllMatchesApiRepository.findByMatchId(match.getId());
                        if (!existingMatchOpt.isPresent()) {
                            // Match does not exist, create a new entity
                            IplAllMatchesApiDto iplAllMatchesApiDto = new IplAllMatchesApiDto();
                            iplAllMatchesApiDto.setVenue(match.getVenue());
                            iplAllMatchesApiDto.setDate(match.getDate());
                            iplAllMatchesApiDto.setTime(match.getDateTimeGMT());
                            iplAllMatchesApiDto.setTeamsName(match.getTeams());
                            iplAllMatchesApiDto.setTeamInfo(match.getTeamInfo());
                            iplAllMatchesApiDto.setMatchId(match.getId());
                            iplAllMatchesApiDto.setStatus(match.getStatus());
                            iplAllMatchesApiDto.setMatchNumber(match.getName().split(",")[1]);

                             iplAllMatchesApiRepository.save(IplAllMatchesApiMapper.toEntity(iplAllMatchesApiDto));
                        } else {
                            if(!match.getStatus().equals(existingMatchOpt.get().getStatus())){
                                IplAllMatchesApiDto iplAllMatchesApiDto=existingMatchOpt.get();
                                iplAllMatchesApiDto.setStatus(match.getStatus());
                                iplAllMatchesApiRepository.save(IplAllMatchesApiMapper.toEntity(iplAllMatchesApiDto));
                            }
                            continue;
                        }
                    }
                    return iplAllMatchesApiRepository.findAll();
            }


    @Override
    public Page<IplAllMatchesApiDto> getAllEntities(Pageable pageable) throws Exception {
        Page<IplAllMatchesApi> entityPage = iplAllMatchesApiRepository.findAll(pageable);
        if (entityPage.getContent().size() > 0) {
            return new PageImpl<>(entityPage.getContent().stream().map(IplAllMatchesApiMapper::toDTO).collect(Collectors.toList()), pageable, entityPage.getTotalElements());
        } else {
            return new PageImpl<>(new ArrayList<>(), pageable, entityPage.getTotalElements());
        }
    }

    @Override
    public List<IplAllMatchesApiDto> getMatchListByDate(String date)throws Exception {
        List<IplAllMatchesApiDto> list  = iplAllMatchesApiRepository.findByDate(date);
        if(list!=null) return list;
        throw new Exception("Ipl List by date Not Found");
    }

    @Override
    public IplAllMatchesApiDto getMatchByTime(String time) throws Exception {
        Optional<IplAllMatchesApiDto> optionalMatch=iplAllMatchesApiRepository.findByTime(time);
        return optionalMatch.get();
    }

    private String getNewGenratedDepartmentId() throws Exception{
        Optional<IplAllMatchesApi> lastMatch = iplAllMatchesApiRepository.findTopByOrderByCreatedOnDesc();
        Long serialNumber = Constants.NUMBER_CONSTANT_LONG_ONE;
        if (lastMatch.isPresent()) {
            Long lastSerialNumber = Long.valueOf(lastMatch.get().getGuid().replace(Constants.ID_PREFIX_IPL_ALLMATCHES, Constants.BLANK_CONSTANT));
            serialNumber = lastSerialNumber + 1;
            if (serialNumber > 999) {
                throw new Exception("ERR_ADMIN_0074");
            }
        }
        return CommonUtil.getCustomGeneratedId(Constants.ID_PREFIX_IPL_ALLMATCHES, Constants.ID_PART_NUMBER_COUNT_IPL_ALLMATCHES, serialNumber);
    }
}
