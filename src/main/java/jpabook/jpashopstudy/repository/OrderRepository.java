package jpabook.jpashopstudy.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jpabook.jpashopstudy.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll() {

//    }
}
