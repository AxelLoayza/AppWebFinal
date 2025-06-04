package pe.edu.tecsup.prj_crud_spring_boot.controllers;

import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Alumno;
import pe.edu.tecsup.prj_crud_spring_boot.services.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @RequestMapping(value = "/listarAlumnos", method = RequestMethod.GET)
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoService.listar());
        return "listarAlumnos";
    }

    @RequestMapping(value = "/formAlumno")
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        return "formAlumno";
    }

    @RequestMapping(value = "/formAlumno", method = RequestMethod.POST)
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            return "formAlumno";
        }
        alumnoService.grabar(alumno);
        status.setComplete();
        return "redirect:/listarAlumnos";
    }

    @RequestMapping(value = "/formAlumno/{id}")
    public String editar(@PathVariable("id") Integer id, Map<String, Object> model) {
        Alumno alumno = null;

        if (id > 0) {
            alumno = alumnoService.buscar(id);
        } else {
            return "redirect:/listarAlumnos";
        }
        model.put("alumno", alumno);
        return "formAlumno";
    }

    @RequestMapping(value = "/eliminarAlumno/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            alumnoService.eliminar(id);
        }
        return "redirect:/listarAlumnos";
    }

    @RequestMapping(value = "/verAlumnos")
    public String ver(Model model) {
        model.addAttribute("alumnos", alumnoService.listar());
        model.addAttribute("titulo", "Lista de Alumnos");
        return "alumno/verAlumnos";
    }
}