package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.order.Order;
import kr.ac.daelim.bbr.domain.order.OrderRepository;
import kr.ac.daelim.bbr.domain.orderItem.OrderBook;
import kr.ac.daelim.bbr.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long order(Long memberId, Long bookId, int count) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 존재하지 않습니다." + memberId)
        );

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("해당 책이 존재하지 않습니다." + bookId)
        );

        OrderBook orderBook = OrderBook.createOrderBook(book, book.getPrice(), count);

        log.info("service.member={}", member.getName());
        Order order = Order.createOrder(member, orderBook);
        orderRepository.save(order);
        return order.getId();
    }

}
