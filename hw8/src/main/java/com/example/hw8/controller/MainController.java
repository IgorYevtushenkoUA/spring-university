package com.example.hw8.controller;

import com.example.hw8.entity.AuthorEntity;
import com.example.hw8.entity.BookEntity;
import com.example.hw8.entity.ClientEntity;
import com.example.hw8.service.AuthorService;
import com.example.hw8.service.BookService;
import com.example.hw8.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController  {
//public class MainController implements ErrorController {

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
        model.addAttribute("page", page);
        setTotalPage(model, page);
        return "authors";
    }

    @PostMapping("/authors")
    public String authorsPost(@PageableDefault(size = 6) Pageable pageable,
                              @ModelAttribute("authorName") String authorName,
                              Model model) {
        model.addAttribute("authorName", authorName);
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
        setTotalPage(model, page);
        return "books";
    }

    @PostMapping("/books")
    public String booksPost(@PageableDefault(size = 6) Pageable pageable,
                            @ModelAttribute("bookName") String bookName,
                            Model model) {

        model.addAttribute("bookName", bookName);
        Page<BookEntity> page = bookName.isEmpty()
                ? bookService.findAllBooks(pageable)
                : bookService.findBooksByNameLike('%' + bookName + '%', pageable);
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

    @GetMapping("/favourite-books")
    public String favouriteBooks(Model model) {
        authClient = clientService.findClientByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("client", authClient);
        return "favourite_books";
    }

    @GetMapping("/favourite-books/{id}/remove")
    public String favouriteBookPost(@PathVariable int id) {
        System.out.println("remove");
        clientService.removeFavouriteBookById(authClient, id);
        return "redirect:/favourite-books";
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        System.out.println("login");
//        model.addAttribute("username", new String());
//        model.addAttribute("password", new String());
//        return "login";
//    }

//    // #todo не заходить в пост і я не можу дістати нашого користувача у правильному місці
//    @PostMapping("/login")
//    public String loginPost(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
//        System.out.println("NAME : " + username);
//        System.out.println("loginPost");
//        return "redirect:/books";
//    }


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

//    @Override
//    @RequestMapping("/error")
//    @ResponseBody
//    public String getErrorPath() {
//        // TODO Auto-generated method stub
//        return "No Mapping Found";
//    }
}

