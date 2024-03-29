package sportify.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sportify.backend.api.config.Constants;
import sportify.backend.api.config.RestEndPoints;
import sportify.backend.api.domain.matches.IplAllMatchesApi;
import sportify.backend.api.pagination.CustomPage;
import sportify.backend.api.service.matches.IplAllMatchesApiService;
import sportify.backend.api.service.scoreCard.IplScoreCradService;

@RestController
@RequestMapping(RestEndPoints.IPL_API_V1)
public class ScoreCardController {
    @Autowired
    private IplScoreCradService iplScoreCradService;
    @PostMapping("/create-scorecard")
    public void createEntity()throws Exception{
        iplScoreCradService.createEntity("2024-03-26T14:00:00");
    }

}
