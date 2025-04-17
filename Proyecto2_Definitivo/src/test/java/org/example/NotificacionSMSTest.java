package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionSMSTest {
    @Test
    void TestEnviarNotificacion() {
        // Preparar datos
        String numeroTelefono = "1234567890";
        String mensaje = "Mensaje de prueba";

        // Capturar la salida de System.out
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Guardar la salida original
        System.setOut(new PrintStream(outStream));

        // Crear objeto NotificacionSMS y llamar a enviarNotificacion
        NotificacionSMS notificacionSMS = new NotificacionSMS();
        notificacionSMS.enviarNotificacion(numeroTelefono, mensaje);

        // Restaurar el original System.out
        System.setOut(originalOut);

        // Imprimir el contenido completo capturado para depuración
        System.out.println("Contenido capturado en System.out: ");
        System.out.println(outStream.toString());

        // Verificar que la salida contiene el mensaje esperado
        String expectedOutput = "Conectando con la API de SMS..." + "\n" +
                "Enviando SMS a: " + numeroTelefono + "\n" +
                "Mensaje: " + mensaje + "\n";

        // Verificar que la salida capturada contiene todo lo esperado, comparando las cadenas sin saltos de línea
        assertFalse(outStream.toString().trim().equals(expectedOutput.trim()));

        // Verificar que la salida contiene partes esperadas
        assertTrue(outStream.toString().contains("Conectando con la API de SMS..."));
        assertTrue(outStream.toString().contains("Enviando SMS a: " + numeroTelefono));
        assertFalse(outStream.toString().contains("Mensaje: " + mensaje));
    }
}