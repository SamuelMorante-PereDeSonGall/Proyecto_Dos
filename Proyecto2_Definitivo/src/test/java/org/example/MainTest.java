package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testOpcionValidaS() {
        assertTrue(Main.opcion('s'));
        assertTrue(Main.opcion('S'));
    }

    @Test
    void testOpcionValidaN() {
        assertFalse(Main.opcion('n'));
        assertFalse(Main.opcion('N'));
    }

    @Test
    void testOpcionInvalidaYLuegoS() {
        // Simulamos que el usuario escribe 'x' (inválido), luego 's' (válido)
        String simulatedInput = "s\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        boolean result = Main.opcion('x'); // entra por el default
        assertTrue(result);
    }

    @Test
    void testOpcionInvalidaYLuegoN() {
        // Simulamos que el usuario escribe 'z' (inválido), luego 'N' (válido)
        String simulatedInput = "N\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        boolean result = Main.opcion('z');
        assertFalse(result);
    }

    @Test
    void testPedirStringEntradaValida() {
        String simulatedInput = "Hola mundo\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        String resultado = Main.pedirString();
        assertEquals("Hola mundo", resultado);
    }

    @Test
    void testPedirStringEntradaVacia() {
        String simulatedInput = "\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        String resultado = Main.pedirString();
        assertEquals("", resultado); // Línea vacía
    }

    @Test
    void testPedirInt_entradaValida() {
        // Simulamos la entrada de un número entero válido
        String input = "42\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Redirigimos la entrada estándar al flujo simulado

        // Llamamos al metodo que debería leer la entrada
        int result = Main.pedirInt();

        // Verificamos que el valor retornado es correcto
        assertEquals(42, result);
    }

    @Test
    void testPedirInt_entradaInvalida() {
        // Simulamos la entrada de un valor no numérico (texto)
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Redirigimos la entrada estándar al flujo simulado

        // Llamamos al metodo que debería manejar la excepción
        int result = Main.pedirInt();

        // Verificamos que el resultado es -999 (el valor que retorna en caso de error)
        assertEquals(-999, result);
    }


    @Test
    void testPedirDouble_entradaInvalida() {
        // Simulamos la entrada de un valor no numérico (texto)
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Redirigimos la entrada estándar al flujo simulado

        // Llamamos al metodo que debería manejar la excepción
        double result = Main.pedirDouble();

        // Verificamos que el resultado es -999 (el valor que retorna en caso de error)
        assertEquals(-999, result);
    }




}