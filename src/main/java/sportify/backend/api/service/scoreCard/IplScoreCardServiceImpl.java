package sportify.backend.api.service.scoreCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sportify.backend.api.config.Constants;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;
import sportify.backend.api.service.CricketDataService;
import sportify.backend.api.service.matches.IplAllMatchesApiService;
import util.JavaApiClass.iplscorecard.IplScoreCard;

@Service
public class IplScoreCardServiceImpl implements IplScoreCradService{
    @Autowired
    CricketDataService cricketDataService;
    @Autowired
    IplAllMatchesApiService iplAllMatchesApiService;

    @Override
    public void createEntity(String time) throws Exception {
     IplAllMatchesApiDto match=iplAllMatchesApiService.getMatchByTime(time);
      IplScoreCard iplScoreCard=cricketDataService.fetchDataFromApi(IplScoreCard.class, match.getMatchId(), Constants.IPL_SCORE_CARD);
    }

//    @Override
//    public void createEntityFirstMatch() throws Exception {
//        List<IplAllMatchesApiDto> list= iplAllMatchesApiService.getMatchListByDate("2024-03-26");
//        String firstMatchId=getMatchId(list,"first");
//        if(firstMatchId!=null){
//            ScoreCard scoreCard=cricketDataService.fetchDataFromApi(ScoreCard.class,firstMatchId);
//        }
//    }
//
//    @Override
//    public void createEntitySecondMatch() throws Exception {
//        List<IplAllMatchesApiDto> list= iplAllMatchesApiService.getMatchListByDate("2024-03-26");
//        String secondMatchId=getMatchId(list,"second");
//       if(secondMatchId!=null){
//           ScoreCard scoreCard=cricketDataService.fetchDataFromApi(ScoreCard.class,secondMatchId);
//       }
//    }
//
//    public String getMatchId( List<IplAllMatchesApiDto> list,String matchNumber){
//        String FirstMatchOfDay="10:00:00";
////        String SecondMatchOfDay="14:00:00";
//
//        String firstMatchId="";
//        String seconfMatchId="";
//
//        for(IplAllMatchesApiDto match:list){
//            String time=match.getTime();
//            LocalDateTime dateTime = LocalDateTime.parse(time);
//
//            // Format to display only the time
//            String timeOnly = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//            if(timeOnly.equals(FirstMatchOfDay)){
//                firstMatchId=match.getMatchId();
//            }else{
//                seconfMatchId=match.getMatchId();
//            }
//        }
//        if(matchNumber.equals("first")) return firstMatchId;
//        else if (matchNumber.equals("second")) return seconfMatchId;
//          return null;
//    }
}
