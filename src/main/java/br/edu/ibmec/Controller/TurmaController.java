package br.edu.ibmec.Controller;

import br.edu.ibmec.entity.Turma;
import br.edu.ibmec.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turmas")
@Tag(name = "Turmas", description = "API for managing classes")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    @Operation(summary = "Create a new class")
    public ResponseEntity<Turma> create(@RequestBody Turma turma) {
        return new ResponseEntity<>(turmaService.create(turma), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all classes")
    public ResponseEntity<List<Turma>> findAll() {
        return new ResponseEntity<>(turmaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a class by ID")
    public ResponseEntity<Turma> findById(@PathVariable long id) {
        Optional<Turma> optionalTurma = turmaService.findById(id);
        return optionalTurma.map(turma -> new ResponseEntity<>(turma, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a class")
    public ResponseEntity<Turma> update(@PathVariable long id, @RequestBody Turma turmaDetails) {
        Turma updatedTurma = turmaService.update(id, turmaDetails);
        if (updatedTurma != null) {
            return new ResponseEntity<>(updatedTurma, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a class")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        turmaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
