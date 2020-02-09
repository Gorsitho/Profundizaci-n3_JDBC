-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-01-17 20:14:59.37

-- tables
-- Table: AUTORIZACIONES
CREATE TABLE `AUTORIZACIONES` (
    `auto_id` int(20) NOT NULL AUTO_INCREMENT,
    `auto_id_autorizador` int(5) NOT NULL,
    `auto_fecha_autorizacion` timestamp NOT NULL,
    CONSTRAINT `AUTORIZACIONES_pk` PRIMARY KEY (`auto_id`)
);

-- Table: AUTORIZADORES
CREATE TABLE `AUTORIZADORES` (
    `autz_id` int(5) NOT NULL AUTO_INCREMENT,
    `autz_nom` int(60) NOT NULL,
    `autz_descripcion` varchar(50) NOT NULL,
    CONSTRAINT `AUTORIZADORES_pk` PRIMARY KEY (`autz_id`)
);

-- Table: CATEGORIAS
CREATE TABLE `CATEGORIAS` (
    `catg_id` int(10) NOT NULL AUTO_INCREMENT,
    `catg_nom` varchar(20) NOT NULL,
    `catg_descripcion` varchar(30) NULL,
    CONSTRAINT `CATEGORIAS_pk` PRIMARY KEY (`catg_id`)
);

-- Table: CIUDADES
CREATE TABLE `CIUDADES` (
    `ciud_id` int(6) NOT NULL AUTO_INCREMENT,
    `ciud_nom` varchar(255) NOT NULL,
    `ciud_id_depto` int(2) NOT NULL,
    CONSTRAINT `CIUDADES_pk` PRIMARY KEY (`ciud_id`)
);

-- Table: CLIENTES
CREATE TABLE `CLIENTES` (
    `clie_id` int(10) NOT NULL AUTO_INCREMENT,
    `clie_cedula` varchar(10) NOT NULL,
    `clie_nom` varchar(60) NOT NULL,
    `clie_telefono` varchar(10) NOT NULL,
    `clie_direccion` varchar(20) NOT NULL,
    `clie_id_ciudad` int(6) NOT NULL,
    CONSTRAINT `CLIENTES_pk` PRIMARY KEY (`clie_id`)
);

-- Table: DEPARTAMENTOS
CREATE TABLE `DEPARTAMENTOS` (
    `depa_id` int(2) NOT NULL AUTO_INCREMENT,
    `depa_nom` varchar(255) NOT NULL,
    CONSTRAINT `DEPARTAMENTOS_pk` PRIMARY KEY (`depa_id`)
);

-- Table: DETALLE_VENTAS
CREATE TABLE `DETALLE_VENTAS` (
    `dtve_id` int(20) NOT NULL AUTO_INCREMENT,
    `dtve_cant` int(2) NOT NULL,
    `dtve_id_venta` int(10) NOT NULL,
    `dtve_id_producto` int(10) NOT NULL,
    `dtve_precio_final` int(10) NOT NULL,
    CONSTRAINT `DETALLE_VENTAS_pk` PRIMARY KEY (`dtve_id`)
);

-- Table: EMPLEADOS
CREATE TABLE `EMPLEADOS` (
    `empl_id` int(8) NOT NULL AUTO_INCREMENT,
    `empl_nom` varchar(60) NOT NULL,
    `empl_cedula` varchar(10) NOT NULL,
    `empl_tipo_empleado` int(1) NOT NULL,
    `empl_id_tienda` int(10) NOT NULL,
    `empl_telefono` varchar(10) NOT NULL,
    `empl_direccion` varchar(20) NOT NULL,
    `empl_id_ciudad` int(6) NOT NULL,
    `empl_fecha_nacimiento` date NOT NULL,
    CONSTRAINT `EMPLEADOS_pk` PRIMARY KEY (`empl_id`)
);

