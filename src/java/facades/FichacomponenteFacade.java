/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Ficha;
import entities.Fichacomponente;
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
public class FichacomponenteFacade extends AbstractFacade<Fichacomponente> {

    @PersistenceContext(unitName = "AWESASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichacomponenteFacade() {
        super(Fichacomponente.class);
    }
    public List<Fichacomponente> listaModulosFicha(Ficha ficha){
        List<Fichacomponente> listaModuloFicha = new ArrayList<>();
        try {
          Query  query = em.createQuery("select mf from Fichacomponente mf where mf.idFicha.idFicha = :ficha");
            query.setParameter("ficha", ficha.getIdFicha());
            listaModuloFicha = query.getResultList();
            return listaModuloFicha;
        } catch (Exception e) {
            System.out.println("Error la ficha no existe");
            e.printStackTrace();
        }
        return null;
    }
    public List<Fichacomponente> listaModulosInstructor(Usuario instructor){
        List<Fichacomponente> listaModuloInstructor = new ArrayList<>();
        try {
          Query  query = em.createQuery("select mf from Fichacomponente mf where mf.idInstructor.idInstructor = :instructor");
            query.setParameter("instructor", instructor.getIdUsuario());
            listaModuloInstructor = query.getResultList();
            return listaModuloInstructor;
        } catch (Exception e) {
            System.out.println("Error la ficha no existe");
            e.printStackTrace();
        }
        return null;
    }
}
