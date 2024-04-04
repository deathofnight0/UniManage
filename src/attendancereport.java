import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

class attendancereport extends JInternalFrame implements ItemListener {
    JLabel title, course, sem, id, sub, percentage;
    JTextField ipercentage;
    JComboBox icourse, isem, iid, isub;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model;
    JTable table;
    String s;
    JScrollPane jsp;
    Object[] rowdata;
    float all, a, p;

    attendancereport() {
        super("Attendance Report", true, true, false, true);
        setTitle("Attendance Report");
        setSize(750, 650);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        title = new JLabel("Attendance Report");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        id = new JLabel("Student id");
        sub = new JLabel("Subject");
        percentage = new JLabel("Attendance percentage");
        icourse = new JComboBox();
        isem = new JComboBox();
        iid = new JComboBox();
        isub = new JComboBox();
        ipercentage = new JTextField();
        model = new DefaultTableModel();
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rowdata = new Object[5];

        add(title);
        add(course);
        add(sem);
        add(id);
        add(sub);
        add(icourse);
        add(isem);
        add(iid);
        add(isub);
        add(jsp);
        add(percentage);
        add(ipercentage);

        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        sub.setFont(new Font("Verdana", Font.PLAIN, 17));
        percentage.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 17));
        iid.setFont(new Font("Verdana", Font.PLAIN, 15));
        isub.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipercentage.setFont(new Font("Verdana", Font.PLAIN, 15));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 15));

        title.setBounds(20, 20, 500, 40);

        course.setBounds(40, 90, 120, 30);
        icourse.setBounds(160, 90, 170, 30);
        sem.setBounds(370, 90, 120, 30);
        isem.setBounds(470, 92, 220, 30);

        id.setBounds(40, 150, 120, 30);
        iid.setBounds(160, 152, 170, 30);
        sub.setBounds(370, 150, 120, 30);
        isub.setBounds(470, 152, 220, 30);

        jsp.setBounds(30, 220, 670, 310);
        percentage.setBounds(160, 552, 250, 40);
        ipercentage.setBounds(380, 552, 140, 40);

        model.addColumn("Sr. no.");
        model.addColumn("Student id");
        model.addColumn("Name");
        model.addColumn("Date");
        model.addColumn("Status");
        table.getColumnModel().getColumn(0).setPreferredWidth(4);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 4; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }
        table.setRowHeight(30);

        icourse.addItemListener(this);
        isem.addItemListener(this);
        iid.addItemListener(this);
        isub.addItemListener(this);

        try {
            s = ("select DISTINCT course from subjects");
            rs = stmt.executeQuery(s);
            icourse.addItem("Select Course");
            while (rs.next()) {
                icourse.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setRowHeight(30);
        ipercentage.setEnabled(false);

    }

    public void insert() {
        try {
            all = 0;
            model.setRowCount(0);
            int j = 1;
            while (rs.next()) {
                all = all + 1;
                rowdata[0] = j;
                rowdata[1] = rs.getString(1);
                rowdata[2] = rs.getString(2);
                rowdata[3] = rs.getString(6);
                rowdata[4] = rs.getString(7);
                model.addRow(rowdata);
                j = j + 1;
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                isem.removeAllItems();
                iid.removeAllItems();
                isub.removeAllItems();
                model.setRowCount(0);
                model.fireTableDataChanged();
                s = ("select DISTINCT semester from subjects where course='" + icourse.getSelectedItem()
                        + "' order by semester");
                rs = stmt.executeQuery(s);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 && isem.getItemCount() != 0) {
                isub.removeAllItems();
                iid.removeAllItems();
                model.setRowCount(0);
                model.fireTableDataChanged();
                s = ("select id from student where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                iid.addItem("Select id");
                while (rs.next()) {
                    iid.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == iid && iid.getSelectedIndex() != 0 && iid.getItemCount() != 0) {
                isub.removeAllItems();
                s = ("select sub_name from subjects where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                isub.addItem("Select Subject");
                while (rs.next()) {
                    isub.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isub && isub.getSelectedIndex() != 0 && isub.getItemCount() != 0) {
                s = ("select * from attendance where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem() + " and subject='" + isub.getSelectedItem() + "' and id="
                        + iid.getSelectedItem());
                rs = stmt.executeQuery(s);
                insert();
                s = ("select * from attendance where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem() + " and subject='" + isub.getSelectedItem() + "' and id="
                        + iid.getSelectedItem()) + " and status='absent'";
                rs = stmt.executeQuery(s);
                a = 0;
                while (rs.next()) {
                    a = a + 1;
                }
                p = all - a;
                ipercentage.setText(String.valueOf(p / all * 100));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String args[]) {

    }
}