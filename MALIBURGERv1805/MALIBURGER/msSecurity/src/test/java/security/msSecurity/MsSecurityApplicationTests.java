package security.msSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import security.msSecurity.Controller.AuthController;
import security.msSecurity.Model.LoginRequest;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class MsSecurityApplicationTests {

	@Autowired
	private MockMvc mockMvc; // El "Postman" automatizado de Spring

	@Autowired
	private ObjectMapper objectMapper; // Transforma nuestros objetos a JSON

	@Test
	void login_ConCredencialesCorrectas_DeberiaRetornarToken() throws Exception {
		// 1. Arrange: Preparamos los datos correctos
		LoginRequest request = new LoginRequest();
		request.setUsername("karensita");
		request.setPassword("maliburger123");

		// 2. Act & Assert: Disparamos la petición y verificamos el éxito
		mockMvc.perform(post("/api/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()) // Esperamos un 200 OK
				.andExpect(jsonPath("$.token").exists()) // Verificamos que el JSON tenga un atributo "token"
				.andExpect(jsonPath("$._links").exists()); // Verificamos que HATEOAS mande los links
	}

	@Test
	void login_ConCredencialesIncorrectas_DeberiaRetornarNoAutorizado() throws Exception {
		// 1. Arrange: Preparamos datos falsos
		LoginRequest request = new LoginRequest();
		request.setUsername("hacker");
		request.setPassword("claveFalsa");

		// 2. Act & Assert: Disparamos la petición y verificamos el bloqueo
		mockMvc.perform(post("/api/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isUnauthorized()); // Esperamos un error 401
	}
}

