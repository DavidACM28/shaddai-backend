package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaddai.backend.dtos.UsuarioDTO;
import shaddai.backend.dtos.auth.LoginDTO;
import shaddai.backend.dtos.auth.LoginResponse;
import shaddai.backend.dtos.auth.RegisterDTO;
import shaddai.backend.dtos.auth.RegisteResponse;
import shaddai.backend.entities.UsuarioEntity;
import shaddai.backend.security.JwtGenerador;
import shaddai.backend.services.UsuarioService;

@RestController
@RequestMapping("/shaddai/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtGenerador jwtGenerador;

    @PostMapping("/register")
    public ResponseEntity<RegisteResponse> register(@Valid @RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(usuarioService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token  = jwtGenerador.generarToken(authentication);
        UsuarioEntity usuario = usuarioService.findByUsername(dto.getUsername());
        return ResponseEntity.ok().body(
                new LoginResponse(
                        token,
                        "Bearer ",
                        new UsuarioDTO(
                                usuario.getId(),
                                usuario.getUsername(),
                                usuario.getNombre(),
                                usuario.getApellido(),
                                usuario.isActivo())));
    }
}
