package com.example;

import com.example.bean.Article;
import com.example.bean.Book;
import com.example.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

@SpringBootTest
class Springboot11ElasticApplicationTests {

    @Autowired
    JestClient jestClient;
//    private static final JestClient jestClient;
//    static {
//        JestClientFactory factory = new JestClientFactory();
//        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://192.168.0.102:9200").multiThreaded(true).build());
//        jestClient = factory.getObject();
//    }

    @Autowired
    BookRepository bookRepository;

    @Test
    public void test02(){
//		Book book = new Book();
//		book.setId(1);
//		book.setBookName("西游记");
//		book.setAuthor("吴承恩");
//		bookRepository.index(book);


        for (Book book : bookRepository.findByBookNameLike("游")) {
            System.out.println(book);
        };
    }

    @Test
    public void contextLoads() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhangsan");
        article.setContent("Hello World");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("atguigu").type("news").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试搜索
    @Test
    public void search(){
        //查询表达式
        String json ="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
