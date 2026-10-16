package br.com.senai.teste.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity 
@Table (name = "livro")
public class Livro {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank (message = "O título do livro é obrigatório")
    private String titulo;

    @NotBlank (message = "O autor do livro é obrigatório")
    private String autor;
    
    @NotBlank (message = "A editora do livro é obrigatória")
    private String editora;

    public Livro() {
    }

    public Livro( String titulo, String autor, String editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
    
}
