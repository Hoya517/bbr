package kr.ac.daelim.bbr.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.id DESC")
    List<Book> findAllDesc();

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> findByTitle(String title);
}
