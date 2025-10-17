package com.ch5.ch5techno.order;

import org.springframework.data.jpa.repository.JpaRepository;

/** Acceso a datos para Order. */
public interface OrderRepository extends JpaRepository<Order, Long> { }
