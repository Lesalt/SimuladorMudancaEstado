package ferramentas_aux;

import java.util.Random;

public class random_num {
    private static Random ran = new Random();
    public static int ran_Int(int start, int num){

        int valor = start + ran.nextInt(num + 1);

            return valor;
        }

}
