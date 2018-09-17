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

        File aliasopt = new File(home+"/.alias.txt");     //Checken ob Optionsdtei existiert
       if (aliasopt.exists()){
          // System.out.println("Starte Start-Funktion");

           start();
       }else{
         //  System.out.println("Optionsdatei schreiben");
           String optliste[]=new String[10];

           for(int i =0;i<10;i++){

                   optliste[i]="\n";

               System.out.println(optliste[i]);
           }

           String optlistestr = "";
           for (int i =0;i<optliste.length;i++){
               optlistestr = optlistestr +"\n"+optliste[i];

           }

           System.out.println("File: \n"+optlistestr);

            try {

                File optdatei = new File(home+"/.alias.txt");
                optdatei.createNewFile();                                   //Opt-Datei erstellen

                System.out.println("Written new Option-File.");
            }catch(IOException e){
                System.out.println("OOOPS: "+e);
            }

           try {
               File optdatei = new File(home+"/.alias.txt");         //Datei schreiben https://stackoverflow.com/questions/20509114/bufferedwriter-not-writing-to-text-file
               FileWriter fw = new FileWriter(optdatei);
               BufferedWriter bw = new BufferedWriter(fw);
               bw.write(optlistestr);
               bw.close();


           }catch(IOException e){

               System.out.println("OOOPS SCHREIEBN: "+e);
           }

           try {

	           File bash_aliases = new File(home + "/.bash_profile");
	           
	               FileWriter fw = new FileWriter(bash_aliases);
	               BufferedWriter bw = new BufferedWriter(fw);

	   		bw.write("\nalias setc='java -jar $HOME/AliasprogMacos/alias/target/alias-1.0-SNAPSHOT.jar'");
	               bw.close();
           }catch(IOException e){
               System.out.println("OOOPS: "+e);
           }
       }
    }

    public static String[] einlesen() throws IOException{
       // System.out.println("Einlesen-Funktion gestartet");
        String home  = System.getProperty("user.home");
        String[] opteinstr = new String[21];
        Scanner optein = new Scanner(new File(home+"/.alias.txt"));      //JfD, S. 215
        for(int i =0;i<10;i++){
            opteinstr[i] = optein.nextLine();                          //mit nextLine() jeweils die nächste Zeile auslesen
        }


       optein.close();
        return (opteinstr);
    }



    public static void start() {

    //System.out.println("Start-Funktion gestartet");

    //Vorhandene Opt-Datei einlesen
    String[] optdateiarray = new String[21];
    try {                                                                       //einlesen() wirft evtl. Fehler, daher trycatch
        optdateiarray= einlesen();
    }catch(IOException e){
        System.out.println("OOOPS LESEN: "+e);
    }


        //UI Start
        System.out.print("\n################\nAliases Program\n################\n\nFollowing commands are written to the shortcuts c0...c9:\n\n");
        for(int i=0; i<10;i++){
            System.out.print("c"+(i)+": "+ optdateiarray[i]+"\n");
        }
        System.out.println("\nChange shortcut?(y/n)");                   //Abfrage Zuweisungen ändern ja nein
        Scanner aendern = new Scanner(System.in);
        String aendernstr = aendern.next();

        switch(aendernstr){
            case "y":
                System.out.print("Which shortcut should be changed? (type c0...c9)");
                String zuwaendernstr = "asd";
                Scanner zuwaendern=new Scanner(System.in);
                zuwaendernstr = zuwaendern.next();


                switch(zuwaendernstr){
                    case "c0":
                        optdateispeichern(0, zuweisungaendern("0"));
                        break;
                    case "c1":
                        optdateispeichern(1,zuweisungaendern("1"));
                        break;
                    case "c2":
                        optdateispeichern(2,zuweisungaendern("2"));
                        break;
                    case "c3":
                        optdateispeichern(3,zuweisungaendern("3"));
                        break;
                    case "c4":
                        optdateispeichern(4,zuweisungaendern("4"));
                        break;
                    case "c5":
                        optdateispeichern(5,zuweisungaendern("5"));
                        break;
                    case "c6":
                        optdateispeichern(6,zuweisungaendern("6"));
                        break;
                    case "c7":
                        optdateispeichern(7,zuweisungaendern("7"));
                        break;
                    case "c8":
                        optdateispeichern(8,zuweisungaendern("8"));
                        break;
                    case "c9":
                        optdateispeichern(9,zuweisungaendern("9"));
                        break;
                        default:
                            break;
                }
                break;
            case "n":
                System.out.print("Close...");
                System.exit(0);
                break;

            default:
                break;

        }

    }


    public static String[] zuweisungaendern(String cx){

        String home  = System.getProperty("user.home");

        System.out.print("Type new command for alias-shortcut c"+cx+":");
        Scanner aliasc = new Scanner(System.in);
        String aliascstr = aliasc.nextLine();
        String[] ret = new String[2];
        ret[0]=cx;                                                  //zurückgeben: 0=platz, 1= der befehl
        ret[1]=aliascstr;

        System.out.print("Written c"+cx+": "+aliascstr+"\n");

        return(ret);
    }

    public static void optdateispeichern(int cx, String[] einarray) {
        String[] optdateiarray = new String[10];
        try {                                                                       //einlesen() wirft evtl. Fehler, daher trycatch
            optdateiarray = einlesen();
        } catch (IOException e) {
            System.out.println("OOOPS LESEN: " + e);
        }

        optdateiarray[(cx)] = einarray[1];

        String home = System.getProperty("user.home");
        File optdatei = new File(home + "/.alias.txt");
        try{
        FileWriter fw = new FileWriter(optdatei);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i =0;i<10;i++){
            bw.write(optdateiarray[i]+"\n");

            }

            bw.close();
    }catch(IOException e){
        System.out.print("OOOPS WRITE: "+e);

            }
                    // in .bash_aliases schreiben

        File bash_aliases = new File(home + "/.bash_profile");
        try{
            FileWriter fw = new FileWriter(bash_aliases);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i =0;i<10;i++){
                bw.write("\nalias c"+i+"='"+optdateiarray[i]+"'");

            }
		bw.write("\nalias setc='java -jar $HOME/AliasprogMacos/alias/target/alias-1.0-SNAPSHOT.jar'");
            bw.close();
        }catch(IOException e) {
            System.out.print("OOOPS WRITE: " + e);
        }

        System.out.print("Start new Terminalsession to let the changes have effect!\n");
    }


}
