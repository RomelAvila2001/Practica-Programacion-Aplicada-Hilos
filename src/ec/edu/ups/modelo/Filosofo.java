/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.modelo.Accion;
import ec.edu.ups.vista.VentanaPrincipal;
import java.util.Random;

/**
 *
 * @author NANCY
 */
public class Filosofo {
    private int id;
    private Accion accion;
    //private int nComidas;
    private final Random randomico = new Random();
    VentanaPrincipal ventanaPrincipal;
    
    public Filosofo(int id, Accion a) {
        this.id = id;
        accion = a;
        //nComidas = 0;

    }
    
    public void pensando() {
        System.out.println("Filosofo " + id + " pensando");
        ventanaPrincipal.filosofosStatus.get(id).setIcon(ventanaPrincipal.pensandoImg);
        try {
            Thread.sleep(randomico.nextInt(5000));                
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void comer() {
        System.out.println("Filosofo " + id + "comiendo");
        try {
            Thread.sleep(randomico.nextInt(5000));
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    public void run() {
        boolean salir = true;
        while (salir==true) {   
            pensando();
            accion.tomarTenedores(id);
            comer();
            accion.soltarTenedores(id);
        }
                 
    }

}
