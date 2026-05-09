package shaddai.backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaddai.backend.dtos.RegisterDTO;
import shaddai.backend.dtos.RegisterUsuarioResponseDTO;
import shaddai.backend.security.JwtGenerador;
import shaddai.backend.services.UsuarioService;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<RegisterUsuarioResponseDTO> register(@Valid @RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(usuarioService.register(dto));
    }

}
