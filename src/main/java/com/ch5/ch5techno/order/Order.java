package com.ch5.ch5techno.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Entidad JPA que representa una orden de compra en la tienda.
 * <p>Se persiste en la tabla {@code orders}.</p>
 */
@Entity
@Table(name = "orders")
public class Order {
    /** Identificador único autogenerado. */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del cliente asociado a la orden. */
    @NotBlank @Size(max = 80)
    private String customerName;

    /** Estado actual de la orden. */
    @NotNull @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    /** Importe total de la orden (moneda base). */
    @NotNull @Positive
    private BigDecimal total;

    /** Fecha/hora de creación en UTC. */
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /** Última fecha/hora de actualización en UTC. */
    private Instant updatedAt;

    // Getters/Setters
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String v) { this.customerName = v; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus s) { this.status = s; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal t) { this.total = t; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant t) { this.updatedAt = t; }
}
