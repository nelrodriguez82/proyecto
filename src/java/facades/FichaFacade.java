/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Entregable;
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
public class FichaFacade extends AbstractFacade<Ficha> {

    @PersistenceContext(unitName = "AWESASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichaFacade() {
        super(Ficha.class);
    }
    public List<Usuario> consultarFichaAprendiz(Ficha ficha){
    Query query=em.createNativeQuery("SELECT ficha.idFicha,usuario.idUsuario, usuario.tipoDocumento, usuario.documento, usuario.nombres, usuario.apellidos\n" +
"FROM ficha \n" +
"	inner JOIN matricula ON matricula.idFicha = ficha.idFicha \n" +
"	inner JOIN aprendiz ON matricula.idAprendiz = aprendiz.idAprendiz \n" +
"	inner JOIN usuario ON aprendiz.idAprendiz = usuario.idUsuario where ficha.idFicha="+ficha.getIdFicha()+"");
    List<Object[]> result=query.getResultList();
    List<Usuario> listaAprendices=new ArrayList<>();
    for(Object[] object : result) { 
        Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(object[1].toString()));

        usuario.setTipoDocumento(object[2].toString());
        usuario.setDocumento(object[3].toString());
        usuario.setNombres(object[4].toString());
        usuario.setApellidos(object[5].toString());
        listaAprendices.add(usuario);
   
    }
    return listaAprendices;
    
    }
    public List<Entregable> consultarEntregableFicha(Ficha ficha)
    {
        List<Entregable> listaEntregables=new ArrayList<>();
    
        try{
 Query query=em.createNativeQuery("SELECT entregable.idEntregable,entregable.nombre, entregable.descripcion, componente.nombreComponente, fichacomponente.idFicha FROM entregable LEFT JOIN componente ON entregable.idComponente = componente.idComponente LEFT JOIN fichacomponente ON fichacomponente.idComponente = componente.idComponente where fichacomponente.idFicha="+ficha.getIdFicha()+"");
    List<Object[]> result=query.getResultList();
    
    for(Object[] object : result) { 
        Entregable entregable = new Entregable();
                entregable.setIdEntregable(Integer.parseInt(object[0].toString()));

        entregable.setNombre(object[1].toString());
        entregable.setDescripcion(object[2].toString());
        
        listaEntregables.add(entregable);
   
    }
        }
        catch( Exception e){
            System.out.println("Error");
        }
    return listaEntregables;        
//Nuevo
        
               
               

    }
    public List<Grupotrabajo> consultarEntregables(Ficha ficha,Entregable entregable ){
    Query query=em.createNativeQuery("SELECT grupotrabajo.idGrupoTrabajo, grupotrabajo.nombreGrupo, grupotrabajo.idEntregable, entregable.idEntregable, componente.idComponente, fichacomponente.idFicha\n" +
"FROM grupotrabajo \n" +
"	LEFT JOIN entregable ON grupotrabajo.idEntregable = entregable.idEntregable \n" +
"	LEFT JOIN componente ON entregable.idComponente = componente.idComponente \n" +
"	LEFT JOIN fichacomponente ON fichacomponente.idComponente = componente.idComponente\n" +
"    where fichacomponente.idFicha="+ficha.getIdFicha()+" and entregable.idEntregable="+entregable.getIdEntregable()+"");
    List<Object[]> result=query.getResultList();
    List<Grupotrabajo> listaGrupos=new ArrayList<>();
    for(Object[] object : result) { 
        Grupotrabajo grupo = new Grupotrabajo();
                grupo.setIdGrupoTrabajo(Integer.parseInt(object[0].toString()));

        grupo.setNombreGrupo(object[1].toString());
        listaGrupos.add(grupo);
   
    }
    return listaGrupos;
    
    }
}

