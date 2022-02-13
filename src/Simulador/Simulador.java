package Simulador;
import Running.Escalonador;
import Running.Processador;
import Processo.Processo;
import gerador.gerador_de_processos;
public class Simulador {

    static Escalonador escalonador;

    static Processo[] process;

    public static void main(String[] args) {

        gerador_de_processos fb = gerador_de_processos.getInstancia();



        process = new Processo[10];

        for(int i = 0; i < 10; i++){

            process[i] = (Processo)fb.gerarProcesso(i, process_Range[i]);
            System.out.println(process[i].toString());
        }


        escalonador = (Escalonador) Processador.getInstancia();

        int i = 0;
        boolean escalonar = true;
        do{
            if(escalonar)
                escalonador.escalonarProcesso(process[i]);

            escalonador.rotina();

            if(++i == process.length){
                escalonar = false;
                i = 0;
            }
        }while(escalonador.temProcesso());

        System.out.println("\n\nFINISH:");

        for(Processo p: process) {

            System.out.println(p.toString());
        }

    }
    final static int[] process_Range = {10000, 5000, 7000, 3000, 3000, 8000, 2000, 5000, 4000, 10000};
}
