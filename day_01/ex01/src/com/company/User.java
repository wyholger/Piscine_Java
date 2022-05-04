package com.company;

public class User
{
	enum Color
	{
		RESET("\033[0m"),

		BLACK("\033[0;30m"),    // BLACK
		RED("\033[0;31m"),      // RED
		GREEN("\033[0;32m"),    // GREEN
		YELLOW("\033[0;33m"),   // YELLOW
		BLUE("\033[0;34m"),     // BLUE
		MAGENTA("\033[0;35m"),  // MAGENTA
		CYAN("\033[0;36m"),     // CYAN
		WHITE("\033[0;37m");    // WHITE

		private final String code;

		Color(String code)
		{
			this.code = code;
		}

		@Override
		public String toString()
		{
			return code;
		}
	}

	private Integer id;
	private final String name;
	private Integer balance;

	public User(String name, int balance)
	{
		this.name = name;
		if (balance < 0)
			balance = 0;
		this.balance = balance;
		this.id = UserIdsGenerator.getInstance().generateId();
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setBalance(Integer balance)
	{
		this.balance = balance;
	}

	public String getName()
	{
		return name;
	}

	public Integer getBalance()
	{
		return balance;
	}

	public Integer getId()
	{
		return id;
	}

	@Override
	public String toString() {
		return  Color.BLUE + name + Color.RESET +
				", balance = " + Color.YELLOW + balance + Color.RESET +
				", id = " + id;
	}
}
