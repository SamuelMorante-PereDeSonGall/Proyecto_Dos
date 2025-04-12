import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorVuelos {

    private List<Vuelo> vuelos;

    public GestorVuelos() {
        try {
            FicheroUsuariosLeer ful = new FicheroUsuariosLeer("vuelos.dat");
            vuelos = ful.leerObjetosVuelos();
            ful.close();
        } catch (IOException e) {
            System.out.println("Error al leeer los vuelos.");
            vuelos = new ArrayList<>();
        }
    }


    public void mostrarVuelos() {
        System.out.println("---Selecciona fecha---");
        System.out.print("Introduce el día: ");
        int dia = Main.pedirInt();
        System.out.print("Introduce el mes: ");
        int mes = Main.pedirInt();
        System.out.print("Introduce el año: ");
        int year = Main.pedirInt();
        try {
            LocalDateTime fecha = LocalDateTime.of(year, mes, dia, 0, 0);
            System.out.println("Mostrando vuelos disponibles a partir del " + dia + "/" + mes + "/" + year + ":");
            Vuelo.ordenarVuelosPorFechaSalida(vuelos);
            boolean hayVuelos = false;
            for (Vuelo vuelo : vuelos) {
                boolean fechaValida = !vuelo.getFechaSalida().isBefore(fecha);
                boolean tienePlazas = vuelo.getNumPasajeros() < vuelo.getMAX_PASAJEROS();
                if (fechaValida && tienePlazas) {
                    System.out.println(vuelo.infoVuelo());
                    hayVuelos = true;
                }
            }
            if (!hayVuelos) {
                System.out.println("No hay vuelos disponibles con plazas a partir de esa fecha.");
            }
        } catch (DateTimeException e) {
            System.out.println("Fecha inválida.");
        }
    }


    public void guardarVuelos() {  // Esta funcion se utilizara alfinal cuando salga
                                   // de la aplicacion para guardar los vuelos
        try {
            FicheroUsuariosEscritura fue = new FicheroUsuariosEscritura("vuelos.dat");
            fue.writeListaVuelo(vuelos);
            fue.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los vuelos.");
        }
    }




}
