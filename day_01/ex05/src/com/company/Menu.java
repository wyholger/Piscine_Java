package com.company;

import java.util.Scanner;
import java.util.UUID;

import static com.company.User.Color.*;

public class Menu
{
	private static Scanner scan;
	private static TransactionsService service;

	public Menu(TransactionsService service)
	{
		Menu.service = service;
		scan = new Scanner(System.in);
	}

	public void menu_cycle(boolean dev_profile)
	{
		Integer menu_item;

		while (true)
		{
			print_menu(dev_profile);
			menu_item = scan_positive_int();
			if ((menu_item.equals(7) && dev_profile) || (menu_item.equals(5) && !dev_profile))
			{
				System.out.println(GREEN + "Bye-Bye" + RESET);
				scan.close();
				break;
			}
			if (!validation_menu_item(menu_item))
			{
				msg_not_valid_menu_item();
				System.out.println(PURPLE + "---------------------------------------------------------" + RESET);
				continue;
			}
			command_execution(menu_item, dev_profile);
			System.out.println(PURPLE + "---------------------------------------------------------" + RESET);
		}
	}

	private static void add_user()
	{
		String name;
		int balance;
		User user;

		System.out.println(PURPLE + "Enter a " + YELLOW + "user name" + PURPLE + " and a " + YELLOW + "balance" + PURPLE + "." + RESET);
		name = scan.next();
		if (scan.hasNextInt())
			balance = scan.nextInt();
		else
		{
			System.out.println(RED + "No valid balance. It must be a number greater than zero." + RESET);
			return;
		}
		if (balance < 0)
		{
			System.out.println(RED + "No valid balance. It must be a number greater than zero." + RESET);
			return;
		}
		user = new User(name, balance);
		service.add_user(user);
		System.out.println(GREEN + "User with id = " + YELLOW + user.getId() + GREEN + " is added." + RESET);
	}

	private static void view_user_balance()
	{
		User user;
		int	id;
		Integer balance;

		System.out.println(PURPLE + "Enter a user ID" + RESET);
		id = scan_positive_int();
		if (id < 0)
		{
			System.out.println(RED + "No valid user id. It must be a number greater than zero." + RESET);
			return;
		}
		user = service.get_user_by_id(id);
		if (user == null)
		{
			System.out.println(RED + "User with id " + YELLOW + id + RED +" not found." + RESET);
			return;
		}
		else
			balance = service.get_user_balance(user);
		System.out.println(BLUE + user.getName() + RESET + " - " + YELLOW + balance + RESET);
	}

	private static void perform_transfer()
	{
		User sender;
		User recipient;
		int id_sender;
		int id_recipient;
		int amount;

		System.out.println(PURPLE + "Enter a " + YELLOW + "sender ID" + PURPLE + ", a " + YELLOW + "recipient ID" + PURPLE + ", and a transfer " + YELLOW + "amount" + PURPLE + "." + RESET);
		id_sender = scan_positive_int();
		id_recipient = scan_positive_int();
		amount = scan_positive_int();
		if (id_sender < 0 || id_recipient < 0 || amount < 0)
		{
			System.out.println(RED + "No valid user id or amount. It must be a number greater than zero." + RESET);
			return;
		}
		sender = find_user_by_id(id_sender);
		recipient = find_user_by_id(id_recipient);
		if (sender == null)
		{
			System.out.println(RED + "User with id " + YELLOW + id_sender + RED +" not found." + RESET);
			return;
		}
		if (recipient == null)
		{
			System.out.println(RED + "User with id " + YELLOW + id_recipient + RED +" not found." + RESET);
			return;
		}
		try
		{
			service.make_transactions(sender, recipient, amount);
		}
		catch (RuntimeException e)
		{
			System.out.println(RED + e.toString() + RESET);
			return;
		}
		service.retrieving_users_balance();
		System.out.println(GREEN + "The transfer is completed." + RESET);
	}

	private static User find_user_by_id(Integer id)
	{
		User user;
		user = service.get_user_by_id(id);
		return user;
	}

	private static int scan_positive_int()
	{
		int integer;

		if (scan.hasNextInt())
			integer = scan.nextInt();
		else
		{
			scan.next();
			integer = -1;
		}
		return integer;
	}

	private static void view_all_transactions_user()
	{
		User user;
		int	id;

		System.out.println(PURPLE + "Enter a " + YELLOW + "user ID" + PURPLE + "." + RESET);
		id = scan_positive_int();
		if (id < 0)
		{
			System.out.println(RED + "No valid user id. It must be a number greater than zero." + RESET);
			return;
		}
		user = find_user_by_id(id);
		if (user == null)
		{
			System.out.println(RED + "User with id " + YELLOW + id + RED +" not found." + RESET);
			return;
		}
		print_transactions_user(user);
	}

