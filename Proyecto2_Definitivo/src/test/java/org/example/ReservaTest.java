package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    @Test
    void TestToString() {
        // Preparar los objetos necesarios
        String idReserva = "R123";
        Vuelo vuelo = new Vuelo("V123", Aeropuerto.MAD, Aeropuerto.BCN,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2),
                30, 50, 100.0);
        double precioFinal = 75.0;

        // Crear la reserva
        Reserva reserva = new Reserva(idReserva, null, vuelo, precioFinal); // Suponiendo que el constructor de Reserva es correcto

        // Llamar al método toString
        String result = reserva.toString();

        // Verificar que la salida sea la esperada
        String expectedOutput = "{" +
                "ID reserva: '" + idReserva + '\'' +
                ", vuelo=" + vuelo.toString() +  // Aquí usamos el toString() del vuelo
                ", precioFinal=" + precioFinal +
                '}';

        // Comprobar que el resultado es correcto
        assertEquals(expectedOutput, result);
    }

}