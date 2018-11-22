package com.qfedu.studyblog1805.spider;

import us.codecraft.webmagic.Spider;

/**
 *@Author feri
 *@Date Created in 2018/11/22 16:31
 */
public class SpiderTest {
    public static void main(String[] args) {
        new Spider(new CnblogsProcess()).addUrl("https://www.cnblogs.com/").thread(3).run();
    }
}
