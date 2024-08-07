package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteControlador {
    private final ClienteServicio clienteServicio;

    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@RequestBody @Valid ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Cliente Actualizado correctamente"));
    }
    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo) throws Exception {
        clienteServicio.eliminarCliente(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Cliente eliminado correctamente"));
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String codigo) throws Exception {
       return ResponseEntity.ok().body(new MensajeDTO<>(false,clienteServicio.obtenerCliente(codigo)));
    }
    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemClienteDTO>>>  listarClientes() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false,clienteServicio.listarClientes()));
    }
}
