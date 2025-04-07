public class Vuelo {
    private String id;
    private String fecha;
    private int numPasajeros;
    private final int MAX_PASAJEROS;

    public Vuelo(String id, String fecha, int MAX_PASAJEROS) {
        this.id = id;
        this.fecha = fecha;
        numPasajeros = 0;
        this.MAX_PASAJEROS = MAX_PASAJEROS;
    }


}
