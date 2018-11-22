package com.qfedu.studyblog1805.service.impl;

import com.qfedu.studyblog1805.domain.Blog;
import com.qfedu.studyblog1805.mapper.BlogMapper;
import com.qfedu.studyblog1805.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:13
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;


    @Override
    public int save(Blog blog) {
        return blogMapper.save(blog);
    }

    @Override
    public List<Blog> queryByPage(int page, int count) {
        int index=0;
        if(page>0){
            index=(page-1)*count;
        }

        return blogMapper.queryByPage(index,count);
    }

    //存在就返回flase 不存在就返回true
    @Override
    public boolean checkIsHave(String title) {
        return blogMapper.queryByTitle(title)==null?true:false;
    }
}
