package org.example.serializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserDeSerializer implements Deserializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public User deserialize(String topic, byte[] data) {
        ByteBuffer allocate = ByteBuffer.allocate(data.length);
        allocate.put(data);
        allocate.flip();
        long id = allocate.getLong();
        int nameLength = allocate.getInt();
        byte[] nameByteArray = allocate.get(data, 12, nameLength).array();
        String name = new String(nameByteArray, StandardCharsets.UTF_8).trim();
        System.out.println(name);
        int age = allocate.getInt();
        return User.builder().id(id)
                .name(name)
                .age(age)
                .build();
    }

    @Override
    public void close() {

    }
}
