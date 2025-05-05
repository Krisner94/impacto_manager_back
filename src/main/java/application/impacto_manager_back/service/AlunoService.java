package application.impacto_manager_back.service;

import application.impacto_manager_back.exceptions.CustomException;
import application.impacto_manager_back.exceptions.Error;
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
			.orElseThrow(() -> new CustomException(
				Error.builder()
					.title("Erro de busca")
					.message(String.format("Aluno com id %d não encontrado", id))
					.build()));
	}
	
	public List<Aluno> findByNomeOrCPF(String value) {
		return repository.findByNameOrCPF(value);
	}
	
	public Aluno create(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public Aluno update(Aluno aluno) {
		return repository.save(aluno);
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
