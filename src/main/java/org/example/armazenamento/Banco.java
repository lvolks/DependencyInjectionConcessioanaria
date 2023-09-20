package org.example.armazenamento;

import org.example.data.Carro;
import org.example.data.Moto;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco implements Armazenamento{
    private Connection connection;

    public Banco(){
        conectarBanco();
    }

    void conectarBanco() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:banco.db");
            System.out.println("Conectado ao do banco de dados SQLite!");

            createTables();
        } catch (Exception exception) {
            System.out.println("Erro ao conectar ao banco de dados.");
            exception.printStackTrace();
        }
    }

    void createTables(){
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS  carros (id INTEGER PRIMARY KEY AUTOINCREMENT, marca STRING, modelo STRING, ano STRING, preco DOUBLE , numeroPortas INTEGER)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS  motos (id INTEGER PRIMARY KEY AUTOINCREMENT, marca STRING, modelo STRING, ano STRING, preco DOUBLE, cilindradas INTEGER)");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void salvarCarro(Carro carro) {
        try {
            var stt = connection.prepareStatement("INSERT INTO carros (marca, modelo, ano, preco, numeroPortas) VALUES (?, ?, ?, ?, ?)");
            stt.setString(1, carro.marca);
            stt.setString(2, carro.modelo);
            stt.setString(3, carro.ano);
            stt.setDouble(4, carro.preco);
            stt.setInt(5, carro.numeroPortas);

            stt.executeUpdate();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void salvarMoto(Moto moto) {
        try {
            var stt = connection.prepareStatement("INSERT INTO motos (marca, modelo, ano, preco, cilindradas) VALUES (?, ?, ?, ?, ?)");
            stt.setString(1, moto.marca);
            stt.setString(2, moto.modelo);
            stt.setString(3, moto.ano);
            stt.setDouble(4, moto.preco);
            stt.setInt(5, moto.cilindradas);

            stt.executeUpdate();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public List<Carro> printCarros() {
        try {
                var stt = connection.createStatement();
                var result = stt.executeQuery("SELECT * FROM carros");

                var carros = new ArrayList<Carro>();

                while (result.next()) {
                    var carro = new Carro();
                    carro.marca = result.getString("marca");
                    carro.modelo = result.getString("modelo");
                    carro.ano = result.getString("ano");
                    carro.numeroPortas = result.getInt("numeroPortas");
                    carro.preco = result.getDouble("preco");
                    carros.add(carro);
            }
                return carros;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Moto> printMotos() {
        try {
            var stt = connection.createStatement();
            var result = stt.executeQuery("SELECT * FROM motos");

            var motos = new ArrayList<Moto>();

            while (result.next()) {
                var moto = new Moto();
                moto.marca = result.getString("marca");
                moto.modelo = result.getString("modelo");
                moto.ano = result.getString("ano");
                moto.cilindradas = result.getInt("cilindradas");
                moto.preco = result.getDouble("preco");
                motos.add(moto);
            }
            return motos;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
