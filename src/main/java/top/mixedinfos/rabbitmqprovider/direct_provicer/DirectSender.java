package top.mixedinfos.rabbitmqprovider.direct_provicer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class DirectSender {
    @Value("${messageQueue.exchangeDirectName}")
    private String directExchangeName;
    @Value("${messageQueue.bindingDirectKey}")
    private String bindingDirectKey;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    private Integer i = 0;
    @Scheduled(cron = "0/5 * * * * ? ")
    public void directSender(){
        System.out.println("开始发送消息"+ i++);
        rabbitTemplate.convertAndSend(directExchangeName,bindingDirectKey,"这是一条direct消息"+i);
    }


}
