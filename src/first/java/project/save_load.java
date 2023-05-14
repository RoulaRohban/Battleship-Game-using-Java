/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.java.project;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author RoR
 */
public class save_load 
{
    public static void save(Serializable info,String name_file)throws Exception
    {
      try(ObjectOutputStream out=new ObjectOutputStream (Files.newOutputStream(Paths.get("src/" + name_file)))){
          out.writeObject(info);
          }
    }
    public static Object load(String name_file)throws Exception{

        try(ObjectInputStream input=new ObjectInputStream(Files.newInputStream(Paths.get("src/" + name_file)))){ 
            return input.readObject();
        }  
    }
    
}
