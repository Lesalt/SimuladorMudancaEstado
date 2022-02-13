package Processo;
import info_estado.estado;
import controle.controle_processo;
public class Processo {
    private final controle_processo registro;
    private final int tamanho;

    public Processo(controle_processo registro, int tamanho){
        this.registro = registro;
        this.tamanho = tamanho;
    }

    public controle_processo getRegistro(){
        return this.registro;
    }

    public estado getEstado(){
        return this.registro.getEP();
    }

    public void setEstado(estado estado){
        this.registro.setEP(estado);
    }

    public int getInstrucao(){
        int TP = this.registro.getTP();

        this.registro.setCP(TP + 1);

        return TP;
    }

    public boolean completou(){
        return (this.tamanho - this.registro.getCP() < 0);
    }

    public void contador_de_Ciclo(){
        int TP = this.registro.getTP();

        this.registro.setTP(TP + 1);
    }

    public void contador_de_ES(){
        int nES = this.registro.getnES();

        this.registro.setnES(nES + 1);
    }

    public void contador_de_CPU(){
        int nCPU = this.registro.getnCPU();

        this.registro.setnCPU(nCPU + 1);
    }

    public String toStringCiclo(){
        return String.format("Ciclo [Processo:%d] \tCP:%d", this.registro.getPID(), this.registro.getCP());
    }

    @Override
    public String toString(){
        return String.format("Tamanho: %d\n\tcontrole_processo - %s", tamanho, registro.toString());
    }
}
