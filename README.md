Maliburger - Sistema de gestión de Pedidos

Proyecto desarrollado como software con cuatro microservicios,
orientado a la gestión eficiente de un catálogo de hamburguesas,
control de su inventario y procesamiento de ventas.


Equipo:
- Juan Pablo Campos
- Karen Gajardo


Funcionalidades implementadaS:
1. Gestión de catálogo (maliburger)
    - CRUD completo de productos.
    - Búsqueda avanzada por tipo de proteína y rangos de precio.

2. Gestión de carrito (ms-carrito)
    - Creación y persistencia de carritos por usuario.
    - Adición de items con validación de precios.
    - Funcionalidad de limpieza de carrito tras finalizar la compra.
   
3. Gestión de inventario (ms-inventario)
    - Control de stock.
    - Validación de disponibilidad.
    - Ajuste dinámico de stock.
   
4. Pricesamiento de Ventas (ms-venta)
    - Integración orquestada, consulta de carrito, descuento de 
   inventario.
    - Historial de compras por usuario.
    - Manejo de transacciones.


Pasos para ejecutaR:
1. Base de datos: asegurarse de que la conexión a Oracle Cloud está 
configurada correctamente con el archivo wallet en cada servicio.

2. Arranque de servicios: ejecutar maliburger, ms-inventario, ms-
carrito, ms-venta.

3. Prueba de flujo: realizar un POST.