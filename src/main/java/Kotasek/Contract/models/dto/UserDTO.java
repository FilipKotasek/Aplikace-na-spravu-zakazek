package Kotasek.Contract.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

   @Email(message = "Vyplňtě email správně!")
   @NotBlank(message = "Vyplňte email")
    String email;
   @NotBlank(message = "Vyplťe heslo")
   @Size(min = 6,message = "Heslo musí mít aspoň 6 znaků.")
   String password;
   @NotBlank(message = "Vypňte heslo")
   @Size(min = 6,message = "Heslo musí mít alespoň 6 znaků.")
   String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
