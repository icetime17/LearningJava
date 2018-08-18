package com.imooc.demo.dao;

import com.imooc.demo.entity.Website;

import java.util.List;

/*
* 该DAO仅仅是interface, 并未实现方法.
* 通过WebsiteDao.xml进行了mapper.
* 调用websiteDao的接口, 如websiteDao.queryWebsite();
* 即对应到WebsiteDao.xml中的db操作.
* */

// dao层即为真实的DB操作, 不考虑逻辑的判断和容错. 而是放到service层中.
public interface WebsiteDao {
    List<Website> queryWebsite();
    Website queryWebsiteByUrl(String url);
    int insertWebsite(Website website);
    int updateWebsite(Website website);
    int deleteWebsite(String url);
}
