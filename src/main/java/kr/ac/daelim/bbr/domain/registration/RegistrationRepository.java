package kr.ac.daelim.bbr.domain.registration;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r " +
           "JOIN FETCH r.member " +
           "WHERE r.member = :member " +
           "ORDER BY r.createdDate DESC")
    List<Registration> findAllDesc(@Login Member member);

    @Query("SELECT r FROM Registration r " +
           "JOIN FETCH r.member " +
           "ORDER BY r.createdDate DESC")
    List<Registration> findAllDesc();
}
