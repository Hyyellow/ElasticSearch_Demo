package com.example.estest;


import com.example.estest.entity.es.ESBlog;
import com.example.estest.repository.es.ESBlogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstestApplicationTests {
    @Autowired
    ESBlogRepository blogRepository;

    @Test
    public void testES() {
        Iterable<ESBlog> all = blogRepository.findAll();
        Iterator<ESBlog> iterator = all.iterator();
        if (iterator.hasNext()) {
            ESBlog next = iterator.next();
            System.out.println("------" + next.getTitle());
        }
    }

}
