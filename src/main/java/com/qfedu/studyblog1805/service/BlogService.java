package com.qfedu.studyblog1805.service;

import com.qfedu.studyblog1805.domain.Blog;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:12
 */
public interface BlogService {
    //新增
    int save(Blog blog);
    //查询分页
    List<Blog> queryByPage(int page, int count);
    //验证是否存在
    boolean checkIsHave(String title);
}
