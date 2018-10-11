package FUNCIONES;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;
import java.awt.Frame;
import java.util.List;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Sara
 */
public class Conexion {
    private DB db;
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
        
        System.out.println("LISTO");
                
        }catch(UnknownHostException e){
            System.out.println(e);
        }
        System.out.println("BASE CONECTADA");
    }
    //--------------------------------------------------------------------------FUNCIONES
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
    }}
    
    public void FuncionProductora2(){
        String NombreProductora="Dreamworks";
        Coleccion=db.getCollection("Pelicula");
        
        BasicDBObject filtro = new BasicDBObject("Productora", new BasicDBObject("$regex",NombreProductora));
        
       System.out.print(Coleccion.find(filtro).count());    
    }
    
    
    public void MayorPelicula(){
        String NP="Dreamworks";
        Coleccion= db.getCollection("Pelicula");
         BasicDBObject filtro = new BasicDBObject("Productora", new BasicDBObject("$regex",NP));
        
      //   DBCursor cursor= Coleccion.aggregate(filtro, dbos);
    }
    
    public void MenorPelicula(){        
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
            String Duracion = object.get("Duracion").toString(); // If required convert the _id to String
            
            int in= Integer.parseInt(Duracion);
            
            
            if(in>mayor){
                mayor=in;
                
            }
            
            else if(in<menor){
                
                menor=in;
            }
             
      
            System.out.println(object); // print _id with object 
            System.out.println(Duracion);
            System.out.println(mayor);
            System.out.println(menor);
            
            
            //N.setText(N.getText()+"\n"+cursor.next());  
            
         }
        /*System.out.println("Se consulto rango desde");
        System.out.println(año1);
        System.out.println("hasta");
        System.out.println(año2);*/
    }
    
     public String cantidadPeliculas(JTextField N, String productora){
        BasicDBObject query = new BasicDBObject("Productora", productora);
        DBCursor cursor = db.getCollection("Pelicula").find(query);
         N.setText(N.getText()+"\n"+cursor.count());
        return null;
        
    }
     
     
     public String peliculaMenorDuracion(JTextField N, String productora){
        BasicDBObject query = new BasicDBObject("Productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("Nombre",1);
        x.put("Duracion",1);
        x.put("_id",0);
        DBCursor cursor = db.getCollection("Pelicula").find(query, x).sort(new BasicDBObject("Duracion", 1)).limit(1);
       
        while (cursor.hasNext()){
            N.setText(N.getText()+"\n"+cursor.next().toString());
          //  cursorResultado +=( N.setText(N.getText()+"\n"+cursor.next().toString()));
        }      
        return null;
    }
     
     
        
     
    public String peliculaDuracionPromedio(JTextField N, String productora){
        BasicDBObject query = new BasicDBObject("Productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("Duracion",1);
        x.put("_id",0);
        DBCursor cursor = db.getCollection("Pelicula").find(query, x);
        float promedio = 0;
        //String cursorResultado = "";
        while (cursor.hasNext()){
            
            String actual = cursor.next().get("Duracion").toString();
            System.out.println(actual);
            int duracion = Integer.parseInt(actual);
            promedio += duracion;
           
        }    
        float cursorResultado = (promedio/cursor.count());
        System.out.println("EL promedio es:");
        System.out.println(cursorResultado);
        return ("\n" + "La duracion promedio es: " + cursorResultado);
        
    }
     
     
     public String peliculaMayorDuracion(JTextField N, String productora){
        BasicDBObject query = new BasicDBObject("Productora", productora);
        BasicDBObject x = new BasicDBObject();
        x.put("Nombre",1);
        x.put("Duracion",1);
        x.put("_id",0);
        DBCursor cursor = db.getCollection("Pelicula").find(query, x).sort(new BasicDBObject("Duracion", -1)).limit(1);
       
        while (cursor.hasNext()){
            N.setText(N.getText()+"\n"+cursor.next().toString());
        }    
        return null;
    }
     
     
    
    
    
    public void readPelicula(JTextArea N){
        
        Coleccion= db.getCollection("Pelicula");
        
        DBCursor cur = Coleccion.find();
        while (cur.hasNext())
        //System.out.println(cur.next());
         N.setText(N.getText()+"\n"+cur.next());        
               
        /*
        DBCollection coll = db.getCollection("Pelicula");
        DBCursor cursor = coll.find();
        
        while(cursor.hasNext()){
            N.setText(N.getText()+"\n"+cursor.next());
        }*/     
    }
    
     public void readProductora(JTextArea N) throws Exception{
      
        Coleccion = db.getCollection("Productoras");
        DBCursor cursor = Coleccion.find();
        
        while(cursor.hasNext()){
             N.setText(N.getText()+"\n"+cursor.next());
        } 
    }
    
       public void insertPelicula ( String nombre, String genero, String director, String franquicia, String pais, int año, int duracion, String productora, String actor1, String actor2, String actor3){
        Coleccion = db.getCollection("Pelicula");
        List<String> actores= Arrays.asList(actor1,actor2,actor3);
        
        DBObject pelicula = new BasicDBObject();
        pelicula.put("Nombre", nombre);
        pelicula.put("Genero",genero);
        pelicula.put("Director", director);
        pelicula.put("Franquicia", franquicia);
        pelicula.put("Pais", pais);
        pelicula.put("Estreno", año);
        pelicula.put("Duracion", duracion);
        pelicula.put("Productora", productora);
        pelicula.put("Actores", actores);
     //   DBCollection collection = db.getCollection("Pelicula");
        
        Coleccion.insert(pelicula);
        System.out.println("Pelicula insertada correctamente");
    }
       
       public void insertarProductora(String nombre,int año,String url){
        DBObject productora = new BasicDBObject()
        .append("Nombre", nombre)
        .append("AnioFundacion", año)
        .append("SitioWeb", url);
        Coleccion= db.getCollection("Productoras");
        
        Coleccion.insert(productora);
        
        System.out.println("Productora insertada correctamente");
    }
   /*    public void insertarProductora(){
           Coleccion = db.getCollection("Productoras");
           
           String D="DisneyProductions";
           int F= 2009;
           String W= "httpssitioweb";
           BasicDBObject document = new BasicDBObject();
           document.put("Nombre","'"+D+"'");
           document.put("AnioFundacion", F);
           document.put("SitioWeb","'"+W+"'");
           
           Coleccion.insert(document);
           
           
       }*/
       
       public void deleteProductora(String nombre){
        DBCollection collection = db.getCollection("Productoras");
        BasicDBObject document = new BasicDBObject();
        document.put("Nombre", nombre);
        collection.remove(document);
    }
       
        public void deletePelicula(String nombre){
        DBCollection collection = db.getCollection("Pelicula");
        BasicDBObject document = new BasicDBObject();
        document.put("Nombre", nombre);
        collection.remove(document);
    }
    
        public void updatePelicula(String nombre,String genero,String director,String franquicia,String pais,int año, int duracion, String productora, String actor1, String actor2, String actor3){
        BasicDBObject newDocument = new BasicDBObject();
        List<String> actores = Arrays.asList(actor1,actor2,actor3);
	newDocument.append("$set", new BasicDBObject().append("Nombre", nombre)
        .append("Genero",genero)
        .append("Director", director)
        .append("Franquicia", franquicia)      
        .append("Pais", pais)
        .append("Estreno", año)
        .append("Duracion", duracion)
        .append("Productora", productora)
        .append("Actores", actores)
        );
			
	BasicDBObject searchQuery = new BasicDBObject().append("Nombre", nombre);
        DBCollection collection = db.getCollection("Pelicula");
	collection.update(searchQuery, newDocument);
    }
        
         public void updateProductora(String nombre,int año,String url){
        BasicDBObject newDocument = new BasicDBObject();
	newDocument.append("$set", new BasicDBObject()
        .append("Nombre", nombre)
        .append("AnioFundacion", año)
        .append("SitioWeb", url));
        
        BasicDBObject searchQuery = new BasicDBObject().append("Nombre", nombre);
        DBCollection collection = db.getCollection("Productoras");
        collection.update(searchQuery, newDocument);   
    }
    
    

    
    
}
