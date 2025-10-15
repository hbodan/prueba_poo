package repository;
import entities.Cargo;
import entities.Empleado;

import java.util.List;

public interface IEmpleado {
    Empleado guardarEmpleado(Empleado empleado);
    Boolean eliminarEmpleado(Empleado empleado);
    Empleado actualizarEmpleado(Empleado empleado);
    Empleado buscarPorId(Long id);
    List<Empleado> listarEmpleados();
}
