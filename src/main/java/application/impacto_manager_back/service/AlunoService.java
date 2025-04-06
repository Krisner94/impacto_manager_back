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
