package Kotasek.Contract.models.dto.mappers;

import Kotasek.Contract.data.entities.UserEntity;
import Kotasek.Contract.models.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

   UserDTO toUserDTO(UserEntity userEntity);
   UserEntity toUserEntity(UserDTO userDTO);

}
