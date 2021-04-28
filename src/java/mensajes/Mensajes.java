/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "mensajes")
@RequestScoped
public class Mensajes {

    private String mensaje;
    
    /**
     * Creates a new instance of Mensajes
     */
    public Mensajes() {
    }
    
    @PostConstruct
    public void init() {
        setMensaje("");
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
