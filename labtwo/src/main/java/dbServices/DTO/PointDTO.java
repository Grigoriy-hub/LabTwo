package dbServices.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PointDTO {
    private Long pointId;
    private Long functionId;
    private Double x;
    private Double y;

    public Long getPointId() {
        return pointId;
    }
    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }
    public Long getFunctionId() {
        return functionId;
    }
    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
    @JsonProperty("x")
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    @JsonProperty("y")
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
}
