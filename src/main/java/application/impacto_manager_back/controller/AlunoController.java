package application.impacto_manager_back.controller;

import application.impacto_manager_back.config.openApi.AlunoDocs.Atualizar;
import application.impacto_manager_back.config.openApi.AlunoDocs.BuscarPorId;
import application.impacto_manager_back.config.openApi.AlunoDocs.BuscarTodos;
import application.impacto_manager_back.config.openApi.AlunoDocs.Criar;
import application.impacto_manager_back.config.openApi.AlunoDocs.Excluir;
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
@RequestMapping("/aluno")
@RequiredArgsConstructor
@Tag(name = "Aluno", description = "Endpoint para gerenciamento de alunos")
public class AlunoController {
    private final AlunoService service;

    @BuscarTodos
    @GetMapping
    public List<Aluno> findAll() {
        return service.findAll();
    }

    @BuscarPorId
    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Criar
    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        service.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno).getBody();
    }

    @Atualizar
    @PutMapping
    public Aluno update(@RequestBody Aluno aluno) {
        Aluno newAluno = new Aluno();
        copyProperties(aluno, newAluno);
        service.save(newAluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAluno).getBody();
    }

    @Excluir
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
