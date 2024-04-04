import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class result extends JInternalFrame implements ActionListener, ItemListener {
    JLabel title, course, id, sem, name;
    JLabel[] sub;
    JTextField[] isub;
    JTextField iname;
    JButton upload, neww;
    JComboBox icourse, iid, isem;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int count;

    result() {
        super("Student Result", true, true, false, true);
        setTitle("Student Result");
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

        title = new JLabel("Upload Result");
        course = new JLabel("Course");
        id = new JLabel("Student id");
        sem = new JLabel("Semester");
        name = new JLabel("Name");

        sub = new JLabel[5];
        isub = new JTextField[5];

        neww = new JButton("New");
        upload = new JButton("Upload");

        icourse = new JComboBox();
        isem = new JComboBox();
        iid = new JComboBox();
        iname = new JTextField(20);

        add(title);
        add(course);
        add(id);
        add(sem);
        add(name);

        add(iid);
        add(icourse);
        add(isem);
        add(iname);

        add(neww);
        add(upload);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        name.setFont(new Font("Verdana", Font.PLAIN, 17));
        iid.setFont(new Font("Verdana", Font.PLAIN, 17));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 17));
        isem.setFont(new Font("Verdana", Font.PLAIN, 17));
        iname.setFont(new Font("Verdana", Font.PLAIN, 17));

        title.setBounds(180, 20, 250, 40);

        course.setBounds(50, 100, 100, 25);
        icourse.setBounds(220, 102, 180, 25);

        sem.setBounds(50, 140, 100, 25);
        isem.setBounds(220, 142, 180, 25);

        id.setBounds(50, 180, 100, 25);
        iid.setBounds(220, 182, 180, 25);

        name.setBounds(50, 220, 100, 25);
        iname.setBounds(220, 222, 180, 25);
        int y = 220;
        for (int i = 0; i < 5; i++) {
            y = y + 40;
            sub[i] = new JLabel("Subject " + (i + 1));
            isub[i] = new JTextField(30);
            add(sub[i]);
            add(isub[i]);
            sub[i].setFont(new Font("Verdana", Font.PLAIN, 17));
            isub[i].setFont(new Font("Verdana", Font.PLAIN, 15));
            sub[i].setBounds(50, y, 100, 25);
            isub[i].setBounds(220, y, 180, 25);
        }

        neww.setBounds(130, 490, 85, 35);
        upload.setBounds(280, 490, 85, 35);

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
        iname.setEditable(false);
        neww.addActionListener(this);
        upload.addActionListener(this);

        iid.addItemListener(this);
        icourse.addItemListener(this);
        isem.addItemListener(this);
        clear();

    }

    public void clear() {

        try {
            icourse.setSelectedIndex(0);
            // isem.setSelectedIndex(0);
            for (int i = 0; i < 5; i++) {
                isub[i].setText("");
            }
            String s1 = ("select * from result order by sr_no desc");
            rs = stmt.executeQuery(s1);
            if (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            count = count + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int y = 220;
        for (int i = 0; i < 5; i++) {
            y = y + 40;
            sub[i].setText("Subject " + (i + 1));
            isub[i].setBounds(220, y, 180, 25);
        }
        upload.setEnabled(true);
        isem.removeAllItems();
        iid.removeAllItems();
        iname.setText("");

    }

    public boolean chkdata() {
        for (int i = 0; i < 5; i++) {
            if (validate.numeric(isub[i].getText())) {
                int s = Integer.parseInt(isub[i].getText());
                if (s > 100 || s < 0) {
                    JOptionPane.showMessageDialog(this, "Marks entered are invalid!");
                    return false;
                }

            } else {
                JOptionPane.showMessageDialog(this, "Marks entered are not numeric!");
                return false;
            }

        }
        if (icourse.getSelectedIndex() == 0 || isem.getSelectedIndex() == 0 || iid.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Incomplete data");
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == neww) {
            int y = 220;
            for (int i = 0; i < 5; i++) {
                y = y + 40;
                sub[i].setBounds(50, y, 100, 25);
                isub[i].setBounds(220, y, 180, 25);
            }
            clear();
        } else if (e.getSource() == upload) {
            if (chkdata()) {
                String s = ("insert into result values(" + count + "," + iid.getSelectedItem() + ",'" + iname.getText()
                        + "','"
                        + icourse.getSelectedItem() + "'," + isem.getSelectedItem() + "," + isub[0].getText() + ","
                        + isub[1].getText() + "," + isub[2].getText() + "," + isub[3].getText() + ","
                        + isub[4].getText() + ")");
                try {
                    stmt.executeUpdate(s);
                    JOptionPane.showMessageDialog(this, "Result has been uploaded!");
                    clear();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }

        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                String s = icourse.getSelectedItem().toString();
                isem.removeAllItems();
                String s1 = ("select DISTINCT semester from subjects where course='" + s + "'");
                rs = stmt.executeQuery(s1);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 &&
                    isem.getItemCount() != 0) {
                String s = ("select sub_name from subjects where course='" + icourse.getSelectedItem()
                        + "' and semester="
                        + isem.getSelectedItem());

                rs = stmt.executeQuery(s);
                int y = 220;
                for (int i = 0; i < 5; i++) {
                    y = y + 40;
                    sub[i].setBounds(50, y, 280, 25);
                    isub[i].setBounds(330, y, 180, 25);
                }
                int i = 0;
                while (rs.next()) {
                    sub[i].setText(rs.getString(1));
                    i = i + 1;
                }
                iid.removeAllItems();
                s = ("select id from student where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                iid.addItem("Select id");
                while (rs.next()) {
                    iid.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == iid && iid.getSelectedIndex() != 0 && iid.getItemCount() != 0) {
                String s = ("select name from student where id=" + iid.getSelectedItem());
                rs = stmt.executeQuery(s);
                rs.next();
                iname.setText(rs.getString(1));

                s = ("select * from result where student_id=" + iid.getSelectedItem());
                rs = stmt.executeQuery(s);
                if (rs.next()) {
                    for (int i = 0; i < 5; i++) {
                        isub[i].setText(rs.getString(5 + i));
                    }
                    upload.setEnabled(false);
                } else {
                    upload.setEnabled(true);
                    for (int i = 0; i < 5; i++) {
                        isub[i].setText("");
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String args[]) {

    }
}