package com.ch5.ch5techno.order;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** API REST para CRUD de órdenes. */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    private final OrderService service;

    public OrderController(OrderRepository repo, OrderService service) {
        this.repo = repo; this.service = service;
    }

    /** Crea una orden nueva. */
    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    /** Lista todas las órdenes. */
    @GetMapping
    public List<Order> list() { return repo.findAll(); }

    /** Obtiene una orden por id. */
    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    /** Actualiza datos básicos. */
    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @Valid @RequestBody OrderRequest req) {
        return service.update(id, req);
    }

    /** Cambia el estado de la orden. */
    @PatchMapping("/{id}/status")
    public Order setStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return service.changeStatus(id, status);
    }

    /** Elimina la orden. */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
