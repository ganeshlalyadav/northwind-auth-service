package User.northwind.service;

import User.northwind.dao.UserDto;
import User.northwind.entity.User;
import User.northwind.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    UsersRepo usersRepo;

   public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        for (User user :usersRepo.findAll()){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setCity(user.getCity());
            userDto.setAddress(user.getAddress());
            userDto.setFax(user.getFax());
            userDto.setCountry(user.getCountry());
            userDto.setPhone(user.getPhone());
            userDto.setRegion(user.getRegion());
            userDto.setContactName(user.getContactName());
            userDto.setCompanyName(user.getCompanyName());
            userDto.setContactTitle(user.getContactTitle());
            userDto.setPostalCode(user.getPostalCode());

            userDtos.add(userDto);

        }
        return userDtos;
    }
        public int saveUser(UserDto userDto){
        UserDto userDtos = new UserDto();
        User user =new User();
        user.setId(userDto.getId());
        user.setCountry(userDto.getCountry());
        user.setAddress(userDto.getAddress());
        user.setContactTitle(userDto.getContactTitle());
        user.setCompanyName(userDto.getCompanyName());
        user.setCity(userDto.getCity());
        user.setPhone(userDto.getPhone());
        user.setRegion(userDto.getRegion());
        user.setContactName(userDto.getContactName());
        user.setFax(userDto.getFax());
        user.setPostalCode(userDto.getPostalCode());
            User save = usersRepo.save(user);
            userDtos.setId(save.getId());

            return userDtos.getId();
        }

    public UserDto getUserbyId(Integer Id){
        Optional<User> user =usersRepo.findById(Id);

        UserDto dto = new UserDto();

        if (!user.isPresent()){
            throw new RuntimeException("given Id is not Persent");
        }
        dto.setId(user.get().getId());
       dto.setFax(user.get().getFax());
       dto.setCity(user.get().getCity());
       dto.setPhone(user.get().getPhone());
       dto.setCountry(user.get().getCountry());
       dto.setRegion(user.get().getRegion());
       dto.setAddress(user.get().getAddress());
       dto.setContactTitle(user.get().getContactTitle());
       dto.setCompanyName(user.get().getCompanyName());
       dto.setPostalCode(user.get().getPostalCode());
       dto.setContactName(user.get().getContactName());
        return dto ;
    }


    public UserDto update(UserDto userdto) {
        Optional<User> byId = usersRepo.findById(userdto.getId());
        UserDto dto =new UserDto();
        User user = new User();
        if (byId.isPresent()) {
            user .setId(userdto.getId());
           user.setPostalCode(userdto.getPostalCode());
           user.setCountry(userdto.getCountry());
           user.setAddress(userdto.getAddress());
           user.setRegion(userdto.getRegion());
           user.setCity(userdto.getCity());
           user.setFax(userdto.getFax());
           user.setPhone(userdto.getPhone());
           user.setContactTitle(userdto.getContactTitle());
           user.setContactName(userdto.getContactName());
           user.setCompanyName(userdto.getCompanyName());
            User saveOrders=usersRepo.save(user);
            dto.setId(saveOrders.getId());

        } else {
            throw new RuntimeException("user Not Present");
        }
        return dto;
    }

    public void delete(Integer id){
        Optional<User> user = usersRepo.findById(id);
        if (user.isPresent()){
            usersRepo.deleteById(id);

        }else
        {
            throw new RuntimeException("given id is not persent");
        }



}}
