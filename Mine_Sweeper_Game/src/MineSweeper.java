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
import java.util.Random;

public class MineSweeper
{
	int	line;
	int	row;
	int	slctLine;
	int	slctRow;
	int	countMine;
	int	mineLoc[][];
	int	countBlock;
	int	zeroCheck;
	Scanner input;

	MineSweeper(int line, int row, Scanner input)
	{
		this.line = line;
		this.row = row;
		this.input = input;
		this.countMine = (line * row) / 3;
		this.mineLoc = new int[this.countMine][2];
		this.countBlock = line * row;
		this.zeroCheck = 0;
	}

	public boolean finishCheck(int code, String gameMap[][])
	{
		if (code == 1)
		{
			System.out.println(" _________________________________________________________________\n|_________________________________________________________________|");
			System.out.println("| Boom! Bir mayina bastiniz. Oyun sona erdi.\t\t\t  |\n| Dikkatli olmayi unutmayin, şansinizi tekrar deneyebilirsiniz !  |");
			System.out.println("|_________________________________________________________________|\n|_________________________________________________________________|");
			showMap(gameMap);
			return (true);
		}
		else if (code == 2)
		{
			System.out.println("___________________________________\n|__________________________________|");
			System.out.println("| Tebrikler! Oyunu kazandiniz ! :) |");
			System.out.println("|__________________________________|\n|__________________________________|");
			showMap(gameMap);
			return (true);
		}
		return (false);
	}

	public void changeMap(String gameMap[][], int directionMine)
	{
		switch (directionMine)
		{
			case 0:
				gameMap[this.slctLine][this.slctRow] = "0";
				break;
			case 1:
				gameMap[this.slctLine][this.slctRow] = "1";
				break;
			case 2:
				gameMap[this.slctLine][this.slctRow] = "2";
				break;
			case 3:
				gameMap[this.slctLine][this.slctRow] = "3";
				break;
			case 4:
				gameMap[this.slctLine][this.slctRow] = "4";
				break;
			case 5:
				gameMap[this.slctLine][this.slctRow] = "5";
				break;
			case 6:
				gameMap[this.slctLine][this.slctRow] = "6";
				break;
			case 7:
				gameMap[this.slctLine][this.slctRow] = "7";
				break;
			case 8:
				gameMap[this.slctLine][this.slctRow] = "8";
				break;
			default:
				break;
		}
	}

	public int counterMine(String mineMap[][])
	{
		boolean upCheck, downCheck, rightCheck, leftCheck;
		int	directionMine;

		directionMine = 0;
		upCheck = (this.slctLine - 1 >= 0);
		downCheck = (this.slctLine + 1 < this.line);
		leftCheck = (this.slctRow - 1 >= 0);
		rightCheck = (this.slctRow + 1 < this.row);

		if (rightCheck && mineMap[this.slctLine][this.slctRow + 1].equals("*")) // right
			directionMine++;
		if (leftCheck && mineMap[this.slctLine][this.slctRow - 1].equals("*")) // left
			directionMine++;
		if (downCheck && mineMap[this.slctLine + 1][this.slctRow].equals("*")) // up
			directionMine++;
		if (upCheck && mineMap[this.slctLine - 1][this.slctRow].equals("*")) // down
			directionMine++;
		if (downCheck && rightCheck && mineMap[this.slctLine + 1][this.slctRow + 1].equals("*")) // down right cross
			directionMine++;
		if (downCheck && leftCheck && mineMap[this.slctLine + 1][this.slctRow - 1].equals("*")) // down left cross
			directionMine++;
		if (upCheck && rightCheck && mineMap[this.slctLine - 1][this.slctRow + 1].equals("*")) // up right cross
			directionMine++;
		if (upCheck && leftCheck && mineMap[this.slctLine - 1][this.slctRow - 1].equals("*")) //up left cross
			directionMine++;

		return(directionMine);
	}

	public void openMine(String gameMap[][])
	{
		int	i,j;

		i = -1;
		while (++i < this.line)
		{
			j = -1;
			while (++j < this.row)
				if (isHave(this.mineLoc, i, j, this.countMine))
					gameMap[i][j] = "*";
		}
	}

