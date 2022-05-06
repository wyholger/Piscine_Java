package com.company;

import java.util.UUID;

import static com.company.User.Color.*;

public class Transaction
{
	public enum Transfer_category
	{
		DEBIT("\033[0;32mINCOME\033[0m", "+"),
		CREDIT("\033[0;31mOUTCOME\033[0m", "");

		private String text;
		private String sign;

		Transfer_category(String text, String sign)
		{
			this.text = text;
			this.sign = sign;
		}

		@Override
		public String toString()
		{
			return text;
		}

		public String getSign()
		{
			return sign;
		}
	}

	private UUID id;
	private User recipient;
	private User sender;
	private Transfer_category category;
	private Integer amount;

	public Transaction(User recipient, User sender, Transfer_category category, Integer amount)
	{
		this.recipient = recipient;
		this.sender = sender;
		this.category = category;
		this.amount = amount;
		this.id = UUID.randomUUID();
	}

	public static Transaction create_transaction(User recipient, User sender, Transfer_category category, Integer amount)
	{
		if ((category == Transfer_category.CREDIT && amount <= 0 && recipient.getBalance() >= -amount)
				|| (category == Transfer_category.DEBIT && amount >= 0 && sender.getBalance() >= amount))
			return new Transaction(recipient, sender, category, amount);
		return null;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public User getRecipient() {
		return recipient;
	}

	public User getSender() {
		return sender;
	}

	public Transfer_category getCategory() {
		return category;
	}

	public Integer getAmount() {
		return amount;
	}

	@Override
	public String toString()
	{
		if (category.equals(Transfer_category.CREDIT))
		{
			return  BLUE + "" + recipient.getName() + RESET +
					" -> " +
					BLUE + sender.getName() + RESET + ", " +
					YELLOW + category.getSign() + amount + RESET + ", " +
					category + ", " +
					"id = " + id;
		}
		else
		{
			return  BLUE + "" + recipient.getName() + RESET +
					" <- " +
					BLUE + sender.getName() + RESET + ", " +
					YELLOW + category.getSign() + amount + RESET + ", " +
					category + ", " +
					"id = " + id;
		}
	}
}
