package javaee.hw8.demo.controller;

import javaee.hw8.demo.entity.AuthorEntity;
import javaee.hw8.demo.entity.BookEntity;
import javaee.hw8.demo.entity.ClientEntity;
import javaee.hw8.demo.service.AuthorService;
import javaee.hw8.demo.service.BookService;
import javaee.hw8.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ClientService clientService;

    ClientEntity authClient;

    public void setTotalPage(Model model,
                             Page page) {

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    @GetMapping("/")
    public String main(Model model,
                       Pageable pageable) {

        model.addAttribute("books", bookService.findAllBooks(pageable));
        model.addAttribute("authors", authorService.findAllAuthor(pageable));
        return "main";
    }

    @GetMapping("/authors")
    public String authorsGet(@PageableDefault(size = 6) Pageable pageable,
                             Model model) {

        Page<AuthorEntity> page = authorService.findAllAuthor(pageable);
        model.addAttribute("authorName", new String("have name----"));
        model.addAttribute("isbnName", new String("have name----"));
        model.addAttribute("page", page);
        setTotalPage(model, page);
        return "authors";
    }

    @PostMapping("/authors")
    public String authorsPost(@PageableDefault(size = 6) Pageable pageable,
                              @ModelAttribute("authorName") String authorName,
                              @ModelAttribute("isbnName") String isbnName,
                              Model model) {
        model.addAttribute("authorName", authorName);
        model.addAttribute("isbnName", isbnName);

        Page<AuthorEntity> page = authorName.isEmpty()
                ? authorService.findAllAuthor(pageable)
                : authorService.findAllAuthorByName('%' + authorName + '%', pageable);
        model.addAttribute("page", page);
        setTotalPage(model, page);
        return "authors";
    }

    @GetMapping("/books")
    public String booksGet(@PageableDefault(size = 6) Pageable pageable, Model model) {


        Page<BookEntity> page = bookService.findAllBooks(pageable);
        model.addAttribute("page", page);
        model.addAttribute("bookName", new String());
        model.addAttribute("isbnName", new String());
        setTotalPage(model, page);
        return "books";
    }

    @PostMapping("/books")
    public String booksPost(@PageableDefault(size = 6) Pageable pageable,
                            @ModelAttribute("bookName") String bookName,
                            @ModelAttribute("isbnName") String isbnName,
                            Model model) {

        model.addAttribute("bookName", bookName);
        model.addAttribute("isbnName", isbnName);

        Page<BookEntity> page;
        if (isbnName.isEmpty() && bookName.isEmpty()) {
            page = bookService.findAllBooks(pageable);
        } else if (!isbnName.isEmpty() && bookName.isEmpty()) {
            page = bookService.findBooksByISBN(isbnName, pageable);
        } else if (isbnName.isEmpty() && !bookName.isEmpty()) {
            page = bookService.findBooksByNameLike(bookName, pageable);
        } else {
            page = bookService.findBooksByISBNAndName(isbnName, bookName, pageable);
        }

        model.addAttribute("page", page);
        setTotalPage(model, page);
        return "books";
    }

    // todo додати перевірку на неіснуючі книги
    @GetMapping("/books/{id}")
    public String book(Model model, @PathVariable int id) {

        BookEntity book = bookService.findAllBookById(id).stream().findFirst().orElse(null);
        model.addAttribute("authors", bookService.findBookAuthors(book));
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/authors/{id}")
    public String author(Model model, @PathVariable int id) {
        AuthorEntity author = authorService.findAuthorById(id).stream().findFirst().orElse(null);
        model.addAttribute("author", author);
        model.addAttribute("books", authorService.findAllAuthorsBook(author));
        return "author";
    }

    @GetMapping("/books/liked")
    public String favouriteBooks(Model model) {
        authClient = clientService.findClientByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("client", authClient);
        return "favourite_books";
    }

    @GetMapping("/books/liked/{id}/remove")
    public String favouriteBookPost(@PathVariable int id) {
        System.out.println("remove");
        clientService.removeFavouriteBookById(authClient, id);
        return "redirect:/favourite-books";
    }

    @GetMapping("/books/create")
    public String createBookGet(Model model) {
        model.addAttribute("book", new BookEntity());

        model.addAttribute("authors", "authorName");
        System.out.println("create book");
        return "create_book";
    }

    @PostMapping("/books/create")
    public String createBookPost(@ModelAttribute("book") BookEntity book,
                                 @ModelAttribute("authors") String authors,
                                 Model model) {

        model.addAttribute("authors", authors);
        model.addAttribute("book", book);
        System.out.println("POST POST POST POST POST POST POST POST POST POST POST POST ");

//        for (String authorName : authors.split(",")) {
//            book.getAuthors().add(authorService.findAllAuthorByName(authorName).stream().findFirst().orElse(null));
//        }
//        bookService.addBook(book);
        return "";
    }

}

