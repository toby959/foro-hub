package com.toby959.foro_hub.controller;

import com.toby959.foro_hub.dto.*;
import com.toby959.foro_hub.error.UserValidationException;
import com.toby959.foro_hub.models.Topic;
import com.toby959.foro_hub.models.User;
import com.toby959.foro_hub.repository.IUserRepository;
import com.toby959.foro_hub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserRepository userRepository;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDataLogin loginData) {
        // Imprimir los datos de inicio de sesión recibidos
        System.out.println("Datos de inicio de sesión recibidos: " + loginData);

        // Verificar si el usuario existe
        User existingUser = userService.findByEmail(loginData.email())
                .orElseThrow(() -> new UserValidationException("email", "Usuario no encontrado"));

        // Cargar temas del usuario si es necesario
        List<Topic> topics = existingUser.getTopics(); // Esto puede ser lazy loading
        if (topics == null) {
            topics = new ArrayList<>(); // Asegúrate de que no sea null
        }
        System.out.println("Número de temas asociados: " + topics.size());

        // Imprimir el usuario existente (sin mostrar la contraseña)
        System.out.println("Usuario encontrado: " + existingUser.getEmail());

        // Validar la contraseña
        if (!existingUser.getPassword().equals(loginData.password())) {
            throw new UserValidationException("password", "Contraseña incorrecta");
        }

        // Crear lista de TopicDataViews a partir de los temas del usuario
        List<TopicDataView> topicDataViews = topics.stream()
                .map(topic -> new TopicDataView(topic.getId(), topic.getTitle()))
                .collect(Collectors.toList());

        // Crear el objeto UserResponseDTO con la información del usuario y sus temas
        UserResponseDTO userResponse = new UserResponseDTO(existingUser.getId(), existingUser.getEmail(), existingUser.getName(), topicDataViews);

        return ResponseEntity.ok(userResponse);
    }
//"""""""""""""""""""

    @PostMapping
    @Transactional
    public ResponseEntity<UserDataView> create(
            @RequestBody @Valid UserDataCreate dataCreate,
            UriComponentsBuilder uriComponentsBuilder) {
        var dataView = new UserDataView(userService.create(dataCreate));
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(dataView.id()).toUri();
        return ResponseEntity.created(url).body(dataView);
    }
//"""""""""""""""""""
    @GetMapping("/{id}")
    public ResponseEntity<UserDataView> read(@PathVariable() Long id) {
        var dataView = new UserDataView(userService.read(id));
        return ResponseEntity.ok(dataView);
    }
//"""""""""""""""""""

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDataView> update(
            @PathVariable Long id,
            @RequestBody @Valid UserDataUpdate dataUpdate,
            UriComponentsBuilder uriComponentsBuilder) {
        // Asegúrate de que el ID proporcionado coincida con el ID del objeto de actualización
        dataUpdate.setId(id); // Esto es opcional si ya lo tienes en el objeto

        var updatedUser = userService.update(dataUpdate); // Llama al servicio para actualizar
        var dataView = new UserDataView(updatedUser);

        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(dataView.id()).toUri();

        return ResponseEntity.ok().location(url).body(dataView);
    }
//"""""""""""""""""""

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDataView> delete(@PathVariable() Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
//"""""""""""""""""""

    @GetMapping
    public ResponseEntity<Page<UserDataView>> findAll(@PageableDefault(size = 10) Pageable pageable) {
       // var dataList = userService.findAll((pageable).map(UserDataView::new);
       // return ResponseEntity.ok(dataList);
        Page<User> users = userService.findAll(pageable); // Obtiene la página de usuarios
        Page<UserDataView> dataList = users.map(UserDataView::new); // Mapea cada User a UserDataView
        return ResponseEntity.ok(dataList); // Devuelve la respuesta con la lista mapeada
    }
}
