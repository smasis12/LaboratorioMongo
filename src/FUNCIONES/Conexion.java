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
        
        //“$lt“, “$lte“, “$gt” y “$gte” son operadores de comparación que corresponden a <, <=, >, y >=, respectivamente.
                
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
    
    public void FuncionProductora1(JTextArea N, String Productora){
        Coleccion=db.getCollection("Pelicula");
        
       // BasicDBObject filtro = new BasicDBObject("Productora", new BasicDBObject("$regex",Productora));
       
       BasicDBObject query = new BasicDBObject("Productora",new BasicDBObject("$regex", Productora));
    BasicDBObject project = new BasicDBObject();
    
    //Defino el nombre de las "Colunmas que quiero mostrar"
    project.put("Nombre",1);
    project.put("Genero",1);
    project.put("Estreno", 1); // set project to get Estreno
    project.put("_id", 0);//Para no mostrar el id
    
    DBCursor cursorDoc = Coleccion.find(query, project); // find query with projection
    
    while(cursorDoc.hasNext()) {
        
        N.setText(N.getText()+"\n"+cursorDoc.next());
        
     /* BasicDBObject object = (BasicDBObject) cursorDoc.next();
      String Estreno = object.get("Estreno").toString(); // If required convert the _id to String
      System.out.println(object); // print _id with object 
      System.out.println(Estreno); // print _id as a String*/
    }
     
        
    }
    
    public void FuncionProductora2(){
        String NombreProductora="Dreamworks";
        Coleccion=db.getCollection("Pelicula");
        
        BasicDBObject filtro = new BasicDBObject("Productora", new BasicDBObject("$regex",NombreProductora));
        
       System.out.print(Coleccion.find(filtro).count());
       
        
    }
    
    public void PeliculaMenor(){
        
         //“$lt“, “$lte“, “$gt” y “$gte” son operadores de comparación que corresponden a <, <=, >, y >=, respectivamente.
        int mayor=0;
        int menor=500;
        String NombreProductora="Dreamworks";
        
        Coleccion= db.getCollection("Pelicula");
        BasicDBObject filtro = new BasicDBObject("Productora" , new BasicDBObject("$regex",NombreProductora));
        BasicDBObject Columna = new BasicDBObject();
        Columna.put("Duracion",1);
        
        
         DBCursor cursor= Coleccion.find(filtro, Columna);
       // try{
        while(cursor.hasNext()){
            
             BasicDBObject object = (BasicDBObject) cursor.next();
      String Estreno = object.get("Duracion").toString(); // If required convert the _id to String
      
      int in= Integer.parseInt(Estreno);
      
      System.out.println(object); // print _id with object 
      System.out.println(Estreno);
            
            
            //N.setText(N.getText()+"\n"+cursor.next());  
            
         }
        /*System.out.println("Se consulto rango desde");
        System.out.println(año1);
        System.out.println("hasta");
        System.out.println(año2);*/
    }
      
      
    
    
}
