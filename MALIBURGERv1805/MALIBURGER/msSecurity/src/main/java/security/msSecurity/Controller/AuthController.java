package security.msSecurity.Controller;

import security.msSecurity.Seguro.JwtUtils;
import security.msSecurity.Model.LoginRequest;
import security.msSecurity.Model.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Herramientas HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if ("karensita".equals(request.getUsername()) && "maliburger123".equals(request.getPassword())) {

            String tokenStr = jwtUtils.generarToken(request.getUsername());
            TokenResponse tokenResponse = new TokenResponse(tokenStr);

            EntityModel<TokenResponse> modelo = EntityModel.of(tokenResponse);

            // Link a sí mismo
            modelo.add(linkTo(methodOn(AuthController.class).login(request)).withSelfRel());

            // Enlaces manuales hacia el API Gateway (Asumiendo que tu gateway corre en el 8080)
            modelo.add(Link.of("http://localhost:8080/api/inventario").withRel("ir-a-inventario"));
            modelo.add(Link.of("http://localhost:8080/api/venta/historial/1").withRel("ver-mis-compras"));

            return ResponseEntity.ok(modelo);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}