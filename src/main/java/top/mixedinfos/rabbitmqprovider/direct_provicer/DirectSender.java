package top.mixedinfos.rabbitmqprovider.direct_provicer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
public class DirectSender {
    @Value("${messageQueue.exchangeDirectName}")
    private String directExchangeName;

    @Value("${messageQueue.bindingDirectKey}")
    private String bindingDirectKey;

    @Resource
    private AmqpTemplate rabbitTemplate;

    @Resource
    private RabbitTemplate rabbitTemplates;

    private Integer i = 0;


    @Scheduled(cron = "0/5 * * * * ? ")
    public void directSender(){
        System.out.println("开始发送消息"+ i++);
        rabbitTemplates.convertAndSend(directExchangeName,bindingDirectKey,"这是一条direct消息"+i);
    }


}
