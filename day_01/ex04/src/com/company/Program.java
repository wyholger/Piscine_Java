package com.company;

import java.util.UUID;

public class Program
{
	static TransactionsService service = new TransactionsService();
	static User bob = new User("Bob", 100);
	static User carl = new User("Carl", 100);

	public static void main(String[] args)
	{
		testing_add_user_by_service();
		testing_methods_if_no_transactions();
		testing_make_success_transactions();
		testing_make_unsuccessful_transaction();
		testing_make_validation_transactions();
	}

	public static void testing_make_validation_transactions()
	{
		UUID id;
		Transaction[] transactions_bob;
		Transaction[] no_valid_transactions;

		System.out.println(User.Color.PURPLE + "___Testing make validation transactions___\n" + User.Color.RESET);
		transactions_bob = service.get_user_transactions(bob);
		id = transactions_bob[0].getId();
		service.remove_transaction_user_by_id(bob.getId(), id);
		System.out.println("Making validation...");
		no_valid_transactions = service.validate_transaction();
		if (no_valid_transactions != null && no_valid_transactions[0].getId().equals(id))
			System.out.println(User.Color.GREEN + "Test validation transaction is SUCCESS" + User.Color.RESET);
		else
			System.out.println(User.Color.RED + "Test validation transaction is FAILURE" + User.Color.RESET);
		System.out.println(User.Color.PURPLE + "\n___End testing make validation transactions___\n" + User.Color.RESET);

	}

	public static void testing_make_unsuccessful_transaction()
	{
		Integer balance_bob_before_transaction;
		Integer balance_bob_after_first_retrieving;
		Transaction[] no_valid_transaction;

		System.out.println(User.Color.PURPLE + "___Testing make unsuccessful transactions___\n" + User.Color.RESET);
		System.out.println("Making transactions...");
		balance_bob_before_transaction = service.get_user_balance(bob);
		try
		{
			service.make_transactions(bob, carl, 300);
		}
		catch (RuntimeException e)
		{
			service.retrieving_users_balance();
			no_valid_transaction = service.validate_transaction();
			balance_bob_after_first_retrieving = service.get_user_balance(bob);
			System.out.println(e.toString());
			if (no_valid_transaction == null && balance_bob_before_transaction.equals(balance_bob_after_first_retrieving))
				System.out.println(User.Color.GREEN + "Test make unsuccessful transaction is SUCCESS" + User.Color.RESET);
			else
				System.out.println(User.Color.RED + "Test make unsuccessful transaction is FAILURE" + User.Color.RESET);
		}
		System.out.println(User.Color.PURPLE + "\n___End testing make unsuccessful transactions___\n" + User.Color.RESET);

	}

	public static void testing_add_user_by_service()
	{
		System.out.println(User.Color.PURPLE + "___Testing add user by service___\n" + User.Color.RESET);
		System.out.println("Adding user Bob...");
		service.add_user(bob);
		System.out.println("Adding user Carl...");
		service.add_user(carl);
		System.out.println("Bob balance " + User.Color.YELLOW + service.get_user_balance(bob) + User.Color.RESET);
		System.out.println("Carl balance " + User.Color.YELLOW + service.get_user_balance(carl) + User.Color.RESET);
		System.out.println(User.Color.PURPLE + "\n___End testing add user by service___\n" + User.Color.RESET);
	}

	public static void testing_make_success_transactions()
	{
		Integer balance_bob_before_transaction;
		Integer balance_bob_after_transaction;
		Integer balance_bob_after_first_retrieving;
		Integer balance_bob_after_second_retrieving;
		Transaction[] no_valid_transaction;

		System.out.println(User.Color.PURPLE + "___Testing make success transactions___\n" + User.Color.RESET);
		balance_bob_before_transaction = service.get_user_balance(bob);
		System.out.println("Making transactions...");
		service.make_transactions(bob, carl, 30);
		service.make_transactions(carl, bob, 10);
		balance_bob_after_transaction = service.get_user_balance(bob);
		service.retrieving_users_balance();
		balance_bob_after_first_retrieving = service.get_user_balance(bob);
		service.retrieving_users_balance();
		balance_bob_after_second_retrieving = service.get_user_balance(bob);
		if ((!balance_bob_before_transaction.equals(balance_bob_after_transaction)) && (!balance_bob_after_first_retrieving.equals(balance_bob_after_second_retrieving)) && balance_bob_before_transaction.equals(balance_bob_after_first_retrieving))
			System.out.println(User.Color.RED + "Test make success transactions is FAILURE" + User.Color.RESET);
		else
			System.out.println(User.Color.GREEN + "Test make success transactions is SUCCESS" + User.Color.RESET);
		System.out.println("Validation transactions...");
		no_valid_transaction = service.validate_transaction();
		if (no_valid_transaction != null)
			System.out.println(User.Color.RED + "Test validation transactions is FAILURE" + User.Color.RESET);
		else
			System.out.println(User.Color.GREEN + "Test validation transactions is SUCCESS" + User.Color.RESET);
		System.out.println(User.Color.PURPLE + "\n___End testing make success transactions___\n" + User.Color.RESET);
	}

	public static void testing_methods_if_no_transactions()
	{


		System.out.println(User.Color.PURPLE + "___Testing methods if no transactions___\n" + User.Color.RESET);

		testing_validate_transaction_if_no_transactions();
		testing_get_user_transactions_if_no_transactions();
		testing_retrieving_user_balance_if_no_transactions();

		System.out.println(User.Color.PURPLE + "\n___End testing methods if no transactions___\n" + User.Color.RESET);
	}

	public static void testing_validate_transaction_if_no_transactions()
	{
		Transaction[] no_valid;

		System.out.println("Validating service transactions...");
		no_valid = service.validate_transaction();
		if (no_valid != null)
			System.out.println(User.Color.RED + "Test validating empty transactions is FAILURE" + User.Color.RESET);
		else
			System.out.println(User.Color.GREEN + "Test validating empty transactions is SUCCESS" + User.Color.RESET);

	}

	public static void testing_get_user_transactions_if_no_transactions()
	{
		Transaction[] transactions;

		System.out.println("Service getting user transactions...");
		transactions = service.get_user_transactions(bob);
		if (transactions != null)
			System.out.println(User.Color.RED + "Test getting empty user transactions is FAILURE" + User.Color.RESET);
		else
			System.out.println(User.Color.GREEN + "Test getting empty user transactions is SUCCESS" + User.Color.RESET);

	}

	public static void testing_retrieving_user_balance_if_no_transactions()
	{
		Integer balance_bob_before_retrieving;
		Integer balance_bob_after_retrieving;

		balance_bob_before_retrieving = service.get_user_balance(bob);
		System.out.println("Service retrieving users balance...");
		service.retrieving_users_balance();
		balance_bob_after_retrieving = service.get_user_balance(bob);
		if (!balance_bob_before_retrieving.equals(balance_bob_after_retrieving))
			System.out.println(User.Color.RED + "Test balance retrieving with empty user transactions is FAILURE" + User.Color.RESET);
		else
			System.out.println(User.Color.GREEN + "Test balance retrieving with empty user transactions is SUCCESS" + User.Color.RESET);

	}



}
