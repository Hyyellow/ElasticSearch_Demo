package com.example.estest.entity.es;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @program: estest
 * @description: ES实体类
 * @author: Mr.Huang
 * @create: 2020-03-30 21:21
 **/
@Data
@Document(indexName = "blog", type = "blog",
        useServerConfiguration = true, createIndex = false)
public class ESBlog {
    @Id
    private Integer id;
    @Field(type = FieldType.Text, analyzer = "ik_max_work")
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_max_work")
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_max_work")
    private String content;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date updateTime;
}
