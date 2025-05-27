package application.impacto_manager_back.service;

import application.impacto_manager_back.exceptions.CustomException;
import application.impacto_manager_back.exceptions.Error;
import application.impacto_manager_back.model.Pagamento;
import application.impacto_manager_back.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {
	private final PagamentoRepository repository;
	
	public List<Pagamento> findAll() {
		return repository.findAll();
	}
	
	public Pagamento findById(Long id) {
		return repository.findById(id).orElseThrow(() ->
			new CustomException(Error.builder()
			.title("Erro de busca")
			.message(String.format("Pagamento com id %d não encontrado", id))
			.build()));
	}
	
	public List<Pagamento> findAllByIds(List<Long> ids) {
		return repository.findAllById(ids);
	}
	
	public Pagamento create(Pagamento pagamento) {
		return repository.save(pagamento);
	}
	
	public Pagamento update(Pagamento pagamento) {
		return repository.save(pagamento);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
