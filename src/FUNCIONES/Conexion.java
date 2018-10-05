package FUNCIONES;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;
import java.awt.Frame;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 *
 * @author Sara
 */
public class Conexion {
    DB db;
    DBCollection Coleccion;
    public  void ConectarBase()throws Exception{
        try{
        MongoClient mongoClient = new MongoClient("localhost",27017);
         db= mongoClient.getDB("Cine");
      /*  DBCollection ColPelicula= db.getCollection("Pelicula");
        DBCursor cursor= ColPelicula.find();
        
        while(cursor.hasNext()){
            int i=1;
            System.out.println(cursor.next());
            i++;
        }*/
        
        
        System.out.println("LISTOO");
        
        
        }catch(UnknownHostException e){
            System.out.println(e);
        }
        
        System.out.println("SERVER REAdY");
    }
    
    public Object MostrarPeliculasTitulo(JTextArea N, String Titulo){
        //Titulo;
         Coleccion = db.getCollection("Pelicula");
         
         //Convierte un objeto de java a uno que entienda mongo
         BasicDBObject filtro = new BasicDBObject("Nombre", new BasicDBObject("$regex",Titulo));
        //filtro.put(Titulo);
         
        DBCursor cursor= Coleccion.find(filtro);
       // try{
        while(cursor.hasNext()){
            N.setText(N.getText()+"\n"+cursor.next());  
            
          /*  int i=1;
            System.out.println(cursor.next());
            i++;*/
        }
        //}
       /* catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se han encontrado peliculas con este titulo");
        }*/
        System.out.println("El titulo consultado es:");
        System.out.println(Titulo);
        return null;
    }
    
    
    
    public void MostrarPeliculasFranquicia(JTextArea N, String Franquicia){
        //Titulo;
         Coleccion = db.getCollection("Pelicula");
         
         //Convierte un objeto de java a uno que entienda mongo
         BasicDBObject filtro = new BasicDBObject("Franquicia", new BasicDBObject("$regex",Franquicia));
        //filtro.put(Titulo);
         
        DBCursor cursor= Coleccion.find(filtro);
       // try{
        while(cursor.hasNext()){
            N.setText(N.getText()+"\n"+cursor.next());  
            
          /*  int i=1;
            System.out.println(cursor.next());
            i++;*/
        }
        //}
       /* catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se han encontrado peliculas con este titulo");
        }*/
        System.out.println("La franquicia consultada es:");
        System.out.println(Franquicia);
      //  return null;
           
        
    }
    
    
    public void AgregarPorRango(JTextArea N, int año1, int año2){
                
        Coleccion= db.getCollection("Pelicula");
        BasicDBObject filtro = new BasicDBObject();
        filtro.put("Estreno", new BasicDBObject("$gte",año1));
        filtro.put("Estreno", new BasicDBObject("$lte",año2));
        
        
         DBCursor cursor= Coleccion.find(filtro);
       // try{
        while(cursor.hasNext()){
            N.setText(N.getText()+"\n"+cursor.next());  
            
         }
        System.out.println("Se consulto rango desde");
        System.out.println(año1);
        System.out.println("hasta");
        System.out.println(año2);
        
        
        
    }
    
    public void FuncionProductora1(){
        //Ultima funcion
        
    }
      
      
    
    
}
