package frontEnd;

import backEnd.connex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control extends JFrame {
    public JButton confirmarButton1;
    public JButton confirmarButton2;
    public JButton confirmarButton3;
    public JButton confirmarButton4;
    public JButton confirmarButton;
    public JButton salirButton;
    private JPanel ConPane;
    public JLabel lblNumcon;
    public JLabel lblDataPer;
    public JLabel lblDatProg;
    public JLabel lblDocIni;
    public JLabel lblPrese;
    public JLabel lblAcep;
    public JLabel lblFreedom;
    public JLabel lblEnd;
    private JButton confirmarButton5;

    connex conection = new connex();
    PreparedStatement pst;
    ResultSet rs;

    public Control() {
    super("Control de avances");
    setContentPane(ConPane);
    conection.conecc();

    salirButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               lblDocIni.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set docu_inicial = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDocIni.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set docu_inicial = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        confirmarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDocIni.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set presenta = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        confirmarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDocIni.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set carta_acep = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        confirmarButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDocIni.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set cart_libera = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        confirmarButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDocIni.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set cart_termina = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        confirmarButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDatProg.setText("Complete");
                String num;
                num = lblNumcon.getText();
                try{
                    pst = conection.conecc().prepareStatement("Update control set data_programa = '1' where num_control = ?");
                    pst.setString(1,num);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
 //UPDATE `residencias`.`control` SET `data_programa` = '1' WHERE (`num_control` = '20510594');