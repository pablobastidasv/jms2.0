package com.conexia.test.jms;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pbastidas
 */
@Model
public class IndexController {
    
    @Inject
    private MyJmsProvider provider;
    
    private String mensaje;
    private Integer cantidad;
    
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    public void send(){
        try {
            provider.enviarMensaje(cantidad, mensaje);
            
            logger.info("Finaliza el envío de la data.");
        } catch (Throwable ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al enviar el mensaje"));
            logger.error("", ex);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
