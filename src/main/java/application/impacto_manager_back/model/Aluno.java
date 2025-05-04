package application.impacto_manager_back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dataNascimento;
    private String telefone;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "aluno_responsavel",
      joinColumns = @JoinColumn(name = "aluno_id"),
      inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    private List<Responsavel> responsavel = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "aluno_turma",
      joinColumns = @JoinColumn(name = "aluno_id"),
      inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    @JsonIgnoreProperties({"alunos", "professores"})
    private List<Turma> turmas = new ArrayList<>();
}