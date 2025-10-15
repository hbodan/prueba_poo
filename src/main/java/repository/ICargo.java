package repository;
import entities.Cargo;

import java.util.List;

public interface ICargo {
    Cargo guardarCargo(Cargo cargo);
    Cargo eliminarCargo(Cargo cargo);
    Cargo actualizarCargo(Cargo cargo);
    Cargo editarCargo(Cargo cargo);
    List<Cargo> listarCargos();

}
