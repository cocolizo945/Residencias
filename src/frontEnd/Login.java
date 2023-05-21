package frontEnd;

import backEnd.connex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JButton btnLogin;
    private JButton btnExit;
    private JPasswordField passtxt;
    private JTextField usertxt;
    private JLabel txtPass;
    private JLabel txtUser;
    private JPanel mainP;
    private JLabel lblLostpass;
    private JLabel logo;
    ResultSet rs;
    PreparedStatement pst;
    connex conection = new connex();


    public Login() {
        super("Log in");
        setContentPane(mainP);

        conection.conecc();

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valUser(usertxt, passtxt);
            }
        });
    }
    public void valUser(JTextField usertxt, JPasswordField passtxt ){
        try {

            String consult="SELECT * FROM usuarios where nom_user = ? and clave = ?";
            pst= conection.conecc().prepareStatement(consult);

            pst.setString(1, usertxt.getText());
            pst.setString(2, String.valueOf(passtxt.getPassword()));

            rs = pst.executeQuery();

            if(rs.next()){

                JFrame frame2 = new mainMenu();
                frame2.setVisible(true);
                frame2.setSize(1280,720);
                frame2.setDefaultCloseOperation(mainMenu.EXIT_ON_CLOSE);

                dispose();

            }else {
                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");

            }
        } catch (Exception e){
            System.out.println("error:" + e.toString() + rs.toString());
        }
    }


}


