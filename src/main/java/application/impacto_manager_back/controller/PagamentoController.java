package application.impacto_manager_back.controller;

import application.impacto_manager_back.config.openApi.DataDocs.Create;
import application.impacto_manager_back.config.openApi.DataDocs.Delete;
import application.impacto_manager_back.config.openApi.DataDocs.FindAll;
import application.impacto_manager_back.config.openApi.DataDocs.FindById;
import application.impacto_manager_back.config.openApi.DataDocs.Update;
import application.impacto_manager_back.exceptions.ObjectNotFoundException;
import application.impacto_manager_back.model.Pagamento;
import application.impacto_manager_back.service.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pagamento")
@Tag(name = "Pagamento", description = "Gerenciamento do valor da mensalidade a ser paga pelos alunos")
public class PagamentoController {
	private final PagamentoService service;
	
	@FindAll
	@GetMapping("")
	public ResponseEntity<List<Pagamento>> findAll() {
		try {
			List<Pagamento> pagamentos = service.findAll();
			return ResponseEntity.ok(pagamentos);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Lista de pagamentos não encontrada", NOT_FOUND);
		}
	}
	
	@FindById
	@GetMapping("/{id}")
	public Pagamento findById(@PathVariable(value = "id") Long id) {
		try {
			return service.findById(id);
		} catch (Exception e) {
			throw new ObjectNotFoundException(String.format("Pagamento de id %d ão encontrado", id), NOT_FOUND);
		}
	}
	
	@Create
	@PostMapping
	public Pagamento create(Pagamento pagamento) {
		try {
			service.create(pagamento);
			return ResponseEntity.status(HttpStatus.CREATED).body(pagamento).getBody();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao cadastrar pagamento.");
		}
	}
	
	@Update
	@PutMapping
	public Pagamento update(Pagamento pagamento) {
		try {
			service.update(pagamento);
			return ResponseEntity.ok(pagamento).getBody();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao atualizar pagamento.");
		}
	}
	
	@Delete
	@DeleteMapping
	public void deleteById(Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao excluir pagamento.");
		}
	}
}
