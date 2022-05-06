package com.company;

import java.util.UUID;

public class Program
{
	static User bob = new User("Bob", 100);
	static User carl = new User("Carl", 100);
	static UUID id_first_transaction_bob;
	static UUID id_first_transaction_carl;

	public static void main(String[] args)
	{
		testing_transaction_list_add();
		testing_transform_into_array();
		testing_remove_transaction_by_id();
	}

	private static void testing_remove_transaction_by_id()
	{
		System.out.println(User.Color.PURPLE + "___Testing remove a transaction by ID___\n" + User.Color.RESET);
		System.out.println("Removal first transaction in list for Bob...");
		System.out.println("Removal first transaction in list for Carl...\n");
		bob.getTransactions().remove_transaction_by_id(id_first_transaction_bob);
		carl.getTransactions().remove_transaction_by_id(id_first_transaction_carl);
		System.out.println("Bob transactions in list after remove first element:");
		bob.getTransactions().print_list();
		System.out.println("Carl transactions in list after remove first element:");
		carl.getTransactions().print_list();
		System.out.println(User.Color.PURPLE + "\n___End testing remove a transaction by ID___\n" + User.Color.RESET);

	}


	private static void testing_transaction_list_add()
	{
		System.out.println(User.Color.PURPLE + "___Testing transaction list add___\n" + User.Color.RESET);
		generate_testing_transactions();
		System.out.println("Bob transactions in list:");
		bob.getTransactions().print_list();
		System.out.println("Carl transactions in list:");
		carl.getTransactions().print_list();
		System.out.println(User.Color.PURPLE + "\n___End testing transaction list add___\n" + User.Color.RESET);
	}

	private static void testing_transform_into_array()
	{
		Transaction[] transactions_bob;
		Transaction[] transactions_carl;

		System.out.println(User.Color.PURPLE + "___Testing transform into array___\n" + User.Color.RESET);
		transactions_bob = bob.getTransactions().to_array();
		transactions_carl = carl.getTransactions().to_array();
		id_first_transaction_bob = transactions_bob[0].getId();
		id_first_transaction_carl = transactions_carl[0].getId();
		System.out.println("Bob transactions in array:");
		TransactionsLinkedList.print_array(transactions_bob);
		System.out.println("Carl transactions in array:");
		TransactionsLinkedList.print_array(transactions_carl);
		System.out.println(User.Color.PURPLE + "\n___End testing transform into array___\n" + User.Color.RESET);
	}

	private static void generate_testing_transactions()
	{
		bob.getTransactions().add_transaction(Transaction.create_transaction(bob, carl, Transaction.Transfer_category.CREDIT, -50));
		carl.getTransactions().add_transaction(Transaction.create_transaction(carl, bob, Transaction.Transfer_category.DEBIT, 50));

		carl.getTransactions().add_transaction(Transaction.create_transaction(carl, bob, Transaction.Transfer_category.CREDIT, -10));
		bob.getTransactions().add_transaction(Transaction.create_transaction(bob, carl, Transaction.Transfer_category.DEBIT, 10));

		bob.getTransactions().add_transaction(Transaction.create_transaction(bob, carl, Transaction.Transfer_category.CREDIT, -30));
		carl.getTransactions().add_transaction(Transaction.create_transaction(carl, bob, Transaction.Transfer_category.DEBIT, 30));

		carl.getTransactions().add_transaction(Transaction.create_transaction(carl, bob, Transaction.Transfer_category.CREDIT, -75));
		bob.getTransactions().add_transaction(Transaction.create_transaction(bob, carl, Transaction.Transfer_category.DEBIT, 75));
	}

}
