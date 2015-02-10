package com.conexia.test.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 *
 * @author pbastidas
 */
public class MyJmsProvider {

    @Inject
    private JMSContext context;

    @Resource(name = "java:/jms/queue/AuditoriaProgramacionQueue")
    private Queue answerQueue;

    public void enviarMensaje(Integer cantidad, String msg) throws JMSException {
        for (int i = 0; i < cantidad; i++) {
            context.createProducer().send(answerQueue, msg + " " + i);
        }
    }
}
