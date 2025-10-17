package com.ch5.ch5techno.order;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/** Payload de creación/actualización de orden. */
public class OrderRequest {
    @NotBlank @Size(max = 80)
    private String customerName;

    @NotNull @Positive
    private BigDecimal total;

    // getters/setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String v) { this.customerName = v; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal t) { this.total = t; }
}
