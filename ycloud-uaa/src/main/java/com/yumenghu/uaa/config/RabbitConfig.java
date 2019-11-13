package com.yumenghu.uaa.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@program: ycloud
 *@description: mq配置
 *@author: yu meng hu
 *@create: 2019-11-12 19:40
 */
@Configuration
public class RabbitConfig {

  public static final String queueName = "ycloud";

  @Bean
  public Queue queue(){
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange("ycloud-exchange");
  }

  /**
   * 路由键只接受完全匹配 ycloud 的队列接受者可以收到消息
   * @param queue
   * @param exchange
   * @return
   */
  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(queueName);
  }
}