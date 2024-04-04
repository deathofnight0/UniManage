import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class studentreport extends JInternalFrame implements ActionListener, FocusListener, ItemListener {
    JLabel title, from, to, course, sem;
    JTextField ifrom, ito;
    JButton go;
    JRadioButton b1, b2;
    JComboBox icourse, isem;
    ButtonGroup bg;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model;
    JTable table;
    String s;
    JScrollPane jsp;
    Object[] rowdata;

    studentreport() {
        super("Student Report", true, true, false, true);
        setTitle("Admission Report");
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
        title = new JLabel("Student Details");
        from = new JLabel("From Date");
        to = new JLabel("To Date");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        ifrom = new JTextField(20);
        ito = new JTextField(20);
        icourse = new JComboBox();
        isem = new JComboBox();
        go = new JButton("Go");
        model = new DefaultTableModel();
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        b1 = new JRadioButton("");
        b2 = new JRadioButton("");
        bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        rowdata = new Object[13];

        add(title);
        add(from);
        add(to);
        add(course);
        add(sem);
        add(ifrom);
        add(ito);
        add(icourse);
        add(isem);
        add(go);
        add(b1);
        add(b2);

        add(jsp);

        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        from.setFont(new Font("Verdana", Font.PLAIN, 17));
        to.setFont(new Font("Verdana", Font.PLAIN, 17));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        ifrom.setFont(new Font("Verdana", Font.PLAIN, 17));
        ito.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 17));
        isem.setFont(new Font("Verdana", Font.PLAIN, 17));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 12));

        title.setBounds(20, 20, 500, 40);

        b1.setBounds(50, 90, 20, 30);
        from.setBounds(90, 90, 120, 30);
        ifrom.setBounds(200, 92, 160, 30);
        to.setBounds(420, 90, 120, 30);
        ito.setBounds(540, 92, 170, 30);
        go.setBounds(760, 92, 120, 30);

        b2.setBounds(50, 150, 20, 30);
        course.setBounds(90, 150, 120, 30);
        icourse.setBounds(200, 152, 160, 30);
        sem.setBounds(420, 150, 120, 30);
        isem.setBounds(540, 152, 170, 30);

        jsp.setBounds(20, 220, 1450, 310);

        go.addActionListener(this);

        model.addColumn("Id");
        model.addColumn("Admit Date");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Gender");
        model.addColumn("Phone no.");
        model.addColumn("Date of Birth");
        model.addColumn("Course");
        model.addColumn("Semester");
        model.addColumn("Address");
        model.addColumn("pincode");
        model.addColumn("City");
        model.addColumn("State");
        table.getColumnModel().getColumn(0).setPreferredWidth(4);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 13; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }

        ifrom.addFocusListener(this);
        ito.addFocusListener(this);
        icourse.addItemListener(this);
        isem.addItemListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        ifrom.setText("YYYY-MM-DD");
        ito.setText("YYYY-MM-DD");
        ifrom.setForeground(Color.GRAY);
        ito.setForeground(Color.GRAY);

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

        b1.doClick();

    }

    public void insert() {
        try {
            model.setRowCount(0);
            while (rs.next()) {
                for (int i = 0; i < 13; i++) {
                    rowdata[i] = rs.getObject(i + 1);
                }
                model.addRow(rowdata);
            }
            model.fireTableDataChanged();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == go) {
                if (!validate.date(ifrom.getText()) || !validate.date(ito.getText())) {
                    JOptionPane.showMessageDialog(this, "Date entered is invalid");
                } else {
                    s = ("select * from student where admission_date between '" + ifrom.getText() + "' and '"
                            + ito.getText() + "'");
                    rs = stmt.executeQuery(s);
                    insert();
                }
            } else if (e.getSource() == b1) {
                model.setRowCount(0);
                ifrom.setEnabled(true);
                ito.setEnabled(true);
                go.setEnabled(true);
                icourse.setEnabled(false);
                isem.setEnabled(false);
                icourse.setSelectedIndex(0);
                isem.removeAllItems();
            } else if (e.getSource() == b2) {
                model.setRowCount(0);
                ifrom.setEnabled(false);
                ito.setEnabled(false);
                go.setEnabled(false);
                icourse.setEnabled(true);
                isem.setEnabled(true);
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                isem.removeAllItems();
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
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void focusLost(FocusEvent fe) {

        if (fe.getSource() == ifrom) {
            if (ifrom.getText().isEmpty()) {
                ifrom.setForeground(Color.GRAY);
                ifrom.setText("YYYY-MM-DD");
            }
        } else if (fe.getSource() == ito) {
            if (ito.getText().isEmpty()) {
                ito.setForeground(Color.GRAY);
                ito.setText("YYYY-MM-DD");
            }
        }

    }

    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == ifrom) {
            if (ifrom.getText().equals("YYYY-MM-DD")) {
                ifrom.setText("");
                ifrom.setForeground(Color.BLACK);
            }
        } else if (fe.getSource() == ito) {
            if (ito.getText().equals("YYYY-MM-DD")) {
                ito.setText("");
                ito.setForeground(Color.BLACK);
            }
        }

    }

    public static void main(String args[]) {

    }
}