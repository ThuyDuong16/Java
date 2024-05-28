/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class MultiLineTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

       
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        if (value instanceof List<?>) {
            List<?> listValue = (List<?>) value;
            for (Object item : listValue) {
                textArea.append(item.toString() + "\n");
            }
        } else {
            textArea.append(value.toString());
        }
           if (isSelected) {
            textArea.setBackground(table.getSelectionBackground());
            textArea.setForeground(table.getSelectionForeground());
        } else {
            textArea.setBackground(table.getBackground());
            textArea.setForeground(table.getForeground());
        }
 if (column == 2) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Đặt đường viền cho cột "Lưu ý"
            panel.setBackground(Color.PINK);
             panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        } else {
            setBorder(null); // Không có đường viền cho cột khác
        }
                

        // Thiết lập đường viền đậm cho JPanel
       

        panel.add(textArea, BorderLayout.CENTER);

        return panel;
    }
}
