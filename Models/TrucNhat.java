/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TrucNhat implements Serializable{
    private Lop lop;
    private Date ngayTN;
    private String luuY;
    private int Buoi;
    private ArrayList<SinhVien> listSV = new ArrayList<SinhVien>();



    public void setListSV(ArrayList<SinhVien> listSV) {
        this.listSV = listSV;
    }

    public void setNgayTN(Date ngayTN) {
        this.ngayTN = ngayTN;
    }

    public void setLuuY(String luuY) {
        this.luuY = luuY;
    }
      public void setBuoi(int buoi) {
        this.Buoi = buoi;
    }
       public void setLop(Lop lop) {
        this.lop = lop;
    }
 public Lop getLop(){
     return lop;
 }



    public Date getNgayTN() {
        return ngayTN;
    }
    public String display(Date ngay){
    

        // Định dạng của chuỗi ngày đầu ra
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");

        // Chuyển đổi chuỗi ngày sang đối tượng Date
        
        
        // Chuyển đổi đối tượng Date sang chuỗi ngày theo định dạng mong muốn
        String ngayOutput = sdfOutput.format(ngay);
      return ngayOutput;
    }

    public ArrayList<SinhVien> getListSV() {
        return listSV;
    }

    public String getLuuY() {
        return luuY;
    }
     public int getBuoi() {
        return Buoi;
    }

    public TrucNhat() {
    }

    public TrucNhat(int buoi, Date ngayTN,ArrayList<SinhVien> listSV, String luuY, Lop lop ) {
        this.Buoi=buoi;
        this.ngayTN = ngayTN;
        this.luuY = luuY;
        this.listSV= listSV;
        this.lop=lop;
    }
    
}
