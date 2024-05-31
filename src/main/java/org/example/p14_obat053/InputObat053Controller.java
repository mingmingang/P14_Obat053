package org.example.p14_obat053;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.p14_obat053.connection053.DBConnect;

import javax.swing.*;
import java.sql.SQLException;
import java.util.jar.JarOutputStream;

public class InputObat053Controller {
    @FXML
    private TextField txtKode;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtmerk;
    @FXML
    private TextField txtkemasan;
    @FXML
    private TextField txtefek;
    @FXML
    private TextField txthrgbeli;
    @FXML
    private TextField txthrgjual;
    @FXML
    private TextField txtkadaluarsa;
    @FXML
    private TextField txtstock;

    @FXML
    private Button simpanButton;
    @FXML
    private Button batalButton;

    String kodeObat, nama, merk, kemasan, efek, tanggal;
    int hrgBeli, hrgJual, stock;
    DBConnect connect = new DBConnect();

    @FXML
    protected void onBtnSimpanClick(){
        kodeObat = txtKode.getText();
        nama = txtNama.getText();
        merk = txtmerk.getText();
        kemasan = txtkemasan.getText();
        efek = txtefek.getText();
        hrgBeli = Integer.parseInt(txthrgbeli.getText());
        hrgJual = Integer.parseInt(txthrgjual.getText());
        stock = Integer.parseInt(txtstock.getText());
        tanggal = txtkadaluarsa.getText();

        try {
            String query = "INSERT INTO tbObat VALUES (?,?,?,?,?,?,?,?,?)";
            connect.pstat = connect.conn.prepareStatement(query);
            connect.pstat.setString(1,kodeObat);
            connect.pstat.setString(2,nama);
            connect.pstat.setString(3,merk);
            connect.pstat.setString(4,kemasan);
            connect.pstat.setString(5,efek);
            connect.pstat.setInt(6,hrgBeli);
            connect.pstat.setInt(7,hrgJual);
            connect.pstat.setString(8,tanggal);
            connect.pstat.setInt(9,stock);
            connect.pstat.executeUpdate();
            connect.pstat.close();
        }catch (SQLException ex){
            System.out.println("Terjadi error saat insert data obat: "+ex);
        }
        JOptionPane.showMessageDialog(null,"Insert data obat berhasil!");
    }
}
