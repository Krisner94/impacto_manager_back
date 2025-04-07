package application.impacto_manager_back.service;

import application.impacto_manager_back.model.Professor;
import application.impacto_manager_back.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorRepository repository;
	
	public List<Professor> findAll() {
		return repository.findAll();
	}
	
	public Professor findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Professor não encontrado com id: " + id));
	}
	
	public Professor create(Professor professor) {
		return repository.save(professor);
	}
	
	public Professor update(Long id, Professor professor) {
		Professor existingProfessor = findById(id);
		professor.setId(existingProfessor.getId());
		return repository.save(professor);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
