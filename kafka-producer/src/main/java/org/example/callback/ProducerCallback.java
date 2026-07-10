package org.example.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * kafka发送消息的回调类
 */
public class ProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null){
            //此处可以将失败的消息记录下来或者抛出一场，可以是入库然后通过定时任务进行重试发送
            exception.printStackTrace();
        }else {
            System.out.println("发送成功：partition="+metadata.partition());
        }
    }
}
