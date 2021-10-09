package kr.ac.daelim.bbr.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.id DESC")
    List<Book> findAllDesc();

    @Query("SELECT b FROM Book b WHERE b.title = :title AND b.author = :author AND b.publisher = :publisher AND b.datetime = :datetime AND b.price = :price")
    Optional<Book> findByTitle(String title, String author, String publisher, String datetime, Integer price);
}
