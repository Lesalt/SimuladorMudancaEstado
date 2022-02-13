package gerador;

import controle.controle_processo;
import Processo.Processo;

public class gerador_de_processos {
    private static gerador_de_processos instancia;

    private gerador_de_processos() {
    }

    public static gerador_de_processos getInstancia() {
        if (instancia == null) {
            instancia = new gerador_de_processos();
        }

        return instancia;
    }

    public Processo gerarProcesso(int PID, int range) {
        controle_processo registro;
        Processo processo;

        registro = new controle_processo(PID);
        processo = new Processo(registro, range);

        return processo;
    }
}
