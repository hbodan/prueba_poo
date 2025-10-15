package repository.dao;

import entities.Cargo;
import jakarta.persistence.EntityManager;
import repository.ICargo;

import java.util.List;

public class CargoDao implements ICargo {
    private final EntityManager em;

    public CargoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cargo guardarCargo(Cargo cargo){
        try{
            em.getTransaction().begin();

            Cargo cargado;

            if(cargo.getId() != null){
                em.persist(cargo);
                cargado = cargo;
            }else{
                cargado = em.merge(cargo);
            }
            em.getTransaction().commit();
            return cargado;
        }catch (RuntimeException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Boolean eliminarCargo(Cargo cargo){
        if (cargo.getId() == null){
            throw new IllegalArgumentException("No se puede borrar sin ID");
        }
        try{
            em.getTransaction().begin();
            Cargo eliminado = em.find(Cargo.class, cargo.getId());

            if(eliminado == null){
                em.getTransaction().commit();
                return false;
            }

            em.remove(cargo);
            em.getTransaction().commit();
            return true;

        }catch (RuntimeException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
    }


    @Override
    public Cargo actualizarCargo(Cargo cargo){
        if (cargo.getId() == null){
            throw new IllegalArgumentException("No se puede editar sin ID");
        }
        try{
            em.getTransaction().begin();
            Cargo editado = em.find(Cargo.class, cargo.getId());

            if(editado == null){
                em.getTransaction().commit();
                return editado;
            }

            editado= em.merge(cargo);
            em.getTransaction().commit();
            return editado;

        }catch (RuntimeException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Cargo buscarPorId(Long id){
        return (id==null) ? null : em.find(Cargo.class, id);
    }

    @Override
    public List<Cargo> listarCargos() {
        List<Cargo> lista = em.createQuery("from Cargo", Cargo.class).getResultList();
        return lista;
    }
}

