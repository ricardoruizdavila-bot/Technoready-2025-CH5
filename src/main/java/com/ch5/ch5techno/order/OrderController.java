package com.ch5.ch5techno.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Orders", description = "CRUD de órdenes")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    private final OrderService service;

    public OrderController(OrderRepository repo, OrderService service) {
        this.repo = repo; this.service = service;
    }

    @Operation(
            summary = "Crear orden",
            description = "Crea una orden con nombre de cliente y total.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orden creada",
                            content = @Content(schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400", description = "Error de validación", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @Operation(summary = "Listar órdenes", description = "Obtiene todas las órdenes.")
    @GetMapping
    public List<Order> list() { return repo.findAll(); }

    @Operation(
            summary = "Obtener por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Encontrada",
                            content = @Content(schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public Order get(
            @Parameter(description = "Identificador de la orden", example = "1")
            @PathVariable Long id
    ) {
        return repo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Operation(
            summary = "Actualizar datos básicos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Actualizada",
                            content = @Content(schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400", description = "Error de validación", content = @Content),
                    @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public Order update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest req
    ) {
        return service.update(id, req);
    }

    @Operation(
            summary = "Cambiar estado de la orden",
            description = "Actualiza sólo el estado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estado actualizado",
                            content = @Content(schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400", description = "Estado inválido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
            }
    )
    @PatchMapping("/{id}/status")
    public Order setStatus(
            @PathVariable Long id,
            @Parameter(description = "Nuevo estado", schema = @Schema(implementation = OrderStatus.class), example = "PAID")
            @RequestParam OrderStatus status
    ) {
        return service.changeStatus(id, status);
    }

    @Operation(
            summary = "Eliminar orden",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Eliminada"),
                    @ApiResponse(responseCode = "404", description = "No encontrada", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new OrderNotFoundException(id);
        repo.deleteById(id);
    }
}
