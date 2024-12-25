package dbServices.DTO.buildDTO;

import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;

public class FunctionDTOBuilder {

    static public FunctionDTO makeMathFunctionDto(FunctionEntity entity) {
        if (entity == null)
            return null;
        return FunctionDTO.builder()
                .hashFunction(entity.getHash())
                .name(entity.getName())
                .countPoint(entity.getPoints().size())
                .points(entity.getPoints()
                        .stream()
                        .map(PointDTOBuilder::makePointDto)
                        .toList())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }

}