package top.mixedinfos.rabbitmqprovider.topic_provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class TopicSender {
    @Value("${messageQueue.exchangeTopicName}")
    private String topicExchangeName;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    private Integer i = 0;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void sendSchedule(){
        messageTopicSender();
        palyTopicSender();
        basketballTopicSender();

    }


    public void messageTopicSender(){
        System.out.println("开始发送topic消息"+ i++);
        rabbitTemplate.convertAndSend(topicExchangeName,"mytopickey.message","这是一条message-topic消息"+i);
    }


    public void palyTopicSender(){
        System.out.println("开始发送topic消息"+ i++);
        rabbitTemplate.convertAndSend(topicExchangeName,"mytopickey.play","这是一条direct-topic消息"+i);
    }

    public void basketballTopicSender(){
        System.out.println("开始发送topic消息"+ i++);
        rabbitTemplate.convertAndSend(topicExchangeName,"mytopickey.basketball","这是一条basketball-topic消息"+i);
    }

}
