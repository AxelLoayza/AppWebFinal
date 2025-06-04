package pe.edu.tecsup.prj_crud_spring_boot.domain.persistence;

import org.springframework.data.repository.CrudRepository;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Curso;

public interface CursoDao extends CrudRepository<Curso,Integer> {
}
