import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.WeekFields;
import java.util.*;

public class GestorVuelos {

    private Vuelo[] vuelos;

    //Constructor
    public GestorVuelos() throws IOException {
        FicheroObjetosLeer ficheroVuelos = new FicheroObjetosLeer("vuelos.dat");
        vuelos = ficheroVuelos.cargarVuelos();
    }

    //Getter y Setter
    public Vuelo[] getVuelos() {
        return vuelos;
    }

    public void setVuelos(Vuelo[] vuelos) {
        this.vuelos = vuelos;
    }

    public void guardarVuelos() throws IOException {
        FicheroObjetosEscribir fue = new FicheroObjetosEscribir("vuelos.dat");
        vuelos = getVuelos();
        fue.writeArrayVuelo(vuelos);
        fue.close();
    }

    public void addVuelo(Vuelo vuelo) {
        vuelos = Arrays.copyOf(vuelos, vuelos.length + 1);
        vuelos[vuelos.length - 1] = vuelo;
        System.out.println("Vuelo guardado correctamente: " + vuelos[vuelos.length - 1].infoVuelo());
    }

    public Vuelo textoAddVuelo() {
        //Texto
        System.out.println("Ingresa el ID del vuelo: ");
        String id = Main.pedirString();
        System.out.println("Ingresa el aeropuerto origen: ");
        Aeropuerto origen = seleccionarAeropuerto();
        System.out.println("HAS ELEGIDO: "+origen);
        System.out.println("Introduce la fecha de salida: ");
        LocalDateTime fechaSalida = seleccionarLocalDateTime();;
        System.out.println("Ingresa el aeropuerto destino: ");
        Aeropuerto destino = seleccionarAeropuerto();
        while (destino == origen){
            System.out.println("No puedes seleccionar el mismo aeropuerto. Vuelve a intentarlo: ");
            destino = seleccionarAeropuerto();
        }
        System.out.println("HAS ELEGIDO: "+destino);
        System.out.println("Introduce la fecha de llegada: ");
        LocalDateTime fechaLlegada = seleccionarLocalDateTime();
        while (fechaLlegada.isBefore(fechaSalida)){
            System.out.println("No puedes introducir una fecha de llegada anterior a la de salida. Vuelve a intentarlo: ");
            fechaLlegada = seleccionarLocalDateTime();
        }
        System.out.println("Capacidad máxima de pasajeros: ");
        int max_pasajeros = Main.pedirInt();
        System.out.println("Si hubiese pasajeros con reserva, introduce el número (en caso negativo introducir 0): ");
        int num_pasajeros = Main.pedirInt();
        while (num_pasajeros > max_pasajeros || num_pasajeros < 0){
            System.out.println("El número de pasajeros es menor a 0 o mayor que la capacidad máxima. Vuelve a intentarlo: ");
            num_pasajeros = Main.pedirInt();
        }
        System.out.println("Por último, introduce el precio del billete: ");
        double precio = Main.pedirDouble();

        return new Vuelo(id,origen,destino,fechaSalida,fechaLlegada,max_pasajeros,num_pasajeros,precio);
    }

    //Usuario seleccione una fecha
    public LocalDateTime seleccionarLocalDateTime() {
        System.out.println("Ingresa el día: ");
        int dia = Main.pedirInt();
        System.out.println("Ingresa el mes: ");
        int mes = Main.pedirInt();
        System.out.println("Ingresa el año: ");
        int year = Main.pedirInt();

        System.out.println("Ingresa la hora (formato 24 horas): ");
        int hora = Main.pedirInt();
        while (hora < 0 || hora > 23){
            System.out.println("No has puesto un formato válido. Vuelve a intentarlo: ");
            hora = Main.pedirInt();
        }
        System.out.println("Ingresa los minutos: ");
        int minutos = Main.pedirInt();
        while (minutos < 0 || minutos > 59){
            System.out.println("No has puesto un formato válido. Vuelve a intentarlo: ");
            minutos = Main.pedirInt();
        }

        return LocalDateTime.of(year,mes,dia,hora,minutos);

    }

    //Usuario seleccione un aeropuerto
    private Aeropuerto seleccionarAeropuerto(){
        System.out.println("Elige un aeropuerto de origen:");

        Aeropuerto[] aeropuertos = Aeropuerto.values();
        for (int i = 0; i < aeropuertos.length; i++) {
            System.out.println((i + 1) + ". " + aeropuertos[i]);
        }

        int opcion;
        do {
            System.out.print("Introduce el número correspondiente: ");
            opcion = Main.pedirInt(); // tu metodo de pedir int por teclado
        } while (opcion < 1 || opcion > aeropuertos.length);

        return aeropuertos[opcion - 1];

    }

    public void mostrarVuelos(Vuelo[] vuelos) {

    }

    public void ordenarVuelosFecha() {
        Arrays.sort(vuelos, Comparator.comparing(Vuelo::getFechaSalida));
    }

    public void mostrarVuelosPosteriores(LocalDateTime fecha){
        ordenarVuelosFecha();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFechaSalida().isAfter(fecha) || vuelo.getFechaSalida().isEqual(fecha)) {
                System.out.println(vuelo.infoVuelo());
            }
        }
    }

    public LocalDateTime calcularLunesSemana(int semana){ // Iniciar el primer día del año a medianoche
        LocalDateTime firstDayOfYear = LocalDateTime.of(2025, 1, 1, 0, 0);

        // Obtener el primer lunes del año, ajustando según la semana
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // Calcular la fecha del primer lunes de la semana solicitada
        return firstDayOfYear.with(weekFields.weekOfYear(), semana)
                .with(weekFields.dayOfWeek(), 1);
    }

}
