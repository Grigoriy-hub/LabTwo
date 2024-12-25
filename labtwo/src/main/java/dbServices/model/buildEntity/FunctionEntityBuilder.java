package dbServices.model.buildEntity;

import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;

import java.time.Instant;

public class FunctionEntityBuilder {
    static public FunctionEntity makeMathFunctionEntity(FunctionDTO dto) {
        return FunctionEntity.builder().name(dto.getName())
                .hash(dto.getHashFunction())
                .points(dto.getPoints()
                        .stream()
                        .map(PointEntityBuilder::makePointEntity)
                        .toList())
                .createdAt(Instant.now())
                .updateAt(Instant.now())
                .build();
    }
}
