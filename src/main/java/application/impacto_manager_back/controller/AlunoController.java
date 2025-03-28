package application.impacto_manager_back.controller;

import application.impacto_manager_back.config.openApi.AlunoDocs.Atualizar;
import application.impacto_manager_back.config.openApi.AlunoDocs.BuscarPorId;
import application.impacto_manager_back.config.openApi.AlunoDocs.BuscarTodos;
import application.impacto_manager_back.config.openApi.AlunoDocs.Criar;
import application.impacto_manager_back.config.openApi.AlunoDocs.Excluir;
import application.impacto_manager_back.exceptions.CustomException;
import application.impacto_manager_back.exceptions.Error;
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

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("/api/aluno")
@RequiredArgsConstructor
@Tag(name = "Aluno", description = "Endpoint para gerenciamento de alunos")
public class AlunoController {
    private final AlunoService service;

    @BuscarTodos
    @GetMapping("")
    public List<Aluno> findAll() {
        try {
            return service.findAll();
        } catch (Exception e) {
            throw new CustomException(Error.builder()
                .title("Erro ao buscar todos os alunos")
                .message("Ocorreu um erro ao buscar todos os alunos")
                .httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build());
        }
    }

    @BuscarPorId
    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            throw new CustomException(Error.builder()
                .title("Aluno não encontrado")
                .message("Aluno com id " + id + " não encontrado")
                .httpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .build());
        }
    }

    @Criar
    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        try {
            service.save(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(aluno).getBody();
        } catch (Exception e) {
            throw new CustomException(Error.builder()
                .title("Erro ao criar aluno")
                .message("Ocorreu um erro ao criar o aluno")
                .httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build());
        }
    }

    @Atualizar
    @PutMapping
    public Aluno update(@RequestBody Aluno aluno) {
        try {
            Aluno newAluno = new Aluno();
            copyProperties(aluno, newAluno);
            service.save(newAluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAluno).getBody();
        } catch (Exception e) {
            throw new CustomException(Error.builder()
                .title("Erro ao atualizar aluno")
                .message("Ocorreu um erro ao atualizar o aluno")
                .httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build());
        }
    }

    @Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new CustomException(Error.builder()
                .title("Erro ao excluir aluno")
                .message("Ocorreu um erro ao excluir o aluno com id " + id)
                .httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build());
        }
    }
}