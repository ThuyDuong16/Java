/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.FileTXT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ReadWriteList {
    public ReadWriteList() {
    }

    public <T> void WriteObject(String fileName, ArrayList<T> list) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        }
    }

    public <T> ArrayList<T> ReadObject(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<T> kq = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            kq = (ArrayList<T>) ois.readObject();
        }
        return kq;
    }
}
