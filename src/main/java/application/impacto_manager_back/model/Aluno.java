package application.impacto_manager_back.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "aluno_responsavel",
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "responsavel_id")
    )
    private List<Responsavel> responsavel = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "aluno_turma",
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private Set<Turma> turmas;
}