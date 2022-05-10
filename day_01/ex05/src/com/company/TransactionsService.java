package com.company;

import java.util.UUID;

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

	private void retrieving_user_balance(User user)
	{
		Transaction[] transactions;
		Integer tmp = 0;

		try
		{
			transactions = user.getTransactions().to_array();
		}
		catch (RuntimeException e)
		{
			return;
		}
		for (Transaction transaction : transactions)
		{
			tmp += transaction.getAmount();
		}
		user.setBalance(user.getStart_balance() + tmp);
	}

	public void retrieving_users_balance()
	{
		for (int i = 0; i < list.length(); i++)
		{
			retrieving_user_balance(list.get_user_by_index(i));
		}
	}

	public User get_user_by_id(Integer id)
	{
		User user;

		try
		{
			user = list.get_user_by_id(id);
		}
		catch (RuntimeException e)
		{
			return null;
		}
		return user;
	}

	public Integer get_user_balance(User user)
	{
		return user.getBalance();
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
			throw new IllegalTransactionException();
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
			return null;
		}
		return transactions;
	}

	public void remove_transaction_user_by_id(Integer user_id, UUID transaction_id) throws RuntimeException
	{
		try
		{
			list.get_user_by_id(user_id).getTransactions().remove_transaction_by_id(transaction_id);
		}
		catch (RuntimeException e)
		{
			throw e;
		}
	}

	public Transaction[] validate_transaction()
	{
		TransactionsLinkedList no_valid = new TransactionsLinkedList();
		boolean flag = false;
		Transaction[] tmp;
		User recipient;
		User sender;
		UUID id;

		for (int i = 0; i < list.length(); i++)
		{
			try
			{
				tmp = list.get_user_by_index(i).getTransactions().to_array();
			}
			catch (RuntimeException e)
			{
				continue;
			}
			for (Transaction transaction : tmp)
			{
				recipient = transaction.getRecipient();
				sender = transaction.getSender();
				id = transaction.getId();
				try
				{
					list.get_user_by_id(recipient.getId()).getTransactions().get_transaction_by_id(id);
					list.get_user_by_id(sender.getId()).getTransactions().get_transaction_by_id(id);
				}
				catch (RuntimeException e)
				{
					flag = true;
					no_valid.add_transaction(transaction);
				}
			}
		}
		if (!flag)
			return null;
		return no_valid.to_array();
	}

	private static class NegativeAmountException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Illegal transaction. It is not possible to transfer a negative amount.";
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
