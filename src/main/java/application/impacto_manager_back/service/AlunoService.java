package application.impacto_manager_back.service;

import application.impacto_manager_back.model.Aluno;
import application.impacto_manager_back.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

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
	
	public List<Aluno> findByName(String nome) {
		return repository.findByNome(nome);
	}
	
	public Aluno create(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public Aluno update(Aluno aluno) {
		return repository.findById(aluno.getId())
		  .map(existingAluno -> {
			  copyProperties(aluno, existingAluno, "id", "responsavel", "turmas");
			  existingAluno.setEndereco(aluno.getEndereco());
			  existingAluno.setResponsavel(new ArrayList<>(aluno.getResponsavel()));
			  existingAluno.setTurmas(new ArrayList<>(aluno.getTurmas()));
			  return repository.save(existingAluno);
		  }).orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + aluno.getId()));
	}
	
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
