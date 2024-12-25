package dbServices.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionDTO {

    @JsonProperty("hash_function")
    private Long hashFunction;

    private String name;

    @JsonProperty("count_point")
    Integer countPoint;

    List<PointDTO> points;

    @JsonProperty("created_at")
    Instant createdAt;

    @JsonProperty("update_at")
    Instant updateAt;

}