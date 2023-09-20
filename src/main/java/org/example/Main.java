package org.example;


import org.example.armazenamento.Armazenamento;
import org.example.armazenamento.Arquivo;
import org.example.armazenamento.Banco;
import org.example.data.Carro;
import org.example.data.Moto;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Armazenamento armazenamento = new Banco();
        Arquivo arquivo = new Arquivo();

        Concessionaria concessionaria = new Concessionaria(armazenamento); // Lugar onde está aplicado a injeção de dependência, trocando o parâmetro "armazenamento" por "arquivo", para utilizar o método de salvar por um arquivo.

        Carro carro = Main.criarCarro();
        concessionaria.salvarCarro(carro);

        Moto moto = Main.criarMoto();
        concessionaria.salvarMoto(moto);

        concessionaria.printarCarros();
        concessionaria.printarMotos();
    }

    public static Carro criarCarro(){
        var carro = new Carro();
        var random = new Random();

        carro.modelo = "Modelo " + random.nextInt(200);
        carro.marca = "Marca " + random.nextInt(200);
        carro.ano = "2000";
        carro.preco = random.nextDouble(200000);
        carro.numeroPortas = 4;

        return carro;
    }

    public static Moto criarMoto(){
        var moto = new Moto();
        var random = new Random();

        moto.modelo = "Modelo " + random.nextInt(200);
        moto.marca = "Marca " + random.nextInt(200);
        moto.ano = "1500";
        moto.preco = Math.round(random.nextDouble(200000));
        moto.cilindradas = 1000;

        return moto;
    }

    }