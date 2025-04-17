package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VueloTest {
    @Test
    void TestConstructorVuelo() {
        // Datos de entrada para el vuelo
        String idVuelo = "V123";
        Aeropuerto origen = Aeropuerto.MAD;
        Aeropuerto destino = Aeropuerto.BCN;
        LocalDateTime fechaSalida = LocalDateTime.now().plusDays(1);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(2);
        int numPasajeros = 30;
        int maxPasajeros = 50;
        double precio = 100.0;

        // Crear el vuelo usando el constructor
        Vuelo vuelo = new Vuelo(idVuelo, origen, destino, fechaSalida, fechaLlegada, numPasajeros, maxPasajeros, precio);

        // Verificar que los atributos se asignen correctamente
        assertEquals(idVuelo, vuelo.getId());
        assertEquals(origen, vuelo.getOrigen());
        assertEquals(destino, vuelo.getDestino());
        assertEquals(fechaSalida, vuelo.getFechaSalida());
        assertEquals(fechaLlegada, vuelo.getFechaLlegada());
        assertEquals(numPasajeros, vuelo.getNumPasajeros());
        assertEquals(maxPasajeros, vuelo.getMAX_PASAJEROS());
        assertEquals(precio, vuelo.getPrecio(), 0.01);  // Se usa un margen de error para comparar precios decimales
    }

    @Test
    void TestConstructorVueloConNumPasajerosCero() {
        // Datos de entrada para el vuelo
        String idVuelo = "V123";
        Aeropuerto origen = Aeropuerto.MAD;
        Aeropuerto destino = Aeropuerto.BCN;
        LocalDateTime fechaSalida = LocalDateTime.now().plusDays(1);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(2);
        int maxPasajeros = 50;
        double precio = 100.0;

        // Crear el vuelo usando el constructor que inicializa numPasajeros a 0
        Vuelo vuelo = new Vuelo(idVuelo, origen, destino, fechaSalida, fechaLlegada, maxPasajeros, precio);

        // Verificar que los atributos se asignen correctamente
        assertEquals(idVuelo, vuelo.getId());
        assertEquals(origen, vuelo.getOrigen());
        assertEquals(destino, vuelo.getDestino());
        assertEquals(fechaSalida, vuelo.getFechaSalida());
        assertEquals(fechaLlegada, vuelo.getFechaLlegada());
        assertEquals(0, vuelo.getNumPasajeros());  // Verificar que numPasajeros sea 0
        assertEquals(maxPasajeros, vuelo.getMAX_PASAJEROS());
        assertEquals(precio, vuelo.getPrecio(), 0.01);  // Se usa un margen de error para comparar precios decimales
    }

    @Test
    void TestSetNumPasajeros() {
        // Crear el vuelo
        String idVuelo = "V123";
        Aeropuerto origen = Aeropuerto.MAD;
        Aeropuerto destino = Aeropuerto.BCN;
        LocalDateTime fechaSalida = LocalDateTime.now().plusDays(1);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(2);
        int maxPasajeros = 50;
        double precio = 100.0;

        Vuelo vuelo = new Vuelo(idVuelo, origen, destino, fechaSalida, fechaLlegada, maxPasajeros, precio);

        // Establecer el número de pasajeros
        int nuevoNumPasajeros = 30;
        vuelo.setNumPasajeros(nuevoNumPasajeros);

        // Verificar que el número de pasajeros se ha actualizado correctamente
        assertEquals(nuevoNumPasajeros, vuelo.getNumPasajeros());
    }

    @Test
    void TestToStringFecha() {
        // Crear una fecha para la prueba
        LocalDateTime fecha = LocalDateTime.of(2025, 4, 18, 14, 5, 0, 0);

        // Crear el vuelo
        String idVuelo = "V123";
        Aeropuerto origen = Aeropuerto.MAD;
        Aeropuerto destino = Aeropuerto.BCN;
        LocalDateTime fechaSalida = LocalDateTime.now().plusDays(1);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(2);
        int maxPasajeros = 50;
        double precio = 100.0;

        Vuelo vuelo = new Vuelo(idVuelo, origen, destino, fechaSalida, fechaLlegada, maxPasajeros, precio);

        // Llamar a toStringFecha con la fecha creada
        String result = vuelo.toStringFecha(fecha);

        // Verificar que el formato de fecha es correcto: día/mes/año hora:minuto
        assertEquals("18/4/2025 14:5", result);
    }

    @Test
    void TestInfoVuelo() {
        // Crear el vuelo
        String idVuelo = "V123";
        Aeropuerto origen = Aeropuerto.MAD;
        Aeropuerto destino = Aeropuerto.BCN;
        LocalDateTime fechaSalida = LocalDateTime.now().plusDays(1);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(2);
        int maxPasajeros = 50;
        double precio = 100.0;

        Vuelo vuelo = new Vuelo(idVuelo, origen, destino, fechaSalida, fechaLlegada, maxPasajeros, precio);

        // Llamar al método infoVuelo
        String result = vuelo.infoVuelo();

        // Verificar que la información del vuelo sea correcta
        String expected = "Vuelo V123 " + vuelo.toStringFecha(fechaSalida) + " MAD-BCN " + vuelo.toStringFecha(fechaLlegada);
        assertEquals(expected, result);
    }

    @Test
    void TestToString() {
        // Crear el vuelo
        String idVuelo = "V123";
        Aeropuerto origen = Aeropuerto.MAD;
        Aeropuerto destino = Aeropuerto.BCN;
        LocalDateTime fechaSalida = LocalDateTime.now().plusDays(1);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(2);
        int maxPasajeros = 50;
        double precio = 100.0;
        int numPasajeros = 30;

        Vuelo vuelo = new Vuelo(idVuelo, origen, destino, fechaSalida, fechaLlegada, maxPasajeros, precio);

        // Establecer el número de pasajeros en el vuelo
        vuelo.setNumPasajeros(numPasajeros);

        // Llamar al método toString
        String result = vuelo.toString();

        // Construir el resultado esperado
        String expected = "Vuelo " + idVuelo + " " + vuelo.toStringFecha(fechaSalida) + " " + origen + "-" + destino +
                " " + vuelo.toStringFecha(fechaLlegada) + ", número de pasajeros: " + numPasajeros +
                ", capacidad máxima: " + maxPasajeros + ", precio: " + precio;

        // Verificar que el resultado de toString() es correcto
        assertEquals(expected, result);
    }




}