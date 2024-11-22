package com.toby959.foro_hub.repository;

import com.toby959.foro_hub.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

     boolean existsByName(String name);


      boolean existsByEmail(String email);

      Optional<User> findByEmail(String email);


     Page<User> findByOrderByName(Pageable pageable);
}
