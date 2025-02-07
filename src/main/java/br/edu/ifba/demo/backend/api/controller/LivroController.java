package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.status(201).body(savedLivro);
    }

    // Listar todos os livros
    @GetMapping("/listall")
    public List<LivroModel> listAll() {
        return livroRepository.findAll();
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        return livroRepository.findById(id)
            .map(livro -> ResponseEntity.ok(LivroDTO.converter(livro)))
            .orElse(ResponseEntity.notFound().build());
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
        LivroModel livro = livroRepository.findByIsbn(isbn);
        return (livro != null) ? ResponseEntity.ok(LivroDTO.converter(livro))
                               : ResponseEntity.notFound().build();
    }

    // Buscar livro por título
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroDTO>> buscarPorTitulo(@PathVariable String titulo) {
        List<LivroModel> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        return livros.isEmpty() ? ResponseEntity.notFound().build()
                                : ResponseEntity.ok(LivroDTO.converter(livros));
    }

    // Atualizar um livro por ID
    @PutMapping("/{id}")
    public ResponseEntity<LivroModel> atualizarLivro(@PathVariable Long id, @RequestBody LivroModel livroAtualizado) {
        Optional<LivroModel> optionalLivro = livroRepository.findById(id);

        if (optionalLivro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LivroModel livroExistente = optionalLivro.get();
        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setGenero(livroAtualizado.getGenero());
        livroExistente.setIdioma(livroAtualizado.getIdioma());
        livroExistente.setPreco(livroAtualizado.getPreco());
        livroExistente.setNumPaginas(livroAtualizado.getNumPaginas());
        livroExistente.setDataCadastro(livroAtualizado.getDataCadastro());
        livroExistente.setEditora(livroAtualizado.getEditora());
        livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livroExistente.setIsbn(livroAtualizado.getIsbn());
        livroExistente.setSinopse(livroAtualizado.getSinopse());

        LivroModel livroSalvo = livroRepository.save(livroExistente);

        return ResponseEntity.ok(livroSalvo);
    }
}
