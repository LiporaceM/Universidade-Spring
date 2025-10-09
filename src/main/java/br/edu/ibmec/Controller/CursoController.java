package br.edu.ibmec.Controller;

import br.edu.ibmec.entity.Curso;
import br.edu.ibmec.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "API for managing courses")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        return new ResponseEntity<>(cursoService.create(curso), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all courses")
    public ResponseEntity<List<Curso>> findAll() {
        return new ResponseEntity<>(cursoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a course by ID")
    public ResponseEntity<Curso> findById(@PathVariable long id) {
        Optional<Curso> optionalCurso = cursoService.findById(id);
        return optionalCurso.map(curso -> new ResponseEntity<>(curso, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a course")
    public ResponseEntity<Curso> update(@PathVariable long id, @RequestBody Curso cursoDetails) {
        Curso updatedCurso = cursoService.update(id, cursoDetails);
        if (updatedCurso != null) {
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
