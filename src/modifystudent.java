import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

class modifystudent extends JInternalFrame implements ActionListener, ItemListener, FocusListener {
    JLabel title, course, sem, id, name, email, phone, address, pin, city, state;
    JTextField iname, iemail, iphone, ipin, icity, istate;
    JTextArea iaddress;
    JButton neww, modify;
    JComboBox icourse, isem, iid;
    Connection con;
    Statement stmt;
    ResultSet rs;

    modifystudent() {
        super("Modify student data", true, true, false, true);
        setTitle("Modify student data");
        setSize(500, 700);
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

        title = new JLabel("Student data");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        id = new JLabel("Student id");
        name = new JLabel("Name");
        email = new JLabel("Email id");
        phone = new JLabel("Phone no.");
        address = new JLabel("Address");
        pin = new JLabel("Pincode");
        city = new JLabel("City");
        state = new JLabel("State");

        icourse = new JComboBox();
        isem = new JComboBox();
        iid = new JComboBox();
        iname = new JTextField(30);
        iemail = new JTextField(30);
        iphone = new JTextField(30);
        iaddress = new JTextArea(2, 30);
        ipin = new JTextField(30);
        icity = new JTextField(30);
        istate = new JTextField(30);

        neww = new JButton("New");
        modify = new JButton("Modify");

        // Adding elements
        add(title);
        add(course);
        add(sem);
        add(id);
        add(name);
        add(email);
        add(phone);
        add(address);
        add(pin);
        add(city);
        add(state);

        add(icourse);
        add(isem);
        add(iid);
        add(iname);
        add(iemail);
        add(iphone);
        add(iaddress);
        add(ipin);
        add(icity);
        add(istate);
        add(neww);
        add(modify);

        // Setting font
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        name.setFont(new Font("Verdana", Font.PLAIN, 17));
        email.setFont(new Font("Verdana", Font.PLAIN, 17));
        phone.setFont(new Font("Verdana", Font.PLAIN, 17));
        address.setFont(new Font("Verdana", Font.PLAIN, 17));
        pin.setFont(new Font("Verdana", Font.PLAIN, 17));
        city.setFont(new Font("Verdana", Font.PLAIN, 17));
        state.setFont(new Font("Verdana", Font.PLAIN, 17));

        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));
        iid.setFont(new Font("Verdana", Font.PLAIN, 15));
        iname.setFont(new Font("Verdana", Font.PLAIN, 15));
        iemail.setFont(new Font("Verdana", Font.PLAIN, 15));
        iphone.setFont(new Font("Verdana", Font.PLAIN, 15));
        iaddress.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipin.setFont(new Font("Verdana", Font.PLAIN, 15));
        icity.setFont(new Font("Verdana", Font.PLAIN, 15));
        istate.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Placing elements
        title.setBounds(130, 20, 300, 40);

        course.setBounds(60, 100, 140, 25);
        icourse.setBounds(240, 102, 180, 25);

        sem.setBounds(60, 140, 140, 25);
        isem.setBounds(240, 142, 180, 25);

        id.setBounds(60, 180, 140, 25);
        iid.setBounds(240, 182, 180, 25);

        name.setBounds(60, 220, 140, 25);
        iname.setBounds(240, 222, 180, 25);

        email.setBounds(60, 260, 140, 25);
        iemail.setBounds(240, 262, 180, 25);

        phone.setBounds(60, 300, 140, 25);
        iphone.setBounds(240, 302, 180, 25);

        address.setBounds(60, 340, 140, 25);
        iaddress.setBounds(240, 342, 180, 50);

        pin.setBounds(60, 410, 140, 25);
        ipin.setBounds(240, 412, 180, 25);

        city.setBounds(60, 450, 140, 25);
        icity.setBounds(240, 452, 180, 25);

        state.setBounds(60, 490, 140, 25);
        istate.setBounds(240, 492, 180, 25);

        neww.setBounds(110, 550, 80, 35);
        modify.setBounds(290, 552, 80, 35);

        // Adding listeners
        neww.addActionListener(this);
        modify.addActionListener(this);

        icourse.addItemListener(this);
        isem.addItemListener(this);
        iid.addItemListener(this);
        ipin.addFocusListener(this);

        iname.setEditable(false);
        icity.setEditable(false);
        istate.setEditable(false);

        clear();

    }

    public void clear() {
        try {
            icourse.removeAllItems();
            isem.removeAllItems();
            iid.removeAllItems();
            iname.setText("");
            iemail.setText("");
            iphone.setText("");
            iaddress.setText("");
            ipin.setText("");
            icity.setText("");
            istate.setText("");
            iname.requestFocus();

            String s1 = ("select DISTINCT course from subjects");
            rs = stmt.executeQuery(s1);
            icourse.addItem("Select Course");
            while (rs.next()) {
                icourse.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean chkdata() {
        if (iemail.getText().isEmpty() || icourse.getSelectedIndex() == 0
                || isem.getSelectedIndex() == 0 | iphone.getText().isEmpty() || iaddress.getText().isEmpty()
                || icity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data is incomplete!");
            return false;
        }
        if (!validate.mail(iemail.getText())) {
            JOptionPane.showMessageDialog(this, "Email id is invalid!");
            return false;
        }
        if (!validate.numeric(iphone.getText()) || iphone.getText().length() != 10) {
            JOptionPane.showMessageDialog(this, "Phone no. is invalid!");
            return false;
        }
        return true;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == neww) {
            clear();
        } else if (e.getSource() == modify) {
            try {
                if (chkdata()) {
                    int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to modify this student's data?");
                    if (a == 0) {
                        String s = ("update student set phone='" + iphone.getText() + "', email='" + iemail.getText()
                                + "', address='" + iaddress.getText() + "', pincode=" + ipin.getText() + ", city='"
                                + icity.getText() + "', state='" + istate.getText() + "' where id="
                                + iid.getSelectedItem());
                        stmt.executeUpdate(s);
                        JOptionPane.showMessageDialog(this, "Student data has been modified!");
                        clear();
                    }
                }
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 && icourse.getItemCount() != 0) {
                String s = icourse.getSelectedItem().toString();
                isem.removeAllItems();
                String s1 = ("select DISTINCT semester from subjects where course='" + s + "' order by semester");
                rs = stmt.executeQuery(s1);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 && isem.getItemCount() != 0) {
                iid.removeAllItems();
                String s = ("select id from student");
                rs = stmt.executeQuery(s);
                iid.addItem("Select id");
                while (rs.next()) {
                    iid.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == iid && iid.getSelectedIndex() != 0 && iid.getItemCount() != 0) {
                String s = ("select * from student where id=" + iid.getSelectedItem());
                rs = stmt.executeQuery(s);
                rs.next();
                iname.setText(rs.getString(3));
                iemail.setText(rs.getString(4));
                iphone.setText(rs.getString(6));
                iaddress.setText(rs.getString(10));
                ipin.setText(rs.getString(11));
                icity.setText(rs.getString(12));
                istate.setText(rs.getString(13));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == ipin && !ipin.getText().isEmpty()) {
            String[] str = pincode.find(ipin.getText());
            if (str[0] == null) {
                JOptionPane.showMessageDialog(this, "The pincode is incorrect");
                modify.setEnabled(false);
            } else {
                icity.setText(str[0]);
                istate.setText(str[1]);
                modify.setEnabled(true);
                modify.requestFocus();
            }
        }
    }

    public void focusGained(FocusEvent fe) {
    }

    public static void main(String args[]) {

    }
}