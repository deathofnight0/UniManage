import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class resultreport extends JInternalFrame implements ItemListener, ActionListener {
    JLabel title, course, sem, more, less;
    JComboBox icourse, isem;
    JCheckBox cb;
    JTextField imore, iless;
    JButton clear;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model;
    JTable table;
    String s;
    JScrollPane jsp;
    Object[] rowdata;

    resultreport() {
        super("Student Result", true, true, false, true);
        setTitle("Student Result");
        setSize(1500, 600);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

        title = new JLabel("Result Report");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        more = new JLabel("More than");
        less = new JLabel("Less than");
        icourse = new JComboBox();
        isem = new JComboBox();
        imore = new JTextField(2);
        iless = new JTextField(2);
        clear = new JButton("Clear");
        cb = new JCheckBox("Percentage condition?");
        model = new DefaultTableModel();
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rowdata = new Object[11];

        add(title);
        add(course);
        add(sem);
        add(more);
        add(less);
        add(icourse);
        add(isem);
        add(imore);
        add(iless);
        add(cb);
        add(clear);
        add(jsp);

        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        more.setFont(new Font("Verdana", Font.PLAIN, 17));
        less.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));
        imore.setFont(new Font("Verdana", Font.PLAIN, 15));
        iless.setFont(new Font("Verdana", Font.PLAIN, 15));
        cb.setFont(new Font("Verdana", Font.PLAIN, 17));
        clear.setFont(new Font("Verdana", Font.PLAIN, 17));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 12));

        title.setBounds(20, 20, 500, 40);

        cb.setBounds(50, 90, 220, 30);
        more.setBounds(380, 90, 120, 30);
        imore.setBounds(500, 92, 170, 30);
        less.setBounds(720, 90, 120, 30);
        iless.setBounds(840, 92, 160, 30);
        // 220,120

        course.setBounds(50, 150, 120, 30);
        icourse.setBounds(160, 152, 160, 30);
        sem.setBounds(380, 150, 120, 30);
        isem.setBounds(500, 152, 170, 30);
        clear.setBounds(720, 152, 100, 30);

        jsp.setBounds(20, 220, 1450, 310);

        icourse.addItemListener(this);
        isem.addItemListener(this);
        cb.addItemListener(this);
        clear.addActionListener(this);

        model.addColumn("Sr. no");
        model.addColumn("Student id");
        model.addColumn("Name");
        model.addColumn("Course");
        model.addColumn("Semester");
        model.addColumn("Subject 1");
        model.addColumn("Subject 2");
        model.addColumn("Subject 3");
        model.addColumn("Subject 4");
        model.addColumn("Subject 5");
        model.addColumn("Percentage");

        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 11; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }

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

        imore.setEnabled(false);
        iless.setEnabled(false);
        imore.setBackground(Color.LIGHT_GRAY);
        iless.setBackground(Color.LIGHT_GRAY);

    }

    public void insert() {
        try {
            model.setRowCount(0);
            while (rs.next()) {
                for (int i = 0; i < 10; i++) {
                    rowdata[i] = rs.getObject(i + 1);
                }
                float percentage = (Float.parseFloat(rs.getString(6)) + Float.parseFloat(rs.getString(7))
                        + Float.parseFloat(rs.getString(8)) + Float.parseFloat(rs.getString(9))
                        + Float.parseFloat(rs.getString(10))) / 5;
                rowdata[10] = percentage;
                model.addRow(rowdata);
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void actionPerformed(ActionEvent e) {
        isem.removeAllItems();
        icourse.removeAllItems();
        try {
            s = ("select DISTINCT course from subjects");
            rs = stmt.executeQuery(s);
            icourse.addItem("Select Course");
            while (rs.next()) {
                icourse.addItem(rs.getString(1));
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }

        cb.setSelected(false);
        model.setRowCount(0);
        model.fireTableDataChanged();
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == cb) {
            if (cb.isSelected()) {
                imore.setEnabled(true);
                iless.setEnabled(true);
                imore.setBackground(Color.WHITE);
                iless.setBackground(Color.WHITE);
            } else {
                imore.setEnabled(false);
                iless.setEnabled(false);
                imore.setText("");
                iless.setText("");
                imore.setBackground(Color.LIGHT_GRAY);
                iless.setBackground(Color.LIGHT_GRAY);
            }
            model.setRowCount(0);
            model.fireTableDataChanged();
            icourse.setSelectedIndex(0);
            isem.removeAllItems();
        } else if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
            try {
                isem.removeAllItems();
                s = ("select DISTINCT semester from subjects where course='" + icourse.getSelectedItem()
                        + "' order by semester");
                rs = stmt.executeQuery(s);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 && isem.getItemCount() != 0) {
            try {
                if (!cb.isSelected()) {
                    s = ("select * from result where course='" + icourse.getSelectedItem() + "' and semester="
                            + isem.getSelectedItem());
                    rs = stmt.executeQuery(s);
                    insert();

                } else {
                    if (imore.getText().isEmpty())
                        imore.setText("0");
                    if (iless.getText().isEmpty())
                        iless.setText("100");
                    if (validate.numeric(imore.getText()) && validate.numeric(
                            iless.getText()) && imore.getText().length() <= 2 && iless.getText().length() <= 2
                            || iless.getText().equals("100")) {
                        s = ("select * from result where course='" + icourse.getSelectedItem() + "' and semester="
                                + isem.getSelectedItem() + " and (sub1+sub2+sub3+sub4+sub5)/5>" + imore.getText()
                                + " and (sub1+sub2+sub3+sub4+sub5)/5<" + iless.getText());
                        rs = stmt.executeQuery(s);
                        insert();
                    } else {
                        JOptionPane.showMessageDialog(this, "Percentage constraint is invalid!");
                        isem.setSelectedIndex(0);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String args[]) {
    }
}