package kr.ac.daelim.bbr.domain.order;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import kr.ac.daelim.bbr.domain.orderbook.OrderBook;
import kr.ac.daelim.bbr.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL )
    private List<OrderBook> orderBooks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderBook(OrderBook orderBook) {
        orderBooks.add(orderBook);
        orderBook.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, OrderBook... orderBooks) {
        Order order = new Order();
        order.setMember(member);
        for (OrderBook orderBook : orderBooks) {
            order.addOrderBook(orderBook);
            member.removePoint(orderBook.getOrderPrice());
        }
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void cancel() {
        this.setStatus(OrderStatus.CANCEL);
        for (OrderBook orderBook : orderBooks) {
            orderBook.cancel();
            member.addPoint(orderBook.getOrderPrice());
        }
    }

    public void updateComp() {
        this.status = OrderStatus.COMP;
    }
}
