import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Samuel Morante Pioreck
 */
public class FicheroUsuariosEscritura {

    /**
     * Los atributos son el flujo de salida del fichero y los de objetos
     */
    private FileOutputStream fos;
    private ObjectOutputStream f;

    /**
     * Constructor del fichero a partir de la cadena con el nombre del fichero si
     * se producen devuelve al padre
     *
     * @param nombreFichero
     * @throws java.io.FileNotFoundException
     */
    public FicheroUsuariosEscritura(String nombreFichero) throws FileNotFoundException, IOException {
        fos = new FileOutputStream(nombreFichero);
        f = new ObjectOutputStream(fos);
    }

    /**
     * Escribe un objeto Resultado al fichero
     *
     * @param u
     * @throws java.io.IOException
     */
    public void write(Usuario u) throws IOException {
        f.writeObject(u);
    }
    //Escribe en la lista resultado
    public void writeLista(List<Usuario> listaResultados) throws IOException {
        for (Usuario usuario : listaResultados) {
            f.writeObject(usuario);
        }
    }

    /**
     * Cierra el fichero
     * @throws java.io.IOException
     */
    public void close() throws IOException {
        f.close();
    }
}
