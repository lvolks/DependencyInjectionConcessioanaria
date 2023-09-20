package org.example;

import org.example.armazenamento.Armazenamento;
import org.example.data.Carro;
import org.example.data.Moto;
import org.example.data.Veiculo;

import java.util.List;

public class Concessionaria {
    private Armazenamento armazenamento;

    public Concessionaria(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void salvarCarro(Carro carro) {
            armazenamento.salvarCarro(carro);
    }

    public void salvarMoto(Moto moto) {
        armazenamento.salvarMoto(moto);
    }

    public void printarCarros() {
        List<Carro> carros = armazenamento.printCarros();

        System.out.println("\n------------LISTA DE CARROS------------");
        for (Carro carro : carros) {
            System.out.println(carro.toString());
        }
    }

    public void printarMotos(){
        List<Moto> motos = armazenamento.printMotos();

        System.out.println("\n------------LISTA DE MOTOS------------");
        for (Moto moto : motos) {
            System.out.println(moto.toString());
        }
    }
}
