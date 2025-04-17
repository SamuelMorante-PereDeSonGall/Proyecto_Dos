package org.example;

public class NotificacionSMS implements Notificacion{
    @Override
    public void enviarNotificacion(String numTelefono, String mensaje) {
        System.out.println("Conectando con la API de SMS...");
        System.out.println("Enviando SMS a: " + numTelefono);
        System.out.println("Contenido: " + mensaje);
    }
}
