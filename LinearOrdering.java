import java.io.*;
import java.util.Arrays;

public class LinearOrdering {
    public static int max = 0, min = 0;

    public static  int max_value(int[] ar){
        for(int i = 0; i < ar.length; i++){
            if(ar[i] > max){
                max = ar[i];
            }
        }
        return max;
    }

    public static  int min_value(int[] ar){
        for(int i = 0; i < ar.length; i++){
            if(ar[i] < min){
                min = ar[i];
            }
        }
        return min;
    }

    public static int getDigit(int value, int divisor){

        return (value/divisor)%10;
    }

    public static int[] radix_sort(int[] ar){
        max = max_value(ar);
        int[] a = ar.clone(), b = new int[a.length], c = new int[10];
        int digAux;
        int digits_maxValue = Integer.toString(max).length();
        int divisor = 1;

        while(digits_maxValue > 0){

            //inicializa c com zeros
            for(int i = 0; i < c.length; i++){
                c[i] = 0;
            }
            //cria vetor de frequencias
            for(int i = 0; i <= (a.length-1); ++i){
                //atribuir a uma variavel o valor do digito em questão de a[i]
                digAux = getDigit(a[i], divisor);
                c[digAux] = c[digAux] + 1;

            }
            //System.out.println(Arrays.toString(c) + "\t C em dig " + digits_maxValue);

            //incrementa vetor de frequencias para obter as posições
            for(int i = 0; i < c.length -1; i++){
                c[i+1] = c[i] + c[i+1];
            }

            //System.out.println(Arrays.toString(c) + "\t C frequences ");

            //Preenche vetor B ordenando
            for(int i = (a.length - 1); i >= 0; i--){
                digAux = getDigit(a[i], divisor);
                b[c[digAux]-1] = a[i];
                c[digAux]--;
            }

            a = b.clone();
            //Altera os valores de divisor p pegar o prox digito
            divisor *= 10;
            //decrementa o valor de digts_maxValues
            digits_maxValue--;
        }

        //devolve numeros negativos, caso haja
        if(min < 0){
            for(int i = 0; i < b.length; i++){
                b[i] = b[i] - (-min);
            }
        }
        return b;
    }

    public static int[] cout_sort(int[] ar){
        max = max_value(ar);
        int[] a = ar.clone(), b = new int[a.length], c = new int[max+1];

         //inicializa c com zeros
         for(int i = 0; i < c.length; i++){
             c[i] = 0;
         }
         //cria vetor de frequencias
         for(int i = 0; i <= (a.length-1); ++i){
             //atribuir a uma variavel o valor do digito em questão de a[i]
             c[a[i]] = c[a[i]] + 1;
         }
         //System.out.println(Arrays.toString(c) + "\t C em dig " + digits_maxValue);

         //incrementa vetor de frequencias para obter as posições
         for(int i = 0; i < c.length -1; i++){
             c[i+1] = c[i] + c[i+1];
         }

         //System.out.println(Arrays.toString(c) + "\t C frequences ");

         //Preenche vetor B ordenando
         for(int i = (a.length - 1); i >= 0; i--){
             b[c[a[i]]-1] = a[i];
             c[a[i]]--;
         }

        //devolve numeros negativos, caso haja
        if(min < 0){
            for(int i = 0; i < b.length; i++){
                b[i] = b[i] - (-min);
            }
        }
         return b;
    }

    public static void main(String[] args) throws IOException {
        String path = "/home/rebeca/Área de Trabalho/ORD/instancias-numericas/instancias-num/num.1000.2.in";
        //String path = "/home/rebeca/IdeaProjects/radixsort/num.1000.1.in";
        BufferedReader br = new BufferedReader(new FileReader(path));

        //int size = Integer.parseInt(br.readLine());
        int[] vector = new int[Integer.parseInt(br.readLine())];

        for(int i = 0; i < vector.length; i++){
            vector[i] = Integer.parseInt(br.readLine());
        }

        //encontra menor valor para tratar possíveis valores negativos
        min = min_value(vector);

        //tratamento caso haja numeros negativos
        if(min < 0){
            for(int i = 0; i < vector.length; i++){
                vector[i] = vector[i] + (-min);
            }
        }
        //faz radix sort
        long start_radix = System.nanoTime();
        int[] radix_vector = radix_sort(vector);
        long elapsedTime_radix = System.nanoTime() - start_radix;

         //faz cout sort
        long start_cout = System.nanoTime();
        int[] cout_vector = cout_sort(vector);
        long elapsedTime_cout = System.nanoTime() - start_cout;

        System.out.println("--------RADIX SORT--------");
        System.out.println(Arrays.toString(radix_vector));
        System.out.println("tempo em ns do radix sort: " + elapsedTime_radix);

        System.out.println("--------COUT SORT--------");
        System.out.println(Arrays.toString(cout_vector));
        System.out.println("tempo em ns do cout sort: " + elapsedTime_cout);


    }
}
