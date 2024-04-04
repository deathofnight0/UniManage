import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class course extends JInternalFrame implements ActionListener, ItemListener {
    JLabel title, cat, course, sem;
    JLabel[] sub;
    JTextField[] isub;
    JButton neww, save, modify;
    JRadioButton b1, b2;
    ButtonGroup bg;
    JComboBox icourse, isem;
    Connection con;
    Statement stmt;
    ResultSet rs;
    String ug[] = { "Select course", "BCA", "BTECH" };
    String pg[] = { "Select course", "MCA", "MTECH" };
    String s4[] = { "Select Semester", "1", "2", "3", "4" };
    String s6[] = { "Select Semester", "1", "2", "3", "4", "5", "6" };
    String s8[] = { "Select Semester", "1", "2", "3", "4", "5", "6", "7", "8" };

    course() {
        super("Course Detail", true, true, false, true);
        setTitle("Courses");
        setSize(600, 600);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

        title = new JLabel("Course details");
        cat = new JLabel("Category");
        course = new JLabel("Course");
        sem = new JLabel("Semester");

        b1 = new JRadioButton("Undergrad");
        b1.setActionCommand("Undergrad");
        b2 = new JRadioButton("Postgrad");
        b2.setActionCommand("Postgrad");
        bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        icourse = new JComboBox<>();
        isem = new JComboBox<>();
        sub = new JLabel[5];
        isub = new JTextField[5];

        neww = new JButton("New");
        save = new JButton("Save");
        modify = new JButton("Modify");

        add(title);
        add(cat);
        add(course);
        add(sem);
        add(b1);
        add(b2);
        add(icourse);
        add(isem);
        add(neww);
        add(save);
        add(modify);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        cat.setFont(new Font("Verdana", Font.PLAIN, 17));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        b1.setFont(new Font("Verdana", Font.PLAIN, 17));
        b2.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));

        title.setBounds(185, 20, 250, 30);

        cat.setBounds(90, 100, 100, 25);
        b1.setBounds(280, 102, 120, 25);
        b2.setBounds(400, 102, 180, 25);

        course.setBounds(90, 140, 100, 25);
        icourse.setBounds(290, 142, 180, 25);

        sem.setBounds(90, 180, 100, 25);
        isem.setBounds(290, 182, 180, 25);
        neww.setBounds(90, 450, 80, 40);
        save.setBounds(240, 450, 80, 40);
        modify.setBounds(390, 450, 80, 40);

        int y = 180;
        for (int i = 0; i < 5; i++) {
            y = y + 40;
            sub[i] = new JLabel("Subject " + (i + 1));
            isub[i] = new JTextField(30);
            add(sub[i]);
            add(isub[i]);
            sub[i].setFont(new Font("Verdana", Font.PLAIN, 16));
            isub[i].setFont(new Font("Verdana", Font.PLAIN, 15));
            sub[i].setBounds(90, y, 200, 25);
            isub[i].setBounds(290, y, 180, 25);
        }
        neww.addActionListener(this);
        save.addActionListener(this);
        modify.addActionListener(this);
        b1.addItemListener(this);
        b2.addItemListener(this);

        icourse.addItemListener(this);
        isem.addItemListener(this);

        // setVisible(true);

    }

    public void clear() {

        for (int i = 0; i < 5; i++) {
            isub[i].setText("");
        }
        modify.setEnabled(false);

    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == neww) {
                bg.clearSelection();
                save.setEnabled(true);
                icourse.setSelectedIndex(0);
                isem.setSelectedIndex(0);
                clear();
            } else if (e.getSource() == save) {
                if (icourse.getSelectedIndex() == 0 || isem.getSelectedIndex() == 0
                        || isub[0].getText().isEmpty() || isub[1].getText().isEmpty() || isub[2].getText().isEmpty()
                        || isub[3].getText().isEmpty() || isub[4].getText().isEmpty() || bg.isSelected(null)) {
                    System.out.println("Incomplete information");
                } else {
                    for (int i = 0; i < 5; i++) {
                        String code = (icourse.getSelectedItem().toString()
                                + isem.getSelectedIndex() + 0 + (i + 1));
                        String s1 = ("insert into subjects(category,course,semester,sub_code,sub_name) values('"
                                + bg.getSelection().getActionCommand() + "','" + icourse.getSelectedItem() + "',"
                                + isem.getSelectedItem() + ",'" + code + "','" + isub[i].getText() + "')");
                        stmt.executeUpdate(s1);
                    }
                    JOptionPane.showMessageDialog(this, "Data inserted!");
                }
            } else if (e.getSource() == modify) {
                for (int i = 0; i < 5; i++) {
                    String code = (icourse.getSelectedItem().toString()
                            + isem.getSelectedIndex() + 0 + (i + 1));
                    String s1 = ("update subjects set sub_name='" + isub[i].getText() + "' where sub_code='" + code
                            + "'");
                    stmt.executeUpdate(s1);
                }
                JOptionPane.showMessageDialog(this, "The subjects were updated!");

            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void itemStateChanged(ItemEvent ie) {

        if (ie.getSource() == b1 && b1.isSelected()) {
            icourse.removeAllItems();
            isem.removeAllItems();
            for (int i = 0; i < 3; i++) {
                icourse.addItem(ug[i]);
            }
        } else if (ie.getSource() == b2 && b2.isSelected()) {
            icourse.removeAllItems();
            isem.removeAllItems();
            for (int i = 0; i < 3; i++) {
                icourse.addItem(pg[i]);
            }
        } else if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
            String s = icourse.getSelectedItem().toString();
            isem.removeAllItems();
            if (s.equals("MTECH") || s.equals("MCA")) {
                for (int i = 0; i < 5; i++) {
                    isem.addItem(s4[i]);
                }
            } else if (s.equals("BTECH")) {
                for (int i = 0; i < 9; i++) {
                    isem.addItem(s8[i]);
                }
            } else if (s.equals("BCA")) {
                for (int i = 0; i < 7; i++) {
                    isem.addItem(s6[i]);
                }
            }
        } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 &&
                isem.getItemCount() != 0) {
            clear();
            String s = ("select * from subjects where course='" + icourse.getSelectedItem() + "' and semester="
                    + isem.getSelectedItem());
            try {
                rs = stmt.executeQuery(s);
                if (rs.next()) {
                    for (int i = 0; i < 5; i++) {
                        isub[i].setText(rs.getString(6));
                        rs.next();
                    }
                    save.setEnabled(false);
                    modify.setEnabled(true);
                } else {
                    save.setEnabled(true);
                    modify.setEnabled(false);
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

            for (int i = 0; i < 5; i++) {
                sub[i].setText("Subject " + (i + 1) + " (" +
                        icourse.getSelectedItem().toString()
                        + isem.getSelectedIndex() + 0 + (i + 1) + ")");
            }

        }
    }

    public static void main(String args[]) {
    }

}