-- Table: IMPUESTOS
CREATE TABLE `IMPUESTOS` (
    `impu_id` int(1) NOT NULL AUTO_INCREMENT,
    `impu_porcentaje` int(2) NOT NULL,
    `impu_descripcion` varchar(30) NOT NULL,
    CONSTRAINT `IMPUESTOS_pk` PRIMARY KEY (`impu_id`)
);

-- Table: PAGOS
CREATE TABLE `PAGOS` (
    `pago_id` int(10) NOT NULL AUTO_INCREMENT,
    `pago_id_venta` int(10) NOT NULL,
    `pago_cant_tipo_pago` int(1) NOT NULL,
    `pago_id_tipo_pago` int(1) NOT NULL,
    `pago_requiere_auto` int(1) NOT NULL,
    `auto_id_autorizacion` int(20) NOT NULL,
    `pago_fecha_hora` timestamp NOT NULL,
    CONSTRAINT `PAGOS_pk` PRIMARY KEY (`pago_id`)
);

-- Table: PRODUCTOS
CREATE TABLE `PRODUCTOS` (
    `prod_id` int(10) NOT NULL AUTO_INCREMENT,
    `prod_nom` varchar(70) NOT NULL,
    `prod_stock` int(2) NOT NULL,
    `prod_precio_bruto` int(8) NOT NULL,
    `prod_id_impuesto` int(1) NOT NULL,
    `prod_id_categoria` int(4) NOT NULL,
    `prod_descripcion` varchar(200) NOT NULL,
    CONSTRAINT `PRODUCTOS_pk` PRIMARY KEY (`prod_id`)
);

-- Table: PROVEEDORES
CREATE TABLE `PROVEEDORES` (
    `prov_id` int(10) NOT NULL AUTO_INCREMENT,
    `prov_nom` varchar(60) NOT NULL,
    `prov_nit` varchar(15) NOT NULL,
    `prov_telefono` varchar(10) NOT NULL,
    `prov_direccion` varchar(20) NOT NULL,
    `prov_id_ciudad` int(6) NOT NULL,
    `prov_num_cta_banco` varchar(20) NOT NULL,
    CONSTRAINT `PROVEEDORES_pk` PRIMARY KEY (`prov_id`)
);

-- Table: PROVEEDORES_PRODUCTOS
CREATE TABLE `PROVEEDORES_PRODUCTOS` (
    `prpr_id` int(10) NOT NULL AUTO_INCREMENT,
    `prpr_id_proveedor` int(10) NOT NULL,
    `prp_id_producto` int(10) NOT NULL,
    CONSTRAINT `PROVEEDORES_PRODUCTOS_pk` PRIMARY KEY (`prpr_id`)
);

-- Table: TIENDAS
CREATE TABLE `TIENDAS` (
    `tien_id` int(10) NOT NULL AUTO_INCREMENT,
    `tien_nom` varchar(20) NOT NULL,
    `tien_telefono` varchar(10) NOT NULL,
    `tien_direccion` varchar(30) NOT NULL,
    `tien_id_ciudad` int(6) NOT NULL,
    CONSTRAINT `TIENDAS_pk` PRIMARY KEY (`tien_id`)
);

-- Table: TIPOS_PAGO
CREATE TABLE `TIPOS_PAGO` (
    `tppg_id` int(1) NOT NULL AUTO_INCREMENT,
    `tppg_nom` varchar(20) NOT NULL,
    `tppg_descripcion` varchar(50) NOT NULL,
    CONSTRAINT `TIPOS_PAGO_pk` PRIMARY KEY (`tppg_id`)
);

-- Table: TIPO_EMPLEADOS
CREATE TABLE `TIPO_EMPLEADOS` (
    `tpem_id` int(1) NOT NULL AUTO_INCREMENT,
    `tpem_nom` varchar(20) NOT NULL,
    `tpem_descripcion` varchar(70) NOT NULL,
    CONSTRAINT `TIPO_EMPLEADOS_pk` PRIMARY KEY (`tpem_id`)
);

