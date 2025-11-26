package com.emergencias.Alert;

import com.emergencias.model.EmergencyEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// Clase responsable de enviar alertas y notificar contactos
// Tambien registra eventos en un archivo de log.
public class AlertSender {
    private String destino; // Numero o destinatario de las alertas



    public AlertSender(String destino) { // Constructor que establece el destinatario de las alertas
        this.destino = destino;
        
    }

    public void sendAlert(EmergencyEvent event){ // Envia alerta y la registra en un archivo log
        System.out.println("Enviando alerta a" + destino+":"+ event); // Muestra el mensaje en la consola
        try{
            // Crear carpeta log si no existe
            File carpetaLogs = new File("logs");
            if (!carpetaLogs.exists()){
                carpetaLogs.mkdir();
            }

            // Escribir evento en archivo log
            FileWriter fw = new FileWriter("logs/log.txt",true);
            fw.write(event.toString()+"\n");
            fw.close();


        } catch (IOException e){
            // Manerjar error de escritura
            System.out.println("Error al escribir en log:"+ e.getMessage());
        }

    }

    public  void notifyContacts(EmergencyEvent event){  // Notificar a los contactos de emergencias del usuario
        System.out.println("Notificando contactos:"+event.getDatosUsuario());
    }
}
