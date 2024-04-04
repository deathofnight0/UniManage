import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class changepass extends JInternalFrame implements ActionListener {
    JLabel title, id, iid, old, pass, confirm;
    JPasswordField iold, ipass, iconfirm;
    JButton update;
    Connection con;
    Statement stmt;
    ResultSet rs;

    changepass(String uid) {
        super("Change password", true, true, false, true);
        setTitle("Password");
        setSize(500, 500);
        setLayout(null);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }

        title = new JLabel("Change Password");
        id = new JLabel("User id");
        old = new JLabel("Old Password");
        pass = new JLabel("New Password");
        confirm = new JLabel("Confirm Password");
        iid = new JLabel(uid);

        iold = new JPasswordField(30);
        ipass = new JPasswordField(30);
        iconfirm = new JPasswordField(30);

        update = new JButton("Update");

        add(title);
        add(id);
        add(old);
        add(pass);
        add(confirm);
        add(iid);
        add(iold);
        add(ipass);
        add(iconfirm);
        add(update);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        old.setFont(new Font("Verdana", Font.PLAIN, 17));
        pass.setFont(new Font("Verdana", Font.PLAIN, 17));
        confirm.setFont(new Font("Verdana", Font.PLAIN, 17));
        iid.setFont(new Font("Verdana", Font.PLAIN, 15));
        iold.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipass.setFont(new Font("Verdana", Font.PLAIN, 15));
        iconfirm.setFont(new Font("Verdana", Font.PLAIN, 15));

        title.setBounds(110, 40, 300, 40);

        id.setBounds(60, 140, 170, 25);
        iid.setBounds(240, 142, 180, 25);

        old.setBounds(60, 190, 170, 25);
        iold.setBounds(240, 192, 180, 25);

        pass.setBounds(60, 240, 170, 25);
        ipass.setBounds(240, 242, 180, 25);

        confirm.setBounds(60, 290, 170, 25);
        iconfirm.setBounds(240, 292, 180, 25);

        update.setBounds(180, 360, 100, 35);

        update.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        String p1 = "", p2 = "", p3 = "";
        try {
            if (e.getSource() == update) {
                p1 = String.copyValueOf(iold.getPassword());
                p2 = String.copyValueOf(ipass.getPassword());
                p3 = String.copyValueOf(iconfirm.getPassword());
                if (!p2.contentEquals(p3)) {
                    JOptionPane.showMessageDialog(this, "The two passwords are not same!");
                } else {
                    String s = ("select pass from users where id='" + iid.getText() + "'");
                    rs = stmt.executeQuery(s);
                    rs.next();
                    if (rs.getString(1).contentEquals(p1)) {
                        s = ("update users set pass='" + p2 + "' where id='" + iid.getText() + "'");
                        stmt.executeUpdate(s);
                        JOptionPane.showMessageDialog(this, "Password changed successfully!");
                        iold.setText("");
                        ipass.setText("");
                        iconfirm.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "The old password is incorrect!!");
                    }

                }
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public static void main(String args[]) {

    }

}
