package Kotasek.Contract.models.services;

import Kotasek.Contract.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void create (UserDTO userDTO, Boolean isAdmin);
}
