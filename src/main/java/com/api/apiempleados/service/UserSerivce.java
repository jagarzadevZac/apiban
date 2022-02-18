package com.api.apiempleados.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import com.api.apiempleados.entity.User;
import com.api.apiempleados.repository.UserRepository;

@Service
@Transactional
public class UserSerivce {

    @Autowired
    UserRepository userRepository;

    public List<User> list(){
        return userRepository.findAll();
    }

    public Optional<User> getOne(int id){
        return userRepository.findById(id);
    }

    public User getByCorreoAndPassword(String correo, String password){
        return userRepository.findByCorreoAndPassword(correo, password);
    }


    public void save(User user){
        userRepository.save(user);
    }


    public void delete(int id){
        userRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return userRepository.existsById(id);
    }

    public boolean existsByCorreo(String correo){
        return userRepository.existsByCorreo(correo);
    }

    public User findUserByCorreo(String correo){
        return userRepository.findByCorreo(correo);
    }
}
