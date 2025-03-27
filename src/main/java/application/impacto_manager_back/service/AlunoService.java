package application.impacto_manager_back.service;

import application.impacto_manager_back.model.Aluno;
import application.impacto_manager_back.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository repository;

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Aluno findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
    }

    public List<Aluno> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Aluno> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
