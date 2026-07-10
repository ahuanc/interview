package org.example.demo;

import org.example.mystarter.MyStarterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final MyStarterService myStarterService;

    public DemoController(MyStarterService myStarterService) {
        this.myStarterService = myStarterService;
    }

    @GetMapping("/hello")
    public String hello() {
        return myStarterService.sayHello();
    }
}
