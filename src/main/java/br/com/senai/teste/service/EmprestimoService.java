package br.com.senai.teste.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.senai.teste.model.Aluno;
import br.com.senai.teste.model.Livro;
import br.com.senai.teste.model.Emprestimo;
import br.com.senai.teste.repository.EmprestimoRepository;

@Service 
public class EmprestimoService {
    
    private final EmprestimoRepository emprestimoRepository;
    private final AlunoService alunoService;
    private final LivroService livroService;

    public List<Emprestimo> listar() {
        return emprestimoRepository.findAll();
    }

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository, 
            AlunoService alunoService, 
            LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.alunoService = alunoService;
        this.livroService = livroService;
    }

    public Optional<Emprestimo> cadastrar(Integer alunoId, Integer livroId) {
        Optional<Aluno> aluno = alunoService.buscarPorId(alunoId);
        Optional<Livro> livro = livroService.buscarPorId(livroId);

        if (aluno.isEmpty() || livro.isEmpty()) {
            return Optional.empty();
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno.get());
        emprestimo.setLivro(livro.get());
        emprestimo.setDataEmprestimo(LocalDate.now());

        return Optional.of(emprestimoRepository.save(emprestimo));
    }

    public Optional<Emprestimo> buscarPorId(Integer id) {
        return emprestimoRepository.findById(id);
    }

    public Optional<Emprestimo> devolver(Integer id) {
        Optional<Emprestimo> encontrado = emprestimoRepository.findById(id);

        if (encontrado.isEmpty()) {
            return Optional.empty();
        }

        Emprestimo emprestimo = encontrado.get();

        if (emprestimo.getDataDevolucao() == null) {
            emprestimo.setDataDevolucao(LocalDate.now());
            emprestimoRepository.save(emprestimo);
        }

        return Optional.of(emprestimo);
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByDataDevolucaoIsNull();
    }
}
