package sportify.backend.api.repository.matches;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sportify.backend.api.domain.matches.IplAllMatchesApi;
import sportify.backend.api.dto.matches.IplAllMatchesApiDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface IplAllMatchesApiRepository extends MongoRepository<IplAllMatchesApi,String> {
   Page<IplAllMatchesApi> findAll(Pageable pageable);
    Page<IplAllMatchesApi> findByIsActive(Boolean isActive, Pageable pageable);
    Optional<IplAllMatchesApi> findByGuid(String guid);
    Optional<IplAllMatchesApi> findTopByOrderByCreatedOnDesc();
    List<IplAllMatchesApiDto> findByDate(String date);
    Optional<IplAllMatchesApiDto> findByTime(String time);
    Optional<IplAllMatchesApiDto> findByMatchId(String id);
}
