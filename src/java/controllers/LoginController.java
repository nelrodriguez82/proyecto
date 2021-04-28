/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Usuario;
import facades.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mensajes.Mensajes;

/**
 *
 * @author usuario
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
     private String contrasena;
    private String contrasenaV;
    @Inject
    private Mensajes mensaje;
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario;
private Usuario usuarioLogeado;
    @PostConstruct
    public void init() {
        setContrasena("");
        setContrasenaV("");
        usuario=new Usuario();
        
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasenaV() {
        return contrasenaV;
    }

    public void setContrasenaV(String contrasenaV) {
        this.contrasenaV = contrasenaV;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void salir() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public String ingresar() {
         usuarioLogeado=new Usuario();
        FacesContext fc = FacesContext.getCurrentInstance();
        usuarioLogeado = usuarioFacade.login(usuario);
        if (usuarioLogeado != null) {
             fc.getExternalContext().getSessionMap().put("datosPersona", usuarioLogeado);
            if (usuarioLogeado.getCoordinador()!=null) {
               return "/ficha/List?faces-redirect=true";
               
            }
             if (usuarioLogeado.getInstructor()!=null) {
               return "";
               
            }
              if (usuarioLogeado.getAprendiz()!=null) {
               return "";
               
            }
           
        } else {
            mensaje.setMensaje("Mensaje('Error!', 'El usuario y/o contraseña no son correctos', 'error');");
        }
        return "";
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
}
