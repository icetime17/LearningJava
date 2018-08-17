package com.imooc.demo.dao;

import com.imooc.demo.entity.Website;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteDaoTest {

    @Autowired
    private WebsiteDao websiteDao;

    @Test
    public void queryWebsite() {
        List<Website> websites = websiteDao.queryWebsite();
        for (Website website : websites) {
            System.out.println(website.getId() + " " + website.getName() + " " + website.getUrl() + " " + website.getAlexa() + " " + website.getCountry());
        }

        assertEquals(5, websites.size());
    }

    @Test
    public void queryWebsiteByUrl() {
        Website website = websiteDao.queryWebsiteByUrl("https://www.google.cm/");
        System.out.println(website.getId() + " " + website.getName() + " " + website.getUrl() + " " + website.getAlexa() + " " + website.getCountry());
        assertEquals("Google", website.getName());
    }

    @Test
    @Ignore
    public void insertWebsite() {
        List<Website> websites = websiteDao.queryWebsite();
        assertEquals(5, websites.size());

        Website website = new Website();
        website.setName("大众点评");
        website.setUrl("https://www.dianping.com/");
        website.setAlexa(100);
        website.setCountry("CN");
        websiteDao.insertWebsite(website);

        websites = websiteDao.queryWebsite();
        assertEquals(6, websites.size());
    }

    @Test
    @Ignore
    public void updateWebsite() {
        Website website = new Website();
        website.setUrl("http://www.runoob.com");
        website.setCountry("CN");
        websiteDao.updateWebsite(website);
    }

    @Test
    @Ignore
    public void deleteWebsite() {
        websiteDao.deleteWebsite("http://www.runoob.com");
    }
}