package com.company;

public class User
{
	enum Color
	{
		RESET("\033[0m"),

		BLACK("\033[0;30m"),
		PURPLE("\033[0;35m"),
		RED("\033[0;31m"),
		GREEN("\033[0;32m"),
		YELLOW("\033[0;33m"),
		BLUE("\033[0;34m"),
		MAGENTA("\033[0;35m"),
		CYAN("\033[0;36m"),
		WHITE("\033[0;37m");

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
	private final TransactionsList transactions;

	public User(String name, int balance)
	{
		this.transactions = new TransactionsLinkedList();
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

	public TransactionsList getTransactions()
	{
		return transactions;
	}

	@Override
	public String toString() {
		return  Color.BLUE + name + Color.RESET +
				", balance = " + Color.YELLOW + balance + Color.RESET +
				", id = " + id;
	}
}
