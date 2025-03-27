package application.impacto_manager_back.repository;

import application.impacto_manager_back.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.nome LIKE %:nome%")
    List<Professor> findByNome(String nome);
}