package ru.itvitality.sbrf.cu.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.service.BookService;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController( BookService bookService ) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books")
    public String books( ModelMap modelMap ) {
        modelMap.addAttribute( "books", bookService.getBookDescriptions() );
        return "books";
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public String book( @PathVariable("id") Long id, ModelMap modelMap ) {
        modelMap.addAttribute( "book", bookService.get( id ) );
        return "book";
    }

    @RequestMapping(value = "/book/edit", method = RequestMethod.POST)
    public String bookEdit( @ModelAttribute("book") Book book, ModelMap modelMap ) {
        bookService.update( book );
        modelMap.addAttribute( "book", bookService.get( book.getId() ) );
        return "book";
    }

    @RequestMapping(value = "/initDb")
    public String initDd() {
        Book book = new Book( "978-5-4461-0474-1", "Джошуа Блох", "Effective Java" );

        bookService.add( book );

        book = new Book( "978-5-4461-0474-1", "Джошуа Блох", "Effective Java" );
        bookService.add( book );

        book = new Book( "978-5-4461-0474-1", "Джошуа Блох", "Effective Java" );
        bookService.add( book );

        book = new Book( "978-5-4461-0321-1", "Кей Хорстман", "Java. Библиотека профессионала" );
        bookService.add( book );
        book = new Book( "978-5-4461-0321-1", "Кей Хорстман", "Java. Библиотека профессионала" );
        bookService.add( book );

        return "index";
    }
}
