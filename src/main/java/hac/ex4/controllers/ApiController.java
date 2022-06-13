package hac.ex4.controllers;

import hac.ex4.database.Book;
import hac.ex4.database.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/product/{id}")
    public Book getBook(@PathVariable Long id){
        return bookRepository.findBookById(id);
    }

    @GetMapping("/products")
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/top5products")
    public List<Book> top5() { return bookRepository.findFirst5ByOrderByDiscountDesc(); }
}
