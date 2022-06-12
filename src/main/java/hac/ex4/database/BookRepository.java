package hac.ex4.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book books);

    void delete(Book book);

    List<Book> findAll();

    Book findBookById(Long id);

    Book findBookByName(String name);

    List<Book> findFirst5ByOrderByDiscountDesc();

}
