
package pcsc9.banco;

import java.io.*;
import java.time.LocalDateTime;
import java.util.PriorityQueue;

/**
 * Daniel
 * 
 * @author diego
 */
public class Tiquetes {
    private int contadorTiquetes;
    private PriorityQueue<Tiquete> colaPreferencial;
    private PriorityQueue<Tiquete> colaUnSoloTramite;
    private PriorityQueue<Tiquete> colaDosOMasTramites;
    private Cajero[] cajeros;

    // crear cajas (volatil)
    private PriorityQueue<Tiquete> qCaja1 = new PriorityQueue<>();
    private PriorityQueue<Tiquete> qCaja2 = new PriorityQueue<>();
    private PriorityQueue<Tiquete> qCaja3 = new PriorityQueue<>();

    public Tiquetes(int numCajeros) {
        contadorTiquetes = 0;
        colaPreferencial = new PriorityQueue<>();
        colaUnSoloTramite = new PriorityQueue<>();
        colaDosOMasTramites = new PriorityQueue<>();
        cajeros = new Cajero[numCajeros];
        for (int i = 0; i < numCajeros; i++) {
            cajeros[i] = new Cajero();
        }
    }

    public void agregarTiquete(Tiquete tiquete) {

        System.out.println("Inicia proceso de agregar tiquete");

        if (tiquete.getTipo().equals("P")) { // Preferencial
            colaPreferencial.offer(tiquete);
        } else if (tiquete.getTipo().equals("A")) { // Rapida
            colaUnSoloTramite.offer(tiquete);
        } else if (tiquete.getTipo().equals("B")) { // Dos o mas
            colaDosOMasTramites.offer(tiquete);

        }

        llenarColas(tiquete);
        System.out.println("llamada a llenarColas()");

        // Verifica si hay cajeros desocupados y les asigna un tiquete
        for (Cajero cajero : cajeros) {
            if (!cajero.isOcupado()) {
                Tiquete siguienteTiquete = atenderSiguienteTiquete();
                if (siguienteTiquete != null) {
                    cajero.atenderTiquete(siguienteTiquete);
                }
            }
        }
    }

    public Tiquete atenderSiguienteTiquete() {
        if (!colaPreferencial.isEmpty()) {
            Tiquete siguienteTiquete = colaPreferencial.poll();
            siguienteTiquete.setHoraAtencion(LocalDateTime.now());
            return siguienteTiquete;
        } else if (!colaUnSoloTramite.isEmpty()) {
            Tiquete siguienteTiquete = colaUnSoloTramite.poll();
            siguienteTiquete.setHoraAtencion(LocalDateTime.now());
            return siguienteTiquete;
        } else if (!colaDosOMasTramites.isEmpty()) {
            Tiquete siguienteTiquete = colaDosOMasTramites.poll();
            siguienteTiquete.setHoraAtencion(LocalDateTime.now());
            return siguienteTiquete;
        } else {
            return null;
        }
    }

    public String obtenerCajaYAdelante(Tiquete tiquete) {
        String caja;
        int adelante = 0;

        if (tiquete.getHoraAtencion() == null) {
            // Tiquete no ha sido atendido
            if (colaPreferencial.contains("P")) {
                caja = "Caja Preferencial";
                adelante = colaPreferencial.size() - 1;
            } else if (colaUnSoloTramite.contains("A")) {
                caja = "Caja Rápida";
                adelante = colaUnSoloTramite.size() - 1;
            } else if (colaDosOMasTramites.contains("B")) {
                caja = "Caja Mas Tramites";
                Tiquete[] colaArray = colaDosOMasTramites.toArray(new Tiquete[0]);
                for (int i = 0; i < colaArray.length; i++) {
                    if (colaArray[i] == tiquete) {
                        adelante = i;
                        break;

                    }
                }
            } else {
                caja = "Error: Tiquete no encontrado";
            }
        } else {
            // Tiquete ha sido atendido
            caja = "Tiquete Atendido";
        }

        return " y hay " + adelante + " persona(s) adelante de usted.";
    }

    private class Cajero {
        private boolean ocupado;
        private LocalDateTime horaUltimaAtencion;

        public Cajero() {
            ocupado = false;
            horaUltimaAtencion = null;
        }

        public boolean isOcupado() {
            return ocupado;
        }

        public void atenderTiquete(Tiquete tiquete) {
            tiquete.setHoraAtencion(LocalDateTime.now());
            horaUltimaAtencion = tiquete.getHoraAtencion();
            ocupado = true;
            // Guarda el tiquete en la base de datos de reportes
            // ... (Aquí iría el código para guardar el tiquete en la base de datos)
        }

        public void liberarCajero() {
            ocupado = false;
        }
    }

    String generarNumeroConsecutivo() {
        String numero = String.format("%02d", contadorTiquetes);
        contadorTiquetes++;
        return numero;
    }

