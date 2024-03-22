package sportify.backend.api.repository.matches;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sportify.backend.api.domain.matches.IplAllMatchesApi;

import java.util.List;
import java.util.Optional;

@Repository
public interface IplAllMatchesApiRepository extends MongoRepository<IplAllMatchesApi,String> {
   Page<IplAllMatchesApi> findAll(Pageable pageable);
    Page<IplAllMatchesApi> findByIsActive(Boolean isActive, Pageable pageable);
    Optional<IplAllMatchesApi> findByGuid(String guid);
    Optional<IplAllMatchesApi> findTopByOrderByCreatedOnDesc();
}
