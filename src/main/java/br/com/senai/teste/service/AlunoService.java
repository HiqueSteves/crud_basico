package br.com.senai.teste.service;

import org.springframework.stereotype.Service;

import br.com.senai.teste.model.Aluno;
import br.com.senai.teste.repository.AlunoRepository;

@Service 
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno cadastrar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
}
