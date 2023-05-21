package backEnd;

import javax.swing.*;
import frontEnd.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {




    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new Login();
                frame.setVisible(true);
                frame.setSize(600,300);
                frame.pack();
                frame.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);

            }
        });

        }
    }

