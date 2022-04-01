
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class Penalidade {
    
    int pen1;  int pen2;   int pen3;  int pen4; int per1;  int per2;  int per3; int per4;

    public Penalidade(){
        getPenalidePercent(pen1,pen2,pen3,pen4,per1,per2,per3,per4);
    }


    public  int getPen1(){
        return this.pen1;
    }
    public  void setPen1(int pen1){
        this.pen1 = pen1;
    }

    public  int getPen2(){
        return this.pen2;
    }
    public  void setPen2(int pen2){
        this.pen2 = pen2;
    }

    public  int getPen3(){
        return this.pen3;
    }
    public  void setPen3(int pen3){
        this.pen3 = pen3;
    }

    public  int getPen4(){
        return this.pen4;
    }
    public  void setPen4(int pen4){
        this.pen4 = pen4;
    }


    public  int getPer1(){
        return this.per1;
    }
    public  void setPer1(int per1){
        this.per1 = per1;
    }

    public  int getPer2(){
        return this.per2;
    }
    public  void setPer2(int per2){
        this.per2 = per2;
    }

    public  int getPer3(){
        return this.per3;
    }
    public  void setPer3(int per3){
        this.per3 = per3;
    }

    public  int getPer4(){
        return this.per4;
    }
    public  void setPer4(int per4){
        this.per4 = per4;
    }


    
    public  void getPenalidePercent(int pen1,int pen2,int pen3,int pen4,int per1,int per2,int per3,int per4){

        StringBuilder sb  = new StringBuilder();        
        sb.append(readArqPenealidade("TempoEPercent.txt"));

        String [] temp  = sb.toString().split("\n");
        sb.delete(0,sb.length());

        for(int i = 1; i < temp.length;i++){
            sb.append(temp[i].toString());
            sb.append(":");
        }
        sb.deleteCharAt(sb.length()-1);
       
       String [] arqsplit = sb.toString().split(":");    
        for(int i = 0; i < arqsplit.length; i++){
            if(arqsplit[i].equals("CL2")){
                setPen1(Integer.parseInt(arqsplit[i + 1]));
                setPer1(Integer.parseInt(arqsplit[i + 2]));   
            }
            else if(arqsplit[i].equals("CL3")) {
                setPen2(Integer.parseInt(arqsplit[i + 1]));
                setPer2(Integer.parseInt(arqsplit[i + 2]));  
            }
            else if(arqsplit[i].equals("MR")){
                setPen3(Integer.parseInt(arqsplit[i + 1]));
                setPer3(Integer.parseInt(arqsplit[i + 2]));  
            }
            else if(arqsplit[i].equals("HD")){
                setPen4(Integer.parseInt(arqsplit[i + 1]));
                setPer4(Integer.parseInt(arqsplit[i + 2]));  
            }
        }
    }

    public  String readArqPenealidade(String nomearq){
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
}





