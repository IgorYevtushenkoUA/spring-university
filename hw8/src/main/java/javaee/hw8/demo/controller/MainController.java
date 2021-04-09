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
    Page<BookEntity> page;


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

    public String getRole() {
        return authClient == null ? "user" : authClient.getClientRoles().getRoleName();
    }

    @GetMapping("/")
    public String main(Model model,
                       Pageable pageable) {
        authClient = clientService.findClientByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("books", bookService.findAllBooks(pageable));
        model.addAttribute("authors", authorService.findAllAuthor(pageable));
        model.addAttribute("role", getRole());
        return "main";
    }

    @GetMapping("/authors")
    public String authorsGet(@PageableDefault(size = 6) Pageable pageable,
                             Model model) {

        Page<AuthorEntity> page = authorService.findAllAuthor(pageable);
        model.addAttribute("authorName", new String("have name----"));
        model.addAttribute("isbnName", new String("have name----"));
        model.addAttribute("page", page);
        model.addAttribute("role", getRole());
        setTotalPage(model, page);
        return "authors";
    }

    @PostMapping("/authors")
    public String authorsPost(@PageableDefault(size = 6) Pageable pageable,
                              @ModelAttribute("authorName") String authorName,
                              @ModelAttribute("isbnName") String isbnName,
                              @ModelAttribute("role") String role,
                              Model model) {
        model.addAttribute("authorName", authorName);
        model.addAttribute("isbnName", isbnName);
        model.addAttribute("role", role);

        Page<AuthorEntity> page = authorName.isEmpty()
                ? authorService.findAllAuthor(pageable)
                : authorService.findAllAuthorByName('%' + authorName + '%', pageable);
        model.addAttribute("page", page);
        model.addAttribute("role", getRole());
        setTotalPage(model, page);
        return "authors";
    }

    @GetMapping("/books")
    public String booksGet(@PageableDefault(size = 6) Pageable pageable, Model model) {

        page = bookService.findAllBooks(pageable);

        model.addAttribute("page", page);
        model.addAttribute("bookName", new String());
        model.addAttribute("isbnName", new String());
        model.addAttribute("role", getRole());
        model.addAttribute("bookId", new String());
        setTotalPage(model, page);
        return "books";
    }

    @GetMapping("/books/liked/{id}/add")
    public String addLikedBook(@PathVariable int id) {

        BookEntity book = bookService.findAllBookById(id).orElse(null);
        clientService.addFavouriteBook(authClient, book);
        return "redirect:/books";
    }

    @PostMapping(value = "/books", params = "search")
    public String booksPostSearch(@PageableDefault(size = 6) Pageable pageable,
                                  @ModelAttribute("bookName") String bookName,
                                  @ModelAttribute("isbnName") String isbnName,
                                  @ModelAttribute("role") String role,
                                  Model model) {
        model.addAttribute("bookName", bookName);
        model.addAttribute("isbnName", isbnName);
        model.addAttribute("role", role);
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
        model.addAttribute("role", getRole());
        setTotalPage(model, page);
        return "books";
    }


    @GetMapping("/books/{id}")
    public String book(Model model, @PathVariable int id) {

        BookEntity book = bookService.findAllBookById(id).stream().findFirst().orElse(null);
        model.addAttribute("authors", bookService.findBookAuthors(book));
        model.addAttribute("book", book);
        model.addAttribute("role", getRole());
        return "book";
    }

    @GetMapping("/authors/{id}")
    public String author(Model model, @PathVariable int id) {
        AuthorEntity author = authorService.findAuthorById(id).stream().findFirst().orElse(null);
        model.addAttribute("author", author);
        model.addAttribute("books", authorService.findAllAuthorsBook(author));
        model.addAttribute("role", getRole());
        return "author";
    }

    @GetMapping("/books/liked")
    public String favouriteBooks(Model model) {
        model.addAttribute("client", authClient);
        model.addAttribute("role", getRole());
        return "favourite_books";
    }

    @GetMapping("/books/liked/{id}/remove")
    public String favouriteBookPost(@PathVariable int id) {
        clientService.removeFavouriteBookById(authClient, id);
        return "redirect:/books/liked";
    }

    @GetMapping("/books/create")
    public String createBookGet(Model model) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("authors", "authorName");
        model.addAttribute("role", getRole());
        System.out.println("create book");
        return "create_book";
    }

    @PostMapping(value = "/books/create")
    public String createBookPost(@ModelAttribute("book") BookEntity book,
                                 @ModelAttribute("authors") String authors,
                                 @ModelAttribute("role") String role,
                                 Model model) {

        model.addAttribute("authors", authors);
        model.addAttribute("book", book);
        model.addAttribute("role", role);
        System.out.println("POST POST POST POST POST POST POST POST POST POST POST POST ");

//        for (String authorName : authors.split(",")) {
//            book.getAuthors().add(authorService.findAllAuthorByName(authorName).stream().findFirst().orElse(null));
//        }
//        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("client", new ClientEntity());
        model.addAttribute("password2", "");
        System.out.println("get Register");
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("client") ClientEntity client,
                               @ModelAttribute("password2") String password2,
                               Model model) {
        model.addAttribute(client);
        model.addAttribute(password2);
        client.setRoleId(2);

        clientService.addNewClient(client);

        return "redirect:/";
    }

}

