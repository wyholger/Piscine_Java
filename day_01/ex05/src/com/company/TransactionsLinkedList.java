package com.company;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList
{

	private final ListItem	start;
	private final ListItem	end;
	private ListItem	last;
	private Integer		size;

	TransactionsLinkedList()
	{
		start = new ListItem();
		end = new ListItem();
		ListItem.link_start_with_end(start, end);
		size = 0;
	}

	@Override
	public void add_transaction(Transaction transaction)
	{
		ListItem tmp = new ListItem(transaction);

		if (start.getNext() == end)
		{
			start.setNext(tmp);
			end.setPrev(tmp);
			tmp.setNext(end);
			tmp.setPrev(start);
		}
		else
		{
			last.setNext(tmp);
			end.setPrev(tmp);
			tmp.setPrev(last);
			tmp.setNext(end);
		}
		last = tmp;
		size++;
	}

	@Override
	public void remove_transaction_by_id(UUID id) throws RuntimeException
	{
		ListItem tmp = start.getNext();

		if (tmp == end)
			throw new TransactionNotFoundException();
		for (int i = 0; i < size; i++)
		{
			if (tmp.getItem().getId().equals(id))
			{
				tmp.getPrev().setNext(tmp.getNext());
				tmp.getNext().setPrev(tmp.getPrev());
				if (tmp == last)
					last = tmp.getPrev();
				tmp.setPrev(null);
				tmp.setNext(null);
				size--;
				return;
			}
			tmp = tmp.getNext();
		}
		throw new TransactionNotFoundException();
	}

	@Override
	public Transaction[] to_array()
	{
		Transaction[] transactions = new Transaction[size];
		ListItem tmp = start.getNext();

		if (tmp == end)
			throw new TransactionListEmptyException();
		for (int i = 0; i < size; i++)
		{
			transactions[i] = tmp.getItem();
			tmp = tmp.getNext();
		}
		return transactions;
	}

	@Override
	public void print_list()
	{
		ListItem tmp = start.getNext();

		for (int i = 0; i < size; i++)
		{
			System.out.println(tmp.getItem());
			tmp = tmp.getNext();
		}
	}

	public static void print_array(Transaction[] transactions)
	{
		for (Transaction transaction : transactions)
		{
			System.out.println(transaction);
		}
	}

	@Override
	public int length()
	{
		return size;
	}

	@Override
	public Transaction get_transaction_by_id(UUID id)
	{
		ListItem tmp = start.getNext();
		Transaction result;

		if (tmp == end)
			throw new TransactionNotFoundException();
		for (int i = 0; i < size; i++)
		{
			if (tmp.getItem().getId().equals(id))
			{
				result = tmp.item;
				return result;
			}
			tmp = tmp.getNext();
		}
		throw new TransactionNotFoundException();
	}

	public Integer getSize()
	{
		return size;
	}

	private static class ListItem
	{
		private ListItem next;
		private ListItem prev;
		private Transaction item;

		ListItem(Transaction data)
		{
			next = null;
			prev = null;
			item = data;
		}

		ListItem()
		{
			next = null;
			prev = null;
			item = null;
		}

		private static void link_start_with_end(ListItem start, ListItem end)
		{
			start.setNext(end);
			end.setPrev(start);
		}

		public void setNext(ListItem next)
		{
			this.next = next;
		}

		public void setPrev(ListItem prev)
		{
			this.prev = prev;
		}

		public void setItem(Transaction item)
		{
			this.item = item;
		}

		public ListItem getNext()
		{
			return next;
		}

		public ListItem getPrev()
		{
			return prev;
		}

		public Transaction getItem()
		{
			return item;
		}
	}

	public static class TransactionNotFoundException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Transaction not found error.";
		}
	}

	private static class TransactionListEmptyException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Transaction list is empty error.";
		}
	}
}
