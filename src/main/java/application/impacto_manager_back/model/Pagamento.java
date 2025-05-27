package application.impacto_manager_back.model;

import application.impacto_manager_back.enums.TipoDeAlunoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipoDeAluno;
	private TipoDeAlunoEnum tipoDeAlunoEnum;
	private BigDecimal valorMensalidade;
}
