package crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Decrypttext {
	public static char[] alphabet = "abcdefghijklmnopqrstuvwxyz.ABCDEFGHIJKLMNOPQRSTUVWXYZéèçàê=+@,;:-_?!0123456789()' ".toCharArray();
	public static double xnext;
    public static double xzero;
    
    public static int decrypt(int j){
    	xnext=0;
    	xzero=0.26;
        xnext=4*xzero*(1-xzero);
        for(int i=0;i<j;i++){
            xnext=4*xnext*(1-xnext);
        }
        return (int) (xnext*(alphabet.length));
    
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
    public static int[] file_to_table(File f){
		int[] t = new int[compter(f)];
		Scanner in = null; int i = 0;
		try{
		    in = new Scanner(f);
		}catch (Exception e){
			System.err.println("Erreur de scanner phase de file to table");
			System.exit(2);
		}
		while(in.hasNextInt()){
			t[i]=in.nextInt();
			i++;
		}
		return t;
	}
    public static void decrypterfile(File messagecrypter,File messagedecrypter){
    	PrintWriter out = null;
		try {
			out = new PrintWriter(messagedecrypter);
		} catch (FileNotFoundException e) {
			System.err.println("Erreur de printer");
			System.exit(2);
		}
        int []t = file_to_table(messagecrypter);
        for(int i=0;i<t.length;i++){
        	out.print(alphabet[decrypt(t[i])]);
        }
        out.close();
    }
    public static void main(String[] args) {
    	decrypterfile(new File ("outcrypt.txt"),new File ("outdecrypt.txt"));
    }
}
