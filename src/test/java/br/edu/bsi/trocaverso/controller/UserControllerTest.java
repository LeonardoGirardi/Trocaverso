
package br.edu.bsi.trocaverso.controller;

import br.edu.bsi.trocaverso.dto.UserRequestDTO;
import br.edu.bsi.trocaverso.dto.UserResponseDTO;
import br.edu.bsi.trocaverso.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarUsuario() throws Exception {
        UserRequestDTO request = new UserRequestDTO();
        request.setUserName("Leo");
        request.setUserEmail("leo@email.com");
        request.setUserPassword("123");
        request.setLocationId(UUID.randomUUID());

        UserResponseDTO response = new UserResponseDTO();
        response.setId(UUID.randomUUID());
        response.setUserName("Leo");
        response.setUserEmail("leo@email.com");
        response.setLocationId(request.getLocationId());

        when(userService.create(any(UserRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("Leo"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        UUID id = UUID.randomUUID();

        UserResponseDTO response = new UserResponseDTO();
        response.setId(id);
        response.setUserName("Leo");
        response.setUserEmail("leo@email.com");
        response.setLocationId(UUID.randomUUID());

        when(userService.findDTOById(id)).thenReturn(response);

        mockMvc.perform(get("/api/users/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Leo"));
    }

    @Test
    void deveListarTodosUsuarios() throws Exception {
        UserResponseDTO user = new UserResponseDTO();
        user.setId(UUID.randomUUID());
        user.setUserName("Leo");
        user.setUserEmail("leo@email.com");
        user.setLocationId(UUID.randomUUID());

        when(userService.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("Leo"));
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        UUID id = UUID.randomUUID();

        UserRequestDTO request = new UserRequestDTO();
        request.setUserName("Atualizado");
        request.setUserEmail("novo@email.com");
        request.setUserPassword("321");
        request.setLocationId(UUID.randomUUID());

        UserResponseDTO response = new UserResponseDTO();
        response.setId(id);
        response.setUserName("Atualizado");
        response.setUserEmail("novo@email.com");
        response.setLocationId(request.getLocationId());

        when(userService.update(eq(id), any(UserRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/api/users/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Atualizado"));
    }

    @Test
    void deveExcluirUsuario() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(userService).delete(id);

        mockMvc.perform(delete("/api/users/" + id))
                .andExpect(status().isNoContent());
    }
}
