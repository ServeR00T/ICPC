import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 * This is NOT a correct solution, i assumed that it will touch each other
 * BUT this is not the case.
 * What the code does is that it calculate the minimum euclidean distance
 * between the polygons, without taking care if it intersect ot not.
 * @author saleem
 */
public class ProblemB {

	static String FILE = "BB.in";
	static File inFile;
	static Scanner scanner;
	static ArrayList<Poly> polygons = new ArrayList<>();
	
	private static void importDataSet()
	{
		try
		{
			inFile = new File(FILE);
			scanner = new Scanner(inFile);
			
			while (scanner.hasNextInt())
			{
				Poly p = new Poly();
				int n = scanner.nextInt();
				double[] x = new double[n];
				double[] y = new double[n];
				for (int i = 0; i < n; i++)
				{
					x[i] = scanner.nextInt();
					y[i] = scanner.nextInt();
				}
				p.setX(x);
				p.setY(y);
				p.setvX(scanner.nextInt());
				p.setvY(scanner.nextInt());
				
				polygons.add(p);
			}
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
	
	public static double euclideanDistance(Centroid c1, Centroid c2)
	{
		return Math.sqrt(
						Math.pow(c1.getX() - c2.getX(), 2) +
						Math.pow(c1.getY() - c2.getY(), 2)
						);
	}
	
	public static void main(String[] args)
	{
		importDataSet();
		
		Poly p1 = polygons.get(0);
		Poly p2 = polygons.get(1);
		double lastD=Double.MAX_VALUE;
		double i = 0;
		while (i<10)
		{
			i+=0.001;
			p1.mov();
			p2.mov();
			if (euclideanDistance(p1.getC(), p2.getC()) > lastD)
			{
				if (i <= 0.002)
				{
					System.out.println("never");
					break;
				}
				System.out.println(lastD+" at "+(i-0.001));
				break;
			}
			else
			{
				lastD = euclideanDistance(p1.getC(), p2.getC());
			}
		}
	}
}

class Centroid
{
	private double x, y;
	private Poly poly;

	public Centroid(Poly poly)
	{
		this.poly = poly;
	}

	public Centroid(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Centroid(double x, double y, Poly poly)
	{
		this.x = x;
		this.y = y;
		this.poly = poly;
	}
	
	public void calcCentroid()
	{
		double[] x = this.poly.getX();
		double[] y = this.poly.getY();
		double cX=0, cY=0;
		for (int i = 0; i < x.length; i++)
		{
			cX += x[i];
			cY += y[i];
		}
		this.y = cX / x.length;
		this.x = cY / y.length;
	}

	public Poly getPoly()
	{
		return poly;
	}

	public void setPoly(Poly poly)
	{
		this.poly = poly;
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
}

class Poly
{
	private double[] x;
	private double[] y;
	private Centroid c;
	private int vX, vY;
	private double inc = 0.001;

	public Poly()
	{
		c = new Centroid(this);
	}
	
	public void mov()
	{
		for (int i = 0; i < x.length; i++)
		{
			x[i] += (vX*inc);
			y[i] += (vY*inc);
		}
		c.calcCentroid();
	}

	public int getvX()
	{
		return vX;
	}

	public void setvX(int vX)
	{
		this.vX = vX;
	}

	public int getvY()
	{
		return vY;
	}

	public void setvY(int vY)
	{
		this.vY = vY;
	}

	public double[] getX()
	{
		return x;
	}

	public void setX(double[] x)
	{
		this.x = x;
	}

	public double[] getY()
	{
		return y;
	}

	public void setY(double[] y)
	{
		this.y = y;
	}

	public Centroid getC()
	{
		return c;
	}

	public void setC(Centroid c)
	{
		this.c = c;
	}
}