    private void llenarColas(Tiquete tiquete) {

        System.out.println("ejecucion de llenar colas...");

        try {

            // Crear txt de almacenamiento
            File txtCaja1 = new File("caja1.txt");
            if (!txtCaja1.exists()) {
                txtCaja1.createNewFile();
                System.out.println("TXT 1 creado");
            }

            File txtCaja2 = new File("caja2.txt");
            if (!txtCaja2.exists()) {
                txtCaja2.createNewFile();
                System.out.println("TXT 2 creado");
            }

            File txtCaja3 = new File("caja3.txt");
            if (!txtCaja3.exists()) {
                txtCaja3.createNewFile();
                System.out.println("TXT 3 creado");
            }
            
            //calcular caja mas eficiente, false por default
            Boolean mayor1=false;
            Boolean mayor2=false;
            Boolean mayor3=false;

            // LECTURA de lineas de cajas para comparacion posterior
            BufferedReader readerCaja1 = new BufferedReader(new FileReader(txtCaja1));
            int lineasCaja1 = 0;
            while (readerCaja1.readLine() != null) {
                lineasCaja1++;
            }

            System.out.println("Linea caja 1: " + lineasCaja1 + "\n");

            BufferedReader readerCaja2 = new BufferedReader(new FileReader(txtCaja2));
            int lineasCaja2 = 0;
            while (readerCaja2.readLine() != null) {
                lineasCaja2++;
            }

            System.out.println("Linea caja 2: " + lineasCaja2 + "\n");
            BufferedReader readerCaja3 = new BufferedReader(new FileReader(txtCaja3));
            int lineasCaja3 = 0;
            while (readerCaja3.readLine() != null) {
                lineasCaja3++;
            }

            System.out.println("Linea caja 3: " + lineasCaja3 + "\n");

            // establecer hora de atencion
            tiquete.setHoraAtencion(LocalDateTime.now());
            
//            //calculo cantidad de clientes atendidos
//            Reportes clientesAtendidos = new Reportes();
//            clientesAtendidos.cantidadTiquetes(lineasCaja1, lineasCaja2, lineasCaja3);
            
            //calculo de caja mas eficiente
            if (lineasCaja1 >= lineasCaja2 && lineasCaja1 >= lineasCaja3) { //si caja1 tiene mas que caja 2 y 3
                mayor1=true;                                                //este atendio mas
            }else{                                                          //si no
                mayor1=false;                                               //no fue el que mas atendio                                                    
            }
                
            if (lineasCaja2 >= lineasCaja1 && lineasCaja2 >= lineasCaja3) {
                mayor2=true;
            }else{
                mayor2=false;                 
            }    
                
            if(lineasCaja3 >= lineasCaja1 && lineasCaja3 >= lineasCaja2){
            mayor3=true;   
            }else { // caja 3 tiene mas atendidos
                mayor3=false;
            }
            
            
            
            Reportes reportes = new Reportes();
            reportes.cajaAtencionMayor(mayor1, mayor2, mayor3);
            
            // compraracion y almacenamiento
            if (lineasCaja1 <= lineasCaja2 && lineasCaja1 <= lineasCaja3) { // caja 1 menor carga que las otras dos
                qCaja1.offer(tiquete);
                importarTXT(qCaja1, txtCaja1);
                System.out.println("tiquete: " + tiquete.getNombre() + " agregado a caja 1 \n");

            } else if (lineasCaja2 <= lineasCaja1 && lineasCaja2 <= lineasCaja3) { // caja 2 menor carga que las otras
                                                                                   // dos
                qCaja2.offer(tiquete);
                importarTXT(qCaja2, txtCaja2);
                System.out.println("tiquete: " + tiquete.getNombre() + " agregado a caja 2 \n");

            } else { // caja 3 era el que menos carga tenia
                qCaja3.offer(tiquete);
                importarTXT(qCaja3, txtCaja3);
                System.out.println("tiquete: " + tiquete.getNombre() + " agregado a caja 3 \n");

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void importarTXT(PriorityQueue<Tiquete> cola, File archivo) {// lee los objetos del queue y los importa al
                                                                         // txt

        try (FileWriter fileWriter = new FileWriter(archivo, true); // el true permite continuar escribiendo sin reiniciar

            BufferedWriter buffWriter = new BufferedWriter(fileWriter)) {

            while (!cola.isEmpty()) {
                Tiquete t = cola.poll(); // Extraer un tiquete de la cola
                buffWriter.write(t.toString()); // Escribir en el archivo
                buffWriter.newLine(); // Salto de línea

            }
            
            buffWriter.close();
            
            System.out.println("tiquete importado al txt !!");
        } catch (IOException e) {
            System.out.println("Error al importar tiquete: " + e.getMessage());
        }
    }
}
