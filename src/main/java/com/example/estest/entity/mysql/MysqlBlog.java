package com.example.estest.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: estest
 * @description: MysqlBlog实体类
 * @author: Mr.Huang
 * @create: 2020-03-30 20:54
 **/
@Data
@Entity
@Table(name = "blog")
public class MysqlBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    @Column(columnDefinition = "medinumtext")
    private String content;
    private Date createTime;
    private Date updateTime;
}
