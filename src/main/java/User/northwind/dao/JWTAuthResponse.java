package User.northwind.dao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String userName;
    private Date tokenExpireAt;
}
