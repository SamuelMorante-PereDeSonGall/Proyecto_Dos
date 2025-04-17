package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GestorUsuarioTest {

    private GestorUsuario gestor;

    // Creamos un gestor limpio para cada test
    @BeforeEach
    void setUp() throws IOException {
        // Simular archivo vacío al iniciar
        FileOutputStream fos = new FileOutputStream("usuarios.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.close();

        gestor = new GestorUsuario();
    }

    @Test
    void testAddUsuarioYComprobarUsuario() throws IOException {
        Usuario u = new Usuario("Pau", "García", "12345678A", "pass123", false, false);
        gestor.addUsuario(u);

        Usuario encontrado = gestor.comprobarUsuario("Pau", "pass123");
        assertNotNull(encontrado);
        assertEquals("Pau", encontrado.getNombre());
        assertEquals("pass123", encontrado.getPassword());
    }

    @Test
    void testComprobarUsuarioNoExistente() throws IOException {
        Usuario resultado = gestor.comprobarUsuario("NoExiste", "clave");
        assertNull(resultado);
    }







}
