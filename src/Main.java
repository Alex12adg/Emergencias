import com.emergencias.Alert.AlertSender;
import com.emergencias.controller.EmergencyManager;
import com.emergencias.detector.EmergencyDetector;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.Ubicacion;
import com.emergencias.model.UserData;

import java.util.Scanner;
// Clase principal que ejecuta el sistema de gestion de emergencia.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserData user = new UserData("Maria" , "678524080");// Inicializacion datos de usuario por defecto

        Ubicacion ubicacionInicial = new Ubicacion(40.50,-4.5);// Crear ubicacion inicial de sistema

        EmergencyDetector detector = new EmergencyDetector(3, ubicacionInicial); // Configurar el detector de umbral y la ubicacion inicial
        AlertSender sender = new AlertSender("112");// Configurar el emisor de alertas con destino al 112
        EmergencyManager manager = new EmergencyManager(detector, sender);// Crear gestor de emergencias que coordina detector y emisor


        int opcion;// Bucle principal del menu
        do {

            // Opciones del menu
            System.out.println("\n*** SISTEMA DE EMERGENCIAS***");
            System.out.println("1. Emergencia");
            System.out.println("2. Enviar alerta Manual");
            System.out.println("3. Notificar contactos");
            System.out.println("4. Cambiar datos de usuario");
            System.out.println("5. Umbral de gravedad");
            System.out.println("0. Salir");
            System.out.println("Seleccione Opcion: ");

            while (!sc.hasNextInt()) {  // validar que la entrada sea un numero
                System.out.println("Entrada invalida. Introduzca un numero");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();  // Limpiar Buffer
            switch (opcion) {
                case 1:
                    manager.startSystem(user);  // Iniciar el sistema de detencion automática
                   // break;//
                case 2: {
                    System.out.println("Introduzca  el tipo de emergencia");  // Enviar alerta manual con el tipo de emergencia
                    String tipo = sc.nextLine();
                    // Solicitar coordenadas de emergencias
                    System.out.println("Introduzca latitud");
                    double lat = sc.nextDouble();
                    System.out.println("Introduzca longitud");
                    double lon = sc.nextDouble();
                    sc.nextLine();

                    // Crear ubicación y evento de emergencia
                    Ubicacion ubicacion = new Ubicacion(lat,lon);
                    EmergencyEvent event = new EmergencyEvent(tipo, ubicacion, user);
                    sender.sendAlert(event);  // Enviar alerta
                    //break;//
                }
                case 3: {

                    // Notificar a contactos de emergencia
                    EmergencyEvent event = new EmergencyEvent("Accidente", detector.getUbicacionInicial(), user);
                    sender.notifyContacts(event);
                    //break;//
                }
                case 4: { // Actualizar información del usuario
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Telefono: ");
                    String telefono = sc.nextLine();
                    // Crear un nuevo objeto UserData con los datos actualizados
                    user = new UserData(nombre, telefono);
                    System.out.println("Datos de usuario actualizados");
                    //break;//

                }
                case 5: {
                    // Modificar nivel de gravedad del detector
                    System.out.println("Ingrese el umbral de gravedad: ");
                    int nuevoUmbral = sc.nextInt();
                    sc.nextLine();

                    //Crear nuevo detector con el umbral actualizado
                    detector = new EmergencyDetector(nuevoUmbral, detector.getUbicacionInicial());
                    manager = new EmergencyManager(detector, sender);
                    System.out.println("Umbral actualizado a: " + nuevoUmbral);

                    // Proporcional recomendación según el nivel de umbral
                    if(nuevoUmbral>=3 && nuevoUmbral <=5){
                        System.out.println("Urgente: Se enviara una ambulancia");
                    }else if(nuevoUmbral <3){
                        System.out.println("Dirijase al centro de salud mas cercano");
                    }else{
                        System.out.println("Umbral muy alto ");
                    }
                    break;
                }
                case 0:
                    System.out.println("Saliendo ......");
                    break;
                default:
                    System.out.println(" Opcion incorrecta");
                    break;
            }
        } while (opcion != 0); // Continuar hasta que el usuario seleccione salir

        sc.close();


    }
}