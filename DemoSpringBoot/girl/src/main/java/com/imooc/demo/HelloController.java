package com.imooc.demo;

import com.imooc.demo.dao.WebsiteDao;
import com.imooc.demo.entity.Website;
import com.imooc.demo.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.W3CDomHandler;
import java.util.List;

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

    @Autowired
    private WebsiteDao websiteDao;

    @Autowired
    private WebsiteService websiteService;


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

    @RequestMapping(value = {"/mybatis/{action}"}, method = RequestMethod.GET)
    public String mybatis(@PathVariable("action") String action) {
        if (action.equals("size")) {
//            List<Website> websites = websiteDao.queryWebsite();
            List<Website> websites = websiteService.queryWebsite();
            for (Website website : websites) {
                System.out.println(website.getId() + " " + website.getName() + " " + website.getUrl() + " " + website.getAlexa() + " " + website.getCountry());
            }
            return "Hello mybatis " + websites.size();
        } else if (action.equals("query")) {
//            Website website = websiteDao.queryWebsiteByUrl("https://www.dianping.com/");
            Website website = websiteService.queryWebsiteByUrl("https://www.dianping.com/");
            System.out.println(website.getId() + " " + website.getName() + " " + website.getUrl() + " " + website.getAlexa() + " " + website.getCountry());
            return "Hello mybatis " + website.getName() + " " + website.getUrl();
        } else {
            return "Hello mybatis.";
        }
    }
}
