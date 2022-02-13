package ferramentas_aux;

import java.util.*;

public class Referencia_de_Contexto {
    private LinkedList<Object> obj;

    public Referencia_de_Contexto(){
        this.obj = new LinkedList();
    }

    public void add(Object obj){
        this.obj.add(obj);
    }

    public Object getObject(int id){
        return this.obj.get(id);
    }
}
