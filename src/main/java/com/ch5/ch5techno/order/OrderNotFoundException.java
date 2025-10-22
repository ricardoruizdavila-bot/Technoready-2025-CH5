// src/main/java/com/ch5/ch5techno/order/OrderNotFoundException.java
package com.ch5.ch5techno.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order not found: " + id);
    }
}
