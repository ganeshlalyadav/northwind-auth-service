package User.northwind.controller;

import User.northwind.dao.UserDto;
import User.northwind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/get/all")
    private List<UserDto> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/by/id/{userid}")
    private UserDto getUser(@PathVariable("userid") int userid) {
        return (UserDto) userService.getUserbyId(userid);
    }

    @DeleteMapping("/delete/by/id/{userid}")
    private void deleteUser(@PathVariable("userid") int userid) {
        userService.delete(userid);
    }

    @PostMapping("/save")
    private int saveUser(@RequestBody UserDto userDto) {
        int id = userService.saveUser(userDto);
        return id;

    }

    @PutMapping("/update")
    private int update(@RequestBody UserDto userDto) {
        return userService.update(userDto).getId();
    }


}

