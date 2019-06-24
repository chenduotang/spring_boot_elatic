package bdqn.springboot.elastic;

import bdqn.springboot.elastic.entity.Article;
import bdqn.springboot.elastic.entity.Book;
import bdqn.springboot.elastic.reponsitory.BookRsponsitrory;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private BookRsponsitrory bookRsponsitrory;

    @Autowired

    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void contextLoads() {
        //1、个体Es中索引保存文章(一个文档)
        Article article = new Article();
        article.setId(1);
        article.setAuthor("陈都铎");
        article.setTitle("西游记");
        article.setContect("是真的好看啊");
        //2、构建一个索引功能
        Index index = new Index.Builder(article).index("angugui").type("news").build();
        //3、执行
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行搜索
     */
    @Test
    public  void test(){
        String json="";
        Search search = new Search.Builder(json).addIndex("angugui").addType("news").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试springdata整合
     * ElasticsearchRepository的方式
     * @Document(indexName = "angugui",type = "book")实体类要加
     */
    @Test
    public void test02(){
        Book book = new Book();
        bookRsponsitrory.index(book);
        bookRsponsitrory.findByNameLike("西游记");
    }

    /**
     * 测试springdata整合
     * elasticsearchTemplate实现
     */
    @Test
    public void test03(){
        Book book = new Book();
        book.setId(12);
        book.setAuthor("陈多多");
        book.setBookName("西游记");
        IndexQuery indexQuery = new IndexQueryBuilder().withId(book.getBookName()).withObject(book).build();
        elasticsearchTemplate.index(indexQuery);
    }
}
