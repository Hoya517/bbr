package kr.ac.daelim.bbr.domain.orderItem;

import kr.ac.daelim.bbr.domain.BaseTimeEntity;
import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class OrderBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    @Builder
    public OrderBook(Book book, Order order, int orderPrice, int count) {
        this.book = book;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
