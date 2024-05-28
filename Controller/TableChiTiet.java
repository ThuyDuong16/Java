
package Controller;

import Models.SinhVien;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import Models.TrucNhat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;
public class TableChiTiet extends AbstractTableModel {
    ArrayList<TrucNhat> listTrucNhat;
     
    private String columnName[] = {"Buổi","Ngày", "Sinh viên trực nhật", "Lưu ý"};

  
 public TableChiTiet(ArrayList<TrucNhat> listtrucnhat) {
        this.listTrucNhat = listtrucnhat;
    }
 
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

    @Override
    public int getRowCount() {
        return listTrucNhat.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }
    @Override
        public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 2 ? ArrayList.class : String.class;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrucNhat tn=listTrucNhat.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tn.getBuoi();
            case 1:
                return display(tn.getNgayTN());
            case 2:
                return buildStudentString(tn.getListSV());
            case 3 : 
                return tn.getLuuY();
            default:
                return null;
        }
    }
    public TrucNhat getStudentAt(int rowIndex) {
            return listTrucNhat.get(rowIndex);
        }
     public void removeStudentAt(int rowIndex) {
            listTrucNhat.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
     }
      
    @Override
    public String getColumnName(int column)
    {
        return columnName[column];
    }
}

