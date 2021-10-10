package kr.ac.daelim.bbr.domain.book;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title AND b.author = :author AND b.publisher = :publisher AND b.datetime = :datetime AND b.price = :price")
    Optional<Book> findByTitle(String title, String author, String publisher, String datetime, Integer price);

    @Query("SELECT b FROM Book b JOIN b.registrations r ON r.book.id = b.id WHERE r.status = 'COMP' ORDER BY b.modifiedDate DESC")
    List<Book> findAllModDesc();

    @Query("SELECT b FROM Book b JOIN b.registrations r ON r.book.id = b.id WHERE r.status = 'COMP' ORDER BY b.views DESC")
    List<Book> findAllViewsDesc();

    @Query("select b from Book b " +
           "join b.registrations r ON r.book.id = b.id " +
           "join r.member where r.member = :member AND r.department = r.member.department AND r.status = 'COMP' " +
           "ORDER BY b.modifiedDate desc")
    List<Book> findByMyDept(@Login Member member);

    @Query("select b from Book b " +
           "join b.registrations r ON r.book.id = b.id " +
           "where r.department = :department AND r.status = 'COMP' " +
           "ORDER BY b.modifiedDate desc")
    List<Book> findByDept(String department);
}
