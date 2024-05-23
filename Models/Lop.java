/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Lop implements Serializable {

    private String maLop;
    private String tenLop;
    private Date ngayBD;
    private Date ngayKT;
    private int soBuoiHoc;
    private ArrayList<SinhVien> listSV;

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public void setSoBuoiHoc(int soBuoiHoc) {
        this.soBuoiHoc = soBuoiHoc;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public int getSoBuoiHoc() {
        return soBuoiHoc;
    }

    public void setListSV(ArrayList<SinhVien> listSV) {
        this.listSV = listSV;
    }

    public ArrayList<SinhVien> getListSV() {
        return listSV;
    }

    public Lop() {
    }

    public Lop(String maLop) {
        this.maLop = maLop;
    }


    public Lop(String maLop, String tenLop, Date ngayBD, Date ngayKT, int soBuoiHoc, ArrayList<SinhVien> listSV) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.soBuoiHoc = soBuoiHoc;
        this.listSV = listSV;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.maLop);
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
        final Lop other = (Lop) obj;
        return Objects.equals(this.maLop, other.maLop);
    }
    

}
