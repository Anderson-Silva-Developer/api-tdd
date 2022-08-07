package com.anderson.apitdd.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserReqDto {
    @NotBlank(message = "O Nome não pode ser vazio ou nulo!")
    private String name;
    @Email(message = "O e-mail inválido!")
    @NotBlank(message = "O e-mail não pode ser vazio ou nulo!")
    private String email;
    @NotNull(message = "A senha não pode ser vazio ou nula!")
    @Size(min = 8,max = 16,message = "A senha deve ter tamanho entre 8 e 16 caracteres!")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
