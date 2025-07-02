package pe.edu.tecsup.prj_crud_spring_boot.domain.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {


    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario findByUsername(@Param("username") String username);

}
