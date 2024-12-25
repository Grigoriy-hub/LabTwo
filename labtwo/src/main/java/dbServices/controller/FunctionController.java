package dbServices.controller;


import dbServices.DTO.FunctionDTO;
import dbServices.DTO.buildDTO.FunctionDTOBuilder;
import dbServices.model.FunctionEntity;
import dbServices.model.buildEntity.FunctionEntityBuilder;
import dbServices.repository.FunctionRepository;
import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@RestController
@RequestMapping("/api/functions")
@CrossOrigin(origins = "http://localhost:3000")
public class FunctionController {
    @Autowired
    FunctionRepository functionRepository;

    @PostMapping
    public ResponseEntity<FunctionDTO> createOrUpdateMathFunction(@RequestBody FunctionDTO mathFunctionDto) {
        FunctionEntity entityForFind = FunctionEntityBuilder.makeMathFunctionEntity((mathFunctionDto));
        Optional<FunctionEntity> entityFind = functionRepository.findByHash(entityForFind.getHash());
        if (entityFind.isPresent()) {
            entityForFind.setUpdateAt(Instant.now());
            entityForFind.setCreatedAt(entityFind.get().getCreatedAt());
        }
        FunctionEntity functionEntity = functionRepository.save(entityForFind);
        FunctionDTO dto = FunctionDTOBuilder.makeMathFunctionDto(functionEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<Void> delete(@PathVariable Long hash) {
        try {
            FunctionEntity entity = functionRepository.findByHash(hash).orElseThrow(() -> new NotFoundException("Can't find function with hash " + hash));
            functionRepository.delete(entity);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{hash}")
    public ResponseEntity<FunctionDTO> read(@PathVariable Long hash) {
        try {
            FunctionDTO functionDTO = FunctionDTOBuilder.makeMathFunctionDto(functionRepository.findByHash(hash).orElseThrow(() -> new NotFoundException("Can't find function with hash " + hash)));
            return new ResponseEntity<>(functionDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}