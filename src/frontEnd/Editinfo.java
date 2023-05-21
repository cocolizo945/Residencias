package frontEnd;

import backEnd.connex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Editinfo extends JFrame {
    public JTextField txtConNum;
    public JTextField txtNomProy;
    public JTextField txtproyType;
    public JTextField txtDateStart;
    public JTextField txtDateEnd;
    public JTextField txtAsInt;
    public JTextField txtNomAsInt;
    public JTextField txtAsIntPuesto;
    public JTextField txtAsExtTitulo;
    public JTextField txtNomAsExt;
    public JTextField txtPuestoAsExt;
    public JTextField txtTituloCart;
    public JTextField txtNomCart;
    public JTextField txtPuestoCart;
    public JTextField txtTituloEmpress;
    public JTextField txtNomEmpress;
    public JTextField txtPuestoEmpress;
    public JButton btnAceppt;
    public JButton btnDecline;
    private JLabel lblId;
    private JPanel editPanel;
    public JLabel lblIdP;
    public JButton btnControl;
    connex conection = new connex();
    PreparedStatement pst;
    ResultSet rs;
    mainMenu mm = new mainMenu();



    public Editinfo(){
        super("");
        setContentPane(editPanel);
        conection.conecc();

        btnDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mm.table_load();
                dispose();
                JFrame frame2 = new mainMenu();
                frame2.setVisible(true);
                frame2.setSize(1280,720);
                frame2.setDefaultCloseOperation(mainMenu.EXIT_ON_CLOSE);
            }
        });
        btnAceppt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mm.table_load();
                update();
                JFrame frame2 = new mainMenu();
                frame2.setVisible(true);
                frame2.setSize(1280,720);
                frame2.setDefaultCloseOperation(mainMenu.EXIT_ON_CLOSE);
            }
        });
        btnControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Numcon();
            }
        });
    }

    public void update(){
        String id_proy,num_control,nom_proy,tipo_proy, fecha_ini,fecha_fin, titulo_ase_in,nom_ase_int,puesto_ase_in,titulo_ase_ext,nom_ase_ext,puesto_ase_ext,titulo_cart,nom_cart,puesto_cart,titulo_empre,nom_empre,puesto_empre;
        id_proy=lblIdP.getText().toString();
        num_control=txtConNum.getText();
        nom_proy =txtNomProy.getText();
        tipo_proy=txtproyType.getText();
        fecha_ini=txtDateStart.getText();
        fecha_fin=txtDateEnd.getText();
        titulo_ase_in=txtAsInt.getText();
        nom_ase_int=txtNomAsInt.getText();
        puesto_ase_in=txtAsIntPuesto.getText();
        titulo_ase_ext=txtAsExtTitulo.getText();
        nom_ase_ext=txtNomAsExt.getText();
        puesto_ase_ext=txtPuestoAsExt.getText();
        titulo_cart=txtTituloCart.getText();
        nom_cart=txtNomCart.getText();
        puesto_cart=txtPuestoCart.getText();
        titulo_empre=txtTituloEmpress.getText();
        nom_empre=txtNomEmpress.getText();
        puesto_empre=txtPuestoEmpress.getText();

        try{

                String query = "UPDATE proyecto set num_control = ?,nom_proy = ?,tipo_proy = ?,fecha_ini = ?,fecha_fin = ?,titulo_ase_in = ?,nom_ase_int = ?,puesto_ase_in = ?,titulo_ase_ext = ?,nom_ase_ext = ?,puesto_ase_ext = ?,titulo_cart = ?,nom_cart = ?,puesto_cart = ?,titulo_empre = ?,nom_empre = ?,puesto_empre = ? where id_proy = ?;";
                pst = conection.conecc().prepareStatement(query);

                pst.setString(1, num_control);
                pst.setString(2, nom_proy);
                pst.setString(3, tipo_proy);
                pst.setString(4, fecha_ini);
                pst.setString(5, fecha_fin);
                pst.setString(6, titulo_ase_in);
                pst.setString(7, nom_ase_int);
                pst.setString(8, puesto_ase_in);
                pst.setString(9, titulo_ase_ext);
                pst.setString(10, nom_ase_ext);
                pst.setString(11, puesto_ase_ext);
                pst.setString(12, titulo_cart);
                pst.setString(13, nom_cart);
                pst.setString(14, puesto_cart);
                pst.setString(15, titulo_empre);
                pst.setString(16, nom_empre);
                pst.setString(17, puesto_empre);
                pst.setString(18, id_proy);
                pst.executeUpdate();
                mm.table_load();
                JOptionPane.showMessageDialog(null, "Datos actualizados");
                dispose();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void Numcon(){
        Control cnt = new Control();
        String conmu = txtConNum.getText();
        cnt.lblNumcon.setText(conmu);
        System.out.println(conmu);
        try{
            pst = conection.conecc().prepareStatement("SELECT data_personal,data_programa,docu_inicial,presenta, carta_acep,cart_libera,cart_termina from control where num_control = ?");
            pst.setString(1,conmu);
            rs = pst.executeQuery();
            if(rs.next()){
                String datPer,datProg,DocInit,Present,Cart_Acc,Cart_free,Cart_end;
                datPer=rs.getString(1);
                datProg=rs.getString(2);
                DocInit=rs.getString(3);
                Present=rs.getString(4);
                Cart_Acc=rs.getString(5);
                Cart_free=rs.getString(6);
                Cart_end=rs.getString(7);
                if(datPer.equals("1")){
                    cnt.lblDataPer.setText("Complete");
                }else{
                    cnt.lblDataPer.setText("Sin informacion");
                }

                if(datProg.equals("1")){
                    cnt.lblDatProg.setText("Complete");
                }else{
                    cnt.lblDatProg.setText("Sin informacion");
                }

                if(DocInit.equals("1")){
                    cnt.lblDocIni.setText("Complete");
                    cnt.confirmarButton.setEnabled(false);
                }else{
                    cnt.lblDocIni.setText("Sin informacion");
                }

                if(Present.equals("1")){
                    cnt.lblPrese.setText("Complete");
                    cnt.confirmarButton1.setEnabled(false);
                }else{
                    cnt.lblPrese.setText("Sin informacion");
                }

                if(Cart_Acc.equals("1")){
                    cnt.lblAcep.setText("Complete");
                    cnt.confirmarButton2.setEnabled(false);
                }else{
                    cnt.lblAcep.setText("Sin informacion");
                }

                if(Cart_free.equals("1")){
                    cnt.lblFreedom.setText("Complete");
                    cnt.confirmarButton3.setEnabled(false);
                }else{
                    cnt.lblFreedom.setText("Sin informacion");
                }

                if(Cart_end.equals("1")){
                    cnt.lblEnd.setText("Complete");
                    cnt.confirmarButton4.setEnabled(false);
                }else{
                    cnt.lblEnd.setText("Sin informacion");
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        cnt.setVisible(true);
        cnt.setSize(800,600);
        cnt.pack();
        cnt.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
