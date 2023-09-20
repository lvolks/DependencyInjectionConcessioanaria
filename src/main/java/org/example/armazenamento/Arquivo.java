package org.example.armazenamento;

import org.example.data.Carro;
import org.example.data.Moto;
import org.example.data.Veiculo;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;


public class Arquivo implements Armazenamento {
    private File file = new File("veiculos.txt");
    private List<Carro> carros = new ArrayList<>();
    private List<Moto> motos = new ArrayList<>();

    public Arquivo() {
        createArquivoTxt();
        readArquivoTxt();
    }

    private void createArquivoTxt() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo dados.txt criado");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void readArquivoTxt() {
        try {
            Scanner scanner = new Scanner(file);

            String tipo = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.equals("[CARROS]")) {
                    tipo = "carro";
                } else if (line.equals("[MOTOS]")) {
                    tipo = "moto";
                } else if (tipo != null){
                    Veiculo veiculo;
                    String[] split = line.split(",");

                    if (tipo.equals("carro")) {
                        Carro carro = new Carro();
                        veiculo = carro;
                        carros.add(carro);

                        carro.numeroPortas = Integer.parseInt(split[4]);
                    } else {
                        Moto moto = new Moto();
                        veiculo = moto;
                        motos.add(moto);

                        moto.cilindradas = Integer.parseInt(split[4]);
                    }


                    veiculo.marca = split[0];
                    veiculo.modelo = split[1];
                    veiculo.ano = split[2];
                    veiculo.preco = Double.parseDouble(split[3]);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String serializaVeiculo(Veiculo veiculo) {
        return veiculo.marca + "," + veiculo.modelo + "," + veiculo.ano + "," + veiculo.preco;
    }

    private void salvarDadosTxt() {
        try {
            List<String> lines = new ArrayList<>();

            lines.add("[CARROS]");

            for (Carro carro : carros) {
                lines.add(serializaVeiculo(carro) + "," + carro.numeroPortas);
            }

            lines.add("[MOTOS]");

            for (Moto moto : motos) {
                lines.add(serializaVeiculo(moto) + "," + moto.cilindradas);
            }

            FileWriter fw = new FileWriter(file);

            fw.write(String.join("\n", lines));
            fw.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void salvarCarro(Carro carro) {
        carros.add(carro);
        salvarDadosTxt();
    }

    @Override
    public List<Carro> printCarros() {
        return carros;
    }

    @Override
    public void salvarMoto(Moto moto) {
        motos.add(moto);
        salvarDadosTxt();
    }

    @Override
    public List<Moto> printMotos() {
        return motos;
    }
}
