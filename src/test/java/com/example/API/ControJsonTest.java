package com.example.API;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ControJsonTest {
    private API api;

    @MockBean
    private ControlJSON controlJSON;

    @Test
    public void testAgregarProducto() {
        // Configura datos de prueba
        Producto producto = new Producto();
        producto.setNombre("Producto de prueba");
        producto.setCategoria("Categoría de prueba");
        producto.setPrecio("10");

        // Configura el comportamiento esperado del método GuardarFichero en el objeto mock
        when(controlJSON.GuardarFichero(producto)).thenReturn(1);

        // Supongamos que tu API utiliza el controlJSON para guardar el producto
        int resultado = api.agregarProducto(producto);

        // Verifica que el resultado sea el esperado
        assertEquals(1,resultado);

    }
    @Test
    public void testEliminarProducto() {
        // Llama al método setUp para cargar datos de prueba y configurar comportamiento esperado

        Producto producto = new Producto();
        producto.setNombre("Prueba");
        producto.setCategoria("Prueba");
        producto.setPrecio("1");
        producto.setEan13("12345");

        //agregamos el mismo producto que vamos a eliminar mas adelante
        api.agregarProducto(producto);

        // Configura el comportamiento esperado del método eliminarProducto en el objeto mock
        when(controlJSON.eliminarProducto(producto)).thenReturn(1);

        // Supongamos que tu API utiliza el controlJSON para guardar el producto
        int resultado = api.eliminarProducto(producto);

        // Verifica que el resultado sea el esperado
        assertEquals(1, resultado);

    }



}
