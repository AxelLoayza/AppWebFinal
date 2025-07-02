package pe.edu.tecsup.prj_crud_spring_boot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Curso;
import pe.edu.tecsup.prj_crud_spring_boot.services.CursoService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }


    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos para registrar curso");
        }
        cursoService.grabar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body("Curso creado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
        Curso curso = cursoService.buscar(id);
        if (curso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }
        return ResponseEntity.ok(curso);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @Valid @RequestBody Curso curso,
                                        BindingResult result) {
        curso.setId(id);
        if (result.hasErrors()) {
            Map<String,String> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            fe -> fe.getField(),
                            DefaultMessageSourceResolvable::getDefaultMessage
                    ));
            return ResponseEntity.badRequest().body(errores);
        }
        cursoService.grabar(curso);
        return ResponseEntity.ok("Curso actualizado correctamente");
    }


    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
        cursoService.eliminar(id);
        return ResponseEntity.ok("Curso eliminado correctamente");
    }
}
