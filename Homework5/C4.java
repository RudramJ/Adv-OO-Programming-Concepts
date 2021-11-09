
public class C4 implements I1, I2{
	public int abstractClass2()	{
		System.out.println("C4: abstractClass2()");
		return 314;
	}
	public void i1method()	{
		System.out.println("C4: i1method()/i1variable = " + i1variable);
	}
	public void i2method()	{
		System.out.println("C4: i2method()/i2variable = " + i2variable);
	}
	public void i1and2method()	{
		System.out.println("C4: i1and2method()/i2variable = " + i2variable +
				   " I2.i1and2variable = " + I2.i1and2variable);
	}
}
