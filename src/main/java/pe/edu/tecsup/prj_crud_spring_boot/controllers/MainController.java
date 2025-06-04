package pe.edu.tecsup.prj_crud_spring_boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public String inicio(){
        return "inicio";
    }

    @GetMapping("/lista")
    public String listar(){
        return "listar";
    }
    @GetMapping("/docente")
    public String docente(){
        return "docente";
    }

    @GetMapping("/alumnos")
    public String listarAlumnos(){
        return "listarAlumnos";
    }


}
