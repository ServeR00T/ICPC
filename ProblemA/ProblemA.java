package icpc;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author saleem
 */
public class ProblemA {
	static String FILE = "AA.in";
	static File inFile;
	static Scanner scanner;
	static String[] inputS;
	static int p, a, b, c, d, n;
	static ArrayList<Double> prices = new ArrayList<>();
	static double largestDecline = Double.MIN_VALUE;
	static double high = Double.MIN_VALUE;

	public ProblemA()
	{
	}
	
	private static void importDataSet()
	{
		try
		{
			inFile = new File(FILE);
			scanner = new Scanner(inFile);
			
			while (scanner.hasNextLine())
			{
				inputS = scanner.nextLine().trim().split(" ");
				p = Integer.parseInt(inputS[0]);
				a = Integer.parseInt(inputS[1]);
				b = Integer.parseInt(inputS[2]);
				c = Integer.parseInt(inputS[3]);
				d = Integer.parseInt(inputS[4]);
				n = Integer.parseInt(inputS[5]);
			}
			System.out.println(p+" "+a+" "+b+" "+c+" "+d+" "+n);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (scanner != null)
			{
				scanner.close();
			}
		}
	}
	
	private static double getPrice(int k)
	{
		double sin = Math.sin((a * k) + b);
		double cos = Math.cos((c * k) + d);
		return p * (sin + cos + 2);
	}
	
	private static void calcLargestDecline()
	{
		double decl;
		for (int i = 0; i < prices.size(); i++)
		{
			if (prices.get(i) > high)
			{
				high = prices.get(i);
			}
			else
			{
				decl = high - prices.get(i);
				if (decl > largestDecline)
					largestDecline = decl;
			}
		}
		
		if (largestDecline == Double.MIN_VALUE)
			largestDecline = 0;
	}
	
	public static void main(String[] args)
	{
		long st = System.nanoTime();
		
		importDataSet();
		for (int k = 1; k <= n; k++)
		{
			prices.add(getPrice(k));
		}
		calcLargestDecline();
		System.out.println(largestDecline);
		
		System.out.println("in " + (System.nanoTime() - st)/1000000000.0);
	}
}

