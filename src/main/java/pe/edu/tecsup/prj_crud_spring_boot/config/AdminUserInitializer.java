package pe.edu.tecsup.prj_crud_spring_boot.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Role;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Usuario;
import pe.edu.tecsup.prj_crud_spring_boot.domain.persistence.IUsuarioDao;

import java.util.Collections;

@Component
public class AdminUserInitializer {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (usuarioDao.findByUsername("admin") == null) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);

            Role role = new Role();
            role.setAuthority("ROLE_ADMIN");
            role.setUsuario(admin);

            admin.setRoles(Collections.singletonList(role));
            usuarioDao.save(admin);
        }
    }
}