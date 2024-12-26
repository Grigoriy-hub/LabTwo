package dbServices.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "functions")
public class FunctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long functionId;
    private String name;
    private Double xFrom;
    private Double xTo;
    private Integer count;
    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PointEntity> points = new ArrayList<>();
    public Long getFunctionId() {
        return functionId;
    }
    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getXFrom() {
        return xFrom;
    }
    public void setXFrom(Double xFrom) {
        this.xFrom = xFrom;
    }
    public Double getXTo() {
        return xTo;
    }
    public void setXTo(Double xTo) {
        this.xTo = xTo;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public List<PointEntity> getPoints() {
        return points;
    }
    public void setPoints(List<PointEntity> points) {
        this.points = points;
    }
}