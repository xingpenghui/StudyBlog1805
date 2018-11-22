package com.qfedu.studyblog1805.app;

import com.qfedu.studyblog1805.spider.CnBlogsPipeline;
import com.qfedu.studyblog1805.spider.CnblogsProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:37
 */
@RestController
public class BlogSpiderController {
    @Autowired
    private CnBlogsPipeline pipeline;
    @Autowired
    private CnblogsProcess process;

    @GetMapping("blogspider")
    public String startSpider()
    {
        System.out.println("开始爬取……");
        new Spider(process).addUrl("https://www.cnblogs.com/").addPipeline(pipeline).thread(6).run();
        return "爬虫结束……";
    }
}
