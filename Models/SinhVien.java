/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class SinhVien implements Serializable{

    private String maSV;
    private String hoTen;
    private ArrayList<String> listMaLop ;

    public SinhVien(String maSinhVien, String hoTen) {
        this.maSV = maSinhVien;
        this.hoTen = hoTen;
    }
       public SinhVien(String maSinhVien) {
        this.maSV = maSinhVien;
     
    }

   
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    
    public SinhVien() {
    }
    public SinhVien(String maSV, String hoTen, ArrayList<String> listMaLop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.listMaLop = listMaLop;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHoTen() {
        return hoTen;
    }
    
    public String getMaSV() {
        return maSV;
    }

    public ArrayList<String> getListMaLop() {
        return listMaLop;
    }

    

    public void setListMaLop(ArrayList<String> listMaLop) {
        this.listMaLop = listMaLop;
    }

    public void addMaLop(String maLop) {
        listMaLop.add(maLop);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.maSV);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SinhVien other = (SinhVien) obj;
        return Objects.equals(this.maSV, other.maSV);
    }

 

}
