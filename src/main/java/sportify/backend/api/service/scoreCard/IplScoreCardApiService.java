package sportify.backend.api.service.scoreCard;

import org.springframework.stereotype.Service;
import sportify.backend.api.dto.scoreCard.IplScoreCardApiDto;

@Service
public interface IplScoreCardApiService {
  IplScoreCardApiDto createEntity(String time)throws Exception;
}
