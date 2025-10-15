package repository;
import entities.Cargo;

import java.util.List;

public interface ICargo {
    Cargo guardarCargo(Cargo cargo);
    Boolean eliminarCargo(Cargo cargo);
    Cargo actualizarCargo(Cargo cargo);
    List<Cargo> listarCargos();
    Cargo buscarPorId(Long id);
}
