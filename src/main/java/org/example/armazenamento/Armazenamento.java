package org.example.armazenamento;


import org.example.data.Carro;
import org.example.data.Moto;

import java.util.List;

public interface Armazenamento {

    void salvarCarro(Carro carro);
    List<Carro> printCarros();

    void salvarMoto(Moto moto);
    List<Moto> printMotos();
}
