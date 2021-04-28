/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Ficha;
import entities.Grupotrabajo;
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
public class GrupotrabajoFacade extends AbstractFacade<Grupotrabajo> {

    @PersistenceContext(unitName = "AWESASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupotrabajoFacade() {
        super(Grupotrabajo.class);
    }
    public List<Usuario> consultarAprendicesGrupo(Grupotrabajo grupoTrabajo){
    Query query=em.createNativeQuery("SELECT usuario.idUsuario, usuario.tipoDocumento, usuario.documento, usuario.nombres, usuario.apellidos FROM grupotrabajo \n" +
"	inner JOIN grupoaprendiz ON grupoaprendiz.idGrupo = grupotrabajo.idGrupoTrabajo \n" +
"	inner JOIN aprendiz ON grupoaprendiz.idAprendiz = aprendiz.idAprendiz\n" +
"	inner JOIN usuario ON aprendiz.idAprendiz = usuario.idUsuario where grupotrabajo.idGrupoTrabajo="+grupoTrabajo.getIdGrupoTrabajo()+"");
    List<Object[]> result=query.getResultList();
    List<Usuario> listaAprendices=new ArrayList<>();
    for(Object[] object : result) { 
        Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(object[0].toString()));

        usuario.setTipoDocumento(object[1].toString());
        usuario.setDocumento(object[2].toString());
        usuario.setNombres(object[3].toString());
        usuario.setApellidos(object[4].toString());
        listaAprendices.add(usuario);
   
    }
    return listaAprendices;
    
    }
}
