package application.impacto_manager_back.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name ="USERS")
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
}