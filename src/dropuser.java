import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class dropuser extends JInternalFrame implements ActionListener, ItemListener {
    JLabel title, uid, name, type, designation;
    JTextField iname, itype, idesignation;
    JButton drop;
    JComboBox iuid;
    Connection con;
    Statement stmt;
    ResultSet rs;

    dropuser() {
        super("Drop User", true, true, false, true);
        setTitle("Drop User");
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

        title = new JLabel("Drop User");
        uid = new JLabel("User id");
        name = new JLabel("Name");
        type = new JLabel("Type");
        designation = new JLabel("Designation");

        iname = new JTextField(30);
        itype = new JTextField(30);
        idesignation = new JTextField(30);

        drop = new JButton("Drop");
        iuid = new JComboBox();

        add(title);
        add(uid);
        add(name);
        add(type);
        add(designation);
        add(iuid);
        add(iname);
        add(itype);
        add(idesignation);
        add(drop);

        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        uid.setFont(new Font("Verdana", Font.PLAIN, 17));
        name.setFont(new Font("Verdana", Font.PLAIN, 17));
        type.setFont(new Font("Verdana", Font.PLAIN, 17));
        designation.setFont(new Font("Verdana", Font.PLAIN, 17));
        iuid.setFont(new Font("Verdana", Font.PLAIN, 15));
        iname.setFont(new Font("Verdana", Font.PLAIN, 15));
        itype.setFont(new Font("Verdana", Font.PLAIN, 15));
        idesignation.setFont(new Font("Verdana", Font.PLAIN, 15));
        drop.setFont(new Font("Verdana", Font.PLAIN, 17));

        // Placing elements
        title.setBounds(150, 40, 300, 40);

        uid.setBounds(60, 120, 140, 25);
        iuid.setBounds(240, 122, 180, 25);

        name.setBounds(60, 160, 140, 25);
        iname.setBounds(240, 162, 180, 25);

        type.setBounds(60, 200, 140, 25);
        itype.setBounds(240, 202, 180, 25);

        designation.setBounds(60, 240, 140, 25);
        idesignation.setBounds(240, 242, 180, 25);

        drop.setBounds(170, 310, 110, 40);

        drop.addActionListener(this);
        iuid.addItemListener(this);
        clear();

    }

    public void clear() {
        try {
            iuid.removeAllItems();
            iname.setText("");
            itype.setText("");
            idesignation.setText("");
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drop) {
            if (!iname.getText().isEmpty()) {
                try {
                    int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?");
                    if (a == 0) {
                        String s = ("delete from users where id='" + iuid.getSelectedItem() + "'");
                        stmt.executeUpdate(s);
                        JOptionPane.showMessageDialog(this, "User has been successfully deleted!");
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
                itype.setText(rs.getString(7));
                idesignation.setText(rs.getString(8));
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }

        }
    }

    public static void main(String args[]) {

    }
}