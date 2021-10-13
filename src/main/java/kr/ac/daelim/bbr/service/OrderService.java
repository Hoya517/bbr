package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.order.Order;
import kr.ac.daelim.bbr.domain.order.OrderRepository;
import kr.ac.daelim.bbr.domain.orderbook.OrderBook;
import kr.ac.daelim.bbr.domain.orderbook.OrderBookRepository;
import kr.ac.daelim.bbr.web.member.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderBookRepository orderBookRepository;

    @Transactional
    public Long order(Long memberId, Long bookId, int count) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다." + memberId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("해당 책이 존재하지 않습니다." + bookId));

        // 책정보, 주문금액, 수량 넣고 || 책 재고수량 차감
        OrderBook orderBook = OrderBook.createOrderBook(book, book.getPrice(), count);

        // 주문자, 주문상품, 주문자 포인트 차감, 주문상태(ORDER)
        Order order = Order.createOrder(member, orderBook);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다." + orderId));
        order.cancel();
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> findAllDesc(Member member) {
        return orderBookRepository.findByDesc(member).stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> findAllDesc() {
        return orderBookRepository.findByDesc().stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void completeOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다. id=" + id)
        );
        order.updateComp();
    }
}
