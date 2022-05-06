package com.company;

public class TransactionsService
{
	private final UsersList list;

	TransactionsService()
	{
		list = new UsersArrayList();
	}

	public void add_user(User user)
	{
		list.add_user(user);
	}

	public Integer retrieving_user_balance(User user)
	{
		Transaction[] transactions = user.getTransactions().to_array();
		Integer tmp = 0;

		for (Transaction transaction : transactions)
		{
			tmp += transaction.getAmount();
		}
		return tmp;
	}

	public void retrieving_users_balance()
	{
		Integer balance_changes;

		for (int i = 0; i < list.length(); i++)
		{
			balance_changes = retrieving_user_balance(list.get_user_by_index(i));
			list.get_user_by_index(i).setBalance(list.get_user_by_index(i).getBalance() + balance_changes);
		}
	}

	public void make_transactions(User recipient, User sender, Integer amount)
	{
		Transaction send;
		Transaction take;

		if (amount == 0 || recipient == sender)
			return;
		if (amount < 0)
			throw new NegativeAmountException();
		send = Transaction.create_transaction(recipient, sender, Transaction.Transfer_category.CREDIT, -amount);
		if (send == null)
			throw new NoMoneyException();
		take = Transaction.create_transaction(sender, recipient, Transaction.Transfer_category.DEBIT, amount);
		if (take == null)
			throw new IllegalTransactionException();
		take.setId(send.getId());
		recipient.getTransactions().add_transaction(send);
		sender.getTransactions().add_transaction(take);
	}

	public Transaction[] get_user_transactions(User user)
	{
		Transaction[] transactions;
		try
		{
			transactions = list.get_user_by_id(user.getId()).getTransactions().to_array();
		}
		catch (RuntimeException e)
		{
			transactions = new Transaction[0];
			return transactions;
		}
		return transactions;
	}

	public void remove_transaction_user_by_id(User user)
	{
		//остановился здесь
	}

	private static class NegativeAmountException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Illegal transaction. It is not possible to transfer a negative amount.";
		}
	}
	private static class NoMoneyException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Illegal transaction. There are not enough funds on the sender's balance.";
		}
	}
	private static class IllegalTransactionException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Illegal transaction.";
		}
	}
}
