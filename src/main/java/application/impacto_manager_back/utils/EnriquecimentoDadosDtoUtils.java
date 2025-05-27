package application.impacto_manager_back.utils;

import application.impacto_manager_back.DTO.AlunoDto;
import application.impacto_manager_back.model.Aluno;
import application.impacto_manager_back.model.Pagamento;
import application.impacto_manager_back.service.PagamentoService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnriquecimentoDadosDtoUtils {
	public static void enriquecerComPagamento(List<Aluno> alunos, List<AlunoDto> alunoDtos, PagamentoService service) {
		List<Long> pagamentoIds = alunos.stream()
			.map(Aluno::getPagamentoId)
			.filter(Objects::nonNull)
			.distinct()
			.toList();
		
		Map<Long, Pagamento> pagamentosMap = service.findAllByIds(pagamentoIds).stream()
			.collect(Collectors.toMap(Pagamento::getId, p -> p));
		
		for (int i = 0; i < alunos.size(); i++) {
			Long pagamentoId = alunos.get(i).getPagamentoId();
			if (pagamentoId != null) {
				Pagamento pagamento = pagamentosMap.get(pagamentoId);
				alunoDtos.get(i).setPagamento(pagamento);
			}
		}
	}
	
	public static void enriquecerAlunoDtoComPagamento(Aluno aluno, AlunoDto alunoDto, PagamentoService service) {
		if (aluno.getPagamentoId() != null) {
			Pagamento pagamento = service.findById(aluno.getPagamentoId());
			alunoDto.setPagamento(pagamento);
		}
	}
	
	public static void enriquecerListaAlunoDtoComPagamento(List<Aluno> alunos, List<AlunoDto> alunoDtos, PagamentoService service) {
		enriquecerComPagamento(alunos, alunoDtos, service);
	}
}