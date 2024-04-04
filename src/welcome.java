import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class welcome extends JFrame implements Runnable, ActionListener {
    JLabel title, back, id, pass;
    ImageIcon bgimg;
    Thread t1;
    JTextField iid;
    JPasswordField ipass;
    JButton login;
    Connection con;
    Statement stmt;
    ResultSet rs;

    welcome() {
        setTitle("Tagore Academy");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        title = new JLabel("Welcome");
        bgimg = new ImageIcon("../img/welcomebg.png");
        back = new JLabel(bgimg);
        t1 = new Thread(this);
        t1.start();

        add(title);
        add(back);

        title.setFont(new Font("Verdana", Font.PLAIN, 40));

        title.setBounds(140, 20, 200, 40);
        back.setBounds(-15, 0, this.getWidth(), this.getHeight());

        setVisible(true);

    }

    public void sign() {
        remove(back);

        setTitle("Login Page");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Creating connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e);
        }

        title.setText("Login");
        id = new JLabel("User id");
        pass = new JLabel("Password");

        iid = new JTextField(30);
        ipass = new JPasswordField(30);

        login = new JButton("Login");

        add(title);
        add(id);
        add(pass);
        add(iid);
        add(ipass);
        add(login);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        pass.setFont(new Font("Verdana", Font.PLAIN, 17));
        iid.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipass.setFont(new Font("Verdana", Font.PLAIN, 15));

        title.setBounds(200, 40, 150, 40);

        id.setBounds(100, 130, 100, 25);
        iid.setBounds(220, 132, 180, 25);

        pass.setBounds(100, 190, 100, 25);
        ipass.setBounds(220, 192, 180, 25);

        login.setBounds(190, 260, 100, 35);

        login.addActionListener(this);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == login) {
                String s = ("select * from users where id='" + iid.getText() + "'");
                rs = stmt.executeQuery(s);
                String password;
                if (rs.next()) {
                    password = String.copyValueOf(ipass.getPassword());
                    if (rs.getString(4).compareTo(password) != 0) {
                        JOptionPane.showMessageDialog(this, "Incorrect login details entered!");
                    } else {
                        if (rs.getString(7).compareTo("admin") == 0) {
                            mainpage m1 = new mainpage(iid.getText());
                            this.dispose();
                        } else {
                            userpage u1 = new userpage(iid.getText());
                            this.dispose();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect login details entered!");
                }
            }
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.dispose();
        sign();

    }

    public static void main(String args[]) {
        new welcome();
    }
}