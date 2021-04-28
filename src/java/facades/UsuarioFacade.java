/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "AWESASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario login(Usuario login) {
        try {
          Query  query = em.createQuery("select u from Usuario u where u.documento = :doc and u.clave = :clave");
            query.setParameter("doc", login.getDocumento());
            query.setParameter("clave", login.getClave());
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en el modelo UsuarioFacade.login");
            e.printStackTrace();
        }
        return null;
    }
}
