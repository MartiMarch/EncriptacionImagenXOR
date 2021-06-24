package com.mycompany.encriptarimagenxor;

//Programa para encriptar imaágenes mediante XOR
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncriptarImagenXOR{
    private String key = "Una frase secreta";
    
    public EncriptarImagenXOR(){};
    public void encriptarImagenXOR(String ruta) throws FileNotFoundException, IOException
    {  
        //Abrimos la imagen
        FileInputStream imagen = new FileInputStream(ruta);
        
        //Almacenamos todos los bytes de la imagen
        byte imagenBytes[] = new byte[imagen.available()];
        
        //Leemos la imagen
        imagen.read(imagenBytes);
        
        //Applicamos el XOR
        int j = 0;
        for(int i = 0; i < imagenBytes.length; ++i)
        {
            imagenBytes[i] = (byte)(imagenBytes[i] ^ key.charAt(j));
            if(j==key.length()-1){j=0;}
            else{++j;}
        }
        
        //Obtener una nueva ruta válida
        String nombre = "", tipo = "", temp = "";
        j = ruta.length() -1;
        
        //Se obtiene el tipo, pero invertido
        while(ruta.charAt(j)!='.')
        {
            //el formato de la imagen
            tipo += ruta.charAt(j);
            --j;
        }
        --j;
        
        //Hay que darle la vuelta al tipo
        tipo = this.revertirString(tipo);
        
        //Se obtiene el nombre, pero invertido
        while(ruta.charAt(j)!='\\')
        {
            //el nombre del archivo
            nombre += ruta.charAt(j);
            --j;        
        }
        
        //Hay que darle la vuelta al nombre
        nombre = this.revertirString(nombre);
        
        //la nueva ruta
        ruta = ruta.substring(0, j) + '\\' + nombre + "_encriptado." + tipo;
       
        //Guardamos la imagen
        FileOutputStream imagen_encriptada = new FileOutputStream(ruta);
        
        //Escribimos la imagen
        imagen_encriptada.write(imagenBytes);
        
        imagen.close();
        imagen_encriptada.close();
    }
    
    public String revertirString(String cadena)
    {
        String salida = "";
        int i = cadena.length() - 1;
                
        while(i > -1)
        {
            salida += cadena.charAt(i);
            --i;
        }
        
        return salida;
    }
    
    
    public static void main(String[] args) throws IOException
    {
        EncriptarImagenXOR imagen_XOR = new EncriptarImagenXOR();
        imagen_XOR.encriptarImagenXOR("D:\\Fragmentos de programas\\Encriptar Imagenes [Java]-[Windows]\\EncriptarImagenXOR\\src\\main\\java\\com\\mycompany\\encriptarimagenxor\\lobo_encriptado.jpg");
    } 
}
