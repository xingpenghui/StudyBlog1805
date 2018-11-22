package com.qfedu.studyblog1805.mapper;

import com.qfedu.studyblog1805.domain.Blog;
import org.apache.ibatis.annotations.*;

import java.sql.Blob;
import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:08
 * 基于MyBatis  注解
 */
public interface BlogMapper {

    //新增
    @Insert("insert into t_blog(title,author,readnum,commentnum,content,createtime) values(#{title},#{author},#{readnum},#{commentnum},#{content},now())")
    @Options(useGeneratedKeys = true)
    int save(Blog blog);
    //分页查询
    @Select("select * from t_blog order by id desc limit #{index},#{count}")
    @ResultType(Blog.class)
    List<Blog> queryByPage(@Param("index")int index, @Param("count") int count);

    //查询是否存在
    @Select("select count(*) from t_blog where title=#{title}")
    @ResultType(Long.class)
    Long queryByTitle(String title);

}
