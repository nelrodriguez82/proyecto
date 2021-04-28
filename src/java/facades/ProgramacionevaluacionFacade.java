/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Ficha;
import entities.Programacionevaluacion;
import entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
@Stateless
public class ProgramacionevaluacionFacade extends AbstractFacade<Programacionevaluacion> {

    @PersistenceContext(unitName = "AWESASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramacionevaluacionFacade() {
        super(Programacionevaluacion.class);
    }
    public List<Programacionevaluacion> listaEvaluacionFicha(Ficha ficha){
        List<Programacionevaluacion> listaEvaluacionFicha = new ArrayList<>();
        try {
          Query  query = em.createQuery("select pe from Programacionevaluacion pe where pe.idFicha.idFicha = :ficha");
            query.setParameter("ficha", ficha.getIdFicha());
            listaEvaluacionFicha = query.getResultList();
            return listaEvaluacionFicha;
        } catch (Exception e) {
            System.out.println("Error la ficha no existe");
            e.printStackTrace();
        }
        return null;
    }
      public List<Programacionevaluacion> listaEvaluacionInstructor(Usuario instructor){
        List<Programacionevaluacion> listaEvaluacionInstructor = new ArrayList<>();
        try {
          Query  query = em.createQuery("select pe from Programacionevaluacion pe where pe.idJurado.idInstructor = :instructor");
            query.setParameter("instructor", instructor.getIdUsuario());
            listaEvaluacionInstructor = query.getResultList();
            return listaEvaluacionInstructor;
        } catch (Exception e) {
            System.out.println("Error el instructor no existe");
            e.printStackTrace();
        }
        return null;
    }

}
