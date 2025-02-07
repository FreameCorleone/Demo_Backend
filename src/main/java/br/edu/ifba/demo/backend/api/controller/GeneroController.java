package br.edu.ifba.demo.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.demo.backend.api.dto.GeneroDTO;
import br.edu.ifba.demo.backend.api.model.GeneroModel;
import br.edu.ifba.demo.backend.api.model.LivroModel;
import br.edu.ifba.demo.backend.api.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroRepository generoRepository;

    @PostMapping
    public ResponseEntity<GeneroDTO> criarGenero(@RequestBody GeneroDTO generoDTO) {
        GeneroModel genero = new GeneroModel();
        genero.setNome(generoDTO.getNome());

        GeneroModel salvo = generoRepository.save(genero);
        return ResponseEntity.ok(GeneroDTO.converter(salvo));
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarGeneros() {
        List<GeneroDTO> lista = generoRepository.findAll()
                .stream()
                .map(GeneroDTO::converter)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarGenero(@PathVariable Long id) {
        return generoRepository.findById(id)
                .map(genero -> ResponseEntity.ok(GeneroDTO.converter(genero)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> atualizarGenero(@PathVariable Long id, @RequestBody GeneroDTO generoDTO) {
        return generoRepository.findById(id)
                .map(genero -> {
                    genero.setNome(generoDTO.getNome());
                    GeneroModel atualizado = generoRepository.save(genero);
                    return ResponseEntity.ok(GeneroDTO.converter(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listall")
public ResponseEntity<List<GeneroDTO>> listAll() {
    List<GeneroDTO> lista = generoRepository.findAll()
            .stream()
            .map(GeneroDTO::converter)
            .collect(Collectors.toList());
    return ResponseEntity.ok(lista);
}

}
