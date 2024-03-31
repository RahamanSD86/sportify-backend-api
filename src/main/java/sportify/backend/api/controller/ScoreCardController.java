package sportify.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sportify.backend.api.config.Constants;
import sportify.backend.api.config.RestEndPoints;
import sportify.backend.api.dto.scoreCard.IplScoreCardApiDto;
import sportify.backend.api.pagination.CustomPage;
import sportify.backend.api.service.scoreCard.IplScoreCardApiService;

@RestController
@RequestMapping(RestEndPoints.IPL_API_V1)
public class ScoreCardController {
    @Autowired
    private IplScoreCardApiService iplScoreCardApiService;
    @PostMapping("/create-scorecard/{date_time}")
    public ResponseEntity<CustomPage<IplScoreCardApiDto>> createEntity(@PathVariable(name="date_time") String date_time)throws Exception{
        return new ResponseEntity<>(new CustomPage<>(iplScoreCardApiService.createEntity(date_time), Constants.BLANK_CONSTANT), HttpStatus.CREATED);
    }

}
