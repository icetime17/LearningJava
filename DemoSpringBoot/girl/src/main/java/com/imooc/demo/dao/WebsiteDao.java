package com.imooc.demo.dao;

import com.imooc.demo.entity.Website;

import java.util.List;

import com.imooc.demo.entity.Website;

public interface WebsiteDao {
    List<Website> queryWebsite();
    Website queryWebsiteByUrl(String url);
    int insertWebsite(Website website);
    int updateWebsite(Website website);
    int deleteWebsite(String url);
}
