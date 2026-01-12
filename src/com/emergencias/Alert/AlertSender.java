package com.emergencias.Alert;

import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.UserData;

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
        System.out.println("\n" + "=" .repeat(60));
        System.out.println("Enviando alerta a" + destino); // Muestra el mensaje en la consola
        System.out.println("=".repeat(60));
        System.out.println(event);

        // Si el perfil medico esta completo incluir información//

        UserData usuario = event.getDatosUsuario();
        if (usuario.getPerfilMedico().estaCompleto()) {
            System.out.println(usuario.getPerfilMedico().getInformacionCritica());
        }

        System.out.println("=".repeat(60));

        registrarEnLog(event, usuario);
    }

    private void registrarEnLog(EmergencyEvent event, UserData usuario) {

        try{
            // Crear carpeta log si no existe
            File carpetaLogs = new File("logs");
            if (!carpetaLogs.exists()){
                carpetaLogs.mkdir();
            }

            // Escribir evento en archivo log
            FileWriter fw = new FileWriter("logs/log.txt",true);
            fw.write(event.toString()+"\n");

            //Añadir información medica al log//
            if (usuario.getPerfilMedico().estaCompleto()) {
                fw.write("   " + usuario.getPerfilMedico().toString() + "\n");
            }
            fw.write("\n");
            fw.close();


        } catch (IOException e){
            // Manerjar error de escritura
            System.out.println("Error al escribir en log:"+ e.getMessage());
        }

    }

    public  void notifyContacts(EmergencyEvent event){// Notificar a los contactos de emergencias del usuario
        UserData usuario = event.getDatosUsuario();
        System.out.println("\n Notificando contactos: " + usuario);

        //Notificando contacto de emergencia al perfil médico
        String contacto = usuario.getPerfilMedico().getContactoEmergencia();
        if (!contacto.equals("No especificado")) {
            System.out.println("   ✓ Notificando a: " + contacto +
                    " (" + usuario.getPerfilMedico().getTelefonoEmergencia() + ")");
        }


    }
}
