package bookstore.app.book.service.impl;

import bookstore.app.book.dto.BookDto;
import bookstore.app.book.entity.Book;
import bookstore.app.book.repository.BookRepository;
import bookstore.app.book.service.IBookService;
import bookstore.app.book.service.mapper.IConverterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookService implements IBookService {

    private BookRepository bookRepository;

    private IConverterDto<Book, BookDto> bookMapper;

    public BookService(BookRepository bookRepository,
                       IConverterDto<Book, BookDto> bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> getAll(){
        return (List<BookDto>) bookMapper.convertToListDto(bookRepository.findAll());
    }

    @Override
    public Collection<BookDto> getAll(Pageable pageable){
        return (List<BookDto>) bookMapper.convertToListDto(bookRepository.findAll(pageable).getContent());
    }

    public List<BookDto> getAllBookBySort(String sortBy,String typeSort){
        if (sortBy == null) return (List<BookDto>) bookMapper.convertToListDto(bookRepository.findAll());
        Sort sort;
        if(typeSort.equals("ASC")){
            sort = Sort.by(Sort.Direction.ASC,sortBy);
        }else{
            sort = Sort.by(Sort.Direction.DESC,sortBy);
        }
        return (List<BookDto>) bookMapper.convertToListDto(bookRepository.findAll(sort));
    }

    @Override
    public BookDto getById(Long id){
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null){
            BookDto bookDto = new BookDto();
            bookDto.setId((long) -1);
            return bookDto;
        }
        return (BookDto) bookMapper.convertToDto(book);
    }


    @Override
    public BookDto create(BookDto book){
        bookRepository.save(bookMapper.convertToEntity(book));
        return book;
    }

    @Override
    public BookDto update(Long id, BookDto book){
        if (!bookRepository.existsById(id)){
            create(book);
            return book;
        }
        bookRepository.save(bookMapper.convertToEntity(book));
        return book;
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        if(!bookRepository.existsById(id)){
            throw new Exception("ko tim thay de xoa");
        }
        bookRepository.deleteById(id);
        return true;
    }


}
