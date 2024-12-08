package dbServices.controller;

import dbServices.DTO.FunctionDTO;
import dbServices.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @PostMapping
    public ResponseEntity<FunctionDTO> createFunction(@RequestBody FunctionDTO functionDTO) {
        FunctionDTO createdFunction = functionService.create(functionDTO);
        return  ResponseEntity.status(201).body(createdFunction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FunctionDTO> getFunctionById(@PathVariable Long id) {
        FunctionDTO functionDTO = functionService.read(id);
        if (functionDTO != null) {
            return ResponseEntity.ok(functionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FunctionDTO> updateFunction(@PathVariable Long id, @RequestBody FunctionDTO functionDTO) {
        if (functionService.read(id) == null) {
            return ResponseEntity.notFound().build();
        }
        functionDTO.setFunctionId(id);
        FunctionDTO updatedFunction = functionService.update(functionDTO);
        return ResponseEntity.ok(updatedFunction);
    }

    @DeleteMapping("/{id}")
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

