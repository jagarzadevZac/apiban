package com.api.apiempleados.controller;

import java.util.List;

import com.api.apiempleados.dto.Mensaje;
import com.api.apiempleados.dto.UserDto;
import com.api.apiempleados.entity.User;
import com.api.apiempleados.service.UserSerivce;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserSerivce userService;

    

    @GetMapping("/list")
    public ResponseEntity<List<User>> list(){

        List<User> list = userService.list();
        return new ResponseEntity<List<User>>(list , HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        if(!userService.existsById(id))
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        User user = userService.getOne(id).get();
        return new ResponseEntity<User>(user,OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto ){
       
        User user = userService.getByCorreoAndPassword(userDto.getCorreo(), userDto.getPassword());
        return new ResponseEntity<User>(user,OK);
        
    }

    @GetMapping("/find/{correo}")
    public ResponseEntity<User> findUserByCorreo(@PathVariable("correo")  String correo){
        
        User user = userService.findUserByCorreo(correo);
        return new ResponseEntity<User>(user,OK);
        
    }

   

    @PostMapping("/create")
    public ResponseEntity<Mensaje> create(@RequestBody UserDto userDto){
        if(StringUtils.isBlank(userDto.getCorreo()))
            return new ResponseEntity<Mensaje>(new Mensaje("El correo es requerido"), HttpStatus.BAD_REQUEST);

        if(userService.existsByCorreo(userDto.getCorreo())){
            return new ResponseEntity<Mensaje>(new Mensaje("Este correo ya esta registrado"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(userDto.getNombre(),userDto.getCorreo(),userDto.getPassword());

        userService.save(user);

        return new ResponseEntity<>(new Mensaje("Usuario registrado con exito"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody UserDto userDto){
        if(!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no exist"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(userDto.getNombre()))
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre es rquerido"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(userDto.getCorreo()))
            return new ResponseEntity<Mensaje>(new Mensaje("El correo es requerido"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(userDto.getPassword()))
            return new ResponseEntity<Mensaje>(new Mensaje("L contraseña es requerida"), HttpStatus.BAD_REQUEST);
        

        User user =userService.getOne(id).get();
        user.setNombre(userDto.getNombre());
        user.setCorreo(userDto.getCorreo());
        user.setPassword(userDto.getPassword());
        userService.save(user);

        return new ResponseEntity<Mensaje>(new Mensaje("Usuario actualizado con exito"), HttpStatus.OK);
    }

}
