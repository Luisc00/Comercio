package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.RegistroClienteDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {
    private final AutenticacionServicio autenticacionServicio;
    private final ClienteServicio clienteServicio;

    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@RequestBody @Valid RegistroClienteDTO registroClienteDTO) throws Exception {
        clienteServicio.registrarCliente(registroClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Cliente registrado correctamente"));
    }

    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> loginCliente(@RequestBody @Valid LoginDTO loginDTO)throws Exception{
        TokenDTO tokenDTO=autenticacionServicio.iniciarSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,tokenDTO));
    }
    @PostMapping("/login-moderador")
    public ResponseEntity<MensajeDTO<TokenDTO>> loginModerador(@RequestBody @Valid LoginDTO loginDTO)throws Exception{
        TokenDTO tokenDTO=autenticacionServicio.iniciarSesionModerador(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,tokenDTO));
    }
}
