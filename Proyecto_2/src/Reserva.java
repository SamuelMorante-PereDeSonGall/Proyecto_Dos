import java.io.Serializable;

public class Reserva implements Serializable {
    private String id;
    private Usuario usuario;
    private Vuelo vuelo;

    public Reserva(String id, Usuario usuario, Vuelo vuelo) {
        this.id = id;
        this.usuario = usuario;
        this.vuelo = vuelo;
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
        return "Número de la reserva: " + id + "Vuelo: " + vuelo;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "ID: '" + id + '\'' +
                ", usuario=" + usuario.getNombre() +
                ", vuelo=" + vuelo +
                '}';
    }
}
