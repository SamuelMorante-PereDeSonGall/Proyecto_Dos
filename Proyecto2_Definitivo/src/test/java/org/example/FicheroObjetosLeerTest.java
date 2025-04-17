package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FicheroObjetosLeerTest {
    @Test
    public void testToString() throws IOException {
        // Escribimos algunos usuarios en un archivo para poder leerlos luego
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(new Usuario("Juan", "Pérez", "1234", "sdfaf", true, true));
            oos.writeObject(new Usuario("Ana", "Gómez", "12356", "ana", true, false));
        }

        FicheroObjetosLeer fichero = new FicheroObjetosLeer("usuarios.dat");
        String resultado = fichero.toString();

        // Verificamos que los usuarios están correctamente representados en el string
        assertTrue(resultado.contains("Juan"));
        assertTrue(resultado.contains("Ana"));
    }

    @Test
    public void testCargarUsuarios() throws IOException {
        // Escribimos algunos usuarios en un archivo para poder leerlos luego
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(new Usuario("Juan", "Pérez", "1234", "sdfaf", true, true));
            oos.writeObject(new Usuario("Ana", "Gómez", "12356", "ana", true, false));
        }

        FicheroObjetosLeer fichero = new FicheroObjetosLeer("usuarios.dat");
        Usuario[] usuarios = fichero.cargarUsuarios();

        // Verificamos que los usuarios se cargaron correctamente
        assertEquals(2, usuarios.length);
        assertEquals("Juan", usuarios[0].getNombre());
        assertEquals("Ana", usuarios[1].getNombre());
    }

    @Test
    public void testCargarVuelos() throws IOException {
        // Escribimos algunos vuelos en un archivo para poder leerlos luego
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vuelos.dat"))) {
            oos.writeObject(new Vuelo("V001", Aeropuerto.MAD, Aeropuerto.BCN, LocalDateTime.now(), LocalDateTime.now().plusDays(1),200,100));
            oos.writeObject(new Vuelo("V002", Aeropuerto.PMI, Aeropuerto.BCN, LocalDateTime.now(), LocalDateTime.now().plusDays(2),201,110));
        }

        FicheroObjetosLeer fichero = new FicheroObjetosLeer("vuelos.dat");
        Vuelo[] vuelos = fichero.cargarVuelos();

        // Verificamos que los vuelos se cargaron correctamente
        assertEquals(2, vuelos.length);
        assertEquals("V001", vuelos[0].getId());
        assertEquals("V002", vuelos[1].getId());
    }

}