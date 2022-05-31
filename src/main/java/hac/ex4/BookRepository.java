package hac.ex4;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    void delete(Book book);

    void updateBook(Book book);

    List<Book> save(Book book);

    Book findBookById(Long id);


    List<Book> findAll();


    Book findBookByName(String name);
    List<Book> findFirst5ByOrderByDiscountDesc();

}
