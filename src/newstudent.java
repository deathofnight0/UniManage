import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class newstudent extends JInternalFrame implements ActionListener, ItemListener, FocusListener {
    JLabel title, admno, admdt, fname, lname, email, gender, phone, dob, course, sem, address, pin, city, state;
    JTextField iadmno, iadmdt, ifname, ilname, iemail, iphone, idob, ipin, icity, istate;
    JTextArea iaddress;
    JButton neww, submit;
    JRadioButton b1, b2;
    ButtonGroup bg;
    JComboBox<String> icourse, isem;
    Connection con;
    Statement stmt;
    ResultSet rs;
    Date d1;
    int count = 0;
    String date;
    validate valid;

    newstudent() {
        super("Student details", true, true, false, true);
        setTitle("Student details");
        setSize(500, 800);
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
        d1 = new Date();
        valid = new validate();

        title = new JLabel("Student Admission");
        admno = new JLabel("Admission no");
        admdt = new JLabel("Admission date");
        fname = new JLabel("First Name");
        lname = new JLabel("Last Name");
        email = new JLabel("Email id");
        gender = new JLabel("Gender");
        phone = new JLabel("Phone no.");
        dob = new JLabel("Date of Birth");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        address = new JLabel("Address");
        pin = new JLabel("Pincode");
        city = new JLabel("City");
        state = new JLabel("State");

        iadmno = new JTextField(30);
        iadmdt = new JTextField(30);
        ifname = new JTextField(30);
        ilname = new JTextField(30);
        iemail = new JTextField(30);
        iphone = new JTextField(30);
        idob = new JTextField(20);
        ipin = new JTextField(30);
        icity = new JTextField(30);
        istate = new JTextField(30);
        iaddress = new JTextArea(2, 30);

        neww = new JButton("New");
        submit = new JButton("Submit");
        b1 = new JRadioButton("Male");
        b1.setActionCommand("Male");
        b2 = new JRadioButton("Female");
        b2.setActionCommand("Female");
        bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);

        icourse = new JComboBox<>();
        isem = new JComboBox<>();

        // Adding elements
        add(title);
        add(admno);
        add(admdt);
        add(fname);
        add(lname);
        add(email);
        add(gender);
        add(phone);
        add(dob);
        add(course);
        add(sem);
        add(address);
        add(pin);
        add(city);
        add(state);

        add(iadmno);
        add(iadmdt);
        add(ifname);
        add(ilname);
        add(iemail);
        add(b1);
        add(b2);
        add(iphone);
        add(idob);
        add(icourse);
        add(isem);
        add(iaddress);
        add(ipin);
        add(icity);
        add(istate);
        add(neww);
        add(submit);

        // Setting font
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        admno.setFont(new Font("Verdana", Font.PLAIN, 17));
        admdt.setFont(new Font("Verdana", Font.PLAIN, 17));
        fname.setFont(new Font("Verdana", Font.PLAIN, 17));
        lname.setFont(new Font("Verdana", Font.PLAIN, 17));
        email.setFont(new Font("Verdana", Font.PLAIN, 17));
        gender.setFont(new Font("Verdana", Font.PLAIN, 17));
        phone.setFont(new Font("Verdana", Font.PLAIN, 17));
        dob.setFont(new Font("Verdana", Font.PLAIN, 17));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        address.setFont(new Font("Verdana", Font.PLAIN, 17));
        pin.setFont(new Font("Verdana", Font.PLAIN, 17));
        city.setFont(new Font("Verdana", Font.PLAIN, 17));
        state.setFont(new Font("Verdana", Font.PLAIN, 17));

        iadmno.setFont(new Font("Verdana", Font.PLAIN, 15));
        iadmdt.setFont(new Font("Verdana", Font.PLAIN, 15));
        ifname.setFont(new Font("Verdana", Font.PLAIN, 15));
        ilname.setFont(new Font("Verdana", Font.PLAIN, 15));
        iemail.setFont(new Font("Verdana", Font.PLAIN, 15));
        b1.setFont(new Font("Verdana", Font.PLAIN, 15));
        b2.setFont(new Font("Verdana", Font.PLAIN, 15));
        iphone.setFont(new Font("Verdana", Font.PLAIN, 15));
        idob.setFont(new Font("Verdana", Font.PLAIN, 15));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));
        iaddress.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipin.setFont(new Font("Verdana", Font.PLAIN, 15));
        icity.setFont(new Font("Verdana", Font.PLAIN, 15));
        istate.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Placing elements
        title.setBounds(110, 20, 300, 30);

        admno.setBounds(60, 100, 140, 25);
        iadmno.setBounds(240, 102, 180, 25);

        admdt.setBounds(60, 140, 140, 25);
        iadmdt.setBounds(240, 142, 180, 25);

        fname.setBounds(60, 180, 140, 25);
        ifname.setBounds(240, 182, 180, 25);

        lname.setBounds(60, 220, 140, 25);
        ilname.setBounds(240, 222, 180, 25);

        email.setBounds(60, 260, 140, 25);
        iemail.setBounds(240, 262, 180, 25);

        gender.setBounds(60, 300, 140, 25);
        b1.setBounds(240, 302, 80, 25);
        b2.setBounds(320, 302, 80, 25);

        phone.setBounds(60, 340, 140, 25);
        iphone.setBounds(240, 342, 180, 25);

        dob.setBounds(60, 380, 140, 25);
        idob.setBounds(240, 382, 180, 25);

        course.setBounds(60, 420, 140, 25);
        icourse.setBounds(240, 422, 180, 25);

        sem.setBounds(60, 460, 140, 25);
        isem.setBounds(240, 462, 180, 25);

        address.setBounds(60, 500, 140, 25);
        iaddress.setBounds(240, 502, 180, 50);

        pin.setBounds(60, 560, 140, 25);
        ipin.setBounds(240, 562, 180, 25);

        city.setBounds(60, 600, 140, 25);
        icity.setBounds(240, 602, 180, 25);

        state.setBounds(60, 640, 140, 25);
        istate.setBounds(240, 642, 180, 25);

        neww.setBounds(110, 700, 80, 35);
        submit.setBounds(290, 702, 80, 35);

        // Adding listeners
        neww.addActionListener(this);
        submit.addActionListener(this);
        b1.addItemListener(this);
        b2.addItemListener(this);
        icourse.addItemListener(this);
        isem.addItemListener(this);
        ipin.addFocusListener(this);
        idob.addFocusListener(this);

        iadmno.setEditable(false);
        iadmdt.setEditable(false);
        icity.setEditable(false);
        istate.setEditable(false);
        submit.setEnabled(false);

        clear();

    }

    public void clear() {
        try {
            iadmno.setText("");
            iadmdt.setText("");
            ifname.setText("");
            ilname.setText("");
            iemail.setText("");
            bg.clearSelection();
            iphone.setText("");
            idob.setText("YYYY-MM-DD");
            icourse.removeAllItems();
            isem.removeAllItems();
            iaddress.setText("");
            ipin.setText("");
            icity.setText("");
            istate.setText("");
            ifname.requestFocus();
            idob.setForeground(Color.GRAY);

            String s1 = ("select DISTINCT course from subjects");
            rs = stmt.executeQuery(s1);
            icourse.addItem("Select Course");
            while (rs.next()) {
                icourse.addItem(rs.getString(1));
            }
            s1 = ("select * from student order by id desc limit 1");
            rs = stmt.executeQuery(s1);
            if (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            iadmno.setText(String.valueOf(count + 1));

            if (d1.getMonth() >= 9)
                date = ((d1.getYear() + 1900) + "-" + (d1.getMonth() + 1) + "-" + d1.getDate());
            else
                date = ((d1.getYear() + 1900) + "-0" + (d1.getMonth() + 1) + "-" + d1.getDate());
            iadmdt.setText(date);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean chkdata() {
        if (ifname.getText().isEmpty() || ilname.getText().isEmpty() || iemail.getText().isEmpty()
                || bg.getSelection() == null || icourse.getSelectedIndex() == 0 || isem.getSelectedIndex() == 0
                || iphone.getText().isEmpty() || idob.getText().isEmpty() || iaddress.getText().isEmpty()
                || icity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data is incomplete!");
            return false;
        }
        if (!validate.alpha(ifname.getText()) || !validate.alpha(ilname.getText())) {
            JOptionPane.showMessageDialog(this, "Name contains illegal characters!");
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
        if (!validate.date(idob.getText())) {
            JOptionPane.showMessageDialog(this, "Date is incorrect!");
            return false;
        }
        return true;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == neww) {
            clear();
        } else if (e.getSource() == submit) {
            try {
                if (chkdata()) {
                    String s = ("insert into student values(" + iadmno.getText() + ",'" + iadmdt.getText() + "','"
                            + ifname.getText() + " " + ilname.getText() + "','" + iemail.getText() + "','"
                            + bg.getSelection().getActionCommand().toString() + "','" + iphone.getText() + "','"
                            + idob.getText() + "','" + icourse.getSelectedItem() + "'," + isem.getSelectedItem() + ",'"
                            + iaddress.getText() + "'," + ipin.getText() + ",'" + icity.getText() + "','"
                            + istate.getText() + "')");
                    stmt.executeUpdate(s);
                    JOptionPane.showMessageDialog(this, "Student has been registered!");

                    s = ("select * from fees where course='" + icourse.getSelectedItem() + "' and semester="
                            + isem.getSelectedItem());
                    rs = stmt.executeQuery(s);
                    rs.next();
                    int fee = Integer.parseInt(rs.getString(4));

                    s = ("insert into feerecord values(0,'" + iadmdt.getText() + "'," + iadmno.getText() + ",'"
                            + icourse.getSelectedItem() + "'," +
                            isem.getSelectedItem()
                            + ",0," + fee + ")");
                    stmt.executeUpdate(s);
                    clear();
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
                String s1 = ("select DISTINCT semester from subjects where course='" + s + "'");
                rs = stmt.executeQuery(s1);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == ipin) {
            String[] str = pincode.find(ipin.getText());
            if (str[0] == null) {
                JOptionPane.showMessageDialog(this, "The pincode is incorrect");
            } else {
                icity.setText(str[0]);
                istate.setText(str[1]);
                submit.setEnabled(true);
                submit.requestFocus();
            }
        } else if (fe.getSource() == idob) {
            if (idob.getText().isEmpty()) {
                idob.setForeground(Color.GRAY);
                idob.setText("YYYY-MM-DD");
            }
        }
    }

    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == idob) {
            if (idob.getText().equals("YYYY-MM-DD")) {
                idob.setText("");
                idob.setForeground(Color.BLACK);
            }
        }

    }

    public static void main(String args[]) {
    }
}