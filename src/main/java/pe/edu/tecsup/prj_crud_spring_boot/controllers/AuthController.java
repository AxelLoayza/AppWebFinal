package pe.edu.tecsup.prj_crud_spring_boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.LoginRequest;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private DaoAuthenticationProvider authProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken loginToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            Authentication authentication = authProvider.authenticate(loginToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(Map.of(
                    "mensaje", "Login exitoso",
                    "usuario", request.getUsername(),
                    "roles", authentication.getAuthorities()
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "Credenciales inválidas",
                    "detalle", e.getMessage()
            ));
        }
    }
}
