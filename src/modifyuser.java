import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class modifyuser extends JInternalFrame implements ActionListener, ItemListener, FocusListener {
    JLabel title, uid, name, phone, email, type, designation, address, pin, city, state;
    JTextField iname, iphone, iemail, itype, idesignation, ipin, icity, istate;
    JButton modify;
    JComboBox iuid;
    JTextArea iaddress;
    Connection con;
    Statement stmt;
    ResultSet rs;

    modifyuser() {
        super("Modify User", true, true, false, true);
        setTitle("Modify User");
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

        title = new JLabel("Modify User");
        uid = new JLabel("User id");
        name = new JLabel("Name");
        phone = new JLabel("Phone no");
        email = new JLabel("Email id");
        type = new JLabel("Type");
        designation = new JLabel("Designation");
        address = new JLabel("Address");
        pin = new JLabel("Pincode");
        city = new JLabel("City");
        state = new JLabel("State");

        iname = new JTextField(30);
        iphone = new JTextField(30);
        iemail = new JTextField(30);
        itype = new JTextField(30);
        idesignation = new JTextField(30);
        iaddress = new JTextArea(2, 30);
        ipin = new JTextField(30);
        icity = new JTextField(30);
        istate = new JTextField(30);

        modify = new JButton("Modify");
        iuid = new JComboBox();

        add(title);
        add(uid);
        add(name);
        add(phone);
        add(email);
        add(type);
        add(designation);
        add(address);
        add(pin);
        add(city);
        add(state);

        add(iuid);
        add(iname);
        add(iphone);
        add(iemail);
        add(itype);
        add(idesignation);
        add(iaddress);
        add(ipin);
        add(icity);
        add(istate);
        add(modify);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        uid.setFont(new Font("Verdana", Font.PLAIN, 17));
        name.setFont(new Font("Verdana", Font.PLAIN, 17));
        phone.setFont(new Font("Verdana", Font.PLAIN, 17));
        email.setFont(new Font("Verdana", Font.PLAIN, 17));
        type.setFont(new Font("Verdana", Font.PLAIN, 17));
        designation.setFont(new Font("Verdana", Font.PLAIN, 17));
        address.setFont(new Font("Verdana", Font.PLAIN, 17));
        pin.setFont(new Font("Verdana", Font.PLAIN, 17));
        city.setFont(new Font("Verdana", Font.PLAIN, 17));
        state.setFont(new Font("Verdana", Font.PLAIN, 17));

        iuid.setFont(new Font("Verdana", Font.PLAIN, 15));
        iname.setFont(new Font("Verdana", Font.PLAIN, 15));
        iphone.setFont(new Font("Verdana", Font.PLAIN, 15));
        iemail.setFont(new Font("Verdana", Font.PLAIN, 15));
        itype.setFont(new Font("Verdana", Font.PLAIN, 15));
        idesignation.setFont(new Font("Verdana", Font.PLAIN, 15));
        iaddress.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipin.setFont(new Font("Verdana", Font.PLAIN, 15));
        icity.setFont(new Font("Verdana", Font.PLAIN, 15));
        istate.setFont(new Font("Verdana", Font.PLAIN, 15));
        modify.setFont(new Font("Verdana", Font.PLAIN, 17));

        // Placing elements
        // Placing elements
        title.setBounds(150, 20, 300, 40);

        uid.setBounds(60, 100, 140, 25);
        iuid.setBounds(240, 102, 180, 25);

        name.setBounds(60, 140, 140, 25);
        iname.setBounds(240, 142, 180, 25);

        phone.setBounds(60, 180, 140, 25);
        iphone.setBounds(240, 182, 180, 25);

        email.setBounds(60, 220, 140, 25);
        iemail.setBounds(240, 222, 180, 25);

        type.setBounds(60, 260, 140, 25);
        itype.setBounds(240, 262, 180, 25);

        designation.setBounds(60, 300, 140, 25);
        idesignation.setBounds(240, 302, 180, 25);

        address.setBounds(60, 340, 140, 25);
        iaddress.setBounds(240, 342, 180, 50);

        pin.setBounds(60, 410, 140, 25);
        ipin.setBounds(240, 412, 180, 25);

        city.setBounds(60, 450, 140, 25);
        icity.setBounds(240, 452, 180, 25);

        state.setBounds(60, 490, 140, 25);
        istate.setBounds(240, 492, 180, 25);

        modify.setBounds(160, 570, 120, 35);

        modify.addActionListener(this);
        iuid.addItemListener(this);
        ipin.addFocusListener(this);

        iname.setEditable(false);
        itype.setEditable(false);
        icity.setEditable(false);
        istate.setEditable(false);

        clear();

    }

    public void clear() {
        try {
            iuid.removeAllItems();
            iname.setText("");
            itype.setText("");
            iphone.setText("");
            iemail.setText("");
            idesignation.setText("");
            iaddress.setText("");
            ipin.setText("");
            icity.setText("");
            istate.setText("");
            String s = ("select id from users where id!='admin47'");
            rs = stmt.executeQuery(s);
            iuid.addItem("Select id");
            while (rs.next()) {
                iuid.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean chkdata() {
        if (iphone.getText().isEmpty() || iemail.getText().isEmpty() || idesignation.getText().isEmpty()
                || iaddress.getText().isEmpty() || icity.getText().isEmpty()) {
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
        if (e.getSource() == modify) {
            if (chkdata()) {
                try {
                    int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to modify this user's data?");
                    if (a == 0) {
                        String s = ("update users set phone='" + iphone.getText() + "', email='" + iemail.getText()
                                + "', designation='" + idesignation.getText() + "', address='" + iaddress.getText()
                                + "', pincode=" + ipin.getText() + ", city='" + icity.getText() + "', state='"
                                + istate.getText() + "' where id='" + iuid.getSelectedItem() + "'");
                        stmt.executeUpdate(s);
                        JOptionPane.showMessageDialog(this, "User data has been modified!");
                        clear();
                    }
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }

            }
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == iuid && iuid.getSelectedIndex() != 0 && iuid.getItemCount() != 0) {
            try {
                String s = ("select * from users where id='" + iuid.getSelectedItem() + "'");
                rs = stmt.executeQuery(s);
                rs.next();
                iname.setText(rs.getString(2));
                iphone.setText(rs.getString(5));
                iemail.setText(rs.getString(6));
                itype.setText(rs.getString(7));
                idesignation.setText(rs.getString(8));
                iaddress.setText(rs.getString(9));
                ipin.setText(rs.getString(10));
                icity.setText(rs.getString(11));
                istate.setText(rs.getString(12));

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