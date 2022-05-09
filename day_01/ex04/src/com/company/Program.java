package com.company;

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
		System.out.println("Bob balance " + User.Color.YELLOW + service.get_user_balance(bob) + User.Color.RESET);
		System.out.println("Carl balance " + User.Color.YELLOW + service.get_user_balance(carl) + User.Color.RESET);
		service.make_transactions(bob, carl, 30);
		service.make_transactions(carl, bob, 10);
//		service.validate_transaction();
		service.retrieving_users_balance();
		System.out.println("Bob balance " + User.Color.YELLOW + service.get_user_balance(bob) + User.Color.RESET);
		System.out.println("Carl balance " + User.Color.YELLOW + service.get_user_balance(carl) + User.Color.RESET);
		service.retrieving_users_balance();
		System.out.println("Bob balance " + User.Color.YELLOW + service.get_user_balance(bob) + User.Color.RESET);
		System.out.println("Carl balance " + User.Color.YELLOW + service.get_user_balance(carl) + User.Color.RESET);

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

		service.retrieving_user_balance(bob);
		balance_bob_before_retrieving = service.get_user_balance(bob);
		System.out.println("Service retrieving users balance...");
		service.retrieving_users_balance();
		service.retrieving_user_balance(bob);
		balance_bob_after_retrieving = service.get_user_balance(bob);
		if (!balance_bob_before_retrieving.equals(balance_bob_after_retrieving))
			System.out.println(User.Color.RED + "Test balance retrieving with empty user transactions is FAILURE" + User.Color.RESET);
		else
			System.out.println(User.Color.GREEN + "Test balance retrieving with empty user transactions is SUCCESS" + User.Color.RESET);

	}



}
