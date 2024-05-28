/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Lop;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */

public class TableTrangChu extends AbstractTableModel{
    private String name[]={"Mã lớp","Tên lớp","Ngày bắt đầu", "Ngày kết thúc", "Số buổi học", "Số sinh viên"};
private Class classes[]={String.class,String.class,Date.class, Date.class,Integer.class,Integer.class};      
    //Tạo một đối tượng arrayList có tên dsSV.
    ArrayList<Lop> dsLop=new ArrayList<Lop>();

    public TableTrangChu(ArrayList<Lop> ds) {
        dsLop=ds;
    }
    @Override
    public int getRowCount() {
        return dsLop.size();
    }

    @Override
    public int getColumnCount() {
        return  name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            //Cột mã
            case 0: return dsLop.get(rowIndex).getMaLop(); 
            //Cột họ tên
            case 1: return dsLop.get(rowIndex).getTenLop();
            //Cột tuoi
            case 2: return dsLop.get(rowIndex).getNgayBD();
            case 3: return dsLop.get(rowIndex).getNgayKT();
            case 4: return dsLop.get(rowIndex).getSoBuoiHoc();
            case 5: return dsLop.get(rowIndex).getListSV().size();
            default :return null;
        }
    }
     @Override
    public Class getColumnClass(int columnIndex)
    {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column)
    {
        return name[column];
    }
    
}
