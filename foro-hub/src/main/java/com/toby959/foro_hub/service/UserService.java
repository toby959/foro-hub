package com.toby959.foro_hub.service;

import com.toby959.foro_hub.dto.UserDataCreate;
import com.toby959.foro_hub.dto.UserDataUpdate;
import com.toby959.foro_hub.error.UserValidationException;
import com.toby959.foro_hub.models.User;
import com.toby959.foro_hub.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public User create(UserDataCreate data) {
        if(userRepository.existsByName(data.name())) {
            throw new UserValidationException("nombre","Ya existe un usuario con el mismo nombre");
        }

        if(userRepository.existsByEmail(data.email())) {
            throw new UserValidationException("email","Ya existe un usuario con el mismo email");
        }

        var user = new User(data.name(), data.email(), data.password());

        userRepository.save(user);

        return user;
    }
//""""""""""""""""

    public User read(Long id){
        if(!userRepository.existsById(id)){
            throw new UserValidationException("id","No existe un usuario con el identificador " + id);
        }
        return userRepository.getReferenceById(id);
    }
//""""""""""""""""

    public User update(UserDataUpdate dataUpdate) {
        // Verificar si el usuario existe
        User existingUser = userRepository.findById(dataUpdate.getId())
                .orElseThrow(() -> new UserValidationException("id", "Usuario no encontrado"));
        // Actualizar los campos del usuario
        existingUser.setName(dataUpdate.getName());
        existingUser.setEmail(dataUpdate.getEmail());

        // Si la contraseña se proporciona, se puede optar por actualizarla
        if (dataUpdate.getPassword() != null && !dataUpdate.getPassword().isEmpty()) {
            existingUser.setPassword(dataUpdate.getPassword()); // Considera encriptar la contraseña aquí
        }

        // Guardar los cambios en el repositorio
        return userRepository.save(existingUser);
    }
//""""""""""""""""

    public void delete(Long id) {
        var result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new UserValidationException("id", "No existe un usuario con el identificador " + id);
        } else if (!result.get().getTopics().isEmpty()) {
            throw new UserValidationException("id", "No es posible eliminar un usuario con tópicos");
        }
        userRepository.deleteById(id);
    }
//""""""""""""""""

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findByOrderByName(pageable);
    }
//""""""""""""""""""""

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
