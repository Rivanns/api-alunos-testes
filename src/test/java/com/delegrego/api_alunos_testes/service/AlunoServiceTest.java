package com.delegrego.api_alunos_testes.service;

import com.delegrego.api_alunos_testes.dto.AlunoRequest;
import com.delegrego.api_alunos_testes.dto.AlunoResponse;
import com.delegrego.api_alunos_testes.model.Aluno;
import com.delegrego.api_alunos_testes.repository.AlunoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    AlunoService alunoService;
    @Mock
    AlunoRepository alunoRepository;

    @Test
    void deveSalvarERetornarAluno(){

        //Arrange
        AlunoRequest alunoRequest = new AlunoRequest(
                "Jonatan",
                "jonatan@email.com",
                "senha",
                LocalDate.of(1993,07,21),
                8.5);

        //Stub -> parte
        Mockito.when(
                alunoRepository.existsByEmailIgnoreCase(
                 alunoRequest.getEmail()))
                .thenReturn(false);

        Aluno aluno = new Aluno(1L,
                alunoRequest.getNome(),
                alunoRequest.getEmail(),
                alunoRequest.getSenha(),
                alunoRequest.getDataNascimento(),
                alunoRequest.getMedia());

        Mockito.when(alunoRepository.save(
                Mockito.any(Aluno.class)))
                .thenReturn(aluno);


        //Act
        AlunoResponse alunoSalvo = alunoService.cadastrarAluno(alunoRequest);


        //Assert
        Assertions.assertThat(alunoSalvo).isNotNull();
        Assertions.assertThat(alunoSalvo.getId()).isGreaterThan(0);


    }

    @Test
    void deveRetornarListaDeAlunos() {

        //Arrange
        Aluno aluno1 = new Aluno(1L,
                "Jonatan",
                "jonatan@email.com",
                "senha",
                LocalDate.of(1993,07,21),
                8.5);
        Aluno aluno2 = new Aluno(2L,
                "David",
                "david@email.com",
                "senha",
                LocalDate.of(2002,04,17),
                7.5);

        //Stub
        Mockito.when(alunoRepository.findAll()).thenReturn(List.of(aluno1, aluno2));

        //Act
        List<AlunoResponse> alunoList = alunoService.listarAlunos();

        //Assert
        Assertions.assertThat(alunoList).hasSize(2);

    }
}
