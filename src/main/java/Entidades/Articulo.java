package Entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@Builder
@Getter
@Setter
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "precio")
    private int precio;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_categoria")
    @Builder.Default
    private List<Categoria> categorias = new ArrayList<Categoria>();
}