	public int selectHandler(String mineMap[][], String gameMap[][], int selectLine, int selectRow)
	{
		int	directionMine;

		this.slctLine = selectLine;
		this.slctRow = selectRow;
		directionMine = 0;
		if (isHave(mineLoc, selectLine, selectRow, this.countMine))
		{
			openMine(gameMap);
			return (1);
		}
		else
		{
			directionMine = counterMine(mineMap);
			changeMap(gameMap, directionMine);
			this.countBlock--;
			if (this.countBlock == this.countMine)
				return(2);
		}
		return (0);
	}

	public boolean isRpeat(int slctArry[][], int selectLine, int selectRow, int index, int selectLen)
	{
		if (selectLine == 0 && selectRow == 0 && this.zeroCheck < 1)
			this.zeroCheck++;
		if (isHave(slctArry, selectLine, selectRow,selectLen) == false || this.zeroCheck == 1)
		{
			slctArry[index][0] = selectLine;
			slctArry[index][1] = selectRow;
			if (this.zeroCheck == 1)
				this.zeroCheck++;
		}
		else
			return (true);
		return (false);
	}

	public void showDblString(String str[][])
	{
		int	i, j;

		i = -1;
		while (++i < str.length)
		{
			j = -1;
			System.out.print("| ");
			while (++j < str[i].length)
				System.out.print(str[i][j] + " ");
			System.out.println("|");
		}
	}

	public void printStrLen(int len, char c, int upOrDown)
	{
		int	i;

		i = -1;
		System.out.print(" ");
		while (++i <= len * 2 + upOrDown)
			System.out.print(c);
	}

	public void showMap(String gameMap[][])
	{
		printStrLen(this.row, '_', 0);
		System.out.println();
		showDblString(gameMap);
		System.out.print("|");
		printStrLen(this.row, '_', -1);
		System.out.print("|\n\n");
	}

	public void putChar(String mineMap[][], String gameMap[][])
	{
		int	i,j;

		i = 0;
		j = 0;
		while (i < this.line)
		{
			j = 0;
			while (j < this.row)
			{
				gameMap[i][j] = "-";
				if (isHave(this.mineLoc, i, j, this.countMine))
					mineMap[i][j] = "*";
				else
					mineMap[i][j] = "-";
				j++;
			}
			i++;
		}
	}

	public boolean isHave(int arry[][], int num1, int num2, int len)
	{
		int	i;
		i = -1;
		while (++i < len)
		{
			if (arry[i][0] == num1 && arry[i][1] == num2)
				return (true);
		}
		return (false);
	}

	public void takeLoc()
	{
		int	i,locLine ,locRow, checkZero;
		Random rand = new Random();

		checkZero = 0;
		i = 0;
		while (i < this.countMine)
		{
			locLine = rand.nextInt(this.line);
			locRow = rand.nextInt(this.row);
			if (locLine == 0 && locRow == 0 && checkZero <= 1)
				checkZero++;
			if (isHave(mineLoc, locLine, locRow, this.countMine) == false || checkZero == 1)
			{
				this.mineLoc[i][0] = locLine;
				this.mineLoc[i][1] = locRow;
			}
			else
				i--;
			i++;
		}
	}

	public  void startGame()
	{
		String mineMap[][] = new String[this.line][this.row];
		String gameMap[][] = new String[this.line][this.row];
		int selections[][];
		int selectLine, selectRow, index ,retVal, selectLen;

		selectLen = ((this.line * this.row) - this.countMine);
		selections = new int[selectLen][2];
		index = 0;
		takeLoc();
		putChar(mineMap,gameMap);
		showMap(gameMap);
		while (true)
		{
			System.out.print("Satir: ");
			selectLine = this.input.nextInt();
			System.out.print("Sütun: ");
			selectRow = this.input.nextInt();
			if ((selectLine >= this.line || selectRow >= this.row) || (selectLine < 0 || selectRow < 0))
			{
				System.err.println("----------------------------------------------------\nGeçersiz bir değer girdiniz. Lütfen tekrar deneyin !\n----------------------------------------------------");
				continue ;
			}
			else if (isRpeat(selections,selectLine, selectRow, index, selectLen))
			{
				System.err.println("---------------------------------------------------\nAçilmiş bir değer girdiniz. Lütfen tekrar deneyin !\n---------------------------------------------------");
				continue ;
			}
			else
			{
				retVal = selectHandler(mineMap, gameMap,selectLine, selectRow);
				if (finishCheck(retVal, gameMap))
					break ;
				showMap(gameMap);
			}
			index++;
		}
	}
}


