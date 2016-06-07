package tastatur;

import java.util.Scanner;

public class main{
	public static void main(String args[])
	{
	Scanner sc = new Scanner(System.in);
	System.out.println("Eingabe: ");
	String eingabe = sc.next();
	System.out.println("Das wirde eingegeben: " + eingabe);
	String gross = eingabe.toUpperCase();
	System.out.println("Umgewandelt: " + gross);
	}
	
}