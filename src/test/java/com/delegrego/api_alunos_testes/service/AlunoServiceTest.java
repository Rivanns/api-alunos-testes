package com.delegrego.api_alunos_testes.service;

import com.delegrego.api_alunos_testes.dto.AlunoRequest;
import com.delegrego.api_alunos_testes.model.Aluno;
import com.delegrego.api_alunos_testes.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;


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
        

        //Assert

    }
}
