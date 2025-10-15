package run;

import config.JPAUtil;
import entities.Cargo;
import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.dao.CargoDao;
import repository.dao.EmpleadoDao;
import utils.Input;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        EmpleadoDao empleadoDao = new EmpleadoDao(em);
        CargoDao cargoDao = new CargoDao(em);

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("=== Sistema de gestor de empleados ===");
            System.out.println("#1. Agregar empleado");
            System.out.println("#2. Agregar cargo");
            System.out.println("#3. Eliminar empleado");
            System.out.println("#4. Eliminar cargo");
            System.out.println("#5. Actualizar empleado");
            System.out.println("#6. Actualizar cargo");
            System.out.println("#7. Mostrar empleados");
            System.out.println("#8. Mostrar cargos");
            System.out.println("#9. Salir");

            int opcion = Input.getInt(sc, "> ");

            switch(opcion) {
                case(1): {
                    if (cargoDao.listarCargos().isEmpty()) {
                        System.out.println("No se puede crear un empleado sin cargos disponibles.");
                        break;
                    }
                    String nombre = Input.getString(sc, "Ingrese el nombre del empleado: ", 50);
                    double salario = Input.getDouble(sc, "Ingrese el salario del empleado en $: ");

                    List<Cargo> cargos = cargoDao.listarCargos();
                    for(Cargo c : cargos) {
                        System.out.println("#" + c.getId() + ": " + c);
                    }
                    long cargoId = Input.getLong(sc, "Ingrese el ID del cargo a asignar: ");
                    Cargo cargo = cargoDao.buscarPorId(cargoId);
                    if(cargo == null) {
                        System.out.println("No existe el cargo con el id: " + cargoId);
                        break;
                    }

                    Empleado e = new Empleado();
                    e.setNombre(nombre);
                    e.setSalario(salario);
                    e.setCargo(cargo);

                    try {
                        empleadoDao.guardarEmpleado(e);
                    } catch(Exception ex) {
                        System.out.println("No se pudo agregar al empleado: " + ex.getMessage());
                    }

                    break;
                }

                case(2): {
                    String nombre = Input.getString(sc, "Ingrese el nombre del cargo: ", 50);
                    String descripcion = Input.getString(sc, "Ingrese la descripción del cargo: ", 199);

                    Cargo c = new Cargo();
                    c.setNombreCargos(nombre);
                    c.setDescripcion(descripcion);

                    try {
                        cargoDao.guardarCargo(c);
                    }  catch(Exception ex) {
                        System.out.println("No se pudo agregar el cargo: " + ex.getMessage());
                    }

                    break;
                }

                case(3): {
                    if(empleadoDao.listarEmpleados().isEmpty()) {
                        System.out.println("No hay empleados para actualizar");
                    }
                    for(Empleado empleado : empleadoDao.listarEmpleados()) {
                        System.out.println("#" + empleado.getId() + ": " + empleado);
                    }
                    long empleado_id = Input.getLong(sc, "Ingrese el ID del empleado a eliminar: ");
                    Empleado empleado = empleadoDao.buscarPorId(empleado_id);
                    if(empleado == null) {
                        System.out.println("No existe el empleado con el id: " + empleado_id);
                        break;
                    }
                    try {
                        if(!empleadoDao.eliminarEmpleado(empleado)) {
                            System.out.println("Hubo un error eliminando el empleado");
                        }
                    } catch(Exception ex) {
                        System.out.println("Hubo un error eliminando el empleado: " + ex.getMessage());
                    }

                    break;
                }

                case(4): {
                    if(cargoDao.listarCargos().isEmpty()) {
                        System.out.println("No hay empleados para actualizar");
                    }
                    for(Cargo cargo : cargoDao.listarCargos()) {
                        System.out.println("#" + cargo.getId() + ": " + cargo);
                    }
                    long cargo_id = Input.getLong(sc, "Ingrese el ID del cargo a eliminar: ");
                    Cargo cargo = cargoDao.buscarPorId(cargo_id);
                    if(cargo == null) {
                        System.out.println("No existe el cargo con el id: " + cargo_id);
                        break;
                    }
                    try {
                        if(!cargoDao.eliminarCargo(cargo)) {
                            System.out.println("Hubo un error eliminando el cargo (procure que el cargo no este ocupado por ningun empleado)");
                        }
                    } catch(Exception ex) {
                        System.out.println("Hubo un error eliminando el cargo: " + ex.getMessage());
                    }

                    break;
                }

                case(5): {
                    if(empleadoDao.listarEmpleados().isEmpty()) {
                        System.out.println("No hay empleados para actualizar");
                    }
                    for(Empleado empleado : empleadoDao.listarEmpleados()) {
                        System.out.println("#" + empleado.getId() + ": " + empleado);
                    }
                    long empleado_id = Input.getLong(sc, "Ingrese el ID del empleado a actualizar: ");
                    Empleado empleado = empleadoDao.buscarPorId(empleado_id);
                    if(empleado == null) {
                        System.out.println("No existe el empleado con el id: " + empleado_id);
                        break;
                    }

                    String nombre = Input.getString(sc, "Ingrese el nombre del empleado: ", 50);
                    double salario = Input.getDouble(sc, "Ingrese el salario del empleado en $: ");

                    List<Cargo> cargos = cargoDao.listarCargos();
                    for(Cargo c : cargos) {
                        System.out.println("#" + c.getId() + ": " + c);
                    }
                    long cargoId = Input.getLong(sc, "Ingrese el ID del cargo a asignar: ");
                    Cargo cargo = cargoDao.buscarPorId(cargoId);
                    if(cargo == null) {
                        System.out.println("No existe el cargo con el id: " + cargoId);
                        break;
                    }

                    Empleado nuevo_empleado = new Empleado();
                    nuevo_empleado.setId(empleado_id);
                    nuevo_empleado.setNombre(nombre);
                    nuevo_empleado.setSalario(salario);
                    nuevo_empleado.setCargo(cargo);

                    try {
                        empleadoDao.actualizarEmpleado(nuevo_empleado);
                    } catch(Exception ex) {
                        System.out.println("Hubo un error actualizando el empleado: " + ex.getMessage());
                    }

                    break;
                }

                case(6): {
                    if(cargoDao.listarCargos().isEmpty()) {
                        System.out.println("No hay empleados para actualizar");
                    }
                    for(Cargo cargo : cargoDao.listarCargos()) {
                        System.out.println("#" + cargo.getId() + ": " + cargo);
                    }
                    long cargo_id = Input.getLong(sc, "Ingrese el ID del cargo a eliminar: ");
                    Cargo cargo = cargoDao.buscarPorId(cargo_id);
                    if(cargo == null) {
                        System.out.println("No existe el cargo con el id: " + cargo_id);
                        break;
                    }

                    String nombre = Input.getString(sc, "Ingrese el nombre del cargo: ", 50);
                    String descripcion = Input.getString(sc, "Ingrese la descripción del cargo: ", 199);

                    Cargo nc = new Cargo();
                    nc.setId(cargo_id);
                    nc.setNombreCargos(nombre);
                    nc.setDescripcion(descripcion);

                    try {
                        cargoDao.actualizarCargo(nc);
                    }  catch(Exception ex) {
                        System.out.println("Hubo un error actualizando el cargo: " + ex.getMessage());
                    }
                    break;
                }

                case(7): {
                    for(Empleado empleado : empleadoDao.listarEmpleados()) {
                        System.out.println("#" + empleado.getId() + ": " + empleado);
                    }
                    break;
                }

                case(8): {
                    for(Cargo  cargo : cargoDao.listarCargos()) {
                        System.out.println("#" + cargo.getId() + ": " + cargo);
                    }
                    break;
                }

                case(9): return;
            }

            System.out.println("Presione enter para continuar...");
            sc.nextLine();
        }
    }
}
