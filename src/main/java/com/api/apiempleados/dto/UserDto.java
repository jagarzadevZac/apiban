package com.api.apiempleados.dto;

import org.springframework.lang.NonNull;

public class UserDto {
    
    @NonNull
    private String nombre;
    @NonNull
    private String correo;
    @NonNull
    private String password;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
