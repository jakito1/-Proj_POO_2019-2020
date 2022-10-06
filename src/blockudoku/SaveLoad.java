/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Francisco Moura (180221015), Tiago Farinha (180221011)
 */
public class SaveLoad {
    
    public static <T> void serializeDataOut(T object, String fileName) 
            throws IOException{
    FileOutputStream fos = new FileOutputStream(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        }
    }

    public static <T> T serializeDataIn(String fileName) 
        throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(fileName);
        T object;
            try (ObjectInputStream ois = new ObjectInputStream(fin)) {
                object = (T) ois.readObject();
            }
        return object;
    }
}
