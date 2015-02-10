package com.conexia.test.jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pbastidas
 */
@MessageDriven(mappedName = "java:/jms/queue/AuditoriaProgramacionQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "/jms/queue/AuditoriaProgramacionQueue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")}
)
public class JmsConsumer implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    @Override
    public void onMessage(Message inMessage) {

        try {
            
            Thread.sleep(1000l);
            
            if (inMessage instanceof TextMessage) {
                TextMessage msg = (TextMessage) inMessage;
                logger.info("MESSAGE BEAN: Message received: "
                        + msg.getText());
            } else {
                logger.warn("Message of wrong type: "
                        + inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.error("", e);
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            logger.error("", te);
        }
    }

}
