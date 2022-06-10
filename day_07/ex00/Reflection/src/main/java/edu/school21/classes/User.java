package edu.school21.classes;

public class User
{
	private String login;
	private Integer id;
	private Double rating;
	private Boolean status_online;
	private Long number_of_clicks;

	public User()
	{
		this.login = "login";
		this.id = 0;
		this.rating = 0.0;
		this.status_online = false;
		this.number_of_clicks = 0L;
	}
	public User(String login, Integer id, Double rating, Boolean status_online, Long number_of_clicks)
	{
		this.login = login;
		this.id = id;
		this.rating = rating;
		this.status_online = status_online;
		this.number_of_clicks = number_of_clicks;
	}

	public int forty_two()
	{
		return 42;
	}

	public String forty_two_plus_string(String str)
	{
		return "42 " + str;
	}

	public void black_box()
	{

	}

	@Override
	public String toString()
	{
		return "User[" +
				"login='" + login + '\'' +
				", id=" + id +
				", rating=" + rating +
				", status_online=" + status_online +
				", number_of_clicks=" + number_of_clicks +
				']';
	}
}
