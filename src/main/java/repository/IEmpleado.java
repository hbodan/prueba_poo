package repository;
import entities.Empleado;

import java.util.List;

public interface IEmpleado {
    Empleado guardarEmpleado(Empleado empleado);
    Empleado eliminarEmpleado(Empleado empleado);
    Empleado actualizarEmpleado(Empleado empleado);
    Empleado editarEmpleado(Empleado empleado);
    List<Empleado> listarEmpleado();
}
