/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.vista.VentanaPrincipal;

/**
 *
 * @author NANCY
 */
public class Accion {
    public static final int pensando = 0;
    public static final int hambriento = 1;
    public static final int comiendo = 2;
    private int n;
    private int estado[];
    VentanaPrincipal ventanaPrincipal;
    public Accion(int n) {
        this.n = n;
        estado = new int[n];
    }

    public synchronized void tomarTenedores(int id) {
        estado[id] = hambriento;
        prueba(id);  
        while (estado[id] != comiendo) {
            try {
                System.out.println("Filosofo " + id + " Esta esperando"); 
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public synchronized void soltarTenedores(int id) {
        int izq, der;
        der = (id + 1) % n;
        izq = id - 1;
        if (izq < 0) {
            izq = n - 1;
        }
        ventanaPrincipal.tenedor(id, n);
        ventanaPrincipal.tenedor(der, n);
        estado[id] = pensando;
        ventanaPrincipal.filosofosStatus.get(id).setIcon(ventanaPrincipal.pensandoImg);
        prueba(izq);
        prueba(der);

    }
    

    public void prueba(int id) {
        int izq, der;
        der = (id + 1) % n;
        izq = id - 1;
        if (izq < 0) {
            izq = n - 1;
        }
        if (estado[id] == hambriento && estado[izq] != comiendo && estado[der] != comiendo) {
            ventanaPrincipal.tenedor.get(id).setIcon(null);
            ventanaPrincipal.tenedor.get(der).setIcon(null);
            estado[id] = comiendo;
            ventanaPrincipal.filosofosStatus.get(id).setIcon(ventanaPrincipal.comiendoImg);
            notifyAll();
        }
    }

}
