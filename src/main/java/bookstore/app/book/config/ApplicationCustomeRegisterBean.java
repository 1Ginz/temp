package bookstore.app.book.config;

//import com.shop.bookshop.DTO.BookDto;
//import com.shop.bookshop.DTO.UserDto;
//import com.shop.bookshop.Entity.Book;
//import com.shop.bookshop.Entity.User;
//import com.shop.bookshop.Service.mapper.impl.BookMapper;
//import com.shop.bookshop.Service.mapper.impl.UserMapper;
import bookstore.app.book.dto.BookDto;
import bookstore.app.book.entity.Book;
import bookstore.app.book.service.mapper.impl.BookMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationCustomeRegisterBean {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    @Bean(name = "bookMapper")
    public BookMapper getBookMapper(){
        return new BookMapper(Book.class, BookDto.class);
    }
//    @Bean(name = "UserMapper")
//    public UserMapper getUserMapper(){
//        return new UserMapper(User.class, UserDto.class);
//    }
}
