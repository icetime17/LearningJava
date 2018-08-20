package com.imooc.demo.web;

import com.google.gson.Gson;
import com.imooc.demo.entity.Website;
import com.imooc.demo.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// 指定根路由
@RequestMapping("/website")
public class WebsiteController {

    private Gson gson = new Gson();

    private Map<String, Object> jsonResult = new HashMap<>();

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public String queryWebsite() {
        jsonResult.put("code", 0);
        jsonResult.put("action", "this is action queryWebsite.");

        String jsonString = gson.toJson(jsonResult);
        return jsonString;
    }

    @RequestMapping(value = "size", method = RequestMethod.GET)
    public String queryWebsiteSize() {
        List<Website> websites = websiteService.queryWebsite();

        jsonResult.put("size", websites.size());

        jsonResult.put("code", 0);
        jsonResult.put("action", "this is action queryWebsiteSize.");

        String jsonString = gson.toJson(jsonResult);
        return jsonString;
    }
}
