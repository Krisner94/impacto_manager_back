package application.impacto_manager_back.controller;

import application.impacto_manager_back.config.openApi.DataDocs.Create;
import application.impacto_manager_back.config.openApi.DataDocs.Delete;
import application.impacto_manager_back.config.openApi.DataDocs.FindAll;
import application.impacto_manager_back.config.openApi.DataDocs.FindByCPF;
import application.impacto_manager_back.config.openApi.DataDocs.FindById;
import application.impacto_manager_back.config.openApi.DataDocs.FindByName;
import application.impacto_manager_back.config.openApi.DataDocs.Update;
import application.impacto_manager_back.model.Aluno;
import application.impacto_manager_back.model.Turma;
import application.impacto_manager_back.service.AlunoService;
import application.impacto_manager_back.service.TurmaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
@Tag(name = "Aluno", description = "Endpoint para gerenciamento de alunos")
public class AlunoController {
	private final AlunoService service;
	private final TurmaService turmaService;
	
	@FindAll
	@GetMapping("")
	public ResponseEntity<List<Aluno>> findAll() {
		List<Aluno> alunos = service.findAll();
		return ResponseEntity.ok(alunos);
	}
	
	@FindById
	@GetMapping("/{id}")
	public Aluno findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@FindByName
	@GetMapping("/nome/{nome}")
	public List<Aluno> findByName(@PathVariable(value = "nome") String nome) {
		return service.findByName(nome);
	}
	
	@FindByCPF
	@GetMapping("/cpf/{cpf}")
	public List<Aluno> findByCpf(@PathVariable(value = "cpf") String cpf) {
		return service.findByCpf(cpf);
	}
	
	@Create
	@PostMapping
	public Aluno save(@RequestBody Aluno aluno) {
		service.create(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(aluno).getBody();
	}
	
	@Update
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Aluno aluno) {
		if (aluno.getId() == null) {
			return ResponseEntity.badRequest().body("ID do aluno não pode ser nulo");
		}
		
		Aluno existente = service.findById(aluno.getId());
		existente.setEndereco(aluno.getEndereco());
		existente.setResponsavel(aluno.getResponsavel());
		
		if (aluno.getTurmas() != null) {
			List<Turma> turmasAtualizadas = aluno.getTurmas().stream()
			  .map(t -> turmaService.findById(t.getId()))
			  .toList();
			existente.setTurmas(turmasAtualizadas);
		}
		
		return ResponseEntity.ok(service.update(existente));
	}
	
	
	
	@Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}