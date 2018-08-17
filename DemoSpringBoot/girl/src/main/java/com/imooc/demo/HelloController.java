package com.imooc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    /*
    // 此处的cupSize与application.yml中关联.
    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    // 在配置中使用变量.
    @Value("${content}")
    private String content;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
//        return "Hello Spring Boot. " + "cupSize : " + cupSize + " , " + "age : " + age;
        return "Hello Spring Boot. " + content;
    }
    */


    // 直接使用Autowired与application.yml, GirlProperties相关联
    // 自动装配
    @Autowired
    private GirlProperties girlProperties;

    // 对应http://0.0.0.0:8081/hello/chris这种请求方式
    // 而http://0.0.0.0:8081/hello?name=chris这种请求方式, 可以采用注解@RequestParam
    // public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "somebody") String name) {
    @RequestMapping(value = {"/hello/{name}", "/hi/{name}"}, method = RequestMethod.GET)
    // @GetMapping(value = {"/hello/{name}", "/hi/{name}"})
    public String sayHello(@PathVariable("name") String name) {
        return "Hello Spring Boot. " +
                "cupSize : " + girlProperties.getCupSize() + " , " +
                "age : " + girlProperties.getAge() + " . " +
                "name : " + name;
    }

    @RequestMapping(value = {"/world", "/wo"}, method = RequestMethod.POST)
    // @PostMapping(value = {"/world", "/wo"})
    public String sayWorld() {
        return "World Spring Boot. " + "cupSize : " + girlProperties.getCupSize() + " , " + "age : " + girlProperties.getAge();
    }

    @RequestMapping(value = {"/mybatis/{name}", "/batis/{name}"}, method = RequestMethod.GET)
    public String mybatis(@PathVariable("name") String name) {




        return "Hello mybatis " + name;
    }
}
