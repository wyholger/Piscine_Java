package com.company;

import java.util.UUID;

public interface TransactionsList
{
	public void add_transaction(Transaction transaction);
	public void remove_transaction_by_id(UUID id);
	public Transaction[] to_array();
	public void print_list();
}
