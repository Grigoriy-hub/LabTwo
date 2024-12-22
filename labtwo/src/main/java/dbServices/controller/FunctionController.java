package dbServices.controller;

import dbServices.DTO.FunctionDTO;
import dbServices.model.FunctionEntity;
import dbServices.repository.FunctionRepository;
import dbServices.service.FunctionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class FunctionController {

    @Autowired
    private FunctionService functionService;
    private FunctionRepository functionRepository;

    @PostMapping("/api/functions")
    public ResponseEntity<FunctionDTO> createOrUpdateFunction(@RequestBody FunctionDTO functionDTO) {
        FunctionEntity entityForFind = new FunctionEntity();
        entityForFind.setFunctionId(functionDTO.getFunctionId());
        entityForFind.setName(functionDTO.getName());
        entityForFind.setXFrom(functionDTO.getXFrom());
        entityForFind.setXTo(functionDTO.getXTo());
        entityForFind.setCount(functionDTO.getCount());

        Optional<FunctionEntity> entityFind = functionRepository.findById(entityForFind.getFunctionId());

        FunctionDTO dto = new FunctionDTO();
        dto.setFunctionId(entityFind.get().getFunctionId());
        dto.setName(entityFind.get().getName());
        dto.setXFrom(entityFind.get().getXFrom());
        dto.setXTo(entityFind.get().getXTo());
        dto.setCount(entityFind.get().getCount());

        return  ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/api/functions/{id}")
    public ResponseEntity<FunctionDTO> getFunctionById(@PathVariable Long id) {
        FunctionDTO functionDTO = functionService.read(id);
        if (functionDTO != null) {
            return ResponseEntity.ok(functionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @DeleteMapping("/api/functions/{id}")
    public ResponseEntity<Void> deleteFunction(@PathVariable Long id) {
        if (functionService.read(id) == null) {
            return ResponseEntity.notFound().build();
        }
        functionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FunctionDTO>> getFunctionsByName(@RequestParam String name) {
        List<FunctionDTO> functions = functionService.getByName(name);
        return ResponseEntity.ok(functions);
    }
}

