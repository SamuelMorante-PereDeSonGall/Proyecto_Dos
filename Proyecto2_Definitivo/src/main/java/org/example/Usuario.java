package org.example;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private String password;
    private boolean residente;
    private Reserva[] reservas;
    private boolean administrador;
    @Serial
    private static final long serialVersionUID = 1621303937345875877L;

    //Constructores
    public Usuario(String nombre, String apellido, String dni, String password, boolean residente, boolean administrador) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.password = password;
        this.residente = residente;
        reservas = new Reserva[0];
        this.administrador = administrador;
    }

    //Getter y Setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public boolean getResidente() {
        return residente;
    }

    public boolean getAdministrador() {return administrador;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setResidente(Boolean residente) {
        this.residente = residente;
    }

    public String addReserva(String idVuelo, GestorVuelos gv) {
        Vuelo[] vuelos = gv.getVuelos();
        for (int i=0; i<vuelos.length; i++){
            if (vuelos[i].getId().equals(idVuelo)){
                Vuelo vueloEscogido = vuelos[i];
                // Verificamos que el vuelo no este lleno para no dar errores mas adelante
                if (vueloEscogido.getNumPasajeros() >= vueloEscogido.getMAX_PASAJEROS()) {
                    return "No hay plazas disponibles en este vuelo.";
                }

                // Calculamos precio final si es residente el Usurio
                double precioFinal = vueloEscogido.getPrecio();
                if (this.residente && (vueloEscogido.getDestino() == Aeropuerto.PMI || vueloEscogido.getOrigen() == Aeropuerto.PMI)) {
                    precioFinal *= 0.25;
                    System.out.println("Descuento de residente aplicado de un 75%. Precio final: " + precioFinal + "€");
                } else {
                    System.out.println("No eres residente y no te se aplica el descuento de Residente. Precio: " + precioFinal + "€");
                }


                Reserva reserva = new Reserva(Reserva.crearIDAleatorio(),this,vueloEscogido,precioFinal);
                //Crea el array más grande
                reservas = Arrays.copyOf(reservas, reservas.length + 1);
                //Introduce la reserva al usuario
                reservas[reservas.length-1] = reserva;
                //Aumenta el número de Pasajero del vuelo
                vueloEscogido.setNumPasajeros(vueloEscogido.getNumPasajeros() + 1);
                //Para el bucle
                return "Vuelo reservado correctamente.";
            }
        }
        return "Vuelo no existe.";
    }

    public String cancelarReserva(String idReserva, GestorVuelos gv) {
        for (int i=0; i<reservas.length; i++){
            if (reservas[i].getId().equals(idReserva)){
                Vuelo vueloCancelado = reservas[i].getVuelo(); //Cogemos el Vuelo de la reserva
                vueloCancelado.setNumPasajeros(vueloCancelado.getNumPasajeros() - 1); // le restamos un pasajero al vuelo
                reservas[i] = reservas[reservas.length-1];
                reservas = Arrays.copyOf(reservas, reservas.length - 1);
                Arrays.sort(reservas, Comparator.comparing(r -> r.getVuelo().getFechaSalida()));
                return "Reserva cancelada correctamente.";
            }
        }
        return "Reserva no existe.";
    }

    //Metodo toString
    @Override
    public String toString() {
        return "{ " +
                "Nombre: '" + nombre + '\'' +
                ", apellido: '" + apellido + '\'' +
                ", dni: '" + dni + '\'' +
                ", residente: " + residente +
                '}';
    }

    //Metodo que consulta las Reservas de un usuario
    public String consultarReservas(){
        System.out.println("\n--------------------------");
        if (reservas == null || reservas.length == 0) {
            return "No tienes reservas.";
        } else {
            String res = "";
            for (int i=0; i<reservas.length; i++){
                res += reservas[i].mostrarReserva()+"\n";
            }
            return res;
        }
    }
}
