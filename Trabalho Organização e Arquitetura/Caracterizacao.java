//definir tamanho de endereços no parameto 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class Caracterizacao{

    public Caracterizacao() throws IOException{
        String instrucoes = readArq("programa.txt");
        writeArq("InstrucoesCache.txt",instrucoes); 
        //getPenalidePercent(this.pen1,this.pen2,this.pen3,this.pen4,this.per1,this.per2,this.per3,this.per4);

    } 

    public String readArq(String nomearq){
        String linha = "";
        StringBuilder sb = new StringBuilder();
        try {
            FileReader arq = new FileReader(nomearq);
            BufferedReader lerArq = new BufferedReader(arq);
            while (linha != null) {
               sb.append(linha + "\n" );
               linha = lerArq.readLine(); 

            }
            arq.close();
          } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo.\n",
                e.getMessage());
          }         
        return sb.toString();
    }  
    
    
    public void writeArq(String nomearq, String instrucoes) throws IOException{

        FileWriter fw = new FileWriter(nomearq);  
        BufferedWriter bw = new BufferedWriter(fw);  
        Scanner sc = new Scanner(System.in);

        StringBuilder sb  = new StringBuilder();        
        sb.append(instrucoes);

        String [] temp  = sb.toString().split("\n");

        sb.delete(0,sb.length());

        for(int i = 1; i < temp.length;i++){
            sb.append(temp[i].toString());
            sb.append(":");
        }

        String [] arqsplit = sb.toString().split(":");

        int size = 0;  //tamanho 
        int jilinha = 0; int ji = 0; //jump
        int bilinha = 0; int bijump = 0; int bipercentrate = 0; //jump com taxa de acerto  

        for(int i = 0; i < arqsplit.length; i++){
            if(arqsplit[i].equals("ep")){
                size = Integer.parseInt(arqsplit[i + 1]);        
            }
            else if(arqsplit[i].equals("ji")) {
                jilinha = Integer.parseInt(arqsplit[i + 1]);
                ji = Integer.parseInt(arqsplit[i + 2]);
            }
            else if(arqsplit[i].equals("bi")){
                bilinha = Integer.parseInt(arqsplit[i + 1]);
                bijump =  Integer.parseInt(arqsplit[i + 2]);
                bipercentrate = Integer.parseInt(arqsplit[i + 3]);
            }
        }

        if(jilinha == bilinha){
            System.out.println("ERRO: Dois comandos na mesma linha.");
            System.exit(1);
        }

        System.out.println("Informe a quantidade maxima de numeros de endereco ?");
        int num_enderecos = sc.nextInt();

        Random gerador = new Random();
        int count = 0;
        for(int i = 0; i <= size; i++){
            //System.out.println(i);
            bw.write(String.valueOf(i));
            bw.newLine();
            if(i == bilinha){
                if(bipercentrate == 100){
                    i = bijump;
                }
                else if(bipercentrate == 0){

                }
                else{
                    if(bipercentrate <=80){
                        if(gerador.nextInt(1000/bipercentrate) == 1){
                            i  = bijump;
                        }
                    }
                    else if (bipercentrate >= 90 && bipercentrate <=94){
                        if(gerador.nextInt(4) == 1){
                            i  = bijump;
                        }
                    }
                    else if (bipercentrate >= 95){
                        if(gerador.nextInt(2) == 1){
                            i  = bijump;
                        }
                    }
                }
            }
            if(i == jilinha){
                i = ji-1;
            }
            count++;
            if(count >= num_enderecos){
                break;
            }

        }
        bw.close();
        fw.close();
    }
    
}




