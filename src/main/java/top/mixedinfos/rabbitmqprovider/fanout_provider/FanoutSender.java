package top.mixedinfos.rabbitmqprovider.fanout_provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
public class FanoutSender {
    @Value("${messageQueue.exchangeFanoutName}")
    private String fanoutExchangeName;
    @Resource
    private AmqpTemplate rabbitTemplate;
    private Integer i = 0;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void messageFanoutSender(){
        i++;
        rabbitTemplate.convertAndSend(fanoutExchangeName,"myfanoutkey.message","这是一条message-fanout-message消息"+i);
        i++;
        rabbitTemplate.convertAndSend(fanoutExchangeName,"myfanoutkey.basketball","这是一条message-fanout-basketball消息"+i);
        i++;
        rabbitTemplate.convertAndSend(fanoutExchangeName,"myfanoutkey.play","这是一条message-fanout-play消息"+i);
    }
}
