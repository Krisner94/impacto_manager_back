package application.impacto_manager_back.mapper;

import application.impacto_manager_back.DTO.AlunoDto;
import application.impacto_manager_back.model.Aluno;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoToDtoMapper {
	
	AlunoDto alunoToAlunoDto(Aluno aluno);
	
	List<AlunoDto> alunosToAlunoDtos(List<Aluno> alunos);
}