package br.edu.ifba.demo.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.demo.backend.api.dto.LivroDTO;
import br.edu.ifba.demo.backend.api.model.LivroModel;
import br.edu.ifba.demo.backend.api.repository.LivroRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    // Adicionar um novo livro
    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody LivroModel livro) {
        LivroModel savedLivro = livroRepository.save(livro);
        return new ResponseEntity<>(savedLivro, HttpStatus.CREATED);
    }

    // Listar todos os livros
    @GetMapping("/listall")
    public List<LivroModel> listall() {
        return livroRepository.findAll();
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        var livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LivroDTO.converter(livro.get()));
    }

    // Deletar livro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!livroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar livro por ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDTO> buscarPorISBN(@PathVariable String isbn) {
        var livro = livroRepository.findByIsbn(isbn);
        if (livro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LivroDTO.converter(livro));
    }

    // Buscar livro por título
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroDTO>> buscarPorTitulo(@PathVariable String titulo) {
        var livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LivroDTO.converter(livros));
    }
    
    @PutMapping("/{id}")
public ResponseEntity<LivroModel> atualizarLivro(@PathVariable Long id, @RequestBody LivroModel livroAtualizado) {
    // Verifica se o livro existe no banco
    if (!livroRepository.existsById(id)) {
        return ResponseEntity.notFound().build(); // Retorna 404 caso não encontre o livro
    }

    // Busca o livro atual no banco de dados
    LivroModel livroExistente = livroRepository.findById(id).get();

    // Atualiza os dados do livro existente com os dados fornecidos
    livroExistente.setTitulo(livroAtualizado.getTitulo());
    livroExistente.setAutor(livroAtualizado.getAutor());
    livroExistente.setGenero(livroAtualizado.getGenero());
    livroExistente.setIdioma(livroAtualizado.getIdioma());
    livroExistente.setPreco(livroAtualizado.getPreco());
    livroExistente.setNum_paginas(livroAtualizado.getNum_paginas());
    livroExistente.setData_cadastro(livroAtualizado.getData_cadastro());
    livroExistente.setEditora(livroAtualizado.getEditora());
    livroExistente.setAno_publicacao(livroAtualizado.getAno_publicacao());
    livroExistente.setIsbn(livroAtualizado.getIsbn());
    livroExistente.setSinopse(livroAtualizado.getSinopse());

    // Salva o livro atualizado
    LivroModel livroSalvo = livroRepository.save(livroExistente);

    return ResponseEntity.ok(livroSalvo); // Retorna o livro atualizado
}
    
}
