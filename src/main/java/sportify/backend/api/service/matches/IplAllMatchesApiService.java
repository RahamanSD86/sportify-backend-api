package sportify.backend.api.service.matches;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sportify.backend.api.domain.matches.IplAllMatchesApi;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;

import java.util.List;


@Service
public interface IplAllMatchesApiService {
 List<IplAllMatchesApi> createEntity() throws Exception;
 Page<IplAllMatchesApiDto> getAllEntities(Pageable pageable)throws  Exception;

 List<IplAllMatchesApiDto> getMatchListByDate(String date)throws Exception;
 IplAllMatchesApiDto getMatchByTime(String time)throws Exception;
}
