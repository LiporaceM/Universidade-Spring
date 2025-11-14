package br.edu.ibmec.Controller;

import br.edu.ibmec.entity.Professor;
import br.edu.ibmec.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professores")
@Tag(name = "Professores", description = "API for managing professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    @Operation(summary = "Create a new professor")
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        return new ResponseEntity<>(professorService.create(professor), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all professors")
    public ResponseEntity<List<Professor>> findAll() {
        return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a professor by ID")
    public ResponseEntity<Professor> findById(@PathVariable long id) {
        Optional<Professor> optionalProfessor = professorService.findById(id);
        return optionalProfessor.map(professor -> new ResponseEntity<>(professor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a professor")
    public ResponseEntity<Professor> update(@PathVariable long id, @RequestBody Professor professorDetails) {
        Professor updatedProfessor = professorService.update(id, professorDetails);
        if (updatedProfessor != null) {
            return new ResponseEntity<>(updatedProfessor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a professor")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
