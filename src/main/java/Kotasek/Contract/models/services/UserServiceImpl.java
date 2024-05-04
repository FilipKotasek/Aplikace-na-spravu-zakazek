package Kotasek.Contract.models.services;

import Kotasek.Contract.data.entities.UserEntity;
import Kotasek.Contract.data.repositories.UserRepository;
import Kotasek.Contract.models.dto.UserDTO;
import Kotasek.Contract.models.dto.mappers.UserMapper;
import Kotasek.Contract.models.exceptions.DuplicateEmailException;
import Kotasek.Contract.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void create(UserDTO userDTO, Boolean isAdmin) {

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) throw new PasswordsDoNotEqualException();


        UserEntity user=userMapper.toUserEntity(userDTO);
        user.setAdmin(isAdmin);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new DuplicateEmailException();
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username + "nebyl nalezen"));
    }
}
