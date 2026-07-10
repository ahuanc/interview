package org.example.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserSerializer implements Serializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, User data) {
        // 将User对象转换为字节数组
        Long id = data.getId();
        String name = data.getName();
        int age = data.getAge();
        int cap = 8 + name.getBytes(StandardCharsets.UTF_8).length + 4;
        ByteBuffer byteBuffer = ByteBuffer.allocate(cap);
        byteBuffer.putLong(id);
        byteBuffer.putInt(name.getBytes(StandardCharsets.UTF_8).length);
        byteBuffer.put(name.getBytes(StandardCharsets.UTF_8));
        byteBuffer.putInt(age);
        return byteBuffer.array();
    }

    @Override
    public void close() {

    }
}
