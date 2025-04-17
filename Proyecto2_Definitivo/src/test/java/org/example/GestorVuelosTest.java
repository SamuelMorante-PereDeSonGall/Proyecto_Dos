package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorVuelosTest {

    /*
    @Test
    public void testTextoAddVuelo() throws IOException {
        GestorVuelos gv = new GestorVuelos();

        // Simulamos todas las entradas en el orden que el programa espera
        String input = "V001\n";               // El usuario introduce el ID del vuelo
        input += "1\n";                        // El usuario selecciona "1" para PMI (origen)
        input += "2\n";                        // El usuario selecciona "2" para MAD (destino)
        input += "2025-04-20T10:00\n";         // El usuario introduce la fecha de salida
        input += "2025-04-20T12:00\n";         // El usuario introduce la fecha de llegada
        input += "50\n";                       // El usuario introduce el número de pasajeros
        input += "150\n";                      // El usuario introduce el número máximo de pasajeros
        input += "100.0\n";                    // El usuario introduce el precio

        // Creamos un InputStream a partir del input simulado
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Redirige la entrada estándar para el test

        // Simulamos los valores para el vuelo que deberían ser creados
        String id = "V001";
        Aeropuerto origen = Aeropuerto.PMI;  // PMI se corresponde con la opción "1"
        Aeropuerto destino = Aeropuerto.MAD; // MAD se corresponde con la opción "2"
        LocalDateTime fechaSalida = LocalDateTime.of(2025, 4, 20, 10, 0);
        LocalDateTime fechaLlegada = LocalDateTime.of(2025, 4, 20, 12, 0);
        int numPasajeros = 50;
        int maxPasajeros = 150;
        double precio = 100.0;

        // Creamos el vuelo a partir de los valores simulados
        Vuelo vuelo = new Vuelo(id, origen, destino, fechaSalida, fechaLlegada, numPasajeros, maxPasajeros, precio);

        // Ejecutamos el método textoAddVuelo() para ver si se crea correctamente el vuelo
        Vuelo vueloCreado = gv.textoAddVuelo();

        // Verificamos que el vuelo creado tiene los valores correctos
        assertEquals(vuelo.getId(), vueloCreado.getId());
        assertEquals(vuelo.getOrigen(), vueloCreado.getOrigen());
        assertEquals(vuelo.getDestino(), vueloCreado.getDestino());
        assertEquals(vuelo.getFechaSalida(), vueloCreado.getFechaSalida());
        assertEquals(vuelo.getFechaLlegada(), vueloCreado.getFechaLlegada());
        assertEquals(vuelo.getNumPasajeros(), vueloCreado.getNumPasajeros());
        assertEquals(vuelo.getMAX_PASAJEROS(), vueloCreado.getMAX_PASAJEROS());
        assertEquals(vuelo.getPrecio(), vueloCreado.getPrecio(), 0.01);
    }

*/

    @Test
    public void testOrdenarVuelosFecha() throws IOException {
        GestorVuelos gve = new GestorVuelos();

        // Crear vuelos con fechas desordenadas
        Vuelo vuelo1 = new Vuelo("test", Aeropuerto.MAD, Aeropuerto.PMI, LocalDateTime.of(2025, 4, 20, 10, 0),
                LocalDateTime.of(2025, 4, 20, 12, 0), 50, 150, 100.0);
        Vuelo vuelo2 = new Vuelo("test", Aeropuerto.MAD, Aeropuerto.PMI, LocalDateTime.of(2025, 4, 18, 10, 0),
                LocalDateTime.of(2025, 4, 18, 12, 0), 60, 150, 120.0);
        Vuelo vuelo3 = new Vuelo("test", Aeropuerto.MAD, Aeropuerto.PMI, LocalDateTime.of(2025, 4, 19, 10, 0),
                LocalDateTime.of(2025, 4, 19, 12, 0), 70, 150, 110.0);

        // Añadir vuelos a GestorVuelos
        gve.addVuelo(vuelo1);
        gve.addVuelo(vuelo2);
        gve.addVuelo(vuelo3);


        // Ejecutar el metodo a probar
        gve.ordenarVuelosFecha();
        Vuelo[] vuelosOrdenar = new Vuelo[3];
        int j = 0;
        for (int i = 0; i < gve.getVuelos().length; i++) {
            if (gve.getVuelos()[i].getId().equals("test")){
                vuelosOrdenar[j] = gve.getVuelos()[i];
                j++;
            }
        }

        // Verificar que los vuelos están ordenados por fecha de salida
        assertEquals(vuelo2, vuelosOrdenar[0]);
        assertEquals(vuelo3, vuelosOrdenar[1]);
        assertEquals(vuelo1, vuelosOrdenar[2]);
    }

    @Test
    void calcularLunesSemanaTest() throws IOException {
        GestorVuelos gestor = new GestorVuelos();

        // Semana 1 de 2025 comienza el lunes 30 de diciembre de 2024 (según ISO-8601)
        LocalDateTime esperado = LocalDateTime.of(2024, 12, 30, 0, 0);
        LocalDateTime resultado = gestor.calcularLunesSemana(1);

        assertEquals(esperado, resultado, "El lunes de la semana 1 debería ser 30/12/2024 a las 00:00");
    }

    @Test
    void testMostrarVuelosPosteriores() throws Exception {
        // Preparar vuelos
        LocalDateTime ahora = LocalDateTime.of(2025, 4, 17, 12, 0);

        Vuelo vuelo1 = new Vuelo("V001", Aeropuerto.MAD, Aeropuerto.BCN,
                ahora.minusDays(1), ahora.minusDays(1).plusHours(2), 10, 100, 50);
        Vuelo vuelo2 = new Vuelo("V002", Aeropuerto.MAD, Aeropuerto.BCN,
                ahora.plusDays(1), ahora.plusDays(1).plusHours(2), 20, 100, 60);
        Vuelo vuelo3 = new Vuelo("V003", Aeropuerto.MAD, Aeropuerto.BCN,
                ahora.plusDays(2), ahora.plusDays(2).plusHours(2), 100, 100, 70); // lleno

        // Instanciar GestorVuelos
        GestorVuelos gestor = new GestorVuelos();

        // Usar reflexión para inyectar los vuelos en el campo privado
        Field vuelosField = GestorVuelos.class.getDeclaredField("vuelos");
        vuelosField.setAccessible(true);
        vuelosField.set(gestor, new Vuelo[]{vuelo1, vuelo2, vuelo3});

        // Capturar salida del sistema
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        gestor.mostrarVuelosPosteriores(ahora);

        System.setOut(System.out); // Restaurar

        String salida = outContent.toString();
        assertTrue(salida.contains("V002"));
        assertFalse(salida.contains("V001"));
        assertFalse(salida.contains("V003"));
    }


}