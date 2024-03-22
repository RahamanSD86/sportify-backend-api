package sportify.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sportify.backend.api.config.Constants;
import sportify.backend.api.config.RestEndPoints;
import sportify.backend.api.domain.matches.IplAllMatchesApi;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;
import sportify.backend.api.pagination.CustomPage;
import sportify.backend.api.service.matches.IplAllMatchesApiService;
import util.JavaApiClass.CommonUtil;

import java.util.List;

@RestController
@RequestMapping(RestEndPoints.IPL_API_V1)
public class IplAllMatchesController {

    @Autowired
    private IplAllMatchesApiService iplAllMatchesApiService;
    @PostMapping
    public ResponseEntity<CustomPage<IplAllMatchesApi>> createEntity()throws Exception{
        return new ResponseEntity<>(new CustomPage<>(iplAllMatchesApiService.createEntity(), Constants.BLANK_CONSTANT), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomPage<IplAllMatchesApiDto>> getAllEntity(Pageable pageable)throws Exception{
        return new ResponseEntity<>(new CustomPage<>(iplAllMatchesApiService.getAllEntities(pageable), Constants.BLANK_CONSTANT), HttpStatus.CREATED);
    }
}