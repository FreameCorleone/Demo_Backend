package br.edu.ifba.demo.backend.api.dto;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import br.edu.ifba.demo.backend.api.model.LivroModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO implements Serializable {
    private Long idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoPublicacao;
    private String genero;  // Agora corretamente definido como String
    private String isbn;
    private Integer numPaginas;
    private String sinopse;
    private String idioma;
    private Timestamp dataCadastro;
    private Double preco;

    // Conversão LivroModel -> LivroDTO
    public static LivroDTO converter(LivroModel model) {
        return new LivroDTO(
            model.getIdLivro(), 
            model.getTitulo(), 
            model.getAutor(), 
            model.getEditora(),
            model.getAnoPublicacao(), 
            model.getGenero() != null ? model.getGenero().getNome() : null, // Corrigido
            model.getIsbn(),
            model.getNumPaginas(), 
            model.getSinopse(), 
            model.getIdioma(),
            model.getDataCadastro(), 
            model.getPreco()
        );
    }

    // Conversão de lista
    public static List<LivroDTO> converter(List<LivroModel> livros) {
        return livros.stream().map(LivroDTO::converter).collect(Collectors.toList());
    }
}
