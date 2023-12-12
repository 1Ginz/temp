package bookstore.app.book.web.controller;

import bookstore.app.book.dto.BookDto;
import bookstore.app.book.entity.Account;
import bookstore.app.book.entity.Book;
import bookstore.app.book.entity.Comment;
import bookstore.app.book.service.IAccountService;
import bookstore.app.book.service.IBookService;
import bookstore.app.book.service.ICommentService;
import bookstore.app.book.service.mapper.IConverterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IConverterDto<Book,BookDto> bookMapper;

    @Autowired
    private IAccountService accountService;


    @PostMapping("/")
    public String postComment(Authentication authentication,
                              @RequestParam("content") String content,
                              @RequestParam("bookid") String bookId){
        System.out.println(content);
        System.out.println(bookId);
        Comment comment = new Comment();
        Account account = accountService.getByUserName(authentication.getName());
        BookDto book = bookService.getById(Long.valueOf(bookId));
        comment.setUser(account.getUser());
        comment.setContent(content);
        comment.setBook(bookMapper.convertToEntity(book));
        try {
            commentService.create(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable String id){
        Comment comment = commentService.getById(Long.valueOf(id));
        Book book = comment.getBook();
        Long bid = book.getId();

        try {
            commentService.deleteById(Long.valueOf(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/books/" + bid;
    }


}
