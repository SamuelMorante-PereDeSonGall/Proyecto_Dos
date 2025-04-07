import java.io.IOException;

public class Usuario {
    private String nombre;
    private String apellido;
    private String dni;
    private boolean residente;
    private Reserva[] reservas;

    //Constructores
    public Usuario(){}

    public Usuario(String nombre, String apellido, String dni, boolean residente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.residente = residente;
        reservas = new Reserva[0];

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

    public boolean isResidente() {
        return residente;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

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

    private void crearFicheroUsuario(String nombre, String apellido, String dni, boolean residente) {
        try {
            FicheroUsuariosEscritura fout = new FicheroUsuariosEscritura("usuarios.dat");
            Usuario usuario = new Usuario(nombre, apellido, dni,residente);
            fout.write(usuario);
            fout.close();
        } catch (IOException e) {
            System.err.print(e);
        }
    }


}
