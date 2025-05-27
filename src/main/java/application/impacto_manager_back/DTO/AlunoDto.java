package application.impacto_manager_back.DTO;

import application.impacto_manager_back.model.Endereco;
import application.impacto_manager_back.model.Pagamento;
import application.impacto_manager_back.model.Responsavel;
import application.impacto_manager_back.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;
    private String dataNascimento;
    private String telefone;
    private Endereco endereco;
    private List<Responsavel> responsavel;
    private List<Turma> turmas;
    
    private Pagamento pagamento;
}