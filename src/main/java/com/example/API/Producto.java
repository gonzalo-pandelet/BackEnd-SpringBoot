package com.example.API;

public class Producto {
    private String nombre;
    private String categoria;
    private String precio;
    private String ean13;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public String getEan13() {
        return ean13;
    }
}
