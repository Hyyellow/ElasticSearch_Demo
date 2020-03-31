package com.example.estest.controller;

/**
 * @program: estest
 * @description: Index控制层
 * @author: Mr.Huang
 * @create: 2020-03-30 21:02
 **/

import com.example.estest.entity.mysql.MysqlBlog;
import com.example.estest.repository.mysql.MysqlBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MysqlBlogRepository mysqlBlogRepository;

    @RequestMapping("/")
    public String index() {
        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        System.out.println(all.size());
        return "index.html";
    }
}
