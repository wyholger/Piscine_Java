package edu.school21.classes;

public class Human
{
	private String name;
	private Integer age;
	private Double growth_m;
	private Boolean pet;
	private Long amount_of_hair;

	public Human(String name, Integer age, Double growth_m, Boolean pet, Long amount_of_hair)
	{
		this.name = name;
		this.age = age;
		this.growth_m = growth_m;
		this.pet = pet;
		this.amount_of_hair = amount_of_hair;
	}

	public Human()
	{
		this.name = "No_name";
		this.age = 0;
		this.growth_m = (double) 0;
		this.pet = false;
		this.amount_of_hair = 0L;
	}

	public Double jump(int centimeters)
	{
		return (growth_m + (centimeters / 100));
	}

	public void say_hello(String name, int count)
	{
		for (int i = 0; i < count; i++) {
			System.out.println("Hello " + name + "!");
		}
	}

	@Override
	public String toString()
	{
		return "Human[" +
				"name='" + name + '\'' +
				", age=" + age +
				", growth_m=" + growth_m +
				", pet=" + pet +
				", amount_of_hair=" + amount_of_hair +
				']';
	}
}
