package hac.ex4.controllers;

import hac.ex4.database.Book;
import hac.ex4.database.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/api")
public class AdminApiController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping(value = "/add")
    public ResponseEntity addBook(@Valid @RequestBody Book book, BindingResult result) {
        // Spring validation according to User @Entity definition
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // you don’t necessarily need a full UI response
            // if the error is not normal behavior
        }
        book.setImage("bookImage.png");
        bookRepository.save(book);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public String updateBook(@PathVariable("id") final Long id, @Valid @RequestBody Book updatedBook, BindingResult result) {
        if (result.hasErrors()) {
            return "update-book";
        }

        Book book = bookRepository.findBookById(updatedBook.getId());
        if (book != null)
            bookRepository.save(updatedBook);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");

        return "index";
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") final Long id) {
        Book book = bookRepository.findBookById(id);  //.orElseThrow(()-> new IllegalArgumentException("invalid book id: " + id));
        bookRepository.delete(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
