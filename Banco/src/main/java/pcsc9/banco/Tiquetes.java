/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

/**
 *Daniel
 * @author diego
 */
public class Tiquetes {
    private int contadorTiquetes;
    private PriorityQueue<Tiquete> colaPreferencial;
    private PriorityQueue<Tiquete> colaUnSoloTramite;
    private PriorityQueue<Tiquete> colaDosOMasTramites;
    private Cajero[] cajeros;
    
//    private PriorityQueue<Tiquete> caja1 = new PriorityQueue<>();   //creacion e cajas
//    private PriorityQueue<Tiquete> caja2 = new PriorityQueue<>();   //tipo B (dos o mas transmites)
//    private PriorityQueue<Tiquete> caja3 = new PriorityQueue<>();
    


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
        
        if (tiquete.getTipo().equals("P")) {        // Preferencial
            colaPreferencial.offer(tiquete);
        } else if (tiquete.getTipo().equals("A")) { // Rapida
            colaUnSoloTramite.offer(tiquete);
        } else if (tiquete.getTipo().equals("B")) { // Dos o mas
            colaDosOMasTramites.offer(tiquete);
            llenarColas(); // Llamar llenarColas() sin pasar parámetros
        }
        

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
    

     private void llenarColas() {
        while (!colaDosOMasTramites.isEmpty()) {
            Tiquete tiquete = colaDosOMasTramites.poll();
            if (tiquete.getTramites() == 1) {
                colaUnSoloTramite.offer(tiquete);
            } else {
                colaDosOMasTramites.offer(tiquete);
            }
        }
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
}

//    public void llenarColas(Tiquete tiquete){   //pone los tiquets en caja al de menor espacio en cola
//    
//        int cantidadCaja1 = caja1.size();   //determina cantidad de objetos en caja
//        int cantidadCaja2 = caja2.size();
//        int cantidadCaja3 = caja3.size();
//
//        if(cantidadCaja1 <= cantidadCaja2 && cantidadCaja1 <= cantidadCaja3){       //caja 1 menor carga que las otras dos
//          caja1.offer(tiquete);
//
//        }else if(cantidadCaja2 <= cantidadCaja1 && cantidadCaja2 <= cantidadCaja3){ //caja 2 menor carga que las otras dos
//           caja2.offer(tiquete);
//
//        }else{                                                                      //caja 3 era el que menos carga tenia
//            caja3.offer(tiquete);
//        }  
//   }
    
