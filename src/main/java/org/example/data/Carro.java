package org.example.data;

public class Carro extends Veiculo{

    public int numeroPortas;

    @Override
    public String toString() {
        return super.toString() + ", Número de portas: " + numeroPortas;
    }
}
