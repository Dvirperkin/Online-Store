package hac.ex4;

import hac.ex4.database.Book;
import hac.ex4.database.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class RestAPIController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/hi")
    public String hi() {

        System.out.println("hi");

        return "show-books";
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity addBook(@Valid Book book, BindingResult result) {

        System.out.println("addBook");
//        // Spring validation according to User @Entity definition
//        if (result.hasErrors()) {
//            return "some-error-page"; // you don’t necessarily need a full UI response
//            // if the error is not normal behavior
//        }
        //bookRepository.save(book);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public String updateBook(@PathVariable("id") final Long id, @Valid Book bk, BindingResult result, Model model) {
        if (result.hasErrors()) {
            bk.setId(id);
            return "update-book";
        }

//        Book b = bookRepository.findBookById(bk.getId());
//        if (b != null)
//            bookRepository.updateBook(bk);
//        else
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
//        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") final Long id, Model model) {
//        Book book = bookRepository.findBookById(id);  //.orElseThrow(()-> new IllegalArgumentException("invalid book id: " + id));
//        bookRepository.delete(book);
//        model.addAttribute("book", bookRepository.findAll());
        return "index";
    }
}
