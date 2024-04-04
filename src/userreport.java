import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class userreport extends JInternalFrame implements ItemListener {
    JLabel title;
    JRadioButton b1, b2, b3;
    ButtonGroup bg;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model;
    JTable table;
    Object rows[];
    String s;
    JScrollPane jsp;

    userreport() {
        super("User Report", true, true, false, true);
        setTitle("User Report");
        setSize(1500, 500);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

        // it = new JTable(rows, head);
        title = new JLabel("User Details");

        b1 = new JRadioButton("All");
        b2 = new JRadioButton("Teaching");
        b3 = new JRadioButton("Non-Teaching");
        bg = new ButtonGroup();
        model = new DefaultTableModel();
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);

        add(title);
        add(b1);
        add(b2);
        add(b3);
        add(jsp);

        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        b1.setFont(new Font("Verdana", Font.PLAIN, 17));
        b2.setFont(new Font("Verdana", Font.PLAIN, 17));
        b3.setFont(new Font("Verdana", Font.PLAIN, 17));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 12));

        title.setBounds(20, 20, 250, 40);

        b1.setBounds(60, 90, 60, 30);
        b2.setBounds(130, 90, 120, 30);
        b3.setBounds(260, 90, 150, 30);
        jsp.setBounds(20, 150, 1450, 280);
        b1.addItemListener(this);
        b2.addItemListener(this);
        b3.addItemListener(this);
        model.addColumn("Sr. no");
        model.addColumn("User id");
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Email");
        model.addColumn("Designation");
        model.addColumn("Address");
        model.addColumn("pincode");
        model.addColumn("City");
        model.addColumn("State");
        table.getColumnModel().getColumn(0).setPreferredWidth(4);
        table.getColumnModel().getColumn(3).setPreferredWidth(8);
        table.getColumnModel().getColumn(7).setPreferredWidth(8);

        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 10; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }
        b1.doClick();

    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == b1) {
                model.setRowCount(0);
                Object[] rowdata = new Object[10];
                s = ("select * from users");
                rs = stmt.executeQuery(s);
                while (rs.next()) {
                    rowdata[0] = rs.getObject("s_no");
                    rowdata[1] = rs.getObject("id");
                    rowdata[2] = rs.getObject("name");
                    rowdata[3] = rs.getObject("phone");
                    rowdata[4] = rs.getObject("email");
                    rowdata[5] = rs.getObject("designation");
                    rowdata[6] = rs.getObject("address");
                    rowdata[7] = rs.getObject("pincode");
                    rowdata[8] = rs.getObject("city");
                    rowdata[9] = rs.getObject("state");
                    model.addRow(rowdata);
                }

                model.fireTableDataChanged();

            } else if (ie.getSource() == b2) {
                model.setRowCount(0);
                Object[] rowdata = new Object[10];
                s = ("select * from users where type='Teaching'");
                rs = stmt.executeQuery(s);
                while (rs.next()) {
                    rowdata[0] = rs.getObject("s_no");
                    rowdata[1] = rs.getObject("id");
                    rowdata[2] = rs.getObject("name");
                    rowdata[3] = rs.getObject("phone");
                    rowdata[4] = rs.getObject("email");
                    rowdata[5] = rs.getObject("designation");
                    rowdata[6] = rs.getObject("address");
                    rowdata[7] = rs.getObject("pincode");
                    rowdata[8] = rs.getObject("city");
                    rowdata[9] = rs.getObject("state");
                    model.addRow(rowdata);
                }
                model.fireTableDataChanged();

            } else if (ie.getSource() == b3) {
                model.setRowCount(0);
                Object[] rowdata = new Object[10];
                s = ("select * from users where type='Non-Teaching'");
                rs = stmt.executeQuery(s);
                while (rs.next()) {
                    rowdata[0] = rs.getObject("s_no");
                    rowdata[1] = rs.getObject("id");
                    rowdata[2] = rs.getObject("name");
                    rowdata[3] = rs.getObject("phone");
                    rowdata[4] = rs.getObject("email");
                    rowdata[5] = rs.getObject("designation");
                    rowdata[6] = rs.getObject("address");
                    rowdata[7] = rs.getObject("pincode");
                    rowdata[8] = rs.getObject("city");
                    rowdata[9] = rs.getObject("state");
                    model.addRow(rowdata);
                }
                model.fireTableDataChanged();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String args[]) {

    }
}