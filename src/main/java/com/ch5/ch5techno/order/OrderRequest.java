package com.ch5.ch5techno.order;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Payload de entrada para crear/actualizar órdenes mediante la API.
 * <p>Se valida con Jakarta Validation en el controlador.</p>
 */
public class OrderRequest {
    /** Nombre del cliente (requerido, máx 80). */
    @NotBlank @Size(max = 80)
    private String customerName;

    /** Importe total positivo. */
    @NotNull @Positive
    private BigDecimal total;

    // getters/setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String v) { this.customerName = v; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal t) { this.total = t; }
}
