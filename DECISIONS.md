# Decisions & Changes Log

## 2025-10-17 – BD en desarrollo (H2)
- **Decisión:** Usar H2 en memoria en `dev`.
- **Motivo:** Cero instalación, arranque rápido para pruebas locales.
- **Implicaciones:** Datos volátiles; se recrea al reiniciar.

## 2025-10-17 – BD en producción (PostgreSQL)
- **Decisión:** Usar PostgreSQL en `prod` con `DB_URL`, `DB_USER`, `DB_PASS`.
- **Motivo:** Estabilidad y soporte amplio.
- **Implicaciones:** Manejo de credenciales y migraciones.

## 2025-10-17 – DTO separado
- **Decisión:** `OrderRequest` para entrada; no exponer `Order` en el API.
- **Motivo:** Validaciones (Jakarta) y desacople web ↔ persistencia.

## 2025-10-17 – Estados de orden
- **Decisión:** `OrderStatus = {CREATED, PAID, SHIPPED, CANCELLED}`.
- **Motivo:** Evitar strings libres y facilitar reportes.

## 2025-10-17 – Sin Lombok (Sprint 1)
- **Decisión:** No usar Lombok inicialmente.
- **Motivo:** Claridad didáctica y dependencia cero.

## 2025-10-17 – Puerto en dev
- **Decisión:** `server.port=8081` en `dev`.
- **Motivo:** Evitar conflictos con servicios en 8080.
