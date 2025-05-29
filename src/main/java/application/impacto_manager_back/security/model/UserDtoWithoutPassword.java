package application.impacto_manager_back.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoWithoutPassword {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String role;

}