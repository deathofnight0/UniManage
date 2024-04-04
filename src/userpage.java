import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userpage extends JFrame implements ActionListener {
    JMenu users, master, student, report, id;
    JMenuItem pass, exit, newadm, smodify, resultt, attend, sfees, spromote, rfees, rstudent, rattend, rresultt;
    JLabel back;
    ImageIcon bgimg;
    JMenuBar mb;
    JDesktopPane pane = new JDesktopPane();
    Dimension desktopSize;
    String uid;

    userpage(String uid) {
        setTitle("Tagore Academy");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.uid = uid;
        setContentPane(pane);
        users = new JMenu("Users");
        student = new JMenu("Student");
        report = new JMenu("Reports");
        id = new JMenu(uid);
        id.setEnabled(false);
        pass = new JMenuItem("Change password");
        exit = new JMenuItem("Exit");

        newadm = new JMenuItem("New Admission");
        smodify = new JMenuItem("Modify Data");
        resultt = new JMenuItem("Upload Result");
        attend = new JMenuItem("Mark Attendance");
        sfees = new JMenuItem("Fees Deposit");
        spromote = new JMenuItem("Promote Student");

        rfees = new JMenuItem("Fee records");
        rstudent = new JMenuItem("Student details");
        rattend = new JMenuItem("Student attendance");
        rresultt = new JMenuItem("Student result");

        mb = new JMenuBar();

        bgimg = new ImageIcon("../img/logo.png");
        back = new JLabel(bgimg);

        users.add(pass);
        users.add(exit);
        student.add(newadm);
        student.add(smodify);
        student.add(resultt);
        student.add(attend);
        student.add(sfees);
        student.add(spromote);
        report.add(rfees);
        report.add(rstudent);
        report.add(rattend);
        report.add(rresultt);

        mb.add(Box.createHorizontalStrut(10));
        mb.add(users);
        mb.add(Box.createHorizontalStrut(10));
        mb.add(student);
        mb.add(Box.createHorizontalStrut(10));
        mb.add(report);
        mb.add(Box.createHorizontalStrut(1320 - (uid.length() * 10)));
        mb.add(id);

        pane.add(back);

        users.setFont(new Font("Arial", Font.PLAIN, 18));
        student.setFont(new Font("Arial", Font.PLAIN, 18));
        report.setFont(new Font("Arial", Font.PLAIN, 18));
        id.setFont(new Font("Arial", Font.PLAIN, 18));

        pass.setFont(new Font("Arial", Font.PLAIN, 16));
        exit.setFont(new Font("Arial", Font.PLAIN, 16));
        newadm.setFont(new Font("Arial", Font.PLAIN, 16));
        smodify.setFont(new Font("Arial", Font.PLAIN, 16));
        resultt.setFont(new Font("Arial", Font.PLAIN, 16));
        attend.setFont(new Font("Arial", Font.PLAIN, 16));
        sfees.setFont(new Font("Arial", Font.PLAIN, 16));
        spromote.setFont(new Font("Arial", Font.PLAIN, 16));
        rfees.setFont(new Font("Arial", Font.PLAIN, 16));
        rstudent.setFont(new Font("Arial", Font.PLAIN, 16));
        rattend.setFont(new Font("Arial", Font.PLAIN, 16));
        rresultt.setFont(new Font("Arial", Font.PLAIN, 16));

        back.setBounds(0, -20, this.getWidth(), this.getHeight());
        this.setJMenuBar(mb);

        pass.addActionListener(this);
        exit.addActionListener(this);
        newadm.addActionListener(this);
        smodify.addActionListener(this);
        resultt.addActionListener(this);
        attend.addActionListener(this);
        sfees.addActionListener(this);
        spromote.addActionListener(this);
        rfees.addActionListener(this);
        rstudent.addActionListener(this);
        rattend.addActionListener(this);
        rresultt.addActionListener(this);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pass) {
            changepass cp = new changepass(uid);
            pane.add(cp);
            desktopSize = pane.getSize();
            Dimension size = cp.getSize();
            cp.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            cp.show();

        } else if (e.getSource() == newadm) {
            newstudent ns = new newstudent();
            pane.add(ns);
            desktopSize = pane.getSize();
            Dimension size = ns.getSize();
            ns.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            ns.show();

        } else if (e.getSource() == smodify) {
            modifystudent ms = new modifystudent();
            pane.add(ms);
            desktopSize = pane.getSize();
            Dimension size = ms.getSize();
            ms.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            ms.show();

        } else if (e.getSource() == resultt) {
            result r1 = new result();
            pane.add(r1);
            desktopSize = pane.getSize();
            Dimension size = r1.getSize();
            r1.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            r1.show();

        } else if (e.getSource() == attend) {
            attendance a1 = new attendance();
            pane.add(a1);
            desktopSize = pane.getSize();
            Dimension size = a1.getSize();
            a1.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            a1.show();

        } else if (e.getSource() == sfees) {
            feerecord fr = new feerecord();
            pane.add(fr);
            desktopSize = pane.getSize();
            Dimension size = fr.getSize();
            fr.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            fr.show();

        } else if (e.getSource() == spromote) {
            promote p1 = new promote();
            pane.add(p1);
            desktopSize = pane.getSize();
            Dimension size = p1.getSize();
            p1.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            p1.show();

        } else if (e.getSource() == rfees) {
            feereport fr = new feereport();
            pane.add(fr);
            desktopSize = pane.getSize();
            Dimension size = fr.getSize();
            fr.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            fr.show();

        } else if (e.getSource() == rstudent) {
            studentreport sr = new studentreport();
            pane.add(sr);
            desktopSize = pane.getSize();
            Dimension size = sr.getSize();
            sr.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            sr.show();

        } else if (e.getSource() == rattend) {
            attendancereport ar = new attendancereport();
            pane.add(ar);
            desktopSize = pane.getSize();
            Dimension size = ar.getSize();
            ar.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            ar.show();

        } else if (e.getSource() == rresultt) {
            resultreport rr = new resultreport();
            pane.add(rr);
            desktopSize = pane.getSize();
            Dimension size = rr.getSize();
            rr.setLocation((desktopSize.width - size.width) / 2,
                    (desktopSize.height - size.height) / 2);
            rr.show();

        } else if (e.getSource() == exit) {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
            if (a == 0) {
                System.exit(0);
            }
        }

    }

    public static void main(String args[]) {
    }
}