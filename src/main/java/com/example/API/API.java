package com.example.API;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
public class API {

    // MOSTRAR PRODUCTOS
    @GetMapping("/Productos")
    public ArrayList<Producto> getProductos() {
        try {
            return new ControlJSON().LeerFichero();
        } catch (Exception e) {
            // Manejar excepciones al leer el archivo JSON
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la lista de productos: " + e.getMessage());
        }
    }

    // AÑADIR PRODUCTOS
    @PostMapping("/Productos/Agregar")
    public void agregarProducto(@RequestBody Producto productoNuevo) {
        try {
            ControlJSON controlJSON = new ControlJSON();
            int resultado = controlJSON.GuardarFichero(productoNuevo);

            if (resultado == 1) {
                System.out.println("Se ha agregado el producto correctamente");
            } else {
                System.out.println("Error al agregar el producto");
            }
        } catch (Exception e) {
            // Manejar excepciones al agregar un nuevo producto
            e.printStackTrace();
            throw new RuntimeException("Error al agregar el producto: " + e.getMessage());
        }
    }
    //FUNCION ELIMINAR
    @DeleteMapping("/Productos/Eliminar")
    public void eliminarProducto(@RequestBody Producto productoEliminar) {
        try {
            ControlJSON controlJSON = new ControlJSON();
            int resultado = controlJSON.eliminarProducto(productoEliminar);

            if (resultado == 1) {
                System.out.println("Se ha eliminado el producto correctamente");
            } else {
                System.out.println("Error al eliminar el producto");
            }
        } catch (Exception e) {
            // Manejar excepciones al eliminar un producto
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // FUNCION ACTUALIZAR
    @PutMapping("/Productos/Actualizar")
    public void actualizarProducto(@RequestBody Producto productoActualizar) {
        try {
            ControlJSON controlJSON = new ControlJSON();
            int resultado = controlJSON.actualizarProductoEnFichero(productoActualizar);

            if (resultado == 1) {
                System.out.println("Se ha actualizado el producto correctamente");
            } else {
                System.out.println("Error al actualizar el producto");
            }
        } catch (Exception e) {
            // Manejar excepciones al actualizar un producto
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

}
