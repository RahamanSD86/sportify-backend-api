package sportify.backend.api.service.scoreCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sportify.backend.api.config.Constants;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;
import sportify.backend.api.dto.scoreCard.IplScoreCardApiDto;
import sportify.backend.api.mapper.scoreCard.IplScoreCardApiMapper;
import sportify.backend.api.repository.scoreCard.IplScoreCardApiRepository;
import sportify.backend.api.service.CricketDataService;
import sportify.backend.api.service.matches.IplAllMatchesApiService;
import util.JavaApiClass.iplscorecard.IplScoreCard;
import util.JavaApiClass.iplscorecard.ScoreCard;

import java.util.List;
import java.util.Optional;

@Service
public class IplScoreCardApiServiceImpl implements IplScoreCardApiService {
    @Autowired
    CricketDataService cricketDataService;
    @Autowired
    IplAllMatchesApiService iplAllMatchesApiService;
    @Autowired
    IplScoreCardApiRepository iplScoreCardApiRepository;
    @Override
    public IplScoreCardApiDto createEntity(String time) throws Exception {
     IplAllMatchesApiDto match=iplAllMatchesApiService.getMatchByTime(time);
     IplScoreCard iplScoreCard=cricketDataService.fetchDataFromApi(IplScoreCard.class, match.getMatchId(), Constants.IPL_SCORE_CARD);

     IplScoreCardApiDto iplScoreCardApiDto=new IplScoreCardApiDto();
     Optional<IplScoreCardApiDto> iplScoreCardApiDtoOptional =iplScoreCardApiRepository.findByMatchId(iplScoreCard.getMatch().getId());

     //Checking Existing ScoreCard
     if(iplScoreCardApiDtoOptional.isEmpty()){

         iplScoreCardApiDto.setName(iplScoreCard.getMatch().getName());
         iplScoreCardApiDto.setVenue(iplScoreCard.getMatch().getVenue());
         iplScoreCardApiDto.setStatus(iplScoreCard.getStatus());
         iplScoreCardApiDto.setDateTimeGMT(iplScoreCard.getMatch().getDateTimeGMT());
         iplScoreCardApiDto.setTeamInfo(iplScoreCard.getMatch().getTeamInfo());
         iplScoreCardApiDto.setScoreList(iplScoreCard.getMatch().getScore());
         iplScoreCardApiDto.setTossWinner(iplScoreCard.getMatch().getTossWinner());
         iplScoreCardApiDto.setTossChoice(iplScoreCard.getMatch().getTossChoice());
         iplScoreCardApiDto.setSeriesId(iplScoreCard.getMatch().getSeries_id());
         iplScoreCardApiDto.setMatchId(iplScoreCard.getMatch().getId());

         List<ScoreCard> scorecardList =iplScoreCard.getMatch().getScorecard();
         if(scorecardList!=null) {
             //Fetching details from scoreCardList
             for (ScoreCard scoreCard : scorecardList) {
                 iplScoreCardApiDto.setBatting(scoreCard.getBatting());
                 iplScoreCardApiDto.setBowling(scoreCard.getBowling());
                 iplScoreCardApiDto.setCatching(scoreCard.getCatching());
                 iplScoreCardApiDto.setExtras(scoreCard.getExtras());
                 iplScoreCardApiDto.setTotals(scoreCard.getTotals());
                 iplScoreCardApiDto.setInning(scoreCard.getInning());
             }
         }
         return IplScoreCardApiMapper.toDTO(iplScoreCardApiRepository.save(IplScoreCardApiMapper.toEntity(iplScoreCardApiDto)));
     }
     throw new Exception("Already Created");
    }

}
