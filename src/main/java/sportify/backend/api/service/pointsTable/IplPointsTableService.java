package sportify.backend.api.service.pointsTable;

import sportify.backend.api.dto.pointsTable.IplPointsTableDto;

public interface IplPointsTableService {
    IplPointsTableDto createEntity(String teamName) throws Exception;
}
