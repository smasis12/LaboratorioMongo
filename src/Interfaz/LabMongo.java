/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import FUNCIONES.Conexion;

/**
 *
 * @author Sara
 */
public class LabMongo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        MongoJframe M1= new MongoJframe();
        Conexion conectar =new Conexion();
        conectar.ConectarBase();
        M1.setVisible(true);
        // TODO code application logic here
    }
    
}
