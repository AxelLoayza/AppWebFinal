package pe.edu.tecsup.prj_crud_spring_boot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Alumno;
import pe.edu.tecsup.prj_crud_spring_boot.services.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alumnos")

public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> listarAlumnos() {
        List<Alumno> alumnos = alumnoService.listar();
        return ResponseEntity.ok(alumnos);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Alumno alumno, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Errores en los campos del alumno");
        }
        alumnoService.grabar(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Alumno registrado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Alumno alumno = alumnoService.buscar(id);
        if (alumno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Alumno alumno, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Errores de validación al actualizar");
        }
        alumno.setId(id); // Importante: setear el ID
        alumnoService.grabar(alumno);
        return ResponseEntity.ok("Alumno actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        alumnoService.eliminar(id);
        return ResponseEntity.ok("Alumno eliminado");
    }
}
