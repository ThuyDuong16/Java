/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.SinhVien;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ADMIN
 */
public class TableSinhVienTN  extends AbstractTableModel {
       private ArrayList<SinhVien> listsvtn;
    private String[] columnNames = {"Mã Sinh Viên", "Tên Sinh Viên"};
     public TableSinhVienTN(ArrayList<SinhVien> listsvtn) {
        this.listsvtn = listsvtn;
    }
    @Override
    public int getRowCount() {
        return listsvtn.size();
    }
  

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SinhVien student = listsvtn.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return student.getMaSV();
            case 1:
                return student.getHoTen();
                
            default:
                return null;
        }
    }
          public void addStudent(SinhVien student) {
            listsvtn.add(student);
            fireTableRowsInserted(listsvtn.size() - 1, listsvtn.size() - 1);
        }
             public SinhVien getStudentAt(int rowIndex) {
            return listsvtn.get(rowIndex);
        }
                 public void removeStudentAt(int rowIndex) {
            listsvtn.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
         @Override
                public boolean isCellEditable(int row, int column) {
                    System.out.println("isCellEditable called for row: " + row + ", column: " + column);
                    return false; // Cho phép chỉnh sửa tất cả các ô
                }
                
    
}
