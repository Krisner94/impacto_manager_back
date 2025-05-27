package application.impacto_manager_back.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDeAlunoEnum {
	INTEGRAL(1, "Aluno Integral"),
	BOLSISTA(2, "Aluno Bolsista");
	
	private final int codigo;
	private final String descricao;
}
