package application.impacto_manager_back.service;

import application.impacto_manager_back.model.Turma;
import application.impacto_manager_back.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {
    private final TurmaRepository repository;

    public List<Turma> findAll() {
        return repository.findAll();
    }

    public Turma findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada com id: " + id));
    }

    public Turma create(Turma turma) {
        return repository.save(turma);
    }

    public Turma update(Long id, Turma turma) {
        Turma existingTurma = findById(id);
        turma.setId(existingTurma.getId());
        return repository.save(turma);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}