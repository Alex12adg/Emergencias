package com.emergencias.controller;

import com.emergencias.Alert.AlertSender;
import com.emergencias.detector.EmergencyDetector;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.UserData;


// Clase controladora que coordina la deteccion y respuesta a emergencias
// Integra el detector y el emisor de alertas
public class EmergencyManager {
    private EmergencyDetector detector; // Detector de emergencias
    private AlertSender sender; // Emisor de alertas


    // Constructor que inicializa el gestor con sus componentes
    public EmergencyManager(EmergencyDetector detector, AlertSender sender) {
        this.detector = detector;
        this.sender = sender;
    }

    // Inicializa el sistema de detección y respuesta automatica
    // Detecta el evento, valida gravedad y envia alertas si es necesario
    public void startSystem(UserData user){
        try{
            if(detector.detectEvent()){  // Intenta detectar una emergencia
                // Validar si la emergencia es grave
                boolean grave = detector.validateSverity(5);
                if (grave){
                    // Si es grave: crea el evento, envia alerta y notifica a los contactos
                    EmergencyEvent event = new EmergencyEvent(" Accidente", detector.getUbicacionInicial(), user);
                    sender.sendAlert(event);
                    sender.notifyContacts(event);

                } else{
                    // Si no es grave solo informa
                    System.out.println("Evento detectado pero no grave");
                }
            }else{
                // No se detecto ninguna emergenci
                System.out.println("No se detecto emergencia");
            }

        }catch (IllegalArgumentException e){
            // captura y muestra cualquier error de validación
            System.out.println("Error"+ e.getMessage());
        }
    }


}
