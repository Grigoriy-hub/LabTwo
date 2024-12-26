package dbServices.controller;
import dbServices.DTO.PointDTO;
import dbServices.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/points")
public class PointController {
    @Autowired
    private PointService pointService;
    @PostMapping
    public ResponseEntity<PointDTO> createPoint(@RequestBody PointDTO pointDTO) {
        PointDTO createdPoint = pointService.create(pointDTO);
        return ResponseEntity.status(201).body(createdPoint);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PointDTO> getPoint(@PathVariable Long id) {
        PointDTO point = pointService.read(id);
        if (point == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(point);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PointDTO> updatePoint(@PathVariable Long id, @RequestBody PointDTO pointDTO) {
        if (pointService.read(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pointDTO.setPointId(id);
        PointDTO updatedPoint = pointService.update(pointDTO);
        return ResponseEntity.ok(updatedPoint);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoint(@PathVariable Long id) {
        if (pointService.read(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pointService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/by-function/{functionId}")
    public ResponseEntity<List<PointDTO>> getPointsByFunction(@PathVariable Long functionId) {
        List<PointDTO> points = pointService.getByFunction(functionId);
        if (points == null || points.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(points);
    }
}