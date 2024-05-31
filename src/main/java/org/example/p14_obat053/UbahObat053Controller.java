package org.example.p14_obat053;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.p14_obat053.connection053.DBConnect;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UbahObat053Controller implements Initializable {
    DBConnect connect = new DBConnect();
    private ObservableList<ViewObatController053.Obat> oblist = FXCollections.observableArrayList();

    @FXML
    private TableView<ViewObatController053.Obat> tableObat;

    @FXML
    private TableColumn<ViewObatController053.Obat, String> kodeObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, String> namaObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, String> merkObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, String> kemasanObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, String> efekObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, Integer> hrgBeliObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, Integer> hrgJualObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, Integer> kadaluarsaObat;
    @FXML
    private TableColumn<ViewObatController053.Obat, Integer> stockObat;

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
    private TextField txtkadaluarsa;
    @FXML
    private TextField txthrgjual;
    @FXML
    private TextField txtstock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connect.stat = connect.conn.createStatement();
            String query = "SELECT * FROM tbObat";
            connect.result = connect.stat.executeQuery(query);

            while (connect.result.next()) {
                oblist.add(new ViewObatController053.Obat(
                        connect.result.getString("idObat"),
                        connect.result.getString("namaObat"),
                        connect.result.getString("merkObat"),
                        connect.result.getString("kemasanObat"),
                        connect.result.getString("efekObat"),
                        connect.result.getString("tglKadaluarsaObat"),
                        connect.result.getInt("hargaBeliObat"),
                        connect.result.getInt("hargaJualObat"),
                        connect.result.getInt("stock")));
            }
            connect.stat.close();
            connect.result.close();
        } catch (Exception ex) {
            System.out.println("Terjadi error saat load data obat: " + ex);
        }

        kodeObat.setCellValueFactory(new PropertyValueFactory<>("kode"));
        namaObat.setCellValueFactory(new PropertyValueFactory<>("nama"));
        merkObat.setCellValueFactory(new PropertyValueFactory<>("merk"));
        kemasanObat.setCellValueFactory(new PropertyValueFactory<>("kemasan"));
        efekObat.setCellValueFactory(new PropertyValueFactory<>("efek"));
        hrgBeliObat.setCellValueFactory(new PropertyValueFactory<>("hargaBeli"));
        hrgJualObat.setCellValueFactory(new PropertyValueFactory<>("hargaJual"));
        kadaluarsaObat.setCellValueFactory(new PropertyValueFactory<>("kadaluarsa"));
        stockObat.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableObat.setItems(oblist);
    }
    String kode,nama,merk,kemasan,efek,kadaluarsa;
    int hrgBeli, hrgJual, stock;
    @FXML
    public void onBtnUbahClick() {
        kode = txtKode.getText();
        nama = txtNama.getText();
        merk = txtmerk.getText();
        kemasan = txtkemasan.getText();
        efek = txtefek.getText();
        hrgBeli = Integer.parseInt(txthrgbeli.getText());
        hrgJual = Integer.parseInt(txthrgjual.getText());
        stock = Integer.parseInt(txtstock.getText());
        kadaluarsa = txtkadaluarsa.getText();
        try {
            DBConnect connection = new DBConnect();
            connection.stat = connection.conn.createStatement();
            String query = "Update tbObat SET namaObat=?, merkObat=?, kemasanObat=?," +
                    "efekObat=?, hargaBeliObat=?, hargaJualObat=?, stock=? WHERE idObat=?";
            connection.pstat = connection.conn.prepareStatement(query);
            connection.pstat.setString(1,nama);
            connection.pstat.setString(2,merk);
            connection.pstat.setString(3,kemasan);
            connection.pstat.setString(4,efek);
            connection.pstat.setInt(5,hrgBeli);
            connection.pstat.setInt(6,hrgJual);
            connection.pstat.setInt(7,stock);
            connection.pstat.setString(8,kode);

            connection.pstat.executeUpdate();
            connection.pstat.close();
        }catch (Exception ec){
            System.out.println("Terjadi error saat update obat "+ec);
        }
        JOptionPane.showMessageDialog(null,"Update data obat berhasil");
    }

    @FXML
    public void setOnMouseClick(MouseEvent mouseEvent) {
        ViewObatController053.Obat selectedObat = tableObat.getSelectionModel().getSelectedItem();
        if (selectedObat != null) {
            txtKode.setText(selectedObat.getKode());
            txtNama.setText(selectedObat.getNama());
            txtmerk.setText(selectedObat.getMerk());
            txtkemasan.setText(selectedObat.getKemasan());
            txtefek.setText(selectedObat.getEfek());
            txthrgbeli.setText(String.valueOf(selectedObat.getHargaBeli()));
            txthrgjual.setText(String.valueOf(selectedObat.getHargaJual()));
            txtkadaluarsa.setText(selectedObat.getKadaluarsa());
            txtstock.setText(String.valueOf(selectedObat.getStock()));
        }
    }
}
