package com.imooc.demo.service;

import com.imooc.demo.entity.Website;

import java.util.List;

// dao层即为真实的DB操作, 不考虑逻辑的判断和容错. 而是放到service层中.
public interface WebsiteService {
    List<Website> queryWebsite();
    Website queryWebsiteByUrl(String url);
    boolean insertWebsite(Website website);
    boolean updateWebsite(Website website);
    boolean deleteWebsite(String url);
}
