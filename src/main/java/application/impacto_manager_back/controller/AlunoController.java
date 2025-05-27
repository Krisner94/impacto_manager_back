package application.impacto_manager_back.controller;

import application.impacto_manager_back.DTO.AlunoDto;
import application.impacto_manager_back.config.openApi.DataDocs.Create;
import application.impacto_manager_back.config.openApi.DataDocs.Delete;
import application.impacto_manager_back.config.openApi.DataDocs.FindAll;
import application.impacto_manager_back.config.openApi.DataDocs.FindById;
import application.impacto_manager_back.config.openApi.DataDocs.FindByName;
import application.impacto_manager_back.config.openApi.DataDocs.Update;
import application.impacto_manager_back.exceptions.ObjectNotFoundException;
import application.impacto_manager_back.mapper.AlunoToDtoMapperImpl;
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

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
@Tag(name = "Aluno", description = "Endpoint para gerenciamento de alunos")
public class AlunoController {
	private final AlunoService service;
	private final AlunoToDtoMapperImpl alunoMapper;
	
	@FindAll
	@GetMapping("")
	public ResponseEntity<List<AlunoDto>> findAll() {
		try {
			List<Aluno> alunos = service.findAll();
			List<AlunoDto> alunoDtos = alunoMapper.alunosToAlunoDtos(alunos);
			return ResponseEntity.ok(alunoDtos);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar alunos", NOT_FOUND);
		}
	}
	
	@FindById
	@GetMapping("/{id}")
	public AlunoDto findById(@PathVariable(value = "id") Long id) {
		try {
			Aluno aluno = service.findById(id);
			return alunoMapper.alunoToAlunoDto(aluno);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar aluno", NOT_FOUND);
		}
	}
	
	@FindByName
	@GetMapping("/find/{value}")
	public ResponseEntity<List<AlunoDto>> findByNomeOrCPF(@PathVariable(value = "value") String value) {
		try {
			List<Aluno> alunos = service.findByNomeOrCPF(value);
			List<AlunoDto> alunoDtos = alunoMapper.alunosToAlunoDtos(alunos);
			return ResponseEntity.ok().body(alunoDtos);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar aluno", NOT_FOUND);
		}
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
