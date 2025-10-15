package repository.dao;

import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.IEmpleado;

import java.util.List;

public class EmpleadoDao implements IEmpleado {
    private final EntityManager em;

    public EmpleadoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Empleado guardarEmpleado (Empleado empleado) {
        try{
            em.getTransaction().begin();
            Empleado administrado;
            if (empleado.getId() == null) {
                em.persist(empleado);
                administrado = empleado;
            }else{
                administrado = em.merge(empleado);
            }
            em.getTransaction().commit();
            return administrado;
        }catch(RuntimeException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Boolean eliminarEmpleado(Empleado empleado) {
        if(empleado.getId() == null){
            throw new IllegalArgumentException("No se puede eliminar el empleado sin ID");
        }

        try{
            em.getTransaction().begin();
            Empleado administrado = em.find(Empleado.class, empleado.getId());

            if(administrado == null){
                em.getTransaction().commit();
                return false;
            }

            em.remove(administrado);
            em.getTransaction().commit();
            return true;

        }catch (RuntimeException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {
        if(empleado.getId() == null){
            throw new IllegalArgumentException("No se puede editar el empleado sin ID");
        }

        try{
            em.getTransaction().begin();
            Empleado existente = em.find(Empleado.class, empleado.getId());

            if(existente == null){
                em.getTransaction().commit();
                return null;
            }

            Empleado actualizado = em.merge(empleado);
            em.getTransaction().commit();
            return actualizado;

        }catch (RuntimeException e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Empleado buscarPorId(Long id){
        return (id==null) ? null : em.find(Empleado.class, id);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        List<Empleado> lista = em.createQuery("from Empleado", Empleado.class).getResultList();
        return lista;
    }
}