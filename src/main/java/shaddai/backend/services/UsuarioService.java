package shaddai.backend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shaddai.backend.dtos.auth.RegisterDTO;
import shaddai.backend.dtos.auth.RegisteResponse;
import shaddai.backend.entities.UsuarioEntity;
import shaddai.backend.exceptions.UserNameAlreadyExistsException;
import shaddai.backend.exceptions.UserNotFoundException;
import shaddai.backend.repositories.UsuarioRepository;
import shaddai.backend.utils.Role;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity findByUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Transactional
    public RegisteResponse register(RegisterDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new UserNameAlreadyExistsException(dto.getUsername());
        }
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setRol(Role.TRABAJADOR);
        usuario.setActivo(true);
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(encoder.encode(dto.getPassword()));
        usuario = usuarioRepository.save(usuario);

        return new RegisteResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.isActivo());
    }

}
