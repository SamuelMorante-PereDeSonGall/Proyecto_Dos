package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

}