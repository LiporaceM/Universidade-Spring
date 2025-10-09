package br.edu.ibmec.Controller;

import br.edu.ibmec.entity.Disciplina;
import br.edu.ibmec.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disciplinas")
@Tag(name = "Disciplinas", description = "API for managing disciplines")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    @Operation(summary = "Create a new discipline")
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        return new ResponseEntity<>(disciplinaService.create(disciplina), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all disciplines")
    public ResponseEntity<List<Disciplina>> findAll() {
        return new ResponseEntity<>(disciplinaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a discipline by ID")
    public ResponseEntity<Disciplina> findById(@PathVariable long id) {
        Optional<Disciplina> optionalDisciplina = disciplinaService.findById(id);
        return optionalDisciplina.map(disciplina -> new ResponseEntity<>(disciplina, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a discipline")
    public ResponseEntity<Disciplina> update(@PathVariable long id, @RequestBody Disciplina disciplinaDetails) {
        Disciplina updatedDisciplina = disciplinaService.update(id, disciplinaDetails);
        if (updatedDisciplina != null) {
            return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a discipline")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        disciplinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
