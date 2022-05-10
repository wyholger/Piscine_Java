package com.company;

import java.util.UUID;

public interface TransactionsList
{
	void add_transaction(Transaction transaction);
	void remove_transaction_by_id(UUID id);
	Transaction[] to_array();
	void print_list();
	int length();
	Transaction get_transaction_by_id(UUID id);
}
