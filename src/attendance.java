import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

class attendance extends JInternalFrame implements ActionListener, ItemListener {
    JLabel title, date, course, sem, sub;
    JTextField idate;
    JButton record;
    JComboBox icourse, isem, isub;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel model;
    JTable table;
    String s, datee;
    JScrollPane jsp;
    Object[] rowdata;
    ArrayList id, name;
    Date d1;
    int count;

    attendance() {
        super("Record Attendance", true, true, false, true);
        setTitle("Record Attendance");
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

        // Initializing elements
        title = new JLabel("Record Attendance");
        date = new JLabel("Date");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        sub = new JLabel("Subject");
        idate = new JTextField(20);
        icourse = new JComboBox();
        isem = new JComboBox();
        isub = new JComboBox();
        record = new JButton("Record");
        model = new DefaultTableModel();
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rowdata = new Object[4];
        d1 = new Date();
        id = new ArrayList<>();
        name = new ArrayList<>();

        // Adding elements
        add(title);
        add(date);
        add(course);
        add(sem);
        add(sub);
        add(idate);
        add(icourse);
        add(isem);
        add(isub);
        add(record);
        add(jsp);

        // Setting font
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        date.setFont(new Font("Verdana", Font.PLAIN, 17));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        sub.setFont(new Font("Verdana", Font.PLAIN, 17));
        idate.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 17));
        isem.setFont(new Font("Verdana", Font.PLAIN, 17));
        isub.setFont(new Font("Verdana", Font.PLAIN, 17));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Placing elements
        title.setBounds(230, 20, 500, 40);

        date.setBounds(40, 90, 120, 30);
        idate.setBounds(160, 90, 170, 30);
        course.setBounds(370, 90, 120, 30);
        icourse.setBounds(470, 92, 220, 30);

        sem.setBounds(40, 150, 120, 30);
        isem.setBounds(160, 152, 170, 30);
        sub.setBounds(370, 150, 120, 30);
        isub.setBounds(470, 152, 220, 30);

        jsp.setBounds(30, 220, 670, 310);
        record.setBounds(this.getWidth() / 2 - 75, 552, 100, 40);

        // Table settings
        model.addColumn("Sr. no.");
        model.addColumn("Student id");
        model.addColumn("Name");
        model.addColumn("Mark");
        table.getColumnModel().getColumn(0).setPreferredWidth(4);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 4; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }
        table.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new CheckBoxEditor());

        // Adding Listeners
        record.addActionListener(this);
        icourse.addItemListener(this);
        isem.addItemListener(this);
        isub.addItemListener(this);

        // Inserting data in combobox
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
            datee = ((d1.getYear() + 1900) + "-" + (d1.getMonth() + 1) + "-" + d1.getDate());
        else
            datee = ((d1.getYear() + 1900) + "-0" + (d1.getMonth() + 1) + "-" + d1.getDate());
        idate.setText(datee);
        idate.setEnabled(false);
        table.setRowHeight(30);
        record.setEnabled(false);

    }

    public void insert() {
        try {
            model.setRowCount(0);
            int j = 1;
            id.clear();
            name.clear();
            while (rs.next()) {
                id.add(rs.getString(1));
                name.add(rs.getString(3));
                rowdata[0] = j;
                rowdata[1] = rs.getString(1);
                rowdata[2] = rs.getString(3);
                rowdata[3] = true;
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
            int a = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to record attendance of these students?");
            if (a == 0) {
                for (int i = 0; i < count; i++) {
                    if ((boolean) model.getValueAt(i, 3) == true) {
                        s = ("insert into attendance values(" + id.get(i) + ",'" + name.get(i) + "','"
                                + icourse.getSelectedItem()
                                + "'," + isem.getSelectedItem() + ",'" + isub.getSelectedItem() + "','"
                                + idate.getText() + "','present')");
                    } else if ((boolean) model.getValueAt(i, 3) == false) {
                        s = ("insert into attendance values(" + id.get(i) + ",'" + name.get(i) + "','"
                                + icourse.getSelectedItem()
                                + "'," + isem.getSelectedItem() + ",'" + isub.getSelectedItem() + "','"
                                + idate.getText() + "','absent')");
                    }
                    stmt.executeUpdate(s);
                }
                JOptionPane.showMessageDialog(this, "Attendance has been marked!");
                isem.removeAllItems();
                isub.removeAllItems();
                model.setRowCount(0);
                record.setEnabled(false);
                model.fireTableDataChanged();
                icourse.setSelectedIndex(0);
            }

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                isem.removeAllItems();
                isub.removeAllItems();
                model.setRowCount(0);
                record.setEnabled(false);
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
                model.setRowCount(0);
                model.fireTableDataChanged();
                record.setEnabled(false);
                s = ("select sub_name from subjects where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                isub.addItem("Select Subject");
                while (rs.next()) {
                    isub.addItem(rs.getString(1));
                }
                s = ("select * from student where course='" + icourse.getSelectedItem() + "'and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                count = 0;
                while (rs.next()) {
                    count = count + 1;
                }
            } else if (ie.getSource() == isub && isub.getSelectedIndex() != 0 && isub.getItemCount() != 0) {
                s = ("select * from attendance where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem() + " and subject='" + isub.getSelectedItem() + "' and date='" + datee
                        + "'");
                rs = stmt.executeQuery(s);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Attendance for this subject has already been marked!");
                    isub.setSelectedIndex(0);
                } else {
                    s = ("select * from student where course='" + icourse.getSelectedItem() + "'and semester="
                            + isem.getSelectedItem());
                    rs = stmt.executeQuery(s);
                    record.setEnabled(true);
                    insert();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckBoxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setSelected((Boolean) value);
            return this;
        }
    }

    // Custom cell editor for handling JCheckBox editing
    static class CheckBoxEditor extends DefaultCellEditor {
        private JCheckBox checkBox;

        public CheckBoxEditor() {
            super(new JCheckBox());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            checkBox = (JCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            return checkBox;
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }
    }

    public static void main(String args[]) {

    }
}