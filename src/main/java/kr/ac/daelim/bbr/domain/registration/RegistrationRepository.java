package kr.ac.daelim.bbr.domain.registration;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("select r from Registration r join r.member m where r.member = :member")
    List<Registration> findAllDesc(@Login Member member);
}
