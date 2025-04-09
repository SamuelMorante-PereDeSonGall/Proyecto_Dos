public class NotificacionEmail implements Notificacion{
    @Override
    public void enviarNotificacion(String correo, String mensaje) {
        System.out.println("Conectando con la API de Email...");
        System.out.println("Enviando Email a: " + correo);
        System.out.println("Contenido: " + mensaje);
    }
}
