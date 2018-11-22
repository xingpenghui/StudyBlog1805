package com.qfedu.studyblog1805.spider;
import com.qfedu.studyblog1805.domain.Blog;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/11/22 14:46
 */
@Service
public class CnblogsProcess implements PageProcessor {
    //1、创建站点信息
    private Site site=Site.me().setRetryTimes(3).setTimeOut(3000);

    //核心 处理抓取的到网页
    @Override
    public void process(Page page) {
        //System.err.println("网页内容："+page.getHtml().get());
        //基于Jsoup进行网页内容提取（XPath）
        //博客的标题
        //提取标签内容：text() ---->innerText
        List<String> titles=page.getHtml().xpath("div[@class=\"post_item\"]/div[@class=\"post_item_body\"]/h3/a[@class=\"titlelnk\"]/text()").all();
        //博客的原文链接
        //提取标签的属性的值：@属性名
        List<String> links=page.getHtml().xpath("div[@class=\"post_item\"]/div[@class=\"post_item_body\"]/h3/a[@class=\"titlelnk\"]/@href").all();
        //博客的作者
        List<String> authors=page.getHtml().xpath("div[@class='post_item_foot']/a[@class='lightblue']/text()").all();
//        List<String> contents=page.getHtml().xpath("p[@class='post_item_summary']/").all();
        //评论
        List<String> comments=page.getHtml().xpath("div[@class='post_item_foot']/span[@class='article_comment']/a/text()").all();
        List<String> views=page.getHtml().xpath("div[@class='post_item_foot']/span[@class='article_view']/a/text()").all();
        List<Blog> blogs=new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            Blog blog=new Blog();
            blog.setTitle(titles.get(i));
            blog.setAuthor(authors.get(i));
            String s=comments.get(i);
            blog.setCommentnum(Integer.parseInt(s.substring(s.indexOf('(')+1,s.length()-1)));
            blog.setReadnum(Integer.parseInt(views.get(i).substring(views.get(i).indexOf('(')+1,views.get(i).length()-1)));
           blog.setContent("");
           blog.setBlogurl(links.get(i));
           blogs.add(blog);
        }
        //将提取的内容传递到指定的处理器中
        page.putField("blogs",blogs);
        //只有主页才需要生成分页数据
        if(page.getUrl().get().equals("https://www.cnblogs.com/")) {
            //分页抓取

            //获取总共多少页
            List<String> targetTotal = page.getHtml().xpath("div[@id='paging_block']/div/a/text()").all();
            int total = Integer.parseInt(targetTotal.get(targetTotal.size() - 2));
            //获取分页的url路径
            List<String> targetLinks = page.getHtml().xpath("div[@id='paging_block']/div/a/@href").all();
            String pageurl = targetLinks.get(targetLinks.size() - 2);
            //循环生成所有分页的url
            List<String> nextUrl = new ArrayList<String>();
            for (int i = 2; i <= total; i++) {
                nextUrl.add("https://www.cnblogs.com/sitehome/p/" + i);
            }
            //继续抓取
            page.addTargetRequests(nextUrl);
        }
       // System.err.println(nextUrl);
    }
    @Override
    public Site getSite() {
        return site;
    }
}
