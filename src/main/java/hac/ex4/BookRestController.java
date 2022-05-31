package hac.ex4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class BookRestController {

    @Autowired
    private BookRepository repository;

    private BookRepository getRepository(){
        return repository;
    }

    @PostMapping(value = "/update/{id}")
    public String updateBook(@PathVariable("id") final Long id, @Valid Book bk, BindingResult result, Model model) {
        if (result.hasErrors()) {
            bk.setId(id);
            return "update-book";
        }

        Book b = getRepository().findBookById(bk.getId());
        if (b != null)
            getRepository().updateBook(bk);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
        model.addAttribute("books",getRepository().findAll());
        return "index";
    }

    @PostMapping("/addBook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {

    // Spring validation according to User @Entity definition
        if (result.hasErrors()) {
            return "some-error-page"; // you don’t necessarily need a full UI response
    // if the error is not normal behavior
        }
        getRepository().save(book);

    // select * from Books
        model.addAttribute("books", getRepository().findAll());
        return "show-books";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") final Long id, Model model) {
        Book book = getRepository().findBookById(id);  //.orElseThrow(()-> new IllegalArgumentException("invalid book id: " + id));
        getRepository().delete(book);
        model.addAttribute("book", getRepository().findAll());
        return "index";
    }
}
