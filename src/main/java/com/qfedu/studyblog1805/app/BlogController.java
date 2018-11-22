package com.qfedu.studyblog1805.app;

import com.qfedu.studyblog1805.domain.Blog;
import com.qfedu.studyblog1805.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:15
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    //查询
    @GetMapping("blogpage")
    public List<Blog> queryByPage(int page,int count){
        return blogService.queryByPage(page,count);
    }
}
