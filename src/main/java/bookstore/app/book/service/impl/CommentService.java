package bookstore.app.book.service.impl;

import bookstore.app.book.entity.Book;
import bookstore.app.book.entity.Comment;
import bookstore.app.book.repository.CommentRepository;
import bookstore.app.book.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(Comment dto) throws Exception {
        return commentRepository.save(dto);
    }

    @Override
    public Comment update(Long id, Comment dto) throws Exception {
        return null;
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public boolean deleteById(Long id) throws Exception {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public Collection<Comment> getAll() {
        return null;
    }

    @Override
    public Page<Comment> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Comment> getByBook(Book book) {
        return commentRepository.findByBookOrderByIdDesc(book);
    }
}
