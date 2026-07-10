package org.example.intercepter;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 生产者拦截器
 */

public class MyProducerInterceptor implements ProducerInterceptor {


    /**
     * 发送消息的时候触发
     * @param record the record from client or the record returned by the previous interceptor in the chain of interceptors.
     * @return
     */
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
//        String value = (String)record.value();
        record.toString();
        return record;
    }

    // 收到服务端相应的时候触发
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("收到服务端相应的时候触发-----");
    }

    // 连接关闭时候触发
    @Override
    public void close() {
        System.out.println("连接关闭咯-----");
    }

    // 整理配置项
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
