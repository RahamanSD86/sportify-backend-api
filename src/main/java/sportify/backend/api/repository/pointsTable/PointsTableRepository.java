package sportify.backend.api.repository.pointsTable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sportify.backend.api.domain.pointsTable.IplPointsTable;
@Repository
public interface PointsTableRepository extends MongoRepository<IplPointsTable,String> {

}
