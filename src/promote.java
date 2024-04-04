import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class promote extends JInternalFrame implements ActionListener, ItemListener {
    JLabel title, course, sem;
    JButton promote;
    JComboBox icourse, isem;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model;
    JTable table;
    String s, date;
    JScrollPane jsp;
    Object[] rowdata;
    ArrayList ids;
    Date d1;

    promote() {
        super("Promote Student", true, true, false, true);
        setTitle("Promote Student");
        setSize(750, 600);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        title = new JLabel("Promote to new semester");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        icourse = new JComboBox();
        isem = new JComboBox();
        promote = new JButton("Promote");
        model = new DefaultTableModel();
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rowdata = new Object[13];
        ids = new ArrayList<>();
        d1 = new Date();

        add(title);
        add(course);
        add(sem);
        add(icourse);
        add(isem);
        add(promote);

        add(jsp);

        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));
        promote.setFont(new Font("Verdana", Font.PLAIN, 17));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 12));

        // Positioning the components
        title.setBounds(20, 20, 500, 40);

        course.setBounds(40, 90, 120, 30);
        icourse.setBounds(150, 92, 170, 30);
        sem.setBounds(370, 90, 160, 30);
        isem.setBounds(490, 92, 170, 30);
        jsp.setBounds(30, 150, 680, 310);
        promote.setBounds(this.getWidth() / 2 - 100, 490, 150, 40);

        model.addColumn("Sr. no");
        model.addColumn("Student id");
        model.addColumn("Name");
        model.addColumn("Course");

        table.getColumnModel().getColumn(0).setPreferredWidth(4);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 4; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }

        icourse.addItemListener(this);
        isem.addItemListener(this);
        promote.addActionListener(this);
        promote.setEnabled(false);

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
        if (d1.getMonth() >= 9)
            date = ((d1.getYear() + 1900) + "-" + (d1.getMonth() + 1) + "-" + d1.getDate());
        else
            date = ((d1.getYear() + 1900) + "-0" + (d1.getMonth() + 1) + "-" + d1.getDate());

    }

    public void insert() {
        try {
            ids.clear();
            model.setRowCount(0);
            int i = 1;
            while (rs.next()) {
                ids.add(rs.getString(1));
                rowdata[0] = i;
                rowdata[1] = rs.getString(1);
                rowdata[2] = rs.getString(3);
                rowdata[3] = rs.getString(7);
                model.addRow(rowdata);
                i = i + 1;
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void actionPerformed(ActionEvent e) {
        try {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to promote all these students?");
            if (a == 0) {
                // Creating fees records and updating semester
                int semm = Integer.parseInt(isem.getSelectedItem().toString()) + 1;
                s = ("select * from subjects where course='" + icourse.getSelectedItem() + "' and semester="
                        + semm);
                rs = stmt.executeQuery(s);
                if (rs.next()) {
                    for (int i = 0; i < ids.size(); i++) {
                        s = ("update student set semester=" + semm + " where id=" + ids.get(i));
                        stmt.executeUpdate(s);
                    }

                    s = ("select * from fees where course='" + icourse.getSelectedItem() + "' and semester="
                            + semm);
                    rs = stmt.executeQuery(s);
                    rs.next();
                    int fee = Integer.parseInt(rs.getString(4));

                    for (int i = 0; i < ids.size(); i++) {
                        s = ("insert into feerecord values(0,'" + date + "'," + ids.get(i) + ",'"
                                + icourse.getSelectedItem() + "'," + semm + ",0," +
                                fee + ")");
                        stmt.executeUpdate(s);
                    }
                    JOptionPane.showMessageDialog(this, "Students have been promoted!");

                } else {
                    JOptionPane.showMessageDialog(this, "These students have graduated :)");
                }

            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                isem.removeAllItems();
                model.setRowCount(0);
                model.fireTableDataChanged();
                promote.setEnabled(false);
                s = ("select DISTINCT semester from subjects where course='" + icourse.getSelectedItem()
                        + "' order by semester");
                rs = stmt.executeQuery(s);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 && isem.getItemCount() != 0) {
                s = ("select * from student where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                insert();
                promote.setEnabled(true);
                if (model.getRowCount() == 0) {
                    promote.setEnabled(false);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String args[]) {

    }
}