package br.edu.ifba.demo.backend.api.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "genero")
public class GeneroModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long id;

    @Column(name = "nome_genero", nullable = false, unique = true)
private String nome;

}

