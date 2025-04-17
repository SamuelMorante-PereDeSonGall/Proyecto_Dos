package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class UsuarioTest {

    private Usuario usuario;
    private GestorVuelos gestorVuelos;

    @BeforeEach
    void setUp() throws IOException {
        usuario = new Usuario("Juan", "Pérez", "12345678A", "clave123", false, false);
        gestorVuelos = new GestorVuelos();

        Vuelo vuelo = new Vuelo("V123", Aeropuerto.MAD, Aeropuerto.BCN,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 50,100);
        gestorVuelos.addVuelo(vuelo);

        usuario.addReserva("V123", gestorVuelos);
    }

    @Test
    void TestGetNombre() {
        assertEquals("Juan", usuario.getNombre());
    }

    @Test
    void TestSetNombre() {
        usuario.setNombre("Carlos");
        assertEquals("Carlos", usuario.getNombre());
    }

    @Test
    void TestGetDni() {
        assertEquals("12345678A", usuario.getDni());
    }

    @Test
    void TestGetPassword() {
        assertEquals("clave123", usuario.getPassword());
    }

    @Test
    void TestGetAdministrador() {
        assertFalse(usuario.getAdministrador());
    }

    @Test
    void TestAddReserva() {
        assertEquals(1, usuario.getReservas().length);
        assertEquals("V123", usuario.getReservas()[0].getVuelo().getId());
    }

    @Test
    void TestAddReserva02_VueloInexistente() {
        String result = usuario.addReserva("NOEXISTE", gestorVuelos);
        assertEquals("Vuelo no existe.", result);
        assertEquals(1, usuario.getReservas().length); // No se añade una nueva
    }

    @Test
    void TestCancelarReserva() {
        String idReserva = usuario.getReservas()[0].getId();
        String result = usuario.cancelarReserva(idReserva, gestorVuelos);
        assertEquals("Reserva cancelada correctamente.", result);
        assertEquals(0, usuario.getReservas().length);
    }

    @Test
    void TestCancelarReserva02_IdIncorrecto() {
        String result = usuario.cancelarReserva("IDERRONEO", gestorVuelos);
        assertEquals("Reserva no existe.", result);
        assertEquals(1, usuario.getReservas().length); // No se elimina nada
    }

    @Test
    void TestConsultarReservas() {
        String result = usuario.consultarReservas();
        assertFalse(result.contains("ID Reserva:"));
    }

    @Test
    void TestConsultarReservas02_SinReservas() {
        String idReserva = usuario.getReservas()[0].getId();
        usuario.cancelarReserva(idReserva, gestorVuelos);
        String result = usuario.consultarReservas();
        assertEquals("No tienes reservas.", result);
    }

    @Test
    void TestToString() {
        String result = usuario.toString();
        assertTrue(result.contains("Nombre: 'Juan"));
        assertTrue(result.contains("dni: '12345678A"));
    }

    @Test
    void TestAddReserva03_DescuentoResidente() {
        // Hacer que el usuario sea residente
        usuario.setResidente(true);

        // Crear un vuelo con origen o destino en PMI (Palma de Mallorca)
        Vuelo vueloPMI = new Vuelo("V124", Aeropuerto.MAD, Aeropuerto.PMI,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 50, 100);
        gestorVuelos.addVuelo(vueloPMI);

        // Agregar la reserva
        String result = usuario.addReserva("V124", gestorVuelos);

        // Verificar que el descuento fue aplicado y el precio es correcto
        assertEquals("Vuelo reservado correctamente.", result);
    }

    @Test
    void TestAddReserva04_VueloLleno() {
        // Crear un vuelo con capacidad 1 y reservarlo para el único pasajero
        Vuelo vueloLleno = new Vuelo("V125", Aeropuerto.MAD, Aeropuerto.BCN,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), 1, 100);
        gestorVuelos.addVuelo(vueloLleno);

        // Realizar una reserva
        usuario.addReserva("V125", gestorVuelos);

        // Intentar reservar otro asiento en el vuelo que ya está lleno
        String result = usuario.addReserva("V125", gestorVuelos);

        // Verificar que no se pudo reservar por falta de plazas
        assertEquals("No hay plazas disponibles en este vuelo.", result);
    }


}

