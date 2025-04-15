import java.io.Serializable;

public class Reserva implements Serializable {
    private String id;
    private Usuario usuario;
    private Vuelo vuelo;
    private  double precioFinal;

    public Reserva(String id, Usuario usuario, Vuelo vuelo) {
        this.id = id;
        this.usuario = usuario;
        this.vuelo = vuelo;
    }

    public Reserva(String id, Usuario usuario, Vuelo vuelo, double precioFinal) {
        this.id = id;
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.precioFinal = precioFinal;
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public String mostrarReserva() {
        return "Número de la reserva: " + id + " | " + vuelo.infoVuelo() + " | Precio pagado: " + precioFinal + "€";
    }

    public static String crearIDAleatorio(){
        int numero = (int)(Math.random() * 100000); // número entre 0 y 99999
        return String.format("%05d", numero);
    }

    public String toString() {
        return "{" +
                "ID reserva: '" + id + '\'' +
                ", vuelo=" + vuelo +
                '}';
    }
}
