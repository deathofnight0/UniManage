import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class feerecord extends JInternalFrame implements ActionListener, ItemListener, FocusListener {
    JLabel title, receiptno, receiptdt, course, sem, id, fee, rem, paid, bal;
    JTextField ireceiptno, ireceiptdt, ifee, irem, ipaid, ibal;
    JButton neww, upload;
    JComboBox icourse, isem, iid;
    Connection con;
    Statement stmt;
    ResultSet rs;
    Date d1;
    int count = 0;
    String date;

    feerecord() {
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

        title = new JLabel("Fees Record");
        receiptno = new JLabel("Receipt no.");
        receiptdt = new JLabel("Receipt date");
        course = new JLabel("Course");
        sem = new JLabel("Semester");
        id = new JLabel("Student id");
        fee = new JLabel("Fees");
        rem = new JLabel("Remaining fees");
        paid = new JLabel("Paid");
        bal = new JLabel("Balance");

        ireceiptno = new JTextField(30);
        ireceiptdt = new JTextField(30);
        ifee = new JTextField(30);
        irem = new JTextField(30);
        ipaid = new JTextField(30);
        ibal = new JTextField(30);

        neww = new JButton("New");
        upload = new JButton("Upload");

        icourse = new JComboBox();
        isem = new JComboBox();
        iid = new JComboBox();

        // Adding elements
        add(title);
        add(receiptno);
        add(receiptdt);
        add(course);
        add(sem);
        add(id);
        add(fee);
        add(rem);
        add(paid);
        add(bal);

        add(ireceiptno);
        add(ireceiptdt);
        add(icourse);
        add(isem);
        add(iid);
        add(ifee);
        add(irem);
        add(ipaid);
        add(ibal);
        add(neww);
        add(upload);

        // Setting font
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        receiptno.setFont(new Font("Verdana", Font.PLAIN, 17));
        receiptdt.setFont(new Font("Verdana", Font.PLAIN, 17));
        course.setFont(new Font("Verdana", Font.PLAIN, 17));
        sem.setFont(new Font("Verdana", Font.PLAIN, 17));
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        fee.setFont(new Font("Verdana", Font.PLAIN, 17));
        rem.setFont(new Font("Verdana", Font.PLAIN, 17));
        paid.setFont(new Font("Verdana", Font.PLAIN, 17));
        bal.setFont(new Font("Verdana", Font.PLAIN, 17));

        ireceiptno.setFont(new Font("Verdana", Font.PLAIN, 15));
        ireceiptdt.setFont(new Font("Verdana", Font.PLAIN, 15));
        icourse.setFont(new Font("Verdana", Font.PLAIN, 15));
        isem.setFont(new Font("Verdana", Font.PLAIN, 15));
        iid.setFont(new Font("Verdana", Font.PLAIN, 15));
        ifee.setFont(new Font("Verdana", Font.PLAIN, 15));
        irem.setFont(new Font("Verdana", Font.PLAIN, 15));
        ipaid.setFont(new Font("Verdana", Font.PLAIN, 15));
        ibal.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Placing elements
        title.setBounds(150, 20, 300, 30);

        receiptno.setBounds(60, 100, 140, 25);
        ireceiptno.setBounds(240, 102, 180, 25);

        receiptdt.setBounds(60, 150, 140, 25);
        ireceiptdt.setBounds(240, 152, 180, 25);

        course.setBounds(60, 200, 140, 25);
        icourse.setBounds(240, 202, 180, 25);

        sem.setBounds(60, 250, 140, 25);
        isem.setBounds(240, 252, 180, 25);

        id.setBounds(60, 300, 140, 25);
        iid.setBounds(240, 302, 180, 25);

        fee.setBounds(60, 350, 140, 25);
        ifee.setBounds(240, 352, 180, 25);

        rem.setBounds(60, 400, 140, 25);
        irem.setBounds(240, 402, 180, 25);

        paid.setBounds(60, 450, 140, 25);
        ipaid.setBounds(240, 452, 180, 25);

        bal.setBounds(60, 500, 140, 25);
        ibal.setBounds(240, 502, 180, 25);

        neww.setBounds(100, 580, 80, 35);
        upload.setBounds(290, 580, 80, 35);

        // Adding listeners
        neww.addActionListener(this);
        upload.addActionListener(this);
        icourse.addItemListener(this);
        isem.addItemListener(this);
        iid.addItemListener(this);
        ipaid.addFocusListener(this);

        ireceiptno.setEditable(false);
        ireceiptdt.setEditable(false);
        irem.setEditable(false);
        ifee.setEditable(false);
        ibal.setEditable(false);
        upload.setEnabled(false);

        clear();

    }

    public void clear() {
        try {
            icourse.removeAllItems();
            isem.removeAllItems();
            upload.setEnabled(true);

            ifee.setText("");
            ipaid.setText("");
            ibal.setText("");

            String s1 = ("select DISTINCT course from subjects");
            rs = stmt.executeQuery(s1);
            icourse.addItem("Select Course");
            while (rs.next()) {
                icourse.addItem(rs.getString(1));
            }
            s1 = ("select * from feerecord order by receipt_no desc limit 1");
            rs = stmt.executeQuery(s1);
            if (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            ireceiptno.setText(String.valueOf(count + 1));

            if (d1.getMonth() >= 9)
                date = ((d1.getYear() + 1900) + "-" + (d1.getMonth() + 1) + "-" +
                        d1.getDate());
            else
                date = ((d1.getYear() + 1900) + "-0" + (d1.getMonth() + 1) + "-" +
                        d1.getDate());
            ireceiptdt.setText(date);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == neww) {
            clear();
        } else if (e.getSource() == upload) {
            if (ibal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data is incomplete!");
            } else {
                try {
                    String s = ("select * from feerecord where student_id=" +
                            iid.getSelectedItem() + " and semester="
                            + isem.getSelectedItem() + " and receipt_no=0");
                    rs = stmt.executeQuery(s);
                    if (rs.next()) {
                        s = ("delete from feerecord where student_id=" + iid.getSelectedItem() + " and semester="
                                + isem.getSelectedItem() + " and receipt_no=0");
                        stmt.executeUpdate(s);
                    }
                    s = ("insert into feerecord values(" + ireceiptno.getText() + ",'" + ireceiptdt.getText()
                            + "',"
                            + iid.getSelectedItem() + ",'" + icourse.getSelectedItem() + "',"
                            + isem.getSelectedItem() + "," + ipaid.getText() + "," + ibal.getText() + ")");
                    stmt.executeUpdate(s);
                    JOptionPane.showMessageDialog(this, "Fee record has been entered!");
                    clear();

                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }

            }
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        try {
            if (ie.getSource() == icourse && icourse.getSelectedIndex() != 0 &&
                    icourse.getItemCount() != 0) {
                ifee.setText("");
                irem.setText("");
                String s = icourse.getSelectedItem().toString();
                isem.removeAllItems();
                String s1 = ("select DISTINCT semester from subjects where course='" + s +
                        "' order by semester");
                rs = stmt.executeQuery(s1);
                isem.addItem("Select Semester");
                while (rs.next()) {
                    isem.addItem(rs.getString(1));
                }
            } else if (ie.getSource() == isem && isem.getSelectedIndex() != 0 &&
                    isem.getItemCount() != 0) {
                iid.removeAllItems();
                String s = ("select id from student where course='" + icourse.getSelectedItem() + "' and semester="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                iid.addItem("Select id");
                while (rs.next()) {
                    iid.addItem(rs.getString(1));
                }
                s = ("select * from fees where course='" + icourse.getSelectedItem() + "' and semester ="
                        + isem.getSelectedItem());
                rs = stmt.executeQuery(s);
                if (rs.next()) {
                    ifee.setText(rs.getString(4));
                }

            } else if (ie.getSource() == iid && iid.getSelectedIndex() != 0 &&
                    iid.getItemCount() != 0) {
                irem.setText("");
                String s1 = ("select * from feerecord where student_id=" + iid.getSelectedItem()
                        + " order by date desc");
                rs = stmt.executeQuery(s1);
                if (rs.next()) {
                    if (Integer.parseInt(rs.getString(7)) == 0) {
                        JOptionPane.showMessageDialog(this, "Fees for this semester has been paid!");
                        upload.setEnabled(false);
                    } else {
                        irem.setText(rs.getString(7));

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void focusLost(FocusEvent fe) {
        if (!validate.numeric(ipaid.getText()) && !ipaid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Paid fees input is not numeric");
        } else {
            if (Integer.parseInt(ipaid.getText()) > Integer.parseInt(irem.getText())) {
                JOptionPane.showMessageDialog(this, "Paid fees can't be greater than remaining fees");
            } else {
                int balance = Integer.parseInt(ifee.getText()) - Integer.parseInt(ipaid.getText());
                ibal.setText(String.valueOf(balance));
            }
        }
    }

    public void focusGained(FocusEvent fe) {
    }

    public static void main(String args[]) {
    }
}