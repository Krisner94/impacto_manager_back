package application.impacto_manager_back.controller;

import application.impacto_manager_back.config.openApi.DataDocs.Create;
import application.impacto_manager_back.config.openApi.DataDocs.Delete;
import application.impacto_manager_back.config.openApi.DataDocs.FindAll;
import application.impacto_manager_back.config.openApi.DataDocs.FindById;
import application.impacto_manager_back.config.openApi.DataDocs.Update;
import application.impacto_manager_back.model.Professor;
import application.impacto_manager_back.service.ProfessorService;
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
@RequestMapping("/api/professor")
@RequiredArgsConstructor
@Tag(name = "Professor", description = "Endpoint para gerenciamento de professores")
public class ProfessorController {
	private final ProfessorService service;
	
	@FindAll
	@GetMapping("/")
	public List<Professor> findAll() {
		return service.findAll();
	}
	
	@FindById
	@GetMapping("/{id}")
	public Professor findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@Create
	@PostMapping
	public Professor create(@RequestBody Professor professor) {
		return service.create(professor);
	}
	
	@Update
	@PutMapping
	public Professor update(@RequestBody Professor professor) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(service.update(professor.getId(), professor))
			.getBody();
	}
	
	@Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
