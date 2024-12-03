package br.edu.ifba.demo.backend.api.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.demo.backend.api.model.LivroModel;

public class LivroDTO implements Serializable {

    private Long idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoPublicacao;
    private String genero;
    private String isbn;
    private Integer num_paginas;
    private String sinopse;
    private String idioma;
    private Timestamp dataCadastro;
    private Double preco;

    // Construtores
    public LivroDTO() {
        super();
    }

    public LivroDTO(Long idLivro, String titulo, String autor, String editora, Integer anoPublicacao, String genero,
                    String isbn, Integer num_paginas, String sinopse, String idioma, Timestamp dataCadastro, Double preco) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.isbn = isbn;
        this.num_paginas = num_paginas;
        this.sinopse = sinopse;
        this.idioma = idioma;
        this.dataCadastro = dataCadastro;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
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

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(Integer num_paginas) {
        this.num_paginas = num_paginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Método estático para converter LivroModel em LivroDTO
    public static LivroDTO converter(LivroModel model) {
        var livro = new LivroDTO();
        livro.setIdLivro(model.getId_livro());
        livro.setTitulo(model.getTitulo());
        livro.setAutor(model.getAutor());
        livro.setEditora(model.getEditora());
        livro.setAnoPublicacao(model.getAno_publicacao());
        livro.setGenero(model.getGenero());
        livro.setIsbn(model.getIsbn());
        livro.setNum_paginas(model.getNum_paginas());
        livro.setSinopse(model.getSinopse());
        livro.setIdioma(model.getIdioma());
        livro.setDataCadastro(model.getData_cadastro());
        livro.setPreco(model.getPreco());
        return livro;
    }

    // Método estático para converter uma lista de LivroModel em uma lista de LivroDTO
    public static List<LivroDTO> converter(List<LivroModel> livros) {
        List<LivroDTO> list = new ArrayList<>();
        for (LivroModel model : livros) {
            list.add(LivroDTO.converter(model));
        }
        return list;
    }
}
