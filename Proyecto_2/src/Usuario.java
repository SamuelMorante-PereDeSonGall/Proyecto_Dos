import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private String password;
    private boolean residente;
    private Reserva[] reservas;
    private boolean administrador;

    //Constructores
    public Usuario(){}

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

    public boolean isResidente() {
        return residente;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public boolean getAdministrador() {return administrador;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setResidente(boolean residente) {
        this.residente = residente;
    }

    public void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
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

    public static Usuario iniciarSesion() throws IOException {
        System.out.println("Nombre: ");
        String nombre = Main.pedirString();
        System.out.println("Password: ");
        String password = Main.pedirString();
        Usuario usuario = comprobarUsuario(nombre, password);
        if (usuario != null ) {
            return usuario;
        }else {
            System.out.println("EL NOMBRE O LA CONTRASEÑA SON INCORRECTAS");
            return null;
        }
    }


    public static Usuario crearUsuario() throws IOException {
        System.out.println("Nombre: ");
        String nombre = Main.pedirString();
        System.out.println("Apellidos: ");
        String apellido = Main.pedirString();
        System.out.println("DNI: ");
        String dni = Main.pedirString();
        System.out.println("¿Es usted residente en Mallorca? s/n");
        boolean residente = Main.opcion(Main.pedirCar());
        System.out.println("Contraseña: ");
        String password = Main.pedirString();
        System.out.println("¿Eres administrador de SkyPauScanner? s/n");
        boolean administrador = Main.opcion(Main.pedirCar());
        Usuario usuario = new Usuario(nombre,apellido,dni,password,residente, administrador);
        if (comprobarUsuario(nombre,password) != null) {
            System.out.println("YA EXISTE EL USUARIO");
            return null;
        }else {return usuario;}

    }

    private static Usuario comprobarUsuario(String nombre, String password) throws IOException {
        FicheroUsuariosLeer ful = new FicheroUsuariosLeer("usuarios.dat");
        List<Usuario> usuarios = ful.leerObjetosUsuarios();
        for (int i=0; i<usuarios.size(); i++){
            if (usuarios.get(i).getNombre().equals(nombre)){
                if (usuarios.get(i).getPassword().equals(password)){return usuarios.get(i);}
            }
        }
        ful.close();
        return null;
    }


}
