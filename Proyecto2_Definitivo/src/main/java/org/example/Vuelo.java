package org.example;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Vuelo implements Serializable {
    private String id;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private int numPasajeros;
    private final int MAX_PASAJEROS;
    private double precio;

    public Vuelo(String id, Aeropuerto origen, Aeropuerto destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, int MAX_PASAJEROS, double precio) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        numPasajeros = 0;
        this.MAX_PASAJEROS = MAX_PASAJEROS;
        this.precio = precio;
    }

    public Vuelo(String id, Aeropuerto origen, Aeropuerto destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, int numPasajeros, int MAX_PASAJEROS, double precio) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.numPasajeros = numPasajeros;
        this.MAX_PASAJEROS = MAX_PASAJEROS;
        this.precio = precio;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public LocalDateTime getFechaLlegada() {return fechaLlegada;}

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public int getMAX_PASAJEROS() {
        return MAX_PASAJEROS;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }


    public String toStringFecha(LocalDateTime fecha){
        return fecha.getDayOfMonth()+"/"+ fecha.getMonthValue()+"/"+ fecha.getYear()+" "+fecha.getHour()+":"+ fecha.getMinute();
    }

    public String infoVuelo(){
        return "Vuelo "+id+" "+toStringFecha(fechaSalida)+" "+origen+"-"+destino+
                " "+toStringFecha(fechaLlegada);
    }

    public double getPrecio() {
        return precio;
    }

    public String toString(){
        return "Vuelo "+id+" "+toStringFecha(fechaSalida)+" "+origen+"-"+destino+
                " "+toStringFecha(fechaLlegada)+", número de pasajeros: "+numPasajeros+", capacidad máxima: "+MAX_PASAJEROS+", precio: "+precio;
    }
}

enum Aeropuerto implements Serializable {
    PMI("Mallorca"),
    MAD("Madrid"), // Madrid, España
    BCN("Barcelona"), // Barcelona, España
    CDG("París"), // París, Francia
    LHR("Londres"), // Londres, Reino Unido
    JFK("Nueva York"), // Nueva York, EE.UU.
    LAX("Los Ángeles"), // Los Ángeles, EE.UU.
    NRT("Tokio"), // Tokio, Japón
    SIN("Singapur"), // Singapur
    AMS("Ámsterdam"), // Ámsterdam, Países Bajos
    FRA("Frankfurt"), // Frankfurt, Alemania
    DXB("Dubái"), // Dubái, Emiratos Árabes Unidos
    SCQ("Santiago"), // Santiago, España
    MXP("Milán"), // Milán, Italia
    FCO("Roma"), // Roma, Italia
    DME("Moscú"), // Moscú, Rusia
    PEK("Pekín"), // Pekín, China
    EZE("Buenos Aires"), // Buenos Aires, Argentina
    BOG("Bogotá"), // Bogotá, Colombia
    LIM("Lima"), // Lima, Perú
    MEX("Ciudad de México"), // Ciudad de México, México
    GRU("São Paulo"), // São Paulo, Brasil
    GIG("Río de Janeiro"), // Río de Janeiro, Brasil
    SFO("San Francisco"), // San Francisco, EE.UU.
    YYZ("Toronto"), // Toronto, Canadá
    VKO("Moscú"), // Moscú, Rusia
    MAD_T1("Madrid T1"), // Madrid, España
    LGW("Londres"), // Londres, Reino Unido
    LUX("Luxemburgo"), // Luxemburgo
    ORY("París"); // París, Francia

    private final String ciudad;

    Aeropuerto(String ciudad) {
        this.ciudad = ciudad;
    }
}
