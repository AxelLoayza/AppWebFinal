package pe.edu.tecsup.prj_crud_spring_boot.domain.persistence;

import org.springframework.data.repository.CrudRepository;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Alumno;

public interface AlumnoDao extends CrudRepository<Alumno,Integer> {
}
