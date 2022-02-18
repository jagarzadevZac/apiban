package com.api.apiempleados.repository;

import com.api.apiempleados.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByCorreoAndPassword(String correo,String password);
    boolean existsByCorreo(String correo);
}
