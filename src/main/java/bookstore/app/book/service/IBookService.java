package bookstore.app.book.service;

import bookstore.app.book.dto.BookDto;
import bookstore.app.book.dto.CategoryDto;

import java.util.List;
import java.util.Set;

public interface IBookService extends IServiceAdapter<BookDto> {

    List<BookDto> searchBook(String title);

    List<BookDto> getByCategory(String id);

}
