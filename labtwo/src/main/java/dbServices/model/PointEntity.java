package dbServices.model;

import jakarta.persistence.*;
@Entity
@Table(name = "function_points")
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @ManyToOne
    @JoinColumn(name = "function_id")
    private FunctionEntity function;
    private Double x;
    private Double y;
    public Long getPointId() {
        return pointId;
    }
    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }
    public FunctionEntity getFunction() {
        return function;
    }
    public void setFunction(FunctionEntity function) {
        this.function = function;
    }
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
}
