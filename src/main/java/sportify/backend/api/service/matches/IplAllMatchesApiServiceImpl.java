package sportify.backend.api.service.matches;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sportify.backend.api.config.Constants;
import sportify.backend.api.domain.matches.IplAllMatchesApi;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;
import sportify.backend.api.mapper.matches.IplAllMatchesApiMapper;
import sportify.backend.api.repository.matches.IplAllMatchesApiRepository;
import sportify.backend.api.service.CricketDataService;
import util.JavaApiClass.CommonUtil;
import util.JavaApiClass.iplAllMatches.IplAllMatches;
import util.JavaApiClass.iplAllMatches.Match;

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
        IplAllMatches iplAllMatches = cricketDataService.fetchDataFromApi();
        List<Match> matchList = iplAllMatches.getData().getMatchList();


            //Incrementally Generating ID & Retry If Got DuplicateKeyException Due To ID
            while (true) {
                try {

                    for (Match match : matchList) {
                        IplAllMatchesApiDto iplAllMatchesApiDto = new IplAllMatchesApiDto();
                        //generating guid
                        iplAllMatchesApiDto.setGuid(getNewGenratedDepartmentId());

                        iplAllMatchesApiDto.setVenue(match.getVenue());
                        iplAllMatchesApiDto.setDate(match.getDate());
                        iplAllMatchesApiDto.setTime(match.getDateTimeGMT());
                        iplAllMatchesApiDto.setTeamsName(match.getTeams());

                        iplAllMatchesApiRepository.save(IplAllMatchesApiMapper.toEntity(iplAllMatchesApiDto));
                    }
                  return iplAllMatchesApiRepository.findAll();
                } catch (Exception ex) {
                    if (ex instanceof DuplicateKeyException) {
                        continue;
                    }
                    throw new Exception("ERR_ADMIN_0061");
                }
            }
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
