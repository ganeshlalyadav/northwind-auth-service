package User.northwind.controller;
import User.northwind.dao.JWTAuthResponse;
import User.northwind.dao.LoginDto;
import User.northwind.exception.InvalidUsernamePasswordException;
import User.northwind.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/*@AllArgsConstructor*/
@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthService authService;
    private UserDetailsService userDetailsService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        try {
            JWTAuthResponse logi = authService.login(loginDto);
            return ResponseEntity.ok(logi);

        } catch (BadCredentialsException badCredentialsException) {

            throw new InvalidUsernamePasswordException("Invalid user credentials, pls try again");

        }
    }





}


