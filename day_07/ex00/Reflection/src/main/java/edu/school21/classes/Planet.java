package edu.school21.classes;

public class Planet
{
	private String name;
	private Integer position_from_star;
	private Double period_rotation_around_axis_hours;
	private Boolean planetary_ring_system;
	private Long equatorial_radius;

	public Planet(String name, Integer position_from_star, Double period_rotation_around_axis_hours, Boolean planetary_ring_system, Long equatorial_radius)
	{
		this.name = name;
		this.position_from_star = position_from_star;
		this.period_rotation_around_axis_hours = period_rotation_around_axis_hours;
		this.planetary_ring_system = planetary_ring_system;
		this.equatorial_radius = equatorial_radius;
	}

	public Planet()
	{
		this.name = "No_name";
		this.position_from_star = 0;
		this.period_rotation_around_axis_hours = (double) 0;
		this.planetary_ring_system = false;
		this.equatorial_radius = 0L;
	}

	public Double days_pass_for_number_earth_hours (int hours)
	{
		return hours / period_rotation_around_axis_hours;
	}

	@Override
	public String toString()
	{
		return "Planet{" +
				"name='" + name + '\'' +
				", position_from_star=" + position_from_star +
				", period_rotation_around_axis_hours=" + period_rotation_around_axis_hours +
				", planetary_ring_system=" + planetary_ring_system +
				", equatorial_radius=" + equatorial_radius +
				'}';
	}
}
