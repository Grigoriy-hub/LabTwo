package dbServices.controller;

import dbServices.DTO.PointDTO;
import dbServices.model.PointEntity;
import dbServices.repository.PointRepository;
import dbServices.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PointController {

    @Autowired
    private PointService pointService;
    private PointRepository pointRepository;


    @PostMapping("/api/points")
    public ResponseEntity<PointDTO> createPointOrUpdate(@RequestParam(value = "x", required = false) Double X,
                                                        @RequestParam(value = "y", required = false) Double Y) {

        PointEntity pEntity = new PointEntity();
        pEntity.setY(X);
        pEntity.setY(Y);
        PointEntity pointEntity = pointRepository.save(pEntity);


        PointDTO dto = new PointDTO();
        dto.setPointId(pointEntity.getPointId());
        dto.setFunctionId(pointEntity.getFunction().getFunctionId());
        dto.setX(pointEntity.getX());
        dto.setY(pointEntity.getY());

        return ResponseEntity.status(201).body(dto);
    }


    @GetMapping("/api/points/{id}")
    public ResponseEntity<PointDTO> getPoint(@PathVariable Long id) {
        PointDTO point = pointService.read(id);
        if (point == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(point);
    }



    @DeleteMapping("/api/points/{id}")
    public ResponseEntity<Void> deletePoint(@PathVariable Long id) {
        if (pointService.read(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pointService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

