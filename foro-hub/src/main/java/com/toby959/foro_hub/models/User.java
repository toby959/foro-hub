package com.toby959.foro_hub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "ui_user_name", columnNames = {"name"}),
        @UniqueConstraint(name = "ui_user_email", columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 250)
    @Email(message = "Correo electrónico no válido.")
    @Pattern(regexp = ".+@.+\\..+", message = "El correo electrónico debe contener un dominio válido.")//"usuario@gmail.com, el patrón \\..+"
    private String email;

    @Column(nullable = false, length = 250)
    private String password;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Topic> topics = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
