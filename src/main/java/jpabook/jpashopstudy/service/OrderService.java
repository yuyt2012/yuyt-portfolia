package jpabook.jpashopstudy.service;

import java.util.List;
import jpabook.jpashopstudy.domain.Delivery;
import jpabook.jpashopstudy.domain.Member;
import jpabook.jpashopstudy.domain.Order;
import jpabook.jpashopstudy.domain.OrderItem;
import jpabook.jpashopstudy.domain.item.Item;
import jpabook.jpashopstudy.repository.ItemRepository;
import jpabook.jpashopstudy.repository.MemberRepository;
import jpabook.jpashopstudy.repository.OrderRepository;
import jpabook.jpashopstudy.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    /**
     * 주문 기능
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    //검색

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
