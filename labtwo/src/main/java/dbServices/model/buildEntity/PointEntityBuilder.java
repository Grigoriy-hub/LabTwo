package dbServices.model.buildEntity;

import dbServices.DTO.PointDTO;
import dbServices.model.PointEntity;

public class PointEntityBuilder {
    public static PointEntity makePointEntity(PointDTO pointDto) {
        if (pointDto == null) {
            return null;
        }
        return PointEntity.builder().id(pointDto.getId())
                .x(pointDto.getX())
                .y(pointDto.getY())
                .build();
    }
}
