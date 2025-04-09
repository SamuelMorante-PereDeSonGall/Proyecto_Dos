import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Vuelo implements Serializable {
    private String id;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private int numPasajeros;
    private final int MAX_PASAJEROS;
    //private int precio;

    public Vuelo(String id, Aeropuerto origen, Aeropuerto destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, int MAX_PASAJEROS) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        numPasajeros = 0;
        this.MAX_PASAJEROS = MAX_PASAJEROS;
        //this.precio = precio;
    }

    public Vuelo(String id, Aeropuerto origen, Aeropuerto destino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, int numPasajeros, int MAX_PASAJEROS) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.numPasajeros = numPasajeros;
        this.MAX_PASAJEROS = MAX_PASAJEROS;
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

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public int getMAX_PASAJEROS() {
        return MAX_PASAJEROS;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }


    //!!!!!!!!!!!!Hacer un metodo separado pasar fecha por parametro
    public String toStringFecha(char c) {
        if (c == 'i'){return fechaSalida.getDayOfMonth()+"/"+ fechaSalida.getMonthValue()+"/"+ fechaSalida.getYear();}
        return fechaLlegada.getDayOfMonth()+"/"+ fechaLlegada.getMonthValue()+"/"+ fechaLlegada.getYear();
    }

    public String toStringHorario(char c) {
        if (c == 'i'){return fechaSalida.getHour()+":"+ fechaSalida.getMinute();}
        return fechaLlegada.getHour()+":"+ fechaLlegada.getMinute();
    }

    public String infoVuelo(){
        return "Vuelo "+id+" "+toStringFecha('i')+" "+toStringHorario('i')+" "+origen+"-"+destino+
                " "+toStringFecha('v')+" "+toStringHorario('v');
    }

    public static void mostrarVuelos() {
        System.out.println("---Selecciona fecha--- ");
        System.out.println("Introduce el día: ");
        int dia = Main.pedirInt();
        System.out.println("Introduce el mes: ");
        int mes = Main.pedirInt();
        System.out.println("Introduce el año: ");
        int year = Main.pedirInt();
        LocalDateTime fecha = null;
        try {
            fecha = LocalDateTime.of(year, mes, dia, 0, 0);
            System.out.println("Mostrando vuelos a partir del " + dia + "/" + mes + "/" + year + ":");
            FicheroUsuariosLeer ful = new FicheroUsuariosLeer("vuelos.dat");
            List<Vuelo> vuelos = ful.leerObjetosVuelos();
            ordenarVuelosPorFechaSalida(vuelos);
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getFechaSalida().equals(fecha) || vuelo.getFechaSalida().isAfter(fecha)) {
                    System.out.println(vuelo.infoVuelo());
                }
            }
            ful.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DateTimeException e){
            System.out.println("Fecha inválida");
        }
    }

    public static void mostrarVuelosSemana(int dia, int mes, int anyo) {
        try {
            LocalDate fechaInicio = LocalDate.of(anyo, mes, dia);
            LocalDate fechaFin = fechaInicio.plusDays(7);
            FicheroUsuariosLeer ful = new FicheroUsuariosLeer("vuelos.dat");
            List<Vuelo> vuelos = ful.leerObjetosVuelos();
            ordenarVuelosPorFechaSalida(vuelos);
            for (Vuelo vuelo : vuelos) {
                LocalDate fechaVuelo = vuelo.getFechaSalida().toLocalDate();
                if (!fechaVuelo.isBefore(fechaInicio) && !fechaVuelo.isAfter(fechaFin)) {
                    System.out.println(vuelo.infoVuelo());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo de vuelos no encontrado", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void ordenarVuelosPorFechaSalida(List<Vuelo> vuelos) {
        vuelos.sort(Comparator.comparing(Vuelo::getFechaSalida));
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

    public String getCiudad() {
        return ciudad;
    }
}