-- Table: VENTAS
CREATE TABLE `VENTAS` (
    `vent_id` int(10) NOT NULL AUTO_INCREMENT,
    `vent_fecha_hora` timestamp NOT NULL,
    `vent_id_empleado` int(8) NOT NULL,
    `vent_id_tienda` int(10) NOT NULL,
    `vent_id_cliente` int(10) NOT NULL,
    `vent_precio_bruto` int(20) NOT NULL,
    `vent_dscto_tipo` varchar(1) NULL,
    `vent_dscto_valor` int(20) NULL,
    `vent_precio_final` int(20) NOT NULL,
    CONSTRAINT `VENTAS_pk` PRIMARY KEY (`vent_id`)
);

-- foreign keys
-- Reference: DETALLE_VENTAS_VENTAS (table: DETALLE_VENTAS)
ALTER TABLE `DETALLE_VENTAS` ADD CONSTRAINT `DETALLE_VENTAS_VENTAS` FOREIGN KEY `DETALLE_VENTAS_VENTAS` (`dtve_id_venta`)
    REFERENCES `VENTAS` (`vent_id`);

-- Reference: PAGOS_AUTORIZACIONES (table: PAGOS)
ALTER TABLE `PAGOS` ADD CONSTRAINT `PAGOS_AUTORIZACIONES` FOREIGN KEY `PAGOS_AUTORIZACIONES` (`auto_id_autorizacion`)
    REFERENCES `AUTORIZACIONES` (`auto_id`);

-- Reference: PAGOS_VENTAS (table: PAGOS)
ALTER TABLE `PAGOS` ADD CONSTRAINT `PAGOS_VENTAS` FOREIGN KEY `PAGOS_VENTAS` (`pago_id_venta`)
    REFERENCES `VENTAS` (`vent_id`);

-- Reference: PRODUCTOS_CATEGORIAS (table: PRODUCTOS)
ALTER TABLE `PRODUCTOS` ADD CONSTRAINT `PRODUCTOS_CATEGORIAS` FOREIGN KEY `PRODUCTOS_CATEGORIAS` (`prod_id_categoria`)
    REFERENCES `CATEGORIAS` (`catg_id`);

-- Reference: PRODUCTOS_IMPUESTOS (table: PRODUCTOS)
ALTER TABLE `PRODUCTOS` ADD CONSTRAINT `PRODUCTOS_IMPUESTOS` FOREIGN KEY `PRODUCTOS_IMPUESTOS` (`prod_id_impuesto`)
    REFERENCES `IMPUESTOS` (`impu_id`);

-- Reference: PROVEEDORES_CIUDADES (table: PROVEEDORES)
ALTER TABLE `PROVEEDORES` ADD CONSTRAINT `PROVEEDORES_CIUDADES` FOREIGN KEY `PROVEEDORES_CIUDADES` (`prov_id_ciudad`)
    REFERENCES `CIUDADES` (`ciud_id`);

-- Reference: TIENDAS_EMPLEADOS (table: EMPLEADOS)
ALTER TABLE `EMPLEADOS` ADD CONSTRAINT `TIENDAS_EMPLEADOS` FOREIGN KEY `TIENDAS_EMPLEADOS` (`empl_id_tienda`)
    REFERENCES `TIENDAS` (`tien_id`);

-- Reference: TIPO_EMPLEADOS_EMPLEADOS (table: EMPLEADOS)
ALTER TABLE `EMPLEADOS` ADD CONSTRAINT `TIPO_EMPLEADOS_EMPLEADOS` FOREIGN KEY `TIPO_EMPLEADOS_EMPLEADOS` (`empl_tipo_empleado`)
    REFERENCES `TIPO_EMPLEADOS` (`tpem_id`);

