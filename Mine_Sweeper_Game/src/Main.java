/*
 ███▄ ▄███▓▓█████  ███▄    █  ▄▄▄        ██████▓██   ██▓
▓██▒▀█▀ ██▒▓█   ▀  ██ ▀█   █ ▒████▄    ▒██    ▒ ▒██  ██▒
▓██    ▓██░▒███   ▓██  ▀█ ██▒▒██  ▀█▄  ░ ▓██▄    ▒██ ██░
▒██    ▒██ ▒▓█  ▄ ▓██▒  ▐▌██▒░██▄▄▄▄██   ▒   ██▒ ░ ▐██▓░
▒██▒   ░██▒░▒████▒▒██░   ▓██░ ▓█   ▓██▒▒██████▒▒ ░ ██▒▓░
░ ▒░   ░  ░░░ ▒░ ░░ ▒░   ▒ ▒  ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░  ██▒▒▒
░  ░      ░ ░ ░  ░░ ░░   ░ ▒░  ▒   ▒▒ ░░ ░▒  ░ ░▓██ ░▒░
░      ░      ░      ░   ░ ░   ░   ▒   ░  ░  ░  ▒ ▒ ░░
       ░      ░  ░         ░       ░  ░      ░  ░ ░
                                                ░ ░*/
												
import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		MineSweeper game;
		int	line;
		int	row;

		System.out.println("\n\t  *****************************************\n \t\tMayin Tarlasina Hoşgeldiniz ! \n\t  *****************************************");
		do
		{
			System.out.println("\t <> Haritanin boyutlarini belirleyin lütfen. <>\n");
			System.out.print("Satir sayisi: ");
			line = input.nextInt();
			System.out.print("Sütun sayisi: ");
			row = input.nextInt();
			if ((line < 2 || row < 2))
				System.out.println("--------------------------------------\nHarita en az 2x2 boyutunda olamlidir !\n--------------------------------------");
			else if ((line > 73 || row > 73))
				System.out.println("--------------------------------------------\nHarita en az 73x73 boyutundan büyük olamaz !\n--------------------------------------------");
		} while ((line < 2 || row < 2) || (line > 73 || row > 73));
		game = new MineSweeper(line, row, input);
		game.startGame();
		input.close();
	}
}
