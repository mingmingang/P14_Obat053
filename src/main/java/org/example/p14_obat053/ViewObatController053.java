package org.example.p14_obat053;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.p14_obat053.connection053.DBConnect;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewObatController053 implements Initializable {

    DBConnect connect = new DBConnect();
    private ObservableList<Obat> oblist = FXCollections.observableArrayList();
    @FXML
    public TableView<Obat> tableObat;
    @FXML
    private TableColumn<Obat, String> kodeObat;

    @FXML
    private TableColumn<Obat, String> namaObat;
    @FXML
    private TableColumn<Obat, String> merkObat;

    @FXML
    private TableColumn<Obat, String> kemasanObat;
    @FXML
    private TableColumn<Obat, String> efekObat;
    @FXML
    private TableColumn<Obat, Integer> hrgBeliObat;
    @FXML
    private TableColumn<Obat, Integer> hrgJualObat;
    @FXML
    private TableColumn<Obat, Integer> kadaluarsaObat;
    @FXML
    private TableColumn<Obat, Integer> stockObat;

    public static class Obat{
        public Obat(String kode, String nama, String merk, String kemasan, String efek, String kadaluarsa, Integer hargaBeli, Integer hargaJual, Integer stock) {
            this.kode = kode;
            this.nama = nama;
            this.merk = merk;
            this.kemasan = kemasan;
            this.efek = efek;
            this.kadaluarsa = kadaluarsa;
            this.hargaBeli = hargaBeli;
            this.hargaJual = hargaJual;
            this.stock = stock;
        }

        String kode,nama,merk,kemasan,efek,kadaluarsa;
        Integer hargaBeli, hargaJual, stock;

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getMerk() {
            return merk;
        }

        public void setMerk(String merk) {
            this.merk = merk;
        }

        public String getKemasan() {
            return kemasan;
        }

        public void setKemasan(String kemasan) {
            this.kemasan = kemasan;
        }

        public String getEfek() {
            return efek;
        }

        public void setEfek(String efek) {
            this.efek = efek;
        }

        public String getKadaluarsa() {
            return kadaluarsa;
        }

        public void setKadaluarsa(String kadaluarsa) {
            this.kadaluarsa = kadaluarsa;
        }

        public Integer getHargaBeli() {
            return hargaBeli;
        }

        public void setHargaBeli(Integer hargaBeli) {
            this.hargaBeli = hargaBeli;
        }

        public Integer getHargaJual() {
            return hargaJual;
        }

        public void setHargaJual(Integer hargaJual) {
            this.hargaJual = hargaJual;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connect.stat = connect.conn.createStatement();
            String query = "SELECT * FROM tbObat";
            connect.result = connect.stat.executeQuery(query);

            while(connect.result.next()){
                oblist.add(new Obat(connect.result.getString("idObat"),
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
        }catch (Exception ex){
            System.out.println("Terjadi error saat load data obat: "+ex);
        }
        kodeObat.setCellValueFactory(new PropertyValueFactory<>("kode"));
        namaObat.setCellValueFactory(new PropertyValueFactory<>("nama"));
        merkObat.setCellValueFactory(new PropertyValueFactory<>("merk"));
        kemasanObat.setCellValueFactory(new PropertyValueFactory<>("kemasan"));
        efekObat.setCellValueFactory(new PropertyValueFactory<>("efek"));
        hrgBeliObat.setCellValueFactory(new PropertyValueFactory<>("kadaluarsa"));
        hrgJualObat.setCellValueFactory(new PropertyValueFactory<>("hargaBeli"));
        kadaluarsaObat.setCellValueFactory(new PropertyValueFactory<>("hargaJual"));
        stockObat.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableObat.setItems(oblist);
    }
}
