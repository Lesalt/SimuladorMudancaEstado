package memoria;

import Processo.Processo;

public class controle_Fila {
    private Fila_de_Processos[] filas;

    public controle_Fila(int numFila_de_Processoss){
        this.filas = new Fila_de_Processos[numFila_de_Processoss];

        for(int i = 0; i < numFila_de_Processoss; i++){
            this.filas[i] = new Fila_de_Processos();
        }
    }

    public void adicionarProcesso(int fila, Processo processo){
        this.filas[fila].adicionarProcesso(processo);
    }

    public Processo retirarProcesso(int fila){
        return this.filas[fila].retirarProcesso();
    }

    public boolean estaVazia(int fila){
        return this.filas[fila].estaVazia();
    }
}
