package javaee.hw8.demo.dto;

import javaee.hw8.demo.entity.AuthorEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@ToString
public class BookDto {

    @NotEmpty(message = "BookName can't be empty")
    @Pattern(regexp = "^[A-Za-z0-9]{2,40}$", message = "Name has bad format")
    private String name;

    @NotEmpty(message = "Book should have isbn")
    @Pattern(regexp = "^[A-Za-z0-9]{2,40}$", message = "ISBN has bad format")
    @Size(min = 10, max = 13, message = "Incorrect isbn length")
    // todo improve isbn
    private String isbn;

    @NotEmpty(message = "Book should have description")
    private String description;

    @NotEmpty(message = "Book should have author")
    private String authors;

}
