package br.edu.ifba.demo.backend.api.model;

import lombok.Data;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "livro")
public class LivroModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long idLivro;
    
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    private String editora;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "genero_id_genero")
    private GeneroModel genero;

    private String isbn;

    @Column(name = "num_paginas")
    private Integer numPaginas;

    private String sinopse;

    private String idioma;

    @Column(name = "data_cadastro")
    private Timestamp dataCadastro;

    private Double preco;

    public LivroModel() {
    }

    public LivroModel(Long idLivro, String titulo, String autor, String editora, Integer anoPublicacao,
            GeneroModel genero, String isbn, Integer numPaginas, String sinopse, String idioma, Timestamp dataCadastro,
            Double preco) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.isbn = isbn;
        this.numPaginas = numPaginas;
        this.sinopse = sinopse;
        this.idioma = idioma;
        this.dataCadastro = dataCadastro;
        this.preco = preco;
    }



    
}
