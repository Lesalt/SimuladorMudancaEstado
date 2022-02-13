package Running;
import memoria.controle_Fila;
import info_estado.estado;
import Processo.Processo;
public class Escalonador {
    private final static int FILA_NOVO = 0;
    private final static int FILA_PRONTO = 1;
    private final static int FILA_BLOQUEADO = 2;


    private Processador processador;
    private controle_Fila fila_dos_processos;


    protected  Escalonador(Processador processador){
        this.processador = processador;

        this.startFilaProcesso();
    }

    private void startFilaProcesso(){
        this.fila_dos_processos = new controle_Fila(3);
    }

    private void escalona_novo_processo(Processo processo){
        this.fila_dos_processos.adicionarProcesso(this.FILA_NOVO, processo);
    }

    private void escalona_process_pronto(Processo processo){
        this.fila_dos_processos.adicionarProcesso(this.FILA_PRONTO, processo);
    }

    private void escalonarBloqueado(Processo processo){
        this.fila_dos_processos.adicionarProcesso(this.FILA_BLOQUEADO, processo);
    }


    public void escalonarProcesso(Processo processo){
        if(processo.getRegistro().getEP() == estado.Novo){

            this.escalona_novo_processo(processo);

        }else if(processo.getRegistro().getEP() == estado.Pronto){

            this.escalona_process_pronto(processo);

        }else if(processo.getRegistro().getEP() == estado.Bloqueado){

            this.escalonarBloqueado(processo);

        }else{

            return;
        }
    }

    private void prepararProcesso(Processo processo){

        processo.setEstado(estado.Pronto);

        this.escalonarProcesso(processo);
    }


    private void rotinaProcessador(Processo processo){
        processo = this.processador.processar(processo);

        this.escalonarProcesso(processo);
    }

    private Processo rotinaEscalonamento(int id_fila) throws NullPointerException {
        Processo processo;

        processo = this.fila_dos_processos.retirarProcesso(id_fila);

        if(processo != null)
            return processo;

        throw new NullPointerException("Nenhum processo escalonado em (num. fila): " + id_fila);
    }


    public void rotina(){
        try{
            this.rotinaProcessador(
                    this.rotinaEscalonamento(this.FILA_BLOQUEADO));
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        try{
            this.rotinaProcessador(
                    this.rotinaEscalonamento(this.FILA_PRONTO));
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }

        try{

            this.prepararProcesso(
                    this.rotinaEscalonamento(this.FILA_NOVO));
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean temProcesso(){

        if(!this.fila_dos_processos.estaVazia(this.FILA_NOVO) ||
                !this.fila_dos_processos.estaVazia(this.FILA_PRONTO) ||
                !this.fila_dos_processos.estaVazia(this.FILA_BLOQUEADO))
            return true;

        return false;
    }
}
