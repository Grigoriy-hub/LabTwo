package dbServices.controller;

import dbServices.DTO.PointDTO;
import dbServices.DTO.buildDTO.PointDTOBuilder;
import dbServices.model.PointEntity;
import dbServices.repository.PointRepository;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "http://localhost:3000")
public class PointController {

    @Autowired
    private PointRepository pointRepository;


    @PostMapping
    public ResponseEntity<PointDTO> createOrUpdatePoint(@RequestParam(value = "x", required = false) Double X,
                                                        @RequestParam(value = "y", required = false) Double Y) {
        PointEntity pointEntity = pointRepository.saveAndFlush(new PointEntity(X, Y));

        PointDTO dto = PointDTOBuilder.makePointDto(pointEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            PointEntity entity = pointRepository.findById(id).orElseThrow(() -> new NotFoundException("Can't find point with id " + id));
            pointRepository.delete(entity);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointDTO> read(@PathVariable Long id) {
        try {
            PointDTO pointDTO = PointDTOBuilder.makePointDto(pointRepository.findById(id).orElseThrow(() -> new NotFoundException("Can't find point with id " + id)));
            return ResponseEntity.ok(pointDTO);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}