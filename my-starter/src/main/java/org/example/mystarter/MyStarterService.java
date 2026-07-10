package org.example.mystarter;

public class MyStarterService {

    private final MyStarterProperties properties;

    public MyStarterService(MyStarterProperties properties) {
        this.properties = properties;
    }

    public String sayHello() {
        return properties.getMessage();
    }
}
