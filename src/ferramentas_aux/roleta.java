package ferramentas_aux;

public abstract class roleta {
    private int valor;

    public abstract int probalidade();

    public boolean sortear(){
        valor = random_num.ran_Int(0, 99);
        return valor < probalidade();
    }

}
