/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Lop;
import Models.SinhVien;
import Models.TrucNhatLop;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */

public class TableTrangChuSinhVien extends AbstractTableModel{
    private String name[]={"Mã lớp","Tên lớp", "Ngày trực nhật","Sinh viên trực nhật", "Buổi" ,"Lưu ý"};
     
    //Tạo một đối tượng arrayList có tên dsSV.
    ArrayList<TrucNhatLop> dsLop=new ArrayList<TrucNhatLop>();

    public String buildStudentString(ArrayList<SinhVien> studentList) {
        StringBuilder stringBuilder = new StringBuilder();
   
            for (SinhVien student : studentList) {      
                    String maSV = student.getMaSV();
                    System.out.println(maSV);
                    // Kiểm tra xem chuỗi có thể chuyển đổi thành số nguyên không
                    try {
                        int maSVInt = Integer.parseInt(maSV);
                        stringBuilder.append(String.valueOf(maSVInt));
                    } catch (NumberFormatException e) {
                        // Nếu không phải số nguyên, giữ nguyên giá trị
                        stringBuilder.append(maSV);
                    }
                        stringBuilder
                             .append(" - ")
                             .append(student.getHoTen())
                             .append("\n");
            }
        return stringBuilder.toString();
    }
    public String display(Date ngay){
    

        // Định dạng của chuỗi ngày đầu ra
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");

        // Chuyển đổi chuỗi ngày sang đối tượng Date
        
        
        // Chuyển đổi đối tượng Date sang chuỗi ngày theo định dạng mong muốn
        String ngayOutput = sdfOutput.format(ngay);
      return ngayOutput;
    }
    public TableTrangChuSinhVien(ArrayList<TrucNhatLop> ds) {
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
//            Cột mã
            case 0: return dsLop.get(rowIndex).getMalop();            
            case 1: return dsLop.get(rowIndex).getTenlop(); 
            case 2: return display(dsLop.get(rowIndex).getTn().getNgayTN());
            case 3: return buildStudentString(dsLop.get(rowIndex).getTn().getListSV()) ;
            case 4: return dsLop.get(rowIndex).getTn().getBuoi(); 
             
            case 5: return dsLop.get(rowIndex).getTn().getLuuY(); 
            default :return null;
        }
    }
     

    @Override
    public String getColumnName(int column)
    {
        return name[column];
    }

    
    
}
