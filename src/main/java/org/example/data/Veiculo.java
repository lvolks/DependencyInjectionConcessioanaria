package org.example.data;

public class Veiculo {
    public String marca;

    public String modelo;

    public String ano;

    public double preco;


    public String toString() {
        return "Marca do veículo: " + marca +
                ", Modelo do veículo: " + modelo +
                ", Ano do veículo: " + ano +
                ", Preço do veículo: " + preco;
    }
}
