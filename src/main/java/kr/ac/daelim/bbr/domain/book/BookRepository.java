package kr.ac.daelim.bbr.domain.book;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b " +
           "JOIN b.registrations r WHERE b.title = :title AND b.author = :author AND b.publisher = :publisher AND b.datetime = :datetime AND b.price = :price " +
           "ORDER BY r.createdDate DESC")
    Optional<Book> findSameBook(String title, String author, String publisher, String datetime, Integer price);

    @Query("SELECT b FROM  Book b " +
           "WHERE (b.title LIKE %:word% OR b.author LIKE %:word% OR b.publisher LIKE %:word%) AND b.stockQuantity > 0")
    List<Book> findBySearchWord(String word);

    @Query("SELECT DISTINCT b FROM Book b " +
           "WHERE b.stockQuantity > 0 ORDER BY b.registDt DESC")
    List<Book> findAllModDesc();

    @Query("SELECT DISTINCT b FROM Book b " +
           "WHERE b.stockQuantity > 0 " +
           "ORDER BY b.views DESC, b.registDt DESC")
    List<Book> findAllViewsDesc();

    @Query("SELECT DISTINCT b FROM Book b " +
           "JOIN b.registrations r ON r.book.id = b.id " +
           "JOIN r.member WHERE r.member = :member AND r.department = r.member.department AND b.stockQuantity > 0 " +
           "ORDER BY b.registDt DESC")
    List<Book> findByMyDept(@Login Member member);

    @Query("SELECT DISTINCT b FROM Book b " +
           "JOIN b.registrations r ON r.book.id = b.id WHERE r.department = :department AND b.stockQuantity > 0 " +
           "ORDER BY b.registDt DESC")
    List<Book> findByDept(String department);
}
