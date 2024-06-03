package View;

import Models.Lop;
import Models.SinhVien;
import Models.TrucNhat;
import View.ChiTietQLTN;

import Controller.TableSinhVienTN;
import Models.FileTXT.ReadWriteList;
import View.DangNhap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Image;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
/**
 *
 * @author pc
 */
public class Them_Sua_ChiTiet extends javax.swing.JFrame {

    static TableSinhVienTN model;

    /**
     * Creates new form NewApplication
     */
    public Them_Sua_ChiTiet() {

    }

    Them_Sua_ChiTiet(String classID, int buoi, int selectedRow, ChiTietQLTN This) {
        initComponents();
          readListLop();
        readListTrucNhat() ;
        ganlist(classID);
        display(classID, buoi, selectedRow);
        themSVTN(classID, buoi);
        suaSVTN(classID, buoi);
        xoa();
    }
    ArrayList<Lop> listLop = new ArrayList<>();
    ReadWriteList fileListLop = new ReadWriteList();
    ReadWriteList fileListTrucNhat = new ReadWriteList();
    String data_listLop = "CSDL_txt\\data_listLop.txt";
    String data_listTrucNhat = "CSDL_txt\\data_listTrucNhat.txt";
 ArrayList<ArrayList<TrucNhat>> allListTrucNhat=new ArrayList<>();
    void readListLop() {
        try {
            this.listLop = fileListLop.ReadObject(data_listLop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     void readListTrucNhat() {
            
        try {
            this.allListTrucNhat = fileListTrucNhat.ReadObject(data_listTrucNhat);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
         public void writeListTrucNhat(){
            
        try {
            fileListTrucNhat.WriteObject(data_listTrucNhat, allListTrucNhat);
          
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(Them_Sua_ChiTiet.this, "Lỗi ghi truc nhat!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

   public ArrayList<TrucNhat> ganlist(String classID) {
     for(ArrayList<TrucNhat> tn :allListTrucNhat){
         if(tn.get(0).getLop().getMaLop().equals(classID)){
             return tn;
         }
     }
     return new ArrayList<TrucNhat>();
    }

    public Lop findClassByID(String classID) {
        // Tìm kiếm lớp trong danh sách dựa trên mã lớp
        for (Lop classInfo : listLop) {
            if (classInfo.getMaLop().equals(classID)) {
                return classInfo;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public TrucNhat findTNByBuoi(int buoi,String classID) {
      
        // Tìm kiếm lớp trong danh sách dựa trên mã lớp
        for (TrucNhat tn : ganlist(classID)) {
         
            if (tn.getBuoi() == buoi) {
                return tn;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public void display(String classID, int buoi, int selectedRow) {
        ngaytn.setText(findTNByBuoi(buoi,classID).display(findTNByBuoi(buoi,classID).getNgayTN())); //ke thua
        luuy.setText(findTNByBuoi(buoi,classID).getLuuY());
        model = new TableSinhVienTN(findTNByBuoi(buoi,classID).getListSV());
        trucnhat.setModel(model);
        clickback(classID, selectedRow);
    }

    public void clickback(String classID, int selectedRow) {
        btnTroVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng JFrame chi tiết
                // Hiển thị lại JFrame danh sách lớp
                ChiTietQLTN detail = new ChiTietQLTN(classID, selectedRow, null);
                detail.setVisible(true);

            }
        });
    }

    private void themSVTN(String classID, int buoi) {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị cửa sổ dialog để nhập thông tin sinh viên mới
                showAddSinhVienDialog(classID, buoi);
                writeListTrucNhat();
            }
        });
//cap nhat lai vao csdl

    }

    private void showAddSinhVienDialog(String classID, int buoi) {
        // Tạo một dialog mới
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chọn Sinh Viên Trong Lớp Trực Nhật", true);
        JTextField txtTimKiem = new JTextField();
        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setBackground(new Color(255, 204, 0));
        Font boldFont = new Font(btnTimKiem.getFont().getName(), Font.BOLD, btnTimKiem.getFont().getSize());
        btnTimKiem.setFont(boldFont);
        // Sự kiện khi nhấn nút Tìm Kiếm

        // Tạo bảng để hiển thị danh sách sinh viên
        String[] columns = {"Mã Sinh Viên", "Họ Tên"};
        DefaultTableModel Model = new DefaultTableModel(null, columns);
        JTable tableSinhVien = new JTable(Model);

        // Thêm dữ liệu từ danh sách sinh viên vào bảng
        for (SinhVien sinhVien : findClassByID(classID).getListSV()) {
            Model.addRow(new Object[]{sinhVien.getMaSV(), sinhVien.getHoTen()});
        }
        JButton btnReload = new JButton("Reload");
        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện nạp lại dữ liệu
                DefaultTableModel newModel = new DefaultTableModel();
                tableSinhVien.setModel(newModel);
                tableSinhVien.setModel(new TableSinhVienTN(findClassByID(classID).getListSV()));
            }
        });

        // Nút thêm vào danh sách sinh viên trực nhật
        JButton btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(0, 153, 51));
        Font boldFont1 = new Font(btnThem.getFont().getName(), Font.BOLD, btnThem.getFont().getSize());
        btnThem.setFont(boldFont1);
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy hàng được chọn
                int selectedRow = tableSinhVien.getSelectedRow();

                // Kiểm tra xem có hàng được chọn hay không
                if (selectedRow != -1) {
                    // Lấy thông tin sinh viên từ hàng được chọn
                    String maSinhVien = (String) tableSinhVien.getValueAt(selectedRow, 0);
                    String hoTen = (String) tableSinhVien.getValueAt(selectedRow, 1);

                    // Thêm sinh viên vào danh sách sinh viên trực nhật
                    try {
                        if (checkthem(maSinhVien, buoi,classID)) {
                            throw new Exception("Mã sinh viên đã tồn tại.");
                        }
                        model.addStudent(new SinhVien(maSinhVien, hoTen));

                        // Cập nhật bảng sinh viên trực nhật
                        trucnhat.getModel().addTableModelListener(i -> {
                            if (i.getType() == TableModelEvent.UPDATE) {
                                int row = i.getFirstRow();
                                int column = i.getColumn();
                                // Lấy giá trị từ ô đã được chỉnh sửa và cập nhật vào dữ liệu của ứng dụng
                                Object newValue = trucnhat.getValueAt(row, column);
                                // Cập nhật dữ liệu vào mô hình (EditableTableModel) hoặc thực hiện hành động cần thiết
                                model.setValueAt(newValue, row, column);
                                // System.out.println("Updated value: " + newValue);
                            }
                        });
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dialog, ex.getMessage());
                    }

                    // Đóng dialog
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Chọn một sinh viên để thêm.");
                }
            }
        });
        //tim kiem sinh vien de chon
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện tìm kiếm
                String keyword = txtTimKiem.getText().trim().toLowerCase();
                searchSinhVien(keyword, classID, tableSinhVien);
            }
        });

        // Thêm các thành phần vào dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(tableSinhVien), BorderLayout.CENTER);
        dialog.add(btnThem, BorderLayout.SOUTH);
        dialog.add(txtTimKiem, BorderLayout.NORTH); // Thêm ô văn bản vào vị trí phía trên
        dialog.add(btnTimKiem, BorderLayout.EAST);
        dialog.add(btnReload, BorderLayout.WEST);
        // Cài đặt kích thước và hiển thị dialog
        tableSinhVien.setBackground(Color.LIGHT_GRAY);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private boolean checkthem(String maSinhVien, int buoi,String classID) {

        for (SinhVien sv : findTNByBuoi(buoi,classID).getListSV()) {
            if (sv.getMaSV().equals(maSinhVien)) {
                return true; // Mã sinh viên đã tồn tại
            }
        }
        return false; // Mã sinh viên không tồn tại
    }

    private void searchSinhVien(String keyword, String classID, JTable tblthemsvtn) {
        String TimKiem = keyword;
        if (TimKiem.isEmpty() == false) {
            String regex = ".*" + Pattern.quote(TimKiem) + ".*";
            ArrayList<SinhVien> listSinhVienTrucNhat = new ArrayList<>();
            for (SinhVien sv : findClassByID(classID).getListSV()) {
                if (sv.getHoTen().toLowerCase().contains(TimKiem) || sv.getMaSV().toLowerCase().contains(TimKiem)) {
                    listSinhVienTrucNhat.add(sv);
                    //System.out.println(sv);
                }
            }

            DefaultTableModel newModel = new DefaultTableModel();
            tblthemsvtn.setModel(newModel);
            tblthemsvtn.setModel(new TableSinhVienTN(listSinhVienTrucNhat));

        }
    }

    private void suaSVTN(String classID, int buoi) {
        btnSua1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị cửa sổ dialog để nhập thông tin sinh viên mới
                int selectedRow = trucnhat.getSelectedRow();
                // Kiểm tra xem có hàng được chọn không
                if (selectedRow != -1) {
                    // Lấy sinh viên từ mô hình dựa trên chỉ số hàng
                    SinhVien student = model.getStudentAt(selectedRow);
                    // Hiển thị cửa sổ dialog để sửa thông tin sinh viên
                    showSuaSinhVienDialog(classID, buoi, student);
                } else {
                    // Nếu không có hàng nào được chọn, thông báo cho người dùng
                    JOptionPane.showMessageDialog(Them_Sua_ChiTiet.this, "Vui lòng chọn một sinh viên để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
//cap nhat lai vao csdl

    }

    private void showSuaSinhVienDialog(String classID, int buoi, SinhVien student) {
        // Tạo một dialog mới
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chọn Sinh Viên Để Sửa Trong Lớp Trực Nhật", true);
        JTextField txtTimKiem = new JTextField();
        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setBackground(new Color(255, 204, 0));
        Font boldFont = new Font(btnTimKiem.getFont().getName(), Font.BOLD, btnTimKiem.getFont().getSize());
        btnTimKiem.setFont(boldFont);
        // Sự kiện khi nhấn nút Tìm Kiếm

        // Tạo bảng để hiển thị danh sách sinh viên
        String[] columns = {"Mã Sinh Viên", "Họ Tên"};
        DefaultTableModel Model = new DefaultTableModel(null, columns);
        JTable tableSinhViensua = new JTable(Model);

        // Thêm dữ liệu từ danh sách sinh viên vào bảng
        for (SinhVien sinhVien : findClassByID(classID).getListSV()) {
            Model.addRow(new Object[]{sinhVien.getMaSV(), sinhVien.getHoTen()});
        }
        JButton btnReload = new JButton("Reload");
        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện nạp lại dữ liệu
                DefaultTableModel newModel = new DefaultTableModel();
                tableSinhViensua.setModel(newModel);
                tableSinhViensua.setModel(new TableSinhVienTN(findClassByID(classID).getListSV()));
            }
        });

        // Nút sua vào danh sách sinh viên trực nhật
        JButton btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(0, 153, 51));
        Font boldFont1 = new Font(btnSua.getFont().getName(), Font.BOLD, btnSua.getFont().getSize());
        btnSua.setFont(boldFont1);
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy hàng được chọn
                int selectedRow = tableSinhViensua.getSelectedRow();

                // Kiểm tra xem có hàng được chọn hay không
                if (selectedRow != -1) {
                    // Lấy thông tin sinh viên từ hàng được chọn
                    String maSinhVien = (String) tableSinhViensua.getValueAt(selectedRow, 0);
                    String hoTen = (String) tableSinhViensua.getValueAt(selectedRow, 1);

                    // Thêm sinh viên vào danh sách sinh viên trực nhật
                    try {
                        if (checkthem(maSinhVien, buoi,classID)) {
                            throw new Exception(" sinh viên này đang được trực nhật.");
                        }
                        //cap nhat csdl
                        student.setMaSV(maSinhVien);
                        student.setHoTen(hoTen);
                        model.fireTableDataChanged();
                        // Cập nhật bảng sinh viên trực nhật
                        trucnhat.getModel().addTableModelListener(i -> {
                            if (i.getType() == TableModelEvent.UPDATE) {
                                int row = i.getFirstRow();
                                int column = i.getColumn();
                                // Lấy giá trị từ ô đã được chỉnh sửa và cập nhật vào dữ liệu của ứng dụng
                                Object newValue = trucnhat.getValueAt(row, column);
                                // Cập nhật dữ liệu vào mô hình (EditableTableModel) hoặc thực hiện hành động cần thiết
                                model.setValueAt(newValue, row, column);
                                // System.out.println("Updated value: " + newValue);
                                writeListTrucNhat();// ghi vao file
                            }
                        });
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dialog, ex.getMessage());
                    }

                    // Đóng dialog
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Chọn một sinh viên để thêm.");
                }
            }
        });
        //tim kiem sinh vien de chon
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện tìm kiếm
                String keyword = txtTimKiem.getText().trim().toLowerCase();
                searchSinhVien(keyword, classID, tableSinhViensua);
            }
        });

        // Thêm các thành phần vào dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(tableSinhViensua), BorderLayout.CENTER);
        dialog.add(btnSua, BorderLayout.SOUTH);
        dialog.add(txtTimKiem, BorderLayout.NORTH); // Thêm ô văn bản vào vị trí phía trên
        dialog.add(btnTimKiem, BorderLayout.EAST);
        dialog.add(btnReload, BorderLayout.WEST);
        // Cài đặt kích thước và hiển thị dialog
        tableSinhViensua.setBackground(Color.LIGHT_GRAY);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void xoa() {
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = trucnhat.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmResult = JOptionPane.showConfirmDialog(
                            Them_Sua_ChiTiet.this,
                            "Bạn có chắc chắn muốn xóa sinh viên này không?",
                            "Xác nhận Xóa",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (confirmResult == JOptionPane.OK_OPTION) {
                        model.removeStudentAt(selectedRow);
                        writeListTrucNhat();
                    }
                } else {
                    JOptionPane.showMessageDialog(Them_Sua_ChiTiet.this, "Vui lòng chọn một sinh viên để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JButton();
        LaBel_QLTN = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        ngaytn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        luuy = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTroVe = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        trucnhat = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thêm - Sửa - Trang chi tiết");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 102, 51));

        btnTrangChu.setBackground(new java.awt.Color(0, 102, 51));
        javax.swing.ImageIcon originalIcon  = new javax.swing.ImageIcon(getClass().getResource("/HinhAnh/logo-haui.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        btnTrangChu.setIcon(resizedIcon);
        btnTrangChu.setBorder(null);
        btnTrangChu.setFocusable(false);
        btnTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTrangChu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangChu.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnTrangChu.setMaximumSize(new java.awt.Dimension(40, 40));
        btnTrangChu.setMinimumSize(new java.awt.Dimension(40, 40));
        btnTrangChu.setRolloverEnabled(false);
        btnTrangChu.setVerifyInputWhenFocusTarget(false);
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        LaBel_QLTN.setBackground(new java.awt.Color(0, 135, 102));
        LaBel_QLTN.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        LaBel_QLTN.setForeground(new java.awt.Color(255, 255, 255));
        LaBel_QLTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LaBel_QLTN.setText("Quản lý trực nhật sinh viên HAUI");
        LaBel_QLTN.setToolTipText("");
        LaBel_QLTN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaBel_QLTN, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LaBel_QLTN, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnDangXuat.setBackground(new java.awt.Color(0, 153, 255));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setToolTipText("");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        ngaytn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Ngày:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        luuy.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        luuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luuyActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Lưu ý:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ngaytn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(luuy, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(luuy, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ngaytn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(0, 102, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua1.setBackground(new java.awt.Color(0, 102, 0));
        btnSua1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua1.setForeground(new java.awt.Color(255, 255, 255));
        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(204, 51, 0));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTroVe.setBackground(new java.awt.Color(255, 204, 0));
        btnTroVe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTroVe.setForeground(new java.awt.Color(255, 255, 255));
        btnTroVe.setText("Trở về");
        btnTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        trucnhat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        trucnhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        trucnhat.setForeground(new java.awt.Color(0, 51, 51));
        trucnhat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(trucnhat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        DangNhap a = new DangNhap();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTroVeActionPerformed

    private void luuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_luuyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Them_Sua_ChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Them_Sua_ChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Them_Sua_ChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Them_Sua_ChiTiet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame setChiTiet = new Them_Sua_ChiTiet();
                setChiTiet.setVisible(true);
                setChiTiet.getContentPane().setBackground(Color.white);
                setChiTiet.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LaBel_QLTN;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField luuy;
    private javax.swing.JTextField ngaytn;
    private javax.swing.JTable trucnhat;
    // End of variables declaration//GEN-END:variables

}
