package br.edu.ifba.demo.backend.api.dto;

import br.edu.ifba.demo.backend.api.model.GeneroModel;
import lombok.Data;

@Data
public class GeneroDTO {
    private Long id;
    private String nome;

    public static GeneroDTO converter(GeneroModel model) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        return dto;
    }
}
