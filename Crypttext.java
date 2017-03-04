package crypto;

import java.io.*;
import java.util.*;

public class Crypttext {
	public static double xnext;
    public static double xzero;
    public static int N;
    static Scanner s = null;
    static File msg = new File("in.txt");
    static File crypt = new File("outcrypt.txt");
    public static int encrypt(char lettre){
    	xnext=0;
        xzero=0.26;
        N=0;
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz.ABCDEFGHIJKLMNOPQRSTUVWXYZéèçàê=+@,;:-_?!0123456789()' ".toCharArray();
        int index=0;
        for (int i = 0; i < alphabet.length; i++) {
            if(alphabet[i]==lettre) index=i ;
        }
        xnext=4*xzero*(1-xzero);
        N=0;
       while(xnext<((double)(index)/alphabet.length) || xnext>((double)(index+1)/alphabet.length)){
           xnext=4*xnext*(1-xnext);
           N++;
       }
       return N;
    }
    public static int compter(File f){
		int c = 0;
		Scanner in=null;
		try{
			in=new Scanner(f);
			while(in.hasNext()){
				c++;
				in.next();
			}
		}catch (Exception e){
			System.err.println("Erreur de scanner phase de compter les mots");
			System.exit(1);
		}
		return c;
	}
    public static String[] file_to_table(File f){
		String[] t = new String[compter(f)];
		Scanner in = null; int i = 0;
		try{
		    in = new Scanner(f);
		}catch (Exception e){
			System.err.println("Erreur de scanner phase de file to table");
			System.exit(2);
		}
		while(in.hasNext()){
			t[i]=in.next();
			i++;
		}
		return t;
	}
    
    public static void crypterfile(File messagecrypter,File message){
    	PrintWriter out = null;
		try {
			out = new PrintWriter(messagecrypter);
		} catch (FileNotFoundException e) {
			System.err.println("Erreur de printer");
			System.exit(2);
		}
        String []t = file_to_table(message);
        for(int i=0;i<t.length;i++){
        	for(int j=0;j<t[i].length();j++){
        		out.print(encrypt(t[i].charAt(j)));
        	out.print(" ");
        	}
        	out.print(encrypt(' '));
        	out.print(" ");
        }
        out.close();

    }
    public static void main(String[] args) {
    	crypterfile(new File("outcrypt.txt"),new File("in.txt"));
    }

}
