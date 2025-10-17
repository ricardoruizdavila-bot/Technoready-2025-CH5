package com.ch5.ch5techno.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
@Transactional
public class OrderService {
    private final OrderRepository repo;
    public OrderService(OrderRepository repo) { this.repo = repo; }

    public Order create(OrderRequest req) {
        Order o = new Order();
        o.setCustomerName(req.getCustomerName());
        o.setTotal(req.getTotal());
        return repo.save(o);
    }

    public Order update(Long id, OrderRequest req) {
        Order o = repo.findById(id).orElseThrow();
        o.setCustomerName(req.getCustomerName());
        o.setTotal(req.getTotal());
        o.setUpdatedAt(Instant.now());
        return repo.save(o);
    }

    public Order changeStatus(Long id, OrderStatus status) {
        Order o = repo.findById(id).orElseThrow();
        o.setStatus(status);
        o.setUpdatedAt(Instant.now());
        return repo.save(o);
    }
}