-- Reference: VENTAS_EMPLEADOS (table: VENTAS)
ALTER TABLE `VENTAS` ADD CONSTRAINT `VENTAS_EMPLEADOS` FOREIGN KEY `VENTAS_EMPLEADOS` (`vent_id_empleado`)
    REFERENCES `EMPLEADOS` (`empl_id`);

-- Reference: VENTAS_PRODUCTOS (table: DETALLE_VENTAS)
ALTER TABLE `DETALLE_VENTAS` ADD CONSTRAINT `VENTAS_PRODUCTOS` FOREIGN KEY `VENTAS_PRODUCTOS` (`dtve_id_producto`)
    REFERENCES `PRODUCTOS` (`prod_id`);

-- Reference: ciudades_departamentos (table: CIUDADES)
ALTER TABLE `CIUDADES` ADD CONSTRAINT `ciudades_departamentos` FOREIGN KEY `ciudades_departamentos` (`ciud_id_depto`)
    REFERENCES `DEPARTAMENTOS` (`depa_id`);

-- Reference: ciudades_tiendas (table: TIENDAS)
ALTER TABLE `TIENDAS` ADD CONSTRAINT `ciudades_tiendas` FOREIGN KEY `ciudades_tiendas` (`tien_id_ciudad`)
    REFERENCES `CIUDADES` (`ciud_id`);

-- Reference: clientes_ciudades (table: CLIENTES)
ALTER TABLE `CLIENTES` ADD CONSTRAINT `clientes_ciudades` FOREIGN KEY `clientes_ciudades` (`clie_id_ciudad`)
    REFERENCES `CIUDADES` (`ciud_id`);

-- Reference: empleados_ciudades (table: EMPLEADOS)
ALTER TABLE `EMPLEADOS` ADD CONSTRAINT `empleados_ciudades` FOREIGN KEY `empleados_ciudades` (`empl_id_ciudad`)
    REFERENCES `CIUDADES` (`ciud_id`);

-- Reference: proveedores_productos_productos (table: PROVEEDORES_PRODUCTOS)
ALTER TABLE `PROVEEDORES_PRODUCTOS` ADD CONSTRAINT `proveedores_productos_productos` FOREIGN KEY `proveedores_productos_productos` (`prp_id_producto`)
    REFERENCES `PRODUCTOS` (`prod_id`);

-- Reference: proveedores_productos_provedores (table: PROVEEDORES_PRODUCTOS)
ALTER TABLE `PROVEEDORES_PRODUCTOS` ADD CONSTRAINT `proveedores_productos_provedores` FOREIGN KEY `proveedores_productos_provedores` (`prpr_id_proveedor`)
    REFERENCES `PROVEEDORES` (`prov_id`);

-- Reference: tipo_autorizadores_AUTORIZACIONES (table: AUTORIZACIONES)
ALTER TABLE `AUTORIZACIONES` ADD CONSTRAINT `tipo_autorizadores_AUTORIZACIONES` FOREIGN KEY `tipo_autorizadores_AUTORIZACIONES` (`auto_id_autorizador`)
    REFERENCES `AUTORIZADORES` (`autz_id`);

-- Reference: tipos_pago_PAGOS (table: PAGOS)
ALTER TABLE `PAGOS` ADD CONSTRAINT `tipos_pago_PAGOS` FOREIGN KEY `tipos_pago_PAGOS` (`pago_id_tipo_pago`)
    REFERENCES `TIPOS_PAGO` (`tppg_id`);

-- Reference: ventas_clientes (table: VENTAS)
ALTER TABLE `VENTAS` ADD CONSTRAINT `ventas_clientes` FOREIGN KEY `ventas_clientes` (`vent_id_cliente`)
    REFERENCES `CLIENTES` (`clie_id`);

-- Reference: ventas_tiendas (table: VENTAS)
ALTER TABLE `VENTAS` ADD CONSTRAINT `ventas_tiendas` FOREIGN KEY `ventas_tiendas` (`vent_id_tienda`)
    REFERENCES `TIENDAS` (`tien_id`);

-- End of file.

