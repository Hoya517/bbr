package kr.ac.daelim.bbr.domain.orderbook;

import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

    @Query("SELECT ob FROM OrderBook  ob " +
           "JOIN ob.order o WHERE o.member = :member " +
           "ORDER BY ob.createdDate DESC")
    List<OrderBook> findByDesc(@Login Member member);

    @Query("SELECT ob FROM OrderBook  ob " +
            "JOIN ob.order o " +
            "ORDER BY ob.createdDate DESC")
    List<OrderBook> findByDesc();
}
