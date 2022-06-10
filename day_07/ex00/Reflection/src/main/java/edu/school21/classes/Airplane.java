package edu.school21.classes;

public class Airplane
{
	private String model;
	private Integer id;
	private Double rating;
	private Boolean in_the_sky;
	private Long number_of_flights;

	public Airplane()
	{
		this.model = "model";
		this.id = 0;
		this.rating = 0.0;
		this.in_the_sky = false;
		this.number_of_flights = 0L;
	}

	public Airplane(String model, Integer id, Double rating, Boolean in_the_sky, Long number_of_flights)
	{
		this.model = model;
		this.id = id;
		this.rating = rating;
		this.in_the_sky = in_the_sky;
		this.number_of_flights = number_of_flights;
	}

	public int twenty_one()
	{
		return 21;
	}

	public String twenty_one_plus_string(String str)
	{
		return "42 " + str;
	}

	public void black_box()
	{

	}

	@Override
	public String toString()
	{
		return "Airplane[" +
				"model='" + model + '\'' +
				", id=" + id +
				", rating=" + rating +
				", in_the_sky=" + in_the_sky +
				", number_of_flights=" + number_of_flights +
				']';
	}
}
