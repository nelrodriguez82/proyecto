/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Componente;
import entities.Entregable;
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
public class EntregableFacade extends AbstractFacade<Entregable> {

    @PersistenceContext(unitName = "AWESASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntregableFacade() {
        super(Entregable.class);
    }
    public List<Entregable> consultarEntregableComponente(Componente componente){
          List<Entregable> listaEntregable = new ArrayList<>();
        try {
          Query  query = em.createQuery("select e from Entregable e where e.idComponente.idComponente = :componente");
            query.setParameter("componente", componente.getIdComponente());
            listaEntregable = query.getResultList();
            return listaEntregable;
        } catch (Exception e) {
            System.out.println("Error el componente no existe");
            e.printStackTrace();
        }
        return null;
     }
}