	private static void print_transactions_user(User user)
	{
		Transaction[] transactions;
		User.Color color_amount;

		transactions = user.getTransactions().to_array();
		if (transactions == null)
		{
			System.out.println(RED + "User with id " + YELLOW + user.getId() + RED +" has no transactions." + RESET);
		}
		else
		{
			for (Transaction transaction: transactions)
			{
				if (transaction.getCategory().equals(Transaction.Transfer_category.DEBIT))
					color_amount = GREEN;
				else
					color_amount = RED;
				System.out.println("To " + BLUE + transaction.getSender().getName() + RESET
						+ "(id = " + YELLOW + transaction.getSender().getId() + RESET + ") "
						+ color_amount + transaction.getAmount() + RESET
						+ " with id = " + transaction.getId());
			}
		}
	}

	private static void remove_transfer_by_id()
	{
		Transaction transaction;
		int	user_id;
		String transfer_id;
		User user;
		UUID id;
		User.Color color_amount;

		System.out.println(PURPLE + "Enter a " + YELLOW + "user ID" + PURPLE + " and a " + YELLOW + "transfer ID" + PURPLE + "." + RESET);
		user_id = scan_positive_int();
		if (user_id == -1)
		{
			System.out.println(RED + "No valid user id. It must be a number greater than zero." + RESET);
			return;
		}
		transfer_id = scan.next();
		try
		{
			id = UUID.fromString(transfer_id);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(RED + "Illegal id. Id need format UUID. Example 123e4567-e89b-42d3-a456-556642440000" + RESET);
			return;
		}
		user = service.get_user_by_id(user_id);
		if (user == null)
		{
			System.out.println(RED + "User with id " + YELLOW + user_id + RED +" not found." + RESET);
			return;
		}
		try
		{
			transaction = user.getTransactions().get_transaction_by_id(id);
			service.remove_transaction_user_by_id(user_id, id);
		}
		catch (RuntimeException e)
		{
			System.out.println(RED + "Transfer with id " + YELLOW + id + RED +" not found." + RESET);
			return;
		}
		if (transaction.getCategory().equals(Transaction.Transfer_category.DEBIT))
			color_amount = GREEN;
		else
			color_amount = RED;
		System.out.println("Transfer To " + BLUE + transaction.getSender().getName() + RESET
				+ "(id = " + YELLOW + transaction.getSender().getId() + RESET + ") "
				+ color_amount + transaction.getAmount() + RESET
				+ " removed.");
	}

	private static void check_transfer_validity()
	{
		Transaction[] no_valid_transactions;
		System.out.println(PURPLE + "Check results:" + RESET);
		User.Color color_amount;

		no_valid_transactions = service.validate_transaction();
		if (no_valid_transactions == null)
		{
			System.out.println(GREEN + "All transactions is valid." + RESET);
			return;
		}
		for (Transaction transaction: no_valid_transactions)
		{
			if (transaction.getCategory().equals(Transaction.Transfer_category.DEBIT))
				color_amount = GREEN;
			else
				color_amount = RED;
			System.out.println(BLUE + transaction.getRecipient().getName() + RESET
					+ "(id = " + YELLOW + transaction.getRecipient().getId() + RESET
					+ ") has an unacknowledged transfer id = "
					+ transaction.getId() + " from " + BLUE + transaction.getSender().getName() + RESET
					+ "(id = " + YELLOW + transaction.getSender().getId() + RESET + ") for "
					+ color_amount + transaction.getAmount() + RESET);
		}
	}

	private static void command_execution(Integer menu_item, boolean dev_profile)
	{
		if (menu_item.equals(1))
			add_user();
		if (menu_item.equals(2))
			view_user_balance();
		if (menu_item.equals(3))
			perform_transfer();
		if (menu_item.equals(4))
			view_all_transactions_user();
		if (dev_profile)
		{
			if (menu_item.equals(5))
				remove_transfer_by_id();
			if (menu_item.equals(6))
				check_transfer_validity();
		}
	}

	private static void msg_not_valid_menu_item()
	{
		System.out.println(RED + "No valid menu item. Try anything from 1 to 7." + RESET);
	}

	private static boolean validation_menu_item(Integer menu_item)
	{
		return menu_item > 0 && menu_item < 7;
	}

	private void print_menu(boolean dev_profile)
	{
		if (dev_profile)
		{
			System.out.println("1. Add a user\n" +
					"2. View user balances\n" +
					"3. Perform a transfer\n" +
					"4. View all transactions for a specific user\n" +
					"5. DEV – remove a transfer by ID\n" +
					"6. DEV – check transfer validity\n" +
					"7. Finish execution");
		}
		else
		{
			System.out.println("1. Add a user\n" +
					"2. View user balances\n" +
					"3. Perform a transfer\n" +
					"4. View all transactions for a specific user\n" +
					"5. Finish execution");
		}
	}


}
