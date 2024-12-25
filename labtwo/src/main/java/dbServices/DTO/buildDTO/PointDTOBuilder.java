package dbServices.DTO.buildDTO;

import dbServices.DTO.PointDTO;
import dbServices.model.PointEntity;

public class PointDTOBuilder {

    public static PointDTO makePointDto(PointEntity pointEntity) {
        if (pointEntity == null) {
            return null;
        }
        return PointDTO.builder().id(pointEntity.getId())
                .x(pointEntity.getX())
                .y(pointEntity.getY())
                .build();
    }

}
