package org.example.serializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.callback.ProducerCallback;

import java.util.Properties;

public class UserProducer {

    private static final String HOST = "localhost:9092";

    public static void main( String[] args )
    {
        // 1. 指定生产者的配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "org.example.intercepter.MyProducerInterceptor");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.example.serializer.UserSerializer");

        // 2. 使用配置初始化 Kafka 生产者
        Producer<String, User> producer = new KafkaProducer<>(properties);

        User user = User.builder().id(1L)
                .name("hehao")
                .age(12)
                .build();
        ProducerRecord<String, User> record =
                new ProducerRecord<>("CustomerSerializer", "Precision Product", user);
        try {
            System.out.println(record);
            // 3.使用send发送异步消息
            producer.send(record, new ProducerCallback());
            System.out.println("消息发送完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }

}
