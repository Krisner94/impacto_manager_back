package application.impacto_manager_back.repository;

import application.impacto_manager_back.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a FROM Aluno a WHERE " +
      "LOWER(a.nome) LIKE LOWER(CONCAT('%', :value, '%')) OR " +
      "a.cpf LIKE CONCAT('%', :value, '%')")
    List<Aluno> findByNameOrCPF(String value);
}