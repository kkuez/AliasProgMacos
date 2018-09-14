package mdoe.example.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.util.Scanner;


public class App
{

    public static void main( String[] args )
    {
        String home = System.getProperty("user.home");         //Systemunabhaeging homefolder finden https://stackoverflow.com/questions/585534/what-is-the-best-way-to-find-the-users-home-directory-in-java

        File aliasopt = new File(home+"/alias.txt");     //Checken ob Optionsdtei existiert
       if (aliasopt.exists()){
           System.out.println("Starte Start-Funktion");

           start();
       }else{
           System.out.println("Optionsdatei schreiben");
           String optliste[]=new String[20];

           for(int i =0;i<20;i++){
               if (i>9){
                   optliste[i]="c"+(i-10)+": Keine alias-Zuweisung vorhanden!";
               }else{
              optliste[i]="";}
               System.out.println(optliste[i]);
           }

           String optlistestr = "";
           for (int i =0;i<optliste.length;i++){
               optlistestr = optlistestr +"\n"+optliste[i];

           }

           System.out.println("DATEI: \n"+optlistestr);

            try {

                File optdatei = new File(home+"/alias.txt");
                optdatei.createNewFile();                                   //Opt-Datei erstellen

                System.out.println("Neue Options-Datei geschrieben.");
            }catch(IOException e){
                System.out.println("OOOPS: "+e);
            }

           try {
               File optdatei = new File(home+"/alias.txt");         //Datei schreiben https://stackoverflow.com/questions/20509114/bufferedwriter-not-writing-to-text-file
               FileWriter fw = new FileWriter(optdatei);
               BufferedWriter bw = new BufferedWriter(fw);
               bw.write(optlistestr);
               bw.close();


           }catch(IOException e){

               System.out.println("OOOPS SCHREIEBN: "+e);
           }

           try {

               File optdatei = new File(home+"/.bash_aliases");
               optdatei.createNewFile();                                   //bashaliases-Datei erstellen

               System.out.println("Neue .bash_aliases-Datei geschrieben.");
           }catch(IOException e){
               System.out.println("OOOPS: "+e);
           }
       }
    }

    public static String[] einlesen() throws IOException{
        System.out.println("Einlesen-Funktion gestartet");
        String home  = System.getProperty("user.home");
        String[] opteinstr = new String[21];
        Scanner optein = new Scanner(new File(home+"/alias.txt"));      //JfD, S. 215
        for(int i =0;i<21;i++){
            opteinstr[i] = optein.nextLine();                          //mit nextLine() jeweils die nächste Zeile auslesen
        }


       optein.close();
        return (opteinstr);
    }



    public static void start() {

    System.out.println("Start-Funktion gestartet");

    //Vorhandene Opt-Datei einlesen
    String[] optdateiarray = new String[21];
    try {                                                                       //einlesen() wirft evtl. Fehler, daher trycatch
        optdateiarray= einlesen();
    }catch(IOException e){
        System.out.println("OOOPS LESEN: "+e);
    }


        //UI Start
        System.out.print("################\nAliases Programm\n################\n\nFolgende aliases sind bisher den Bezeichnungen c0...c9 zugewiesen:\n\n");
        for(int i=11; i<21;i++){
            System.out.print(optdateiarray[i]+"\n");
        }
        System.out.println("Zuweisungen ändern?(y/n)");                   //Abfrage Zuweisungen ändern ja nein
        Scanner aendern = new Scanner(System.in);
        String aendernstr = aendern.next();

        switch(aendernstr){
            case "y":
                System.out.print("Welche Zuweisung soll geändert werden? (c0...c9 tippen)");
                String zuwaendernstr = "asd";
                Scanner zuwaendern=new Scanner(System.in);
                zuwaendernstr = zuwaendern.next();


                switch(zuwaendernstr){
                    case "c0":
                        zuweisungaendern("0");
                        break;
                    case "c1":
                        zuweisungaendern("1");
                        break;
                    case "c2":
                        zuweisungaendern("2");
                        break;
                    case "c3":
                        zuweisungaendern("3");
                        break;
                    case "c4":
                        zuweisungaendern("4");
                        break;
                    case "c5":
                        zuweisungaendern("5");
                        break;
                    case "c6":
                        zuweisungaendern("6");
                        break;
                    case "c7":
                        zuweisungaendern("7");
                        break;
                    case "c8":
                        zuweisungaendern("8");
                        break;
                    case "c9":
                        zuweisungaendern("9");
                        break;
                        default:
                            break;
                }
                break;
            case "n":
                System.out.print("Schliessen...");
                System.exit(0);
                break;

            default:
                break;

        }

    }


    public static String[] zuweisungaendern(String cx){

        String home  = System.getProperty("user.home");

        System.out.print("Bitte alias-Befehl für c"+cx+" eingeben:");
        Scanner aliasc = new Scanner(System.in);
        String aliascstr = aliasc.next();
        String[] ret = new String[2];
        ret[0]=cx;                                                  //zurückgeben: 0=platz, 1= der befehl
        ret[1]=aliascstr;

        System.out.print("Geschrieben c"+cx+": "+aliascstr);

        return(ret);
    }



}