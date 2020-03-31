package com.example.estest.repository.es;

import com.example.estest.entity.es.ESBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESBlogRepository extends ElasticsearchRepository<ESBlog,Integer> {
}
