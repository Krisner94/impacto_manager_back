package application.impacto_manager_back.security.model;

import lombok.*;

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