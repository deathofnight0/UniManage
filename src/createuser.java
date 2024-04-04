import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class createuser extends JInternalFrame implements ActionListener, FocusListener {
    JLabel title, fname, lname, uid, pass, phone, email, type, designation, address, pin, city, state;
    JTextField ifname, ilname, iuid, ipass, iphone, iemail, idesignation, ipin, icity, istate;
    JTextArea iaddress;
    JButton neww, submit;
    JRadioButton b1, b2;
    ButtonGroup itype;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int count = 0;

    createuser() {
        super("Create new user", true, true, false, true);
        setTitle("Create new user");
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
        title = new JLabel("User Details");
        fname = new JLabel("First name");
        lname = new JLabel("Last name");
        uid = new JLabel("User id");
        pass = new JLabel("Password");
        phone = new JLabel("Phone no");
        email = new JLabel("Email id");
        type = new JLabel("Type");
        designation = new JLabel("Designation");
        address = new JLabel("Address");
        pin = new JLabel("Pincode");
        city = new JLabel("City");
        state = new JLabel("State");

        ifname = new JTextField(30);
        ilname = new JTextField(30);
        iuid = new JTextField(30);
        ipass = new JTextField(30);
        iphone = new JTextField(30);
        iemail = new JTextField(30);
        idesignation = new JTextField(30);
        iaddress = new JTextArea(2, 30);
        ipin = new JTextField(30);
        icity = new JTextField(30);
        istate = new JTextField(30);

        neww = new JButton("New");
        submit = new JButton("Submit");
        b1 = new JRadioButton("Teaching");
        b1.setActionCommand("Teaching");
        b2 = new JRadioButton("Non-Teaching");
        b2.setActionCommand("Non-Teaching");
        itype = new ButtonGroup();
        itype.add(b1);
        itype.add(b2);

        // Adding elements
        add(title);
        add(fname);
        add(lname);
        add(uid);
        add(pass);
        add(phone);
        add(email);
        add(type);
        add(designation);
        add(address);
        add(pin);
        add(city);
        add(state);

        add(ifname);
        add(ilname);
        add(iuid);
        add(ipass);
        add(iphone);
        add(iemail);
        add(b1);
        add(b2);
        add(idesignation);
        add(iaddress);
        add(ipin);
        add(icity);
        add(istate);
        add(neww);
        add(submit);

        // Setting font
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        fname.setFont(new Font("Verdana", Font.PLAIN, 17));
        lname.setFont(new Font("Verdana", Font.PLAIN, 17));
        uid.setFont(new Font("Verdana", Font.PLAIN, 17));
        pass.setFont(new Font("Verdana", Font.PLAIN, 17));
        phone.setFont(new Font("Verdana", Font.PLAIN, 17));
        email.setFont(new Font("Verdana", Font.PLAIN, 17));
        type.setFont(new Font("Verdana", Font.PLAIN, 17));
        designation.setFont(new Font("Verdana", Font.PLAIN, 17));
        address.setFont(new Font("Verdana", Font.PLAIN, 17));
        pin.setFont(new Font("Verdana", Font.PLAIN, 17));
        city.setFont(new Font("Verdana", Font.PLAIN, 17));
        state.setFont(new Font("Verdana", Font.PLAIN, 17));

        ifname.setFont(new Font("Verdana", Font.PLAIN, 15));
        ilname.setFont(new Font("Verdana", Font.PLAIN, 15));
        iuid.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipass.setFont(new Font("Verdana", Font.PLAIN, 15));
        iphone.setFont(new Font("Verdana", Font.PLAIN, 15));
        iemail.setFont(new Font("Verdana", Font.PLAIN, 15));
        b1.setFont(new Font("Verdana", Font.PLAIN, 15));
        b2.setFont(new Font("Verdana", Font.PLAIN, 15));
        idesignation.setFont(new Font("Verdana", Font.PLAIN, 15));
        iaddress.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipin.setFont(new Font("Verdana", Font.PLAIN, 15));
        icity.setFont(new Font("Verdana", Font.PLAIN, 15));
        istate.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Placing elements
        title.setBounds(150, 20, 300, 30);

        fname.setBounds(60, 100, 140, 25);
        ifname.setBounds(240, 102, 180, 25);

        lname.setBounds(60, 140, 140, 25);
        ilname.setBounds(240, 142, 180, 25);

        uid.setBounds(60, 180, 140, 25);
        iuid.setBounds(240, 182, 180, 25);

        pass.setBounds(60, 220, 140, 25);
        ipass.setBounds(240, 222, 180, 25);

        phone.setBounds(60, 260, 140, 25);
        iphone.setBounds(240, 262, 180, 25);

        email.setBounds(60, 300, 140, 25);
        iemail.setBounds(240, 302, 180, 25);

        type.setBounds(60, 340, 140, 25);
        b1.setBounds(240, 342, 120, 25);
        b2.setBounds(240, 380, 140, 25);

        designation.setBounds(60, 420, 140, 25);
        idesignation.setBounds(240, 422, 180, 25);

        address.setBounds(60, 460, 140, 25);
        iaddress.setBounds(240, 462, 180, 50);

        pin.setBounds(60, 530, 140, 25);
        ipin.setBounds(240, 532, 180, 25);

        city.setBounds(60, 570, 140, 25);
        icity.setBounds(240, 572, 180, 25);

        state.setBounds(60, 610, 140, 25);
        istate.setBounds(240, 612, 180, 25);

        neww.setBounds(110, 670, 80, 35);
        submit.setBounds(290, 672, 80, 35);

        // Adding listeners
        neww.addActionListener(this);
        submit.addActionListener(this);
        ipin.addFocusListener(this);

        ipass.setEditable(false);
        icity.setEditable(false);
        istate.setEditable(false);
        submit.setEnabled(false);

        clear();

    }

    public void clear() {
        try {
            ifname.setText("");
            ilname.setText("");
            iuid.setText("");
            ipass.setText("Tagore@1960");
            iphone.setText("");
            iemail.setText("");
            itype.clearSelection();
            idesignation.setText("");
            iaddress.setText("");
            ipin.setText("");
            icity.setText("");
            istate.setText("");
            ifname.requestFocus();
            String s1 = ("select * from users order by s_no desc limit 1");
            rs = stmt.executeQuery(s1);
            if (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            count = count + 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean chkdata() {
        if (ifname.getText().isEmpty() || ilname.getText().isEmpty() || iuid.getText().isEmpty()
                || iphone.getText().isEmpty() || iemail.getText().isEmpty() || itype.getSelection() == null
                || idesignation.getText().isEmpty() || iaddress.getText().isEmpty() || icity.getText().isEmpty()) {
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
        return true;

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == neww) {
            clear();
        } else if (e.getSource() == submit) {
            try {
                if (chkdata()) {
                    String s = ("insert into users values(" + count + ",'" + ifname.getText().trim() + " "
                            + ilname.getText().trim()
                            + "','" + iuid.getText() + "','" + ipass.getText() + "','" + iphone.getText() + "','"
                            + iemail.getText() + "','" + itype.getSelection().getActionCommand() + "','"
                            + idesignation.getText() + "','" + iaddress.getText() + "'," + ipin.getText() + ",'"
                            + icity.getText() + "','" + istate.getText() + "')");
                    stmt.executeUpdate(s);
                    JOptionPane.showMessageDialog(this, "User has been registered!");
                    clear();
                }
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == ipin && !ipin.getText().isEmpty()) {
            String[] str = pincode.find(ipin.getText());
            if (str[0] == null) {
                JOptionPane.showMessageDialog(this, "The pincode is incorrect");
            } else {
                icity.setText(str[0]);
                istate.setText(str[1]);
                submit.setEnabled(true);
                submit.requestFocus();
            }
        }
    }

    public void focusGained(FocusEvent fe) {

    }

    public static void main(String args[]) {
    }
}