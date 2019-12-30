package top.mixedinfos.rabbitmqprovider.MessageConfirmer;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 对发送消息的确认
 */
@Service("messageConfirmEr")
@Component
public class MessageConfirmEr implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
        //指定 ReturnCallback
        rabbitTemplate.setReturnCallback(this);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause){
        System.out.println("confirm--:correlationData:"+correlationData+",ack:"+ack+",cause:"+cause);
    }

    /**
     * 未成功到达队列的时候回调
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String msgId = "";
        if (message.getMessageProperties().getCorrelationId() != null) {
            msgId = new String(message.getMessageProperties().getCorrelationId());
        }
        System.out.println("return--message: msgId:" + msgId + ",msgBody:" + new String(message.getBody())
                + ",replyCode:" + replyCode + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:"
                + routingKey);
    }
}
