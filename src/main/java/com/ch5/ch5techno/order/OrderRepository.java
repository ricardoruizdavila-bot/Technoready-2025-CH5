package com.ch5.ch5techno.order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA de acceso a datos para {@link Order}.
 */
public interface OrderRepository extends JpaRepository<Order, Long> { }