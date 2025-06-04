package pe.edu.tecsup.prj_crud_spring_boot.services;

import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Alumno;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Curso;

import java.util.List;

public interface AlumnoService {
    public void grabar(Alumno alumno);
    public void eliminar(int id);
    public Alumno buscar(Integer id);
    public List<Alumno> listar();
}

