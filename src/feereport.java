import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class feereport extends JInternalFrame implements ActionListener, FocusListener, ItemListener {
    JLabel title, from, to, course, sem;
    JTextField ifrom, ito;
    JButton go;
    JCheckBox pending, paid;
    JRadioButton b1, b2, b3;
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

    feereport() {
        super("Fees Report", true, true, false, true);
        setTitle("Fees Report");
        setSize(1200, 700);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        title = new JLabel("Fees Report");
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
        b1 = new JRadioButton("   All");
        b2 = new JRadioButton("");
        b3 = new JRadioButton("");
        bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        pending = new JCheckBox("  Pending");
        paid = new JCheckBox("  Paid");
        rowdata = new Object[9];

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
        add(b3);
        add(pending);
        add(paid);

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
        b1.setFont(new Font("Verdana", Font.PLAIN, 17));
        pending.setFont(new Font("Verdana", Font.PLAIN, 17));
        paid.setFont(new Font("Verdana", Font.PLAIN, 17));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 12));

        title.setBounds(20, 20, 500, 40);

        pending.setBounds(50, 90, 150, 40);
        paid.setBounds(250, 90, 150, 40);
        b1.setBounds(50, 140, 150, 40);

        b2.setBounds(50, 200, 20, 30);
        from.setBounds(90, 200, 120, 30);
        ifrom.setBounds(200, 202, 160, 30);
        to.setBounds(420, 200, 120, 30);
        ito.setBounds(540, 202, 170, 30);
        go.setBounds(760, 202, 120, 30);

        b3.setBounds(50, 260, 20, 30);
        course.setBounds(90, 260, 120, 30);
        icourse.setBounds(200, 262, 160, 30);
        sem.setBounds(420, 260, 120, 30);
        isem.setBounds(540, 262, 170, 30);

        jsp.setBounds(20, 330, 1150, 310);

        go.addActionListener(this);

        model.addColumn("Sr. no.");
        model.addColumn("Receipt no.");
        model.addColumn("Date");
        model.addColumn("Student id");
        model.addColumn("Course");
        model.addColumn("Semester");
        model.addColumn("Paid");
        model.addColumn("Balance");
        model.addColumn("Status");

        table.getColumnModel().getColumn(0).setPreferredWidth(4);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 9; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }

        ifrom.addFocusListener(this);
        ito.addFocusListener(this);
        icourse.addItemListener(this);
        isem.addItemListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        paid.addActionListener(this);
        pending.addActionListener(this);
        ifrom.setText("YYYY-MM-DD");
        ito.setText("YYYY-MM-DD");
        ifrom.setForeground(Color.GRAY);
        ito.setForeground(Color.GRAY);
        icourse.setEnabled(false);
        isem.setEnabled(false);

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
            int j = 1;
            while (rs.next()) {
                rowdata[0] = j;
                for (int i = 0; i < 7; i++) {
                    rowdata[i + 1] = rs.getObject(i + 1);
                }
                if (Integer.parseInt(rs.getString(7)) == 0) {
                    rowdata[8] = "Paid";
                } else {
                    rowdata[8] = "Pending";
                }
                model.addRow(rowdata);
                j = j + 1;
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
                    if (pending.isSelected()) {
                        s = ("select * from feerecord where balance!=0 and date between '" + ifrom.getText()
                                + "' and '" + ito.getText() + "'");
                    } else if (paid.isSelected()) {
                        s = ("select * from feerecord where balance=0 and date between '" + ifrom.getText()
                                + "' and '"
                                + ito.getText() + "'");
                    } else {
                        s = ("select * from feerecord where date between '" + ifrom.getText() + "' and '"
                                + ito.getText() + "'");
                    }
                    rs = stmt.executeQuery(s);
                    insert();
                }
            } else if (e.getSource() == b1) {
                model.setRowCount(0);
                ifrom.setEnabled(false);
                ito.setEnabled(false);
                go.setEnabled(false);
                icourse.setEnabled(false);
                isem.setEnabled(false);
                icourse.setSelectedIndex(0);
                if (pending.isSelected()) {
                    s = ("select * from feerecord where balance!=0");
                } else if (paid.isSelected()) {
                    s = ("select * from feerecord where balance=0");
                } else {
                    s = ("select * from feerecord");
                }
                rs = stmt.executeQuery(s);
                insert();
                isem.removeAllItems();
            } else if (e.getSource() == b2) {
                model.setRowCount(0);
                ifrom.setEnabled(true);
                ito.setEnabled(true);
                go.setEnabled(true);
                icourse.setEnabled(false);
                isem.setEnabled(false);
                icourse.setSelectedIndex(0);
                isem.removeAllItems();
            } else if (e.getSource() == b3) {
                model.setRowCount(0);
                ifrom.setEnabled(false);
                ito.setEnabled(false);
                go.setEnabled(false);
                icourse.setEnabled(true);
                isem.setEnabled(true);
            } else if (e.getSource() == pending) {
                if (pending.isSelected()) {
                    paid.setEnabled(false);
                } else {
                    paid.setEnabled(true);
                }
                bg.clearSelection();
                model.setRowCount(0);
                model.fireTableDataChanged();
            } else if (e.getSource() == paid) {
                if (paid.isSelected()) {
                    pending.setEnabled(false);
                } else {
                    pending.setEnabled(true);
                }
                bg.clearSelection();
                model.setRowCount(0);
                model.fireTableDataChanged();
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
                if (pending.isSelected()) {
                    s = ("select * from feerecord where course='" + icourse.getSelectedItem() + "' and semester="
                            + isem.getSelectedItem() + " and balance!=0");
                } else if (paid.isSelected()) {
                    s = ("select * from feerecord where course='" + icourse.getSelectedItem() + "' and semester="
                            + isem.getSelectedItem() + " and balance=0");
                } else {
                    s = ("select * from feerecord where course='" + icourse.getSelectedItem() + "' and semester="
                            + isem.getSelectedItem());
                }
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