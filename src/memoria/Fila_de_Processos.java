package memoria;
import Processo.Processo;
import java.util.*;
public class Fila_de_Processos {

    private LinkedList<Processo> fila;

    public Fila_de_Processos(){
        this.fila = new LinkedList();
    }

    public void adicionarProcesso(Processo processo){
        this.fila.add(processo);
    }

    public Processo retirarProcesso(){
        if(this.fila.isEmpty()) return null;

        return this.fila.pop();
    }

    public boolean estaVazia(){
        return this.fila.isEmpty();
    }

}
