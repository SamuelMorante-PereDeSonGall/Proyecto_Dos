import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().metodoPrincipal();
    }

    private void metodoPrincipal() throws IOException {

        //ful.leer();
        textoInicial();

    }

    private void textoInicial() throws IOException {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("Bienvenido a SkyPauScanner");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1 - Crear usuario");
            System.out.println("2 - Iniciar sesión");
            System.out.println("3 - Salir");
            System.out.println("--------------------------");
            opcion = pedirInt();
            switch (opcion) {
                case 1:
                    Usuario usuario = Usuario.crearUsuario();
                    if (usuario != null) {
                        FicheroUsuariosEscritura fue = new FicheroUsuariosEscritura("usuarios.dat");
                        fue.write(usuario);
                        fue.close();
                        menuNormal(usuario);
                    }
                    break;
                case 2:
                    usuario = Usuario.iniciarSesion();
                    if (usuario != null) {
                        menuNormal(usuario);
                    }
                    break;
                default:
                    System.out.println("ADIÓS");
                    opcion = 3;
            }
        }
    }

    private void menuNormal(Usuario usuario) throws IOException {
        int opcion = 0;
        while (opcion != 8) {
            System.out.println("--------------------------");
            System.out.println("Bienvenido " + usuario.getNombre());
            System.out.println("¿Qué desea hacer?");
            System.out.println("1 - Reservar un vuelo");
            System.out.println("2 - Consultar vuelos reservados");
            System.out.println("3 - Cancelar una reserva");
            System.out.println("4 - Consultar vuelos disponibles, por dia y por semana");
            if (usuario.getAdministrador()) {
                System.out.println("5. Añadir vuelos disponibles");
                System.out.println("6. Generar vuelos de forma aleatória");
                System.out.println("7. Enviar notificaciones a un usuario");
                System.out.println("Cualquier otra opción: Cerrar sesión");
            }
            System.out.println("--------------------------");
            opcion = pedirInt();
            if (!usuario.getAdministrador() && opcion > 4) {
                opcion = 8;
            }
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    Reserva[] reservas = usuario.getReservas();
                    for (int i = 0; i < reservas.length; i++) {
                        System.out.println(reservas[i]);
                    }
                    break;
                case 3:
                    break;
                case 4:
//                    Scanner sc = new Scanner(System.in);
//                    System.out.println("Introduce el día:");
//                    int dia = sc.nextInt();
//                    System.out.println("Introduce el mes:");
//                    int mes = sc.nextInt();
//                    System.out.println("Introduce el año:");
//                    int anio = sc.nextInt();
//                    Vuelo.mostrarVuelosSemana(dia, mes, anio);
                    Vuelo.mostrarVuelos();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    enviarNotificacion();
                    break;
                default:
                    System.out.println("ADIÓS");
                    opcion = 8;
            }
        }
    }

    public static void enviarNotificacion() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--------------------------");
            System.out.println("¿Qué tipo de notificacion quiere enviar?");
            System.out.println("1 - SMS");
            System.out.println("2 - Email");
            System.out.println("3 - Salir de esta opcion");
            System.out.println("--------------------------");
            opcion = pedirInt();
            if (opcion > 2) {
                opcion = 3;
            }
            Notificacion notificacion;
            String destinatario;
            String mensaje;
            switch (opcion) {
                case 1:
                    notificacion = new NotificacionSMS();
                    System.out.println("Escribe el numero de telefono del Usuario");
                     destinatario = pedirString();
                    System.out.println("Escribe el mensaje que le quieres enviar");
                     mensaje = pedirString();
                    notificacion.enviarNotificacion(destinatario,mensaje);
                    break;
                case 2:
                    notificacion = new NotificacionEmail();
                    System.out.println("Escribe el correo del Usuario");
                     destinatario = pedirString();
                    System.out.println("Escribe el mensaje que le quieres enviar");
                     mensaje = pedirString();
                    notificacion.enviarNotificacion(destinatario,mensaje);
                    break;
                default:
                    opcion = 3;
            }
        }
    }

    public static boolean opcion(char c) {
        if (c == 's' || c == 'S') return true;
        if (c == 'n' || c == 'N') return false;
        return opcion(c);
    }

    public static String pedirString() {
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextLine();
        } catch (InputMismatchException e) {
            return "error";
        }
    }

    public static int pedirInt() {
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            return -999;
        }
    }

    public static char pedirCar() {
        Scanner sc = new Scanner(System.in);
        try {
            return sc.next().charAt(0);
        } catch (InputMismatchException e) {
            return 'e';
        }
    }


    //INCLUIR PRIMEROS VUELOS AL DOCUMENTO LUEGO NO USAR
    private Vuelo[] meterVuelos() {
        Vuelo[] vuelos = new Vuelo[30];

        // Asignar los vuelos a las posiciones del array
        vuelos[0] = new Vuelo("IB9736", Aeropuerto.MAD, Aeropuerto.CDG, LocalDateTime.of(2025, 5, 10, 15, 30), LocalDateTime.of(2025, 5, 12, 18, 0), 100, 200);
        vuelos[1] = new Vuelo("AA1234", Aeropuerto.BCN, Aeropuerto.LHR, LocalDateTime.of(2025, 6, 15, 9, 0), LocalDateTime.of(2025, 6, 18, 21, 0), 150, 200);
        vuelos[2] = new Vuelo("AF2332", Aeropuerto.CDG, Aeropuerto.JFK, LocalDateTime.of(2025, 7, 20, 16, 45), LocalDateTime.of(2025, 7, 23, 19, 0), 180, 200);
        vuelos[3] = new Vuelo("BA6789", Aeropuerto.LHR, Aeropuerto.MAD, LocalDateTime.of(2025, 8, 1, 10, 15), LocalDateTime.of(2025, 8, 4, 13, 0), 130, 200);
        vuelos[4] = new Vuelo("DL4567", Aeropuerto.PMI, Aeropuerto.MAD, LocalDateTime.of(2025, 5, 25, 14, 30), LocalDateTime.of(2025, 5, 30, 16, 0), 140, 200);
        vuelos[5] = new Vuelo("CX999", Aeropuerto.NRT, Aeropuerto.SFO, LocalDateTime.of(2025, 6, 5, 7, 0), LocalDateTime.of(2025, 6, 7, 18, 30), 120, 200);
        vuelos[6] = new Vuelo("SQ134", Aeropuerto.SIN, Aeropuerto.AMS, LocalDateTime.of(2025, 7, 8, 11, 0), LocalDateTime.of(2025, 7, 10, 15, 30), 160, 200);
        vuelos[7] = new Vuelo("LH403", Aeropuerto.FRA, Aeropuerto.MXP, LocalDateTime.of(2025, 8, 2, 13, 30), LocalDateTime.of(2025, 8, 6, 17, 0), 180, 200);
        vuelos[8] = new Vuelo("EK345", Aeropuerto.DXB, Aeropuerto.BOG, LocalDateTime.of(2025, 6, 30, 22, 0), LocalDateTime.of(2025, 7, 3, 10, 30), 170, 200);
        vuelos[9] = new Vuelo("IB6543", Aeropuerto.MAD, Aeropuerto.GRU, LocalDateTime.of(2025, 9, 1, 6, 30), LocalDateTime.of(2025, 9, 4, 12, 30), 150, 200);
        vuelos[10] = new Vuelo("UA4321", Aeropuerto.SFO, Aeropuerto.LAX, LocalDateTime.of(2025, 5, 30, 17, 0), LocalDateTime.of(2025, 6, 2, 20, 0), 110, 200);
        vuelos[11] = new Vuelo("KL7890", Aeropuerto.AMS, Aeropuerto.LHR, LocalDateTime.of(2025, 10, 10, 9, 15), LocalDateTime.of(2025, 10, 13, 11, 0), 125, 200);
        vuelos[12] = new Vuelo("AR5500", Aeropuerto.EZE, Aeropuerto.MEX, LocalDateTime.of(2025, 8, 18, 18, 0), LocalDateTime.of(2025, 8, 21, 19, 30), 180, 200);
        vuelos[13] = new Vuelo("QR132", Aeropuerto.DXB, Aeropuerto.MEX, LocalDateTime.of(2025, 9, 15, 14, 45), LocalDateTime.of(2025, 9, 18, 16, 30), 160, 200);
        vuelos[14] = new Vuelo("TK6745", Aeropuerto.BCN, Aeropuerto.FCO, LocalDateTime.of(2025, 6, 12, 12, 30), LocalDateTime.of(2025, 6, 14, 20, 30), 135, 200);
        vuelos[15] = new Vuelo("AF772", Aeropuerto.CDG, Aeropuerto.MAD, LocalDateTime.of(2025, 7, 25, 9, 30), LocalDateTime.of(2025, 7, 28, 12, 15), 125, 200);
        vuelos[16] = new Vuelo("AC334", Aeropuerto.PMI, Aeropuerto.MAD, LocalDateTime.of(2025, 8, 22, 21, 0), LocalDateTime.of(2025, 8, 25, 10, 0), 145, 200);
        vuelos[17] = new Vuelo("VA890", Aeropuerto.DME, Aeropuerto.LAX, LocalDateTime.of(2025, 6, 8, 22, 0), LocalDateTime.of(2025, 6, 10, 12, 0), 180, 200);
        vuelos[18] = new Vuelo("NH57", Aeropuerto.NRT, Aeropuerto.LAX, LocalDateTime.of(2025, 7, 14, 14, 15), LocalDateTime.of(2025, 7, 17, 16, 0), 160, 200);
        vuelos[19] = new Vuelo("AF463", Aeropuerto.CDG, Aeropuerto.SFO, LocalDateTime.of(2025, 5, 5, 8, 0), LocalDateTime.of(2025, 5, 8, 17, 0), 150, 200);
        vuelos[20] = new Vuelo("UA321", Aeropuerto.PMI, Aeropuerto.BCN, LocalDateTime.of(2025, 8, 19, 20, 0), LocalDateTime.of(2025, 8, 22, 12, 30), 140, 200);
        vuelos[21] = new Vuelo("LX124", Aeropuerto.GIG, Aeropuerto.CDG, LocalDateTime.of(2025, 6, 18, 11, 30), LocalDateTime.of(2025, 6, 21, 13, 0), 155, 200);
        vuelos[22] = new Vuelo("QR728", Aeropuerto.DXB, Aeropuerto.BOG, LocalDateTime.of(2025, 9, 3, 18, 45), LocalDateTime.of(2025, 9, 6, 20, 0), 130, 200);
        vuelos[23] = new Vuelo("AC789", Aeropuerto.YYZ, Aeropuerto.SFO, LocalDateTime.of(2025, 7, 30, 16, 0), LocalDateTime.of(2025, 8, 2, 18, 30), 120, 200);
        vuelos[24] = new Vuelo("AA232", Aeropuerto.LAX, Aeropuerto.CDG, LocalDateTime.of(2025, 6, 4, 10, 0), LocalDateTime.of(2025, 6, 7, 14, 30), 170, 200);
        vuelos[25] = new Vuelo("EK312", Aeropuerto.DXB, Aeropuerto.SFO, LocalDateTime.of(2025, 9, 12, 17, 30), LocalDateTime.of(2025, 9, 15, 22, 0), 160, 200);
        vuelos[26] = new Vuelo("LH345", Aeropuerto.FRA, Aeropuerto.SIN, LocalDateTime.of(2025, 7, 7, 23, 0), LocalDateTime.of(2025, 7, 10, 8, 0), 180, 200);
        vuelos[27] = new Vuelo("CX888", Aeropuerto.CDG, Aeropuerto.AMS, LocalDateTime.of(2025, 5, 20, 9, 15), LocalDateTime.of(2025, 5, 23, 11, 0), 140, 200);
        vuelos[28] = new Vuelo("QR652", Aeropuerto.PMI, Aeropuerto.MAD, LocalDateTime.of(2025, 10, 10, 16, 45), LocalDateTime.of(2025, 10, 13, 20, 30), 130, 200);
        vuelos[29] = new Vuelo("UA564", Aeropuerto.SFO, Aeropuerto.MEX, LocalDateTime.of(2025, 9, 20, 13, 0), LocalDateTime.of(2025, 9, 23, 18, 0), 180, 200);
        return vuelos;
    }
}