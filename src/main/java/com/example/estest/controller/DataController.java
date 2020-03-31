package com.example.estest.controller;

import java.util.HashMap;

import java.util.List;

import com.example.estest.entity.es.ESBlog;
import com.example.estest.entity.mysql.MysqlBlog;
import com.example.estest.repository.es.ESBlogRepository;
import com.example.estest.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: estest
 * @description: 数据控制层
 * @author: Mr.Huang
 * @create: 2020-03-31 07:35
 **/
@RestController
public class DataController {
    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @Autowired
    ESBlogRepository esBlogRepository;

    @GetMapping("/blogs")
    public Object blog() {
        List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryList();
        return mysqlBlogs;
    }

    @PostMapping
    public Object search(@RequestBody Param param) {
        HashMap<String, Object> map = new HashMap<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String type = param.getType();
        if (type.equalsIgnoreCase("mysql")) {
            // mysql
            List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryBlogs(param.getKeyword());
            map.put("list", mysqlBlogs);
        } else if (type.equalsIgnoreCase("es")) {
            // es
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title",param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content",param.getKeyword()));
            String s = builder.toString();
            System.out.println(s);
            Page<ESBlog> search = (Page<ESBlog>) esBlogRepository.search(builder);
            List<ESBlog> content = search.getContent();
            map.put("list",content);
        } else {
            return "sorry";
        }
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        map.put("duration",totalTimeMillis);
        return map;
    }

    @Data
    public static class Param {
        private String type;
        private String keyword;
    }
}
