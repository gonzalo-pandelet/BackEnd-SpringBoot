package com.example.API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class ControlJSON {

    private final String PathJson = "src/main/resources/Datos.json";

    public ArrayList<Producto> LeerFichero() {
        try {
            // Intentamos abrir un InputStream para leer el archivo desde el classpath
            InputStream inputStream = new FileInputStream(PathJson);

            // Creamos un BufferedReader para facilitar la lectura
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Utilizamos Gson para convertir el JSON en una lista de objetos Persona
            ArrayList<Producto> listaProductos = new Gson().fromJson(reader, new TypeToken<ArrayList<Producto>>() {}.getType());

            // Cerramos el Reader
            reader.close();

            // Devolvemos la lista de personas
            return listaProductos;
        } catch (Exception ex) {
            // Si hay algún error, imprimimos la traza y devolvemos un array vacío
            ex.printStackTrace();
            return new ArrayList<>(); // si no ha leído nada, devuelve un array vacío
        }
    }

    public int actualizarProductoEnFichero(Producto productoActualizado) {
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJSON = new File(PathJson);

        try {
            // Lee el archivo JSON existente
            ArrayList<Producto> listaProductos = LeerFichero();

            // Busca el producto a actualizar
            Producto productoExistente = new Producto();

            for (Producto producto : listaProductos) {
                if (Objects.equals(producto.getEan13(), productoActualizado.getEan13())) {
                    productoExistente = producto;
                    break;
                }
            }

            // Verifica si el producto existe antes de intentar actualizarlo
            if (productoExistente != null) {
                // Actualiza el producto existente con los datos del producto actualizado
                productoExistente.setNombre(productoActualizado.getNombre());
                productoExistente.setCategoria(productoActualizado.getCategoria());
                productoExistente.setPrecio(productoActualizado.getPrecio());

                // Configura el ObjectWriter con sangría
                ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

                // Sobrescribe el JSON en un archivo con formato
                try (FileWriter fileWriter = new FileWriter(archivoJSON, false)) {
                    objectWriter.writeValue(fileWriter, listaProductos);
                    System.out.println("El producto se ha actualizado correctamente, actualice la página");
                    return 1; // Éxito
                }
            } else {
                System.out.println("El producto no existe, no se puede actualizar");
                return 0; // Producto no encontrado
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0; // Error al actualizar
    }


    public int GuardarFichero(Producto producto) {
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJSON = new File(PathJson);

        try {
            // Lee el archivo JSON existente o crea uno nuevo si no existe
            ArrayList<Producto> productos = LeerFichero();

            // Agrega el nuevo producto
            productos.add(producto);

            // Configura el ObjectWriter con sangría
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

            // Sobrescribe el JSON en un archivo con formato
            try (FileWriter fileWriter = new FileWriter(archivoJSON, false)) {
                objectWriter.writeValue(fileWriter, productos);
                System.out.println("El archivo JSON se ha generado/sobrescrito con éxito");
                return 1; // Éxito
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0; // Error al guardar
    }

    public int eliminarProducto(Producto productoEliminar) {
        ObjectMapper objectMapper = new ObjectMapper();
        File archivoJSON = new File(PathJson);

        try {
            // Lee el archivo JSON existente
            ArrayList<Producto> productos = LeerFichero();

            // Busca el producto a eliminar
            Producto productoAEliminar = null;

            for (Producto producto : productos) {
                if (Objects.equals(producto.getEan13(), productoEliminar.getEan13())) {
                    productoAEliminar = producto;
                    break;
                }
            }

            // Verifica si el producto existe antes de intentar eliminarlo
            if (productoAEliminar != null) {
                // Elimina el producto
                productos.remove(productoAEliminar);

                // Configura el ObjectWriter con sangría
                ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

                // Sobrescribe el JSON en un archivo con formato
                try (FileWriter fileWriter = new FileWriter(archivoJSON, false)) {
                    objectWriter.writeValue(fileWriter, productos);
                    System.out.println("El producto se ha eliminado correctamente");
                    return 1; // Éxito
                }
            } else {
                System.out.println("El producto no existe, no se puede eliminar");
                return 0; // Producto no encontrado
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0; // Error al eliminar
    }






}
