package com.qfedu.studyblog1805.spider;

import com.qfedu.studyblog1805.domain.Blog;
import com.qfedu.studyblog1805.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:34
 * 自定义WebMagic的提取数据的处理器
 */
@Service
public class CnBlogsPipeline implements Pipeline {
    @Autowired
    private BlogService blogService;
    @Override
    public void process(ResultItems resultItems, Task task) {
      List<Blog> blogs= resultItems.get("blogs");
      for(Blog b:blogs){
          if(blogService.checkIsHave(b.getTitle())) {
              System.out.println("新增：" + blogService.save(b));
          }
      }
    }
}
