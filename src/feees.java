import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class feees extends JInternalFrame implements ActionListener, ItemListener {
    JLabel title, course, sem, fee;
    JLabel[] sub;
    JTextField ifee;
    JTextField[] isub;
    JButton neww, save, modify;
    JComboBox icourse, isem;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int count;

    feees() {
        super("Fees", true, true, false, true);
        setTitle("Fees");
        setSize(500, 550);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

        sub = new JLabel[5];
        isub = new JTextField[5];

        title = new JLabel("Fee Details");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        fee = new JLabel("Fees");

        ifee = new JTextField(30);

        neww = new JButton("New");
        save = new JButton("Save");
        modify = new JButton("Modify");

        icourse = new JComboBox();
        isem = new JComboBox();

        add(title);
        add(course);
        add(sem);
        add(fee);
        add(ifee);
        add(neww);
        add(save);
        add(modify);
        add(icourse);
        add(isem);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        fee.setFont(new Font("Verdana", Font.PLAIN, 17));
        ifee.setFont(new Font("Verdana", Font.PLAIN, 15));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));

        title.setBounds(150, 20, 200, 30);

        course.setBounds(60, 100, 100, 20);
        icourse.setBounds(190, 102, 175, 25);

        sem.setBounds(60, 140, 100, 20);
        isem.setBounds(190, 142, 175, 25);

        int y = 140;
        for (int i = 0; i < 5; i++) {
            y = y + 40;
            sub[i] = new JLabel("Subject " + (i + 1));
            isub[i] = new JTextField(30);
            add(sub[i]);
            add(isub[i]);
            sub[i].setFont(new Font("Verdana", Font.PLAIN, 17));
            isub[i].setFont(new Font("Verdana", Font.PLAIN, 15));
            sub[i].setBounds(60, y, 100, 25);
            isub[i].setBounds(190, y, 230, 25);
            isub[i].setEditable(false);
        }

        fee.setBounds(60, 380, 100, 25);
        ifee.setBounds(190, 382, 230, 25);

        neww.setBounds(100, 445, 75, 35);
        save.setBounds(200, 445, 75, 35);
        modify.setBounds(300, 445, 75, 35);

        try {
            String s1 = ("select DISTINCT course from subjects");
            rs = stmt.executeQuery(s1);
            icourse.addItem("Select Course");
            while (rs.next()) {
                icourse.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        neww.addActionListener(this);
        save.addActionListener(this);
        modify.addActionListener(this);
        modify.setEnabled(false);

        icourse.addItemListener(this);
        isem.addItemListener(this);

    }

    public void clear() {

        for (int i = 0; i < 5; i++) {
            isub[i].setText("");
        }
        try {
            String s1 = ("select * from fees order by sr_no desc");
            rs = stmt.executeQuery(s1);
            if (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            count = count + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == neww) {
                icourse.setSelectedIndex(0);
                isem.setSelectedIndex(0);
                ifee.setText("");
                save.setEnabled(true);
                clear();
            } else if (e.getSource() == save) {
                if (icourse.getSelectedIndex() == 0 || isem.getSelectedIndex() == 0
                        || ifee.getText().isEmpty() || isub[0].getText().isEmpty()) {
                    System.out.println("Incomplete information");
                } else {
                    if (validate.numeric(ifee.getText())) {

                        String s1 = ("insert into fees values(" + count + ",'" + icourse.getSelectedItem()
                                + "'," + isem.getSelectedItem() + "," + ifee.getText() + ")");
                        stmt.executeUpdate(s1);

                        JOptionPane.showMessageDialog(this, "Data inserted!");
                        icourse.setSelectedIndex(0);
                        isem.setSelectedIndex(0);
                        ifee.setText("");
                        clear();
                    } else {
                        JOptionPane.showMessageDialog(this, "Fees entered is not numeric!");
                    }
                }
            } else if (e.getSource() == modify) {
                if (validate.numeric(ifee.getText())) {
                    String s1 = ("update fees set fees=" + ifee.getText() + " where course='"
                            + icourse.getSelectedItem()
                            + "' and semester=" + isem.getSelectedItem());
                    stmt.executeUpdate(s1);
                    JOptionPane.showMessageDialog(this, "Fees has been updated!");
                }

            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                clear();
                String s = icourse.getSelectedItem().toString();
                isem.removeAllItems();
                String s1 = ("select DISTINCT semester from subjects where course='" + s + "'" + " order by semester");
                rs = stmt.executeQuery(s1);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 &&
                    isem.getItemCount() != 0) {
                clear();
                String s = ("select * from fees where course='" + icourse.getSelectedItem()
                        + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                if (rs.next()) {
                    for (int i = 0; i < 5; i++) {
                        isub[i].setText("");
                    }
                    modify.setEnabled(true);
                    save.setEnabled(false);
                    ifee.setText(rs.getString(4));
                } else {
                    save.setEnabled(true);
                    modify.setEnabled(false);
                }
                s = ("select sub_name from subjects where course='" + icourse.getSelectedItem()
                        + "' and semester="
                        + isem.getSelectedItem());

                rs = stmt.executeQuery(s);
                int i = 0;
                while (rs.next()) {
                    isub[i].setText(rs.getString(1));
                    i = i + 1;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String args[]) {

    }
}