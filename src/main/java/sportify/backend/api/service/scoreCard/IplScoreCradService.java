package sportify.backend.api.service.scoreCard;

import org.springframework.stereotype.Service;

@Service
public interface IplScoreCradService {
  void createEntity(String time)throws Exception;
}
