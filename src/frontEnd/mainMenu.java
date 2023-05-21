package frontEnd;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import backEnd.*;


public class mainMenu extends JFrame {
    private JPanel menu;
    private JLabel lblSearch;
    public JTable table1;
    private JTextField txtNum;
    private JTextField txtAsNom;
    private JButton btnExit;
        connex conection = new connex();
        PreparedStatement pst;

        ResultSet rs;



    public mainMenu() {
        super("menu");
        setContentPane(menu);
        conection.conecc();
        table_load();
        popuptable();
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Deseas salir?",
                        "Cerrar Sesion", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if(resp == 0){
                JFrame frame = new Login();
                frame.setVisible(true);
                frame.setSize(600,300);
                frame.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
                dispose();}

            }
        });

        txtNum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel table = (DefaultTableModel)table1.getModel();
                String search = txtNum.getText();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
                table1.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(search));
            }
        });

        txtAsNom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DefaultTableModel table = (DefaultTableModel)table1.getModel();
                String search = txtAsNom.getText();
                TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
                table1.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(search));
            }
        });
    }



    public void popuptable() {


        JPopupMenu menutabla = new JPopupMenu();

        JMenuItem item1 = new JMenuItem("Ver");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id2;
                int row = table1.getSelectedRow();
                id2 = table1.getModel().getValueAt(row, 0).toString();
                Info(id2);

            }
        });

        JMenuItem item2 = new JMenuItem("Actualizar");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id2;
                int row = table1.getSelectedRow();
                id2 = table1.getModel().getValueAt(row, 0).toString();

               loadData(id2);
               dispose();
            }
        });
        JMenuItem item3 = new JMenuItem("Añadir Proyecto");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               NewProyect();
            }
        });


        JMenuItem item4 = new JMenuItem("Eliminar");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDB();
            }
        });
        menutabla.add(item1);
        menutabla.add(item2);
        menutabla.add(item3);
        menutabla.add(item4);
        table1.setComponentPopupMenu(menutabla);
    }


         void table_load(){
                 try{
                     pst = conection.conecc().prepareStatement("select * from proyecto ;");
                     ResultSet rs = pst.executeQuery();
                     table1.setModel(DbUtils.resultSetToTableModel(rs));

                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
        }



        void deleteDB(){
            int resp =JOptionPane.showConfirmDialog(null, "¿Deseas Eliminar?",
                    "Eliminar", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if((resp == 0)){

                String id;
                int row = table1.getSelectedRow();
                id = table1.getModel().getValueAt(row, 0).toString();
                 try{
                     pst = conection.conecc().prepareStatement("delete from proyecto where id_proy = ? ;");
                     pst.setString(1, id);
                     pst.executeUpdate();
                     table_load();
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
            }
        }
    public void loadData(String GenId){

        Editinfo dt = new Editinfo();
        try{
            pst =conection.conecc().prepareStatement("select num_control,nom_proy,tipo_proy, fecha_ini,fecha_fin, titulo_ase_in," +
                    "nom_ase_int,puesto_ase_in,titulo_ase_ext,nom_ase_ext,puesto_ase_ext,titulo_cart,nom_cart,puesto_cart,titulo_empre,nom_empre,puesto_empre from proyecto where id_proy = ? ;");
            pst.setString(1, GenId);
            rs = pst.executeQuery();
            if (rs.next()){
                String num_control,nom_proy,tipo_proy, fecha_ini,fecha_fin, titulo_ase_in,nom_ase_int,puesto_ase_in,titulo_ase_ext,nom_ase_ext,puesto_ase_ext,titulo_cart,nom_cart,puesto_cart,titulo_empre,nom_empre,puesto_empre;
                num_control = rs.getString(1);
                nom_proy = rs.getString(2);
                tipo_proy = rs.getString(3);
                fecha_ini = rs.getString(4);
                fecha_fin = rs.getString(5);
                titulo_ase_in = rs.getString(6);
                nom_ase_int = rs.getString(7);
                puesto_ase_in= rs.getString(8);
                titulo_ase_ext = rs.getString(9);
                nom_ase_ext = rs.getString(10);
                puesto_ase_ext = rs.getString(11);
                titulo_cart = rs.getString(12);
                nom_cart = rs.getString(13);
                puesto_cart = rs.getString(14);
                titulo_empre = rs.getString(15);
                nom_empre = rs.getString(16);
                puesto_empre = rs.getString(17);

                dt.lblIdP.setText(GenId);
                dt.txtConNum.setText(num_control);
                dt.txtNomProy.setText(nom_proy);
                dt.txtproyType.setText(tipo_proy);
                dt.txtDateStart.setText(fecha_ini);
                dt.txtDateEnd.setText(fecha_fin);
                dt.txtAsInt.setText(titulo_ase_in);
                dt.txtNomAsInt.setText(nom_ase_int);
                dt.txtAsIntPuesto.setText(puesto_ase_in);
                dt.txtAsExtTitulo.setText(titulo_ase_ext);
                dt.txtNomAsExt.setText(nom_ase_ext);
                dt.txtPuestoAsExt.setText(puesto_ase_ext);
                dt.txtTituloCart.setText(titulo_cart);
                dt.txtNomCart.setText(nom_cart);
                dt.txtPuestoCart.setText(puesto_cart);
                dt.txtTituloEmpress.setText(titulo_empre);
                dt.txtNomEmpress.setText(nom_empre);
                dt.txtPuestoEmpress.setText(puesto_empre);
                dt.btnControl.setEnabled(false);
                dt.btnControl.setVisible(false);

                dt.setVisible(true);
                dt.setSize(1280,400);
                dt.pack();
                dt.setDefaultCloseOperation(Editinfo.DISPOSE_ON_CLOSE);

                table_load();
            }

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void Info(String GenId){
        Editinfo dt = new Editinfo();
        try{
            pst =conection.conecc().prepareStatement("select num_control,nom_proy,tipo_proy, fecha_ini,fecha_fin, titulo_ase_in," +
                    "nom_ase_int,puesto_ase_in,titulo_ase_ext,nom_ase_ext,puesto_ase_ext,titulo_cart,nom_cart,puesto_cart,titulo_empre,nom_empre,puesto_empre from proyecto where id_proy = ? ;");
            pst.setString(1, GenId);
            rs = pst.executeQuery();
            if (rs.next()){
                String num_control,nom_proy,tipo_proy, fecha_ini,fecha_fin, titulo_ase_in,nom_ase_int,puesto_ase_in,titulo_ase_ext,nom_ase_ext,puesto_ase_ext,titulo_cart,nom_cart,puesto_cart,titulo_empre,nom_empre,puesto_empre;
                num_control = rs.getString(1);
                nom_proy = rs.getString(2);
                tipo_proy = rs.getString(3);
                fecha_ini = rs.getString(4);
                fecha_fin = rs.getString(5);
                titulo_ase_in = rs.getString(6);
                nom_ase_int = rs.getString(7);
                puesto_ase_in= rs.getString(8);
                titulo_ase_ext = rs.getString(9);
                nom_ase_ext = rs.getString(10);
                puesto_ase_ext = rs.getString(11);
                titulo_cart = rs.getString(12);
                nom_cart = rs.getString(13);
                puesto_cart = rs.getString(14);
                titulo_empre = rs.getString(15);
                nom_empre = rs.getString(16);
                puesto_empre = rs.getString(17);

                dt.lblIdP.setText(GenId);
                dt.txtConNum.setText(num_control);
                dt.txtNomProy.setText(nom_proy);
                dt.txtproyType.setText(tipo_proy);
                dt.txtDateStart.setText(fecha_ini);
                dt.txtDateEnd.setText(fecha_fin);
                dt.txtAsInt.setText(titulo_ase_in);
                dt.txtNomAsInt.setText(nom_ase_int);
                dt.txtAsIntPuesto.setText(puesto_ase_in);
                dt.txtAsExtTitulo.setText(titulo_ase_ext);
                dt.txtNomAsExt.setText(nom_ase_ext);
                dt.txtPuestoAsExt.setText(puesto_ase_ext);
                dt.txtTituloCart.setText(titulo_cart);
                dt.txtNomCart.setText(nom_cart);
                dt.txtPuestoCart.setText(puesto_cart);
                dt.txtTituloEmpress.setText(titulo_empre);
                dt.txtNomEmpress.setText(nom_empre);
                dt.txtPuestoEmpress.setText(puesto_empre);

                dt.txtConNum.setEditable(false);
                dt.txtNomProy.setEditable(false);
                dt.txtproyType.setEditable(false);
                dt.txtDateStart.setEditable(false);
                dt.txtDateEnd.setEditable(false);
                dt.txtAsInt.setEditable(false);
                dt.txtNomAsInt.setEditable(false);
                dt.txtAsIntPuesto.setEditable(false);
                dt.txtAsExtTitulo.setEditable(false);
                dt.txtNomAsExt.setEditable(false);
                dt.txtPuestoAsExt.setEditable(false);
                dt.txtTituloCart.setEditable(false);
                dt.txtNomCart.setEditable(false);
                dt.txtPuestoCart.setEditable(false);
                dt.txtTituloEmpress.setEditable(false);
                dt.txtNomEmpress.setEditable(false);
                dt.txtPuestoEmpress.setEditable(false);
                dt.btnAceppt.setVisible(false);
                dt.btnDecline.setText("Cerrar");

                dt.setVisible(true);
                dt.setSize(1280,400);
                dt.pack();
                dt.setDefaultCloseOperation(Editinfo.DISPOSE_ON_CLOSE);
                table_load();
                dispose();
            }

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void NewProyect(){
        alumno alm = new alumno();
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.okButtonText", "Aceptar");
        String numC = JOptionPane.showInputDialog("inserta el numero de control");
        if( !numC.equals("")){
            try{
                pst = conection.conecc().prepareStatement("insert into alumnos (num_control) values(?);");
                pst.setString(1,numC);
                pst.executeUpdate();
                table_load();
                alm.txtNumC.setText(numC);
                alm.setVisible(true);
                alm.setSize(800,600);
                alm.pack();
                alm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dispose();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Numero de control vacio");
            NewProyect();
        }
        table_load();
    }

}

