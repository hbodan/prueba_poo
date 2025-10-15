package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cargos")
@Getter @Setter
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cargos", length = 50, nullable = false)
    private String nombreCargos;

    @Column(name = "descripcion", length = 199, nullable = false)
    private String descripcion;

    @Override
    public String toString(){
        return nombreCargos + " " + descripcion;
    }

}
