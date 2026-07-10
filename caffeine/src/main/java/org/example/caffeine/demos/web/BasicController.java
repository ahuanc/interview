/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.caffeine.demos.web;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.example.caffeine.demos.config.CaffeineCacheConfig;
import org.example.caffeine.demos.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private Cache<String, Object> caffeineCache;

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {


        User hh = User.builder().name("hh").age(1).build();
        // 将用户信息存入缓存
        caffeineCache.put("user1",hh);

        // 获取数据
        User user1 = (User) caffeineCache.asMap().get("user1");

        System.out.println("输出caffeine中的缓存：" + user1);
        // 如果不存在则去数据库查询
        User user = userMapper.selectById(1);

        System.out.println("输出数据库中的数据：" + user);

        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = User.builder().name("hh").age(1).build();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

//    @ModelAttribute
//    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
//            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
//        user.setName("zhangsan");
//        user.setAge(18);
//    }
}
