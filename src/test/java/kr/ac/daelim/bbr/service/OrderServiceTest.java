package kr.ac.daelim.bbr.service;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.member.MemberType;
import kr.ac.daelim.bbr.domain.order.Order;
import kr.ac.daelim.bbr.domain.order.OrderRepository;
import kr.ac.daelim.bbr.domain.order.OrderStatus;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationRepository;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired BookRepository bookRepository;
    @Autowired RegistrationRepository registrationRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook();

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderBooks().size());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook();

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, book.getStockQuantity());
        assertEquals("주문 취소시 포인트가 증가", 100000, member.getPoint());
    }

    private Member createMember() {
        Member member = Member.builder()
                .username("test")
                .password("test!")
                .name("테스터")
                .department("컴퓨터정보학부")
                .memberType(MemberType.USER)
                .point(100000)
                .build();
        return memberRepository.save(member);
    }

    private Book createBook() {
        Book book =  Book.builder()
                .title("title")
                .author("author")
                .publisher("publisher")
                .datetime("2012-01-11")
                .price(20000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3383739%3Ftimestamp%3D20190220072908")
                .stockQuantity(10)
                .views(0)
                .build();
        return bookRepository.save(book);
    }
}