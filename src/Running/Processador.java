package Running;
import ferramentas_aux.Referencia_de_Contexto;
import ferramentas_aux.cinco_por_cento;
import ferramentas_aux.trinta_por_cento;
import ferramentas_aux.roleta;
import Processo.Processo;
import info_estado.estado;
public class Processador {

    private final byte sorteador_cinco = 0;
    private final byte sorteador_trinta = 1;

    private Referencia_de_Contexto roleta;


    private static final byte IOtoDo = 0;
    private static final byte processtoDo = 1;

    private Escalonador escalonador;
    private int quantum;

    private static Processador instancia;

    private Processador(int quantum){
        this.quantum = quantum;

        try{
            this.escalonador = new Escalonador(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        this.roleta = new Referencia_de_Contexto();
        this.roleta.add(new trinta_por_cento());
        this.roleta.add(new cinco_por_cento());
    }

    public static Escalonador getInstancia(){
        if(instancia == null)
            instancia = new Processador(1000);

        return instancia.escalonador;
    }


    private Processo validarEstado(Processo processo, estado estado){
        if(estado == estado.Pronto){
            this.trocarContexto(processo, estado.Executando);
            return processo;
        }

        roleta sorteador;

        if(estado == estado.Bloqueado){
            sorteador = (trinta_por_cento)roleta.getObject(this.sorteador_trinta);

            if(sorteador.sortear())
                this.trocarContexto(processo, estado.Pronto);
        }

        return processo;
    }

    private byte sortearInstrucao(){
        roleta sorteador;

        sorteador =(cinco_por_cento)roleta.getObject(this.sorteador_cinco);

        if(sorteador.sortear())
            return this.IOtoDo;

        return this.processtoDo;
    }

    private void processarInstrucao(Processo processo, byte tipoInstrucao) throws Exception{
        if(tipoInstrucao == this.processtoDo){
            processo.getInstrucao();
            return;
        }
        else if(tipoInstrucao == this.IOtoDo){
            processo.getInstrucao();
            processo.contador_de_ES();

            this.trocarContexto(processo, estado.Bloqueado);
        }
        else
            throw new Exception("Essa instrução de processo não foi definida.");
    }

    private void trocarContexto(Processo processo, estado estado){
        estado oldState;
        oldState = processo.getEstado();

        processo.setEstado(estado);

        System.out.println(processo.toString());
        System.out.println(".\tTroca de Contexto do estado" + oldState + " para o estado" + estado);
    }

    public Processo processar(Processo processo){

        this.validarEstado(processo, processo.getEstado());
        if(processo.getEstado() != estado.Executando){
            return processo;
        }

        processo.contador_de_CPU();

        int contadorQuantum = 0;


        while(processo.getEstado() == estado.Executando){


            processo.contador_de_Ciclo();
            System.out.println(processo.toStringCiclo());

            try{
                this.processarInstrucao(processo, this.sortearInstrucao());
            }catch(Exception e){
                System.out.println(e.getMessage());
                break;
            }

            if(processo.completou()){
                this.trocarContexto(processo, estado.Terminado);

                System.out.println(processo.toString() + "\n\tTerminado!");
                break;
            }

            contadorQuantum++;
            if(contadorQuantum >= this.quantum)
                break;
        }

        estado estado;
        estado = processo.getEstado();
        if(estado != estado.Terminado && estado != estado.Bloqueado)
            this.trocarContexto(processo, estado.Pronto);

        return processo;
    }
}
