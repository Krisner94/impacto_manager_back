package application.impacto_manager_back.security.model;

import lombok.*;
import java.util.*;
import java.time.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;

}