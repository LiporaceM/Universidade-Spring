package br.edu.ibmec.Controller;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos", description = "API for managing students")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        return new ResponseEntity<>(alunoService.create(aluno), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<List<Aluno>> findAll() {
        return new ResponseEntity<>(alunoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a student by ID")
    public ResponseEntity<Aluno> findById(@PathVariable long id) {
        Optional<Aluno> optionalAluno = alunoService.findById(id);
        return optionalAluno.map(aluno -> new ResponseEntity<>(aluno, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a student")
    public ResponseEntity<Aluno> update(@PathVariable long id, @RequestBody Aluno alunoDetails) {
        Aluno updatedAluno = alunoService.update(id, alunoDetails);
        if (updatedAluno != null) {
            return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a student")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
