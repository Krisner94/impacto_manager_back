package application.impacto_manager_back.repository;

import application.impacto_manager_back.model.Aluno;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNome(String nome);
    List<Aluno> findByCpf(String cpf);

    @EntityGraph(attributePaths = {"endereco", "responsavel", "turmas"})
    @Override
    List<Aluno> findAll();

    @EntityGraph(attributePaths = {"endereco", "responsavel", "turmas"})
    @Override
    Optional<Aluno> findById(Long id);

    @EntityGraph(attributePaths = {"endereco", "responsavel", "turmas"})
    List<Aluno> findByNomeContainingIgnoreCase(String nome);

    @EntityGraph(attributePaths = {"endereco", "responsavel", "turmas"})
    List<Aluno> findByCpfContaining(String cpf);

    @Query("SELECT DISTINCT a FROM Aluno a " +
        "LEFT JOIN FETCH a.endereco " +
        "LEFT JOIN FETCH a.responsavel " +
        "LEFT JOIN FETCH a.turmas")
    List<Aluno> findAllComRelacionamentos();
}