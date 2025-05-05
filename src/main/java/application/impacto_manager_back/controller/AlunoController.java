package application.impacto_manager_back.controller;

import application.impacto_manager_back.config.openApi.DataDocs.Create;
import application.impacto_manager_back.config.openApi.DataDocs.Delete;
import application.impacto_manager_back.config.openApi.DataDocs.FindAll;
import application.impacto_manager_back.config.openApi.DataDocs.FindById;
import application.impacto_manager_back.config.openApi.DataDocs.FindByName;
import application.impacto_manager_back.config.openApi.DataDocs.Update;
import application.impacto_manager_back.model.Aluno;
import application.impacto_manager_back.service.AlunoService;
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

import java.util.List;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
@Tag(name = "Aluno", description = "Endpoint para gerenciamento de alunos")
public class AlunoController {
	private final AlunoService service;
	
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
	@GetMapping("/find/{value}")
	public List<Aluno> findByNomeOrCPF(@PathVariable(value = "value") String value) {
		return service.findByNomeOrCPF(value);
	}
	
	@Create
	@PostMapping
	public Aluno save(@RequestBody Aluno aluno) {
		service.create(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(aluno).getBody();
	}
	
	
	@Update
	@PutMapping
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno) {
		Aluno novoAluno = service.update(aluno);
		return ResponseEntity.ok(novoAluno);
	}
	
	
	@Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}