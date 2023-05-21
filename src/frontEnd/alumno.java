package frontEnd;

import backEnd.connex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class alumno extends JFrame{
    public JTextField txtNumC;
    private JTextField txtNom;
    private JTextField txtApP;
    private JTextField txtApM;
    private JTextField txtBirth;
    private JButton btnConfirm;
    private JTextField txtEmail;
    private JTextField txtNumTel;
    private JTextField txtSem;
    private JTextField txtCalle;
    private JTextField txtNumExt;
    private JTextField txtNumInt;
    private JTextField txtCol;
    private JTextField txtCp;
    private JTextField txtCiudad;
    private JPanel almPane;
    connex conection = new connex();
    PreparedStatement pst;

    mainMenu mm = new mainMenu();
public alumno() {
    super("");
    setContentPane(almPane);
    btnConfirm.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int resp =JOptionPane.showConfirmDialog(null, "¿Deseas Agregar estos datos?",
                    "Crear Alumno", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if(resp == 0){
                try{
                    pst = conection.conecc().prepareStatement("update alumnos set  nom= ?, ape_pa= ?, ape_ma= ?, nac= ?, correo= ?, tel= ?, sem= ?, calle= ?, num_ex= ?, num_in= ?, col= ?, cod_pos= ?, ciudad= ? where num_control = ?;");
                    pst.setString(1,txtNom.getText());
                    pst.setString(2,txtApP.getText());
                    pst.setString(3,txtApM.getText());
                    pst.setString(4,txtBirth.getText());
                    pst.setString(5,txtEmail.getText());
                    pst.setString(6,txtNumTel.getText());
                    pst.setString(7,txtSem.getText());
                    pst.setString(8,txtCalle.getText());
                    pst.setString(9,txtNumExt.getText());
                    pst.setString(10,txtNumInt.getText());
                    pst.setString(11,txtCol.getText());
                    pst.setString(12,txtCp.getText());
                    pst.setString(13,txtCiudad.getText());
                    pst.setString(14,txtNumC.getText());
                    pst.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                mm.table_load();
                mm.setVisible(true);
                mm.setSize(1280,720);
                mm.setDefaultCloseOperation(mainMenu.EXIT_ON_CLOSE);
            }
        }
    });
}
}

