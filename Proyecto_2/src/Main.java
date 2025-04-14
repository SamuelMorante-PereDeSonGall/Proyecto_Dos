import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().metodoPrincipal();
    }

    private void metodoPrincipal() throws IOException {
        menuIngresarUsuario();
    }

    //Texto para iniciar sesion o crear usuario
    private void menuIngresarUsuario() throws IOException {
        GestorUsuario gu = new GestorUsuario();
        GestorVuelos gv = new GestorVuelos();
        gv.ordenarVuelosFecha();
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("Bienvenido a SkyPauScanner");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1 - Crear usuario");
            System.out.println("2 - Iniciar sesión");
            System.out.println("3 - Salir");
            System.out.println("--------------------------");
            opcion = pedirInt();
            //Bucle
            switch (opcion) {
                case 1:
                    //Crea un usuario
                    Usuario usuario = gu.crearUsuario();
                    if (usuario != null) {
                        menuConFunciones(usuario,gv);
                    }
                    break;
                case 2:
                    usuario = gu.iniciarSesion();
                    if (usuario != null) {
                        menuConFunciones(usuario,gv);
                    }
                    break;
                case 4:
                    gv.ordenarVuelosFecha();
                    int opcion2 = pedirInt();
                    while (opcion2 != 3) {
                        System.out.println("¿Quieres buscar a partir de fecha concreta o por semana?");
                        System.out.println("1 - A partir de fecha concreta");
                        System.out.println("2 - A partir de semana concreta");
                        System.out.println("3 - Salir");
                        opcion2 = pedirInt();
                        switch (opcion2) {
                            case 1 -> textoFechaConcreta(gv);
                            case 2 -> textoFechaSemanaConcreto(gv);
                            default -> {
                                System.out.println("ADIÓS");
                                opcion2 = 3;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("ADIÓS");

                    //Guardar usuarios y vuelos

                    gu.guardarUsuarios();
                    gv.guardarVuelos();

                    opcion = 3;
            }
        }
    }

    private void textoFechaConcreta(GestorVuelos gv){
        System.out.println("\n\n---Selecciona fecha---");
        System.out.print("Introduce el día: ");
        int dia = Main.pedirInt();
        System.out.print("Introduce el mes: ");
        int mes = Main.pedirInt();
        System.out.print("Introduce el año: ");
        int year = Main.pedirInt();
        try {
            LocalDateTime fecha = LocalDateTime.of(year, mes, dia, 0, 0);
            System.out.println("Mostrando vuelos disponibles a partir del " + dia + "/" + mes + "/" + year + ":");
            gv.mostrarVuelosPosteriores(fecha);
        } catch (DateTimeException e) {
            System.out.println("Fecha inválida.");
        }
    }

    private void textoFechaSemanaConcreto(GestorVuelos gv){
        System.out.println("Introduce el número de semana del año: ");
        int semana = Main.pedirInt();
        while (semana < 1){
            System.out.println("No puedes introducir un número menor que 1. Vuelve a intentarlo: ");
            semana = Main.pedirInt();
        }
        LocalDateTime fecha = gv.calcularLunesSemana(semana);
        gv.mostrarVuelosPosteriores(fecha);
    }

    private void menuConFunciones(Usuario usuario, GestorVuelos gv) throws IOException {
        int opcion = 0;
        Vuelo[] vuelos = gv.getVuelos();
        while (opcion != 8) {
            System.out.println("\n\n--------------------------");
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
            }
            System.out.println("Cualquier otra opción: Cerrar sesión");
            System.out.println("--------------------------");
            opcion = pedirInt();
            if (!usuario.getAdministrador() && opcion > 4) {
                opcion = 8;
            }
            switch (opcion) {
                case 1:
                    textoAddReserva(usuario,gv);
                    break;
                case 2:
                    System.out.println(usuario.consultarReservas());
                    break;
                case 3:
                    textoCancelarReserva(usuario,gv);
                    break;
                case 4:
                    gv.ordenarVuelosFecha();
                    int opcion2 = 0;
                    while (opcion2 != 3) {
                        System.out.println("\n\n¿Quieres buscar a partir de fecha concreta o por semana?");
                        System.out.println("1 - A partir de fecha concreta");
                        System.out.println("2 - A partir de semana concreta");
                        System.out.println("3 - Salir");
                        opcion2 = pedirInt();
                        switch (opcion2) {
                            case 1 -> textoFechaConcreta(gv);
                            case 2 -> textoFechaSemanaConcreto(gv);
                            default -> {
                                System.out.println("ADIÓS");
                                opcion2 = 3;
                            }
                        }
                    }
                    break;
                case 5:
                    Vuelo vuelo = gv.textoAddVuelo();
                    gv.addVuelo(vuelo);
                    gv.guardarVuelos();
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

    public void textoAddReserva(Usuario usuario, GestorVuelos gv) {
        System.out.println("\n\nIntroduce ID del vuelo: ");
        String id = pedirString();
        System.out.println(usuario.addReserva(id,gv));
    }

    public void textoCancelarReserva(Usuario usuario, GestorVuelos gv) {
        System.out.println("\n\nIntroduce ID de la reserva: ");
        String id = pedirString();
        System.out.println(usuario.cancelarReserva(id,gv));
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

    public static double pedirDouble() {
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextDouble();
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
        Vuelo[] vuelos = new Vuelo[21];

        // Asignar los vuelos a las posiciones del array con tiempos y precios realistas
        vuelos[0] = new Vuelo("IB9736", Aeropuerto.MAD, Aeropuerto.CDG,
                LocalDateTime.of(2025, 5, 10, 15, 30),
                LocalDateTime.of(2025, 5, 10, 17, 30),  // Vuelo de 2 horas
                100, 200);
        vuelos[1] = new Vuelo("AA1234", Aeropuerto.BCN, Aeropuerto.LHR,
                LocalDateTime.of(2025, 6, 15, 9, 0),
                LocalDateTime.of(2025, 6, 15, 10, 45),  // Vuelo de 2 horas
                150, 200);
        vuelos[2] = new Vuelo("AF2332", Aeropuerto.CDG, Aeropuerto.JFK,
                LocalDateTime.of(2025, 7, 20, 16, 45),
                LocalDateTime.of(2025, 7, 20, 19, 30),  // Vuelo de 8 horas
                180, 300);
        vuelos[3] = new Vuelo("BA6789", Aeropuerto.LHR, Aeropuerto.MAD,
                LocalDateTime.of(2025, 8, 1, 10, 15),
                LocalDateTime.of(2025, 8, 1, 13, 30),  // Vuelo de 2 horas 30 minutos
                130, 180);
        vuelos[4] = new Vuelo("DL4567", Aeropuerto.PMI, Aeropuerto.MAD,
                LocalDateTime.of(2025, 5, 25, 14, 30),
                LocalDateTime.of(2025, 5, 25, 15, 30),  // Vuelo de 1 hora
                140, 160);
        vuelos[5] = new Vuelo("CX999", Aeropuerto.NRT, Aeropuerto.SFO,
                LocalDateTime.of(2025, 6, 5, 7, 0),
                LocalDateTime.of(2025, 6, 5, 18, 30),  // Vuelo de 11 horas 30 minutos
                120, 400);
        vuelos[6] = new Vuelo("SQ134", Aeropuerto.SIN, Aeropuerto.AMS,
                LocalDateTime.of(2025, 7, 8, 11, 0),
                LocalDateTime.of(2025, 7, 8, 17, 15),  // Vuelo de 13 horas 15 minutos
                160, 250);
        vuelos[7] = new Vuelo("LH403", Aeropuerto.FRA, Aeropuerto.MXP,
                LocalDateTime.of(2025, 8, 2, 13, 30),
                LocalDateTime.of(2025, 8, 2, 15, 15),  // Vuelo de 2 horas
                180, 220);
        vuelos[8] = new Vuelo("EK345", Aeropuerto.DXB, Aeropuerto.BOG,
                LocalDateTime.of(2025, 6, 30, 22, 0),
                LocalDateTime.of(2025, 7, 2, 16, 0),  // Vuelo de 14 horas
                170, 500);
        vuelos[9] = new Vuelo("IB6543", Aeropuerto.MAD, Aeropuerto.GRU,
                LocalDateTime.of(2025, 9, 1, 6, 30),
                LocalDateTime.of(2025, 9, 1, 17, 30),  // Vuelo de 12 horas
                150, 350);
        vuelos[10] = new Vuelo("UA4321", Aeropuerto.SFO, Aeropuerto.LAX,
                LocalDateTime.of(2025, 5, 30, 17, 0),
                LocalDateTime.of(2025, 5, 30, 18, 0),  // Vuelo corto de 1 hora
                110, 100);
        vuelos[11] = new Vuelo("KL7890", Aeropuerto.AMS, Aeropuerto.LHR,
                LocalDateTime.of(2025, 10, 10, 9, 15),
                LocalDateTime.of(2025, 10, 10, 10, 30),  // Vuelo corto de 1 hora 15 minutos
                125, 130);
        vuelos[12] = new Vuelo("AR5500", Aeropuerto.EZE, Aeropuerto.MEX,
                LocalDateTime.of(2025, 8, 18, 18, 0),
                LocalDateTime.of(2025, 8, 19, 23, 30),  // Vuelo de 8 horas 30 minutos
                180, 280);
        vuelos[13] = new Vuelo("QR132", Aeropuerto.DXB, Aeropuerto.MEX,
                LocalDateTime.of(2025, 9, 15, 14, 45),
                LocalDateTime.of(2025, 9, 16, 7, 30),  // Vuelo de 15 horas 45 minutos
                160, 400);
        vuelos[14] = new Vuelo("TK6745", Aeropuerto.BCN, Aeropuerto.FCO,
                LocalDateTime.of(2025, 6, 12, 12, 30),
                LocalDateTime.of(2025, 6, 12, 14, 30),  // Vuelo de 2 horas
                135, 150);
        vuelos[15] = new Vuelo("AF772", Aeropuerto.CDG, Aeropuerto.MAD,
                LocalDateTime.of(2025, 7, 25, 9, 30),
                LocalDateTime.of(2025, 7, 25, 11, 30),  // Vuelo de 2 horas
                125, 160);
        vuelos[16] = new Vuelo("AC334", Aeropuerto.PMI, Aeropuerto.MAD,
                LocalDateTime.of(2025, 8, 22, 21, 0),
                LocalDateTime.of(2025, 8, 23, 22, 30),  // Vuelo de 1 hora 30 minutos
                145, 170);
        vuelos[17] = new Vuelo("VA890", Aeropuerto.DME, Aeropuerto.LAX,
                LocalDateTime.of(2025, 6, 8, 22, 0),
                LocalDateTime.of(2025, 6, 9, 17, 0),  // Vuelo de 10 horas
                180, 300);
        vuelos[18] = new Vuelo("NH57", Aeropuerto.NRT, Aeropuerto.LAX,
                LocalDateTime.of(2025, 7, 14, 14, 15),
                LocalDateTime.of(2025, 7, 14, 18, 30),  // Vuelo de 11 horas
                160, 350);
        vuelos[19] = new Vuelo("AF463", Aeropuerto.CDG, Aeropuerto.SFO,
                LocalDateTime.of(2025, 5, 5, 8, 0),
                LocalDateTime.of(2025, 5, 5, 18, 0),  // Vuelo de 12 horas
                150, 280);
        vuelos[20] = new Vuelo("UA321", Aeropuerto.PMI, Aeropuerto.BCN,
                LocalDateTime.of(2025, 8, 19, 20, 0),
                LocalDateTime.of(2025, 8, 19, 21, 30),  // Vuelo corto de 1 hora 30 minutos
                140, 160);

        return vuelos;
    }

}