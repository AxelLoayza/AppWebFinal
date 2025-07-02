package pe.edu.tecsup.prj_crud_spring_boot.services;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Role;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Usuario;
import pe.edu.tecsup.prj_crud_spring_boot.domain.persistence.IUsuarioDao;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            logger.error("Error en el Login: no existe el usuario '{}' en el sistema!", username);
            throw new UsernameNotFoundException("Username '" + username + "' no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : usuario.getRoles()) {
            logger.info("Role: {}", role.getAuthority());
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        if (authorities.isEmpty()) {
            logger.error("Error en el Login: el usuario '{}' no tiene roles asignados!", username);
            throw new UsernameNotFoundException("Error en el Login: el usuario '" + username + "' no tiene roles asignados!");
        }

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEnabled(),
                true,  // accountNonExpired
                true,  // credentialsNonExpired
                true,  // accountNonLocked
                authorities
        );
    }
}
