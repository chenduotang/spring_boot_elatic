package bdqn.springboot.elastic.reponsitory;

import bdqn.springboot.elastic.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRsponsitrory extends ElasticsearchRepository<Book,Integer> {
    public List<Book> findByNameLike(String bookName);
}
