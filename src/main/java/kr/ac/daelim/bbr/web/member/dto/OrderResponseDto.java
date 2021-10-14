package kr.ac.daelim.bbr.web.member.dto;

import kr.ac.daelim.bbr.domain.order.OrderStatus;
import kr.ac.daelim.bbr.domain.orderbook.OrderBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class OrderResponseDto {
    private Long id;
    private String title;
    private String author;
    @NumberFormat(pattern = "###,###")
    private Integer price;
    @NumberFormat(pattern = "###,###")
    private Integer count;
    private LocalDateTime buyDatetime;
    private OrderStatus status;

    public OrderResponseDto(OrderBook entity) {
        this.id = entity.getId();
        this.title = entity.getBook().getTitle();
        this.author = entity.getBook().getAuthor();
        this.price = entity.getOrderPrice();
        this.count = entity.getCount();
        this.status = entity.getOrder().getStatus();
        this.buyDatetime = entity.getCreatedDate();
    }
}
