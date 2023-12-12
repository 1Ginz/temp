package bookstore.app.book.repository;

import bookstore.app.book.entity.Book;
import bookstore.app.book.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

//    @Query("select c from Comment c where c.book_id = :id")
    List<Comment> findByBookOrderByIdDesc(Book book);
}
