package com.example.lmoncada.peluches;

    public class Contactos {
        private String id, nombre, cantidad, valor,venta;

        public Contactos(String id, String nombre, String cantidad, String valor, String venta) {
            this.id = id;
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.valor = valor;
            this.venta = cantidad;

        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCantidad() {
            return cantidad;
        }

        public String getValor (){
            return valor;
        }

        public String getVenta (){
            return cantidad;
        }
    }