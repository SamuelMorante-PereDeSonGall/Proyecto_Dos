package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FicheroObjetosEscribirTest {
    private static final String NOMBRE_FICHERO = "test_fichero.dat";
    private FicheroObjetosEscribir fichero;

    // Prepara el entorno antes de cada test
    @BeforeEach
    public void setUp() throws IOException {
        // Creamos un nuevo FicheroObjetosEscribir
        fichero = new FicheroObjetosEscribir(NOMBRE_FICHERO);
    }

    // Limpieza después de cada test
    @AfterEach
    public void tearDown() throws IOException {
        // Borramos el archivo de prueba si existe
        File archivo = new File(NOMBRE_FICHERO);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    // Test para asegurarse que el constructor crea y maneja bien el archivo
    @Test
    public void testConstructor() throws IOException {
        File archivo = new File(NOMBRE_FICHERO);
        assertTrue(archivo.exists(), "El archivo debe existir después de crear el objeto FicheroObjetosEscribir");
    }

    // Test para escribir un objeto Usuario en el archivo
    @Test
    public void testWriteUsuario() throws IOException {
        // Usamos el constructor con todos los parámetros
        Usuario usuario = new Usuario("Juan", "Pérez", "123", "sdfaf", true, true);

        // Escribimos el objeto Usuario en el archivo
        fichero.write(usuario);

        // Leemos el archivo y comprobamos que el objeto Usuario se ha escrito correctamente
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            // Leemos el objeto Usuario desde el archivo
            Usuario usuarioLeido = (Usuario) ois.readObject();

            // Verificamos que cada atributo del usuario coincida con el que se ha escrito
            assertEquals(usuario.getNombre(), usuarioLeido.getNombre(), "El nombre del usuario debe coincidir");
            assertEquals(usuario.getApellido(), usuarioLeido.getApellido(), "El apellido del usuario debe coincidir");
            assertEquals(usuario.getDni(), usuarioLeido.getDni(), "El DNI del usuario debe coincidir");
            assertEquals(usuario.getPassword(), usuarioLeido.getPassword(), "La dirección del usuario debe coincidir");
            assertEquals(usuario.getResidente(), usuarioLeido.getResidente(), "El estado 'activo' del usuario debe coincidir");
            assertEquals(usuario.getAdministrador(), usuarioLeido.getAdministrador(), "El estado 'admin' del usuario debe coincidir");

        } catch (ClassNotFoundException e) {
            fail("Clase no encontrada durante la lectura del objeto");
        }
    }

    // Test para escribir un objeto Vuelo en el archivo
    @Test
    public void testWriteVuelo() throws IOException {
        Vuelo vuelo = new Vuelo("V001", Aeropuerto.MAD, Aeropuerto.BCN, LocalDateTime.now(), LocalDateTime.now().plusDays(1),200,100); // Asegúrate de tener un constructor adecuado
        fichero.write(vuelo);

        // Leemos el archivo y comprobamos que el objeto Vuelo se ha escrito correctamente
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            Vuelo vueloLeido = (Vuelo) ois.readObject();
            assertEquals(vuelo.getId(), vueloLeido.getId(), "El ID del vuelo debe coincidir");
            assertEquals(vuelo.getDestino(), vueloLeido.getDestino(), "El destino del vuelo debe coincidir");
        } catch (ClassNotFoundException e) {
            fail("Clase no encontrada durante la lectura del objeto");
        }
    }

    // Test para escribir un array de Usuarios en el archivo
    @Test
    public void testWriteArrayUsuario() throws IOException {
        Usuario[] usuarios = {
                new Usuario("Juan", "Pérez", "1234", "sdfaf", true, true),
                new Usuario("Ana", "Gómez", "12356", "ana", true, false)
        };
        fichero.writeArrayUsuario(usuarios);

        // Leemos el archivo y comprobamos que los objetos Usuario se han escrito correctamente
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            Usuario usuarioLeido1 = (Usuario) ois.readObject();
            Usuario usuarioLeido2 = (Usuario) ois.readObject();
            assertEquals(usuarios[0].getNombre(), usuarioLeido1.getNombre(), "El nombre del primer usuario debe coincidir");
            assertEquals(usuarios[1].getNombre(), usuarioLeido2.getNombre(), "El nombre del segundo usuario debe coincidir");
        } catch (ClassNotFoundException e) {
            fail("Clase no encontrada durante la lectura del objeto");
        }
    }

    // Test para escribir un array de Vuelos en el archivo
    @Test
    public void testWriteArrayVuelo() throws IOException {
        Vuelo[] vuelos = {
                new Vuelo("V001", Aeropuerto.MAD, Aeropuerto.BCN, LocalDateTime.now(), LocalDateTime.now().plusDays(1),200,100),
        new Vuelo("V002", Aeropuerto.PMI, Aeropuerto.BCN, LocalDateTime.now(), LocalDateTime.now().plusDays(2),200,100)
        };
        fichero.writeArrayVuelo(vuelos);

        // Leemos el archivo y comprobamos que los objetos Vuelo se han escrito correctamente
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            Vuelo vueloLeido1 = (Vuelo) ois.readObject();
            Vuelo vueloLeido2 = (Vuelo) ois.readObject();
            assertEquals(vuelos[0].getId(), vueloLeido1.getId(), "El ID del primer vuelo debe coincidir");
            assertEquals(vuelos[1].getId(), vueloLeido2.getId(), "El ID del segundo vuelo debe coincidir");
        } catch (ClassNotFoundException e) {
            fail("Clase no encontrada durante la lectura del objeto");
        }
    }

    // Test para verificar que el metodo close() cierra correctamente el flujo
    @Test
    public void testClose() throws IOException {
        fichero.close();

        // Verificamos que después de llamar al close, el flujo se haya cerrado correctamente
        assertDoesNotThrow(() -> {
            // Intentamos crear un nuevo FicheroObjetosEscribir y escribir algo
            FicheroObjetosEscribir nuevoFichero = new FicheroObjetosEscribir(NOMBRE_FICHERO);
            Usuario usuario = new Usuario("Ana", "Gómez", "12356", "ana", true, false);
            nuevoFichero.write(usuario);
            nuevoFichero.close(); // Cerramos después de escribir
        });
    }

}