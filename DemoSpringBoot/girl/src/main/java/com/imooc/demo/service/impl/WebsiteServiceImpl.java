package com.imooc.demo.service.impl;

import com.imooc.demo.dao.WebsiteDao;
import com.imooc.demo.entity.Website;
import com.imooc.demo.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// dao层即为真实的DB操作, 不考虑逻辑的判断和容错. 而是放到service层中.
// 在service层中做逻辑判断, 抛出异常等.
@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteDao websiteDao;

    @Override
    public List<Website> queryWebsite() {
        return websiteDao.queryWebsite();
    }

    @Override
    public Website queryWebsiteByUrl(String url) {
        return websiteDao.queryWebsiteByUrl(url);
    }

    @Transactional
    @Override
    public boolean insertWebsite(Website website) {
        if (website.getUrl().length() == 0) {
            throw new RuntimeException("Fail to insert item of Website without URL.");
        }

        try {
            int ret = websiteDao.insertWebsite(website);
            if (ret > 0) {
                return true;
            } else {
                throw new RuntimeException("Fail to insert item of Website.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Fail to insert item of Website: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean updateWebsite(Website website) {
        if (website.getUrl().length() == 0) {
            throw new RuntimeException("Fail to insert item of Website without URL.");
        }

        try {
            int ret = websiteDao.updateWebsite(website);
            if (ret > 0) {
                return true;
            } else {
                throw new RuntimeException("Fail to insert item of Website.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Fail to insert item of Website: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteWebsite(String url) {
        if (url.length() == 0) {
            throw new RuntimeException("Fail to insert item of Website without URL.");
        }

        try {
            int ret = websiteDao.deleteWebsite("http://www.runoob.com");
            if (ret > 0) {
                return true;
            } else {
                throw new RuntimeException("Fail to insert item of Website.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Fail to insert item of Website: " + e.getMessage());
        }
    }
}
