package pe.edu.tecsup.prj_crud_spring_boot.domain.persistence;

import org.springframework.data.repository.CrudRepository;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Auditoria;

public interface AuditoriaDao extends CrudRepository<Auditoria, Integer> {
}
