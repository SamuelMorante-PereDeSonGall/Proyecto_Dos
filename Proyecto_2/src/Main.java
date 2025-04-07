import java.io.Console;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().metodoPrincipal();
    }

    private void metodoPrincipal() throws IOException {
        Usuario usuario = menuUsuario();
        FicheroUsuariosEscritura fout = new FicheroUsuariosEscritura("resultados.dat");
        fout.write(usuario);
    }

    private Usuario menuUsuario(){
        System.out.println("Nombre: ");
        String nombre = pedirString();
        System.out.println("Apellidos: ");
        String apellido = pedirString();
        System.out.println("DNI: ");
        String dni = pedirString();
        System.out.println("¿Es usted residente en Mallorca? s/n");
        boolean residente = opcion(pedirCar());

        return new Usuario(nombre,apellido,dni,residente);
    }


    private boolean opcion(char c){
        if (c == 's' || c == 'S') return true;
        if (c == 'n' || c == 'N') return false;
        return opcion(c);
    }

    private String pedirString(){
        Scanner sc = new Scanner(System.in);
        try{
            return sc.nextLine();
        }catch (InputMismatchException e){
            return "error";
        }
    }

    private char pedirCar(){
        Scanner sc = new Scanner(System.in);
        try{
            return sc.next().charAt(0);
        }catch (InputMismatchException e){
            return 'e';
        }
    }
}