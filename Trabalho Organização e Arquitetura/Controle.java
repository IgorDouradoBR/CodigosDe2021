import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public class Controle{
    static int hitcl2=0;
    static int hitcl3=0;
    static int hitmr=0;
    static int hithd=0;
    static int misscl2=0;
    static int misscl3=0;
    static int missmr=0;
    static int misshd=0;
    static Penalidade p;

    public static void main (String [] args)  throws IOException{
        Caracterizacao c = new Caracterizacao();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Quantos Bytes sua Cache vai ter?");
        int cache = sc.nextInt();


        System.out.println("Quantas vias sua Cache vai ter?");
        int vias = sc.nextInt();

        System.out.println("Qual o tamanho(Bytes) por palavra da sua cache vai ter?");
        int tamanhoPalavra = sc.nextInt();

        System.out.println("Qual o numero de palavras por bloco da sua cache vai ter?");
        int palavraPorBloco = sc.nextInt();

        System.out.println("Qual politica de substituicao voce quer utilizar? ");
        System.out.println("Digte LFU para Utilizar LFU e Digite Random para Utilizar Random");
        String metodo = sc.next();

        p = new Penalidade();
        MontaEstruturaCache(cache,vias,tamanhoPalavra,palavraPorBloco,metodo);
    }

    public static void MontaEstruturaCache (int tamCache,int qtdVias,int tamPalavraBytes,int numPalvraBlocos,String metodo) throws IOException{
        ArrayList<String> enderecos = ConverteBinario32Bits();
        
        ArrayList<String> tagArray = new ArrayList<>();
        ArrayList<String> palavraArray = new ArrayList<>();
        ArrayList<String> seletorConjArray = new ArrayList<>();



        int qntdBytesDadosLinhas = tamPalavraBytes * numPalvraBlocos; //Numero de bytes de dados por linha
        int tamPalavras = tamCache / (tamPalavraBytes * numPalvraBlocos);  //qtd de linhas
        int qntdLinhasCache = tamCache / qntdBytesDadosLinhas;
        int tamConj = qntdLinhasCache / qtdVias;
        numPalvraBlocos = numPalvraBlocos / 2;
        tamConj = tamConj / 2;
        int tamTag = 32 - numPalvraBlocos - tamConj;
        
        for(int i = 0; i < enderecos.size(); i++){
            StringBuilder sb  = new StringBuilder();    

            sb.append(enderecos.get(i).toString());
            String tag  = ""; String seletorConj = ""; String palavra = "";


        
            tag = sb.subSequence(0,tamTag).toString();
            seletorConj = sb.subSequence(tamTag,tamTag + tamConj).toString();
            palavra = sb.subSequence(tamTag + tamConj,tamTag + tamConj + numPalvraBlocos).toString();

            tagArray.add(tag);
            palavraArray.add(palavra);
            seletorConjArray.add(seletorConj);     
  
        }

        tamConj = tamConj * 2;

        // conjuntos associativos criados
                
        ArrayList<LinhaCache> conj = new ArrayList<>();

        for(int i = 0; i < qntdLinhasCache; i ++){
            LinhaCache lc = new LinhaCache(numPalvraBlocos,0);
            conj.add(lc);
        }

        int miss = 0;
        int hit = 0;
        int temptotal = 0;
        int cache1 = 0;
        int cache2 = 0;

        for(int i = 0; i < tagArray.size(); i++){
            for(int j = 0; j < conj.size(); j++){      
                if(containsConj(conj,tagArray.get(i)) == false){  
                      if(conj.get(j).getmemAssociativa() == -1){
                          conj.get(j).setmemAssociativa(Integer.parseInt(tagArray.get(i),2));
                          conj.get(j).somaCountAccess();
                          miss++;
                          temptotal += tempPen();
                          break;
                      }
                                 
                }
                else if(containsConj(conj,tagArray.get(i)) == true){
                    conj.get(j).somaCountAccess();
                    hit++;
                    temptotal++;
                    break;
                }                 
            } 
            
            
            boolean cheio = true; 

            for(int j = 0; j < conj.size(); j ++){
                if(conj.get(j).getmemAssociativa() == -1){
                    cheio = false;
                }
            }
            
            
            if(cheio){
                if(containsConj(conj,tagArray.get(i)) == true){
                    

                }
                else{
                    if(metodo.toUpperCase().equals("LFU")){
                        int indice = LFU(conj);
                        conj.get(indice).setmemAssociativa(Integer.parseInt(tagArray.get(i),2));
                        miss++;
                        temptotal += tempPen();
                    }
                    else{
                        int indice = randomMetod(conj);
                        conj.get(indice).setmemAssociativa(Integer.parseInt(tagArray.get(i),2));
                        miss++;
                        temptotal += tempPen();
                    }
                }
            }
          
            

        }

        System.out.println("Numero total de hits: " + hit);
        System.out.println("Numero total miss: " + miss);
        System.out.println("Tempo total: " + temptotal);
        System.out.println("Tempo medio: " + temptotal / (hit + miss));
        System.out.println("Numero de hits Cl2: "+ hitcl2);
        System.out.println("Numero de hits CL3: "+ hitcl3);
        System.out.println("Numero de hits MR :" + hitmr);
        System.out.println("Numero de hits HD : "+ hithd);
        System.out.println("Numero de miss CL2 "+ misscl2);
        System.out.println("Numero de miss CL3 "+ misscl3);
        System.out.println("Numero de miss MR  "+ missmr);


        System.out.println("Quantidade de Bytes por Linhas:" + qntdBytesDadosLinhas );
        System.out.println("Tamanho da palavras em bytes:" + tamPalavras );
        System.out.println("Qtd linhas cache:" + qntdLinhasCache );
        System.out.println("Numero de Conjuntos associativos: " + tamConj);
        System.out.println("Tag:"  + tagArray.get(3) +"   " +  "Bit Cj Associativo:" + seletorConjArray.get(3) + "   Palavra:"  
                            + palavraArray.get(3));
    }
 

    public static boolean containsConj(ArrayList<LinhaCache> conj,String tag){

        boolean teste = false;
            for(int x=0; x < conj.size();x++){
                if(conj.get(x).getmemAssociativa() == Integer.parseInt(tag,2)){
                    teste= true;
            } 
        }

        return teste;
    } 


    public static int randomMetod(ArrayList<LinhaCache> conj){
        Random gerador = new Random();   
        int indiceLinha = gerador.nextInt(conj.size());    
        return indiceLinha;
    }

    public static int LFU(ArrayList<LinhaCache> conj){

       int minValue = conj.get(0).getCountAccess();
       int indiceLinha = 0;

       for(int i = 0 ; i < conj.size(); i++ ){

          if(conj.get(i).getCountAccess() <minValue){
              minValue = conj.get(i).getCountAccess();
              indiceLinha = i;
          }
       }

      return indiceLinha;
     
    }

    public static ArrayList<String> ConverteBinario32Bits() throws IOException{
        
        StringBuilder sb  = new StringBuilder();    

        ArrayList<String> enderecos = readCaracterizacao();
    
        for(int i  =0; i < enderecos.size(); i++){
            sb.append(Integer.toBinaryString(Integer.parseInt(enderecos.get(i))));
            enderecos.set(i,sb.toString());
            sb.delete(0, sb.length());
        }

        sb.delete(0, sb.length());
        for(int i =0; i < enderecos.size(); i++){
            sb.append(enderecos.get(i));
            while(sb.length() < 32){
                
                sb.insert(0,"0");
            }
            enderecos.set(i,sb.toString());
            sb.delete(0, sb.length());
        }

       return enderecos;
    }

    public static ArrayList<String> readCaracterizacao() throws IOException{

        FileReader arq = new FileReader("InstrucoesCache.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        ArrayList<String> enderecos = new ArrayList<>();

        String linha = "";

        int count = 0;
        while (linha != null) {
                linha = lerArq.readLine(); 
                enderecos.add(linha);
                count++;

            }

        arq.close();
        enderecos.remove(enderecos.size()-1);
        count--;
        System.out.println("Quantidade de enderecos: " + count);
        return enderecos;
    }

    public static int tempPen() {
        Random random = new Random();

            if (random.nextInt(101) <= p.getPer1()) {
                hitcl2++;
                return p.getPen1();
            }
            else{misscl2++;}
            if (random.nextInt(101) <= p.getPer2()) {
                hitcl3++;
                return p.getPen1() + p.getPen2();
            }
            else{misscl3++;}
            if (random.nextInt(101) <= p.getPer3()) {
                hitmr++;
                return  p.getPen1() + p.getPen2() + p.getPen3();
            }
            else{missmr++;}
            hithd++;
            return p.getPen1() + p.getPen2() + p.getPen3() + p.getPen4();

    }
}