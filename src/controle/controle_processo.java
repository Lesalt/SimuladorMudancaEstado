package controle;
import info_estado.estado;
public class controle_processo {
    private estado EP;
    private int PID;
    private int TP;
    private int CP;
    private int nES;
    private int nCPU;

    public controle_processo(int PID){
        this.nES = 0;
        this.nCPU = 0;
        this.PID = PID;
        this.TP = 0;
        this.CP = 0;
        this.EP = estado.Novo;
    }

    public int getnES(){
        return this.nES;
    }

    public int getnCPU(){
        return this.nCPU;
    }

    public estado getEP(){
        return this.EP;
    }

    public int getPID(){
        return this.PID;
    }

    public int getTP(){
        return this.TP;
    }

    public int getCP(){
        return this.CP;
    }

    public void setnES(int nES) {
        this.nES = nES;
    }

    public void setnCPU(int nCPU) {
        this.nCPU = nCPU;
    }

    public void setEP(estado EP) {
        this.EP = EP;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }


    @Override
    public String toString(){
        return String.format("PID: %d\tEP: %s\tTP: %d\tCP: %d\tnES: %d\tnCPU: %d\t",
                this.PID, this.EP.toString(), this.TP, this.CP, this.nES, this.nCPU);
    }
}
