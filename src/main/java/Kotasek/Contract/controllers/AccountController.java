package Kotasek.Contract.controllers;

import Kotasek.Contract.models.dto.UserDTO;
import Kotasek.Contract.models.exceptions.DuplicateEmailException;
import Kotasek.Contract.models.exceptions.PasswordsDoNotEqualException;
import Kotasek.Contract.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;
    @GetMapping("login")
    public String renderLogin() {


        return "pages/login";
    }

    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO) {

        return "pages/register";
    }

    @PostMapping("register")
    public String registerUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return renderRegister(userDTO);
        }
        try {
            userService.create(userDTO, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email","error","Email je již zaregistrován");
            return "pages/register";

        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password","error","Hesla se neshodují");
            result.rejectValue("confirmPassword","error","Hesla se neshodují");
            return "pages/register";
        }

        redirectAttributes.addFlashAttribute("success", "Registrace byla úspěšná");
        return "redirect:/account/login";

    }
}
