package com.company;

public class Program
{

	public static void main(String[] args)
	{
		User bob = new User("Bob", 100);
		User carl = new User("Carl", 100);

		{
			System.out.println(User.Color.PURPLE + "___Testing transaction list add___" + User.Color.RESET);
			bob.getTransactions().add_transaction(Transaction.create_transaction(bob, carl, Transaction.Transfer_category.CREDIT, -50));
			carl.getTransactions().add_transaction(Transaction.create_transaction(carl, bob, Transaction.Transfer_category.DEBIT, 50));
			carl.getTransactions().add_transaction(Transaction.create_transaction(carl, bob, Transaction.Transfer_category.CREDIT, -10));
			bob.getTransactions().add_transaction(Transaction.create_transaction(bob, carl, Transaction.Transfer_category.DEBIT, 10));
			System.out.println();
			System.out.println("Bob transactions:");
			bob.getTransactions().print_list();
			System.out.println("Carl transactions:");
			carl.getTransactions().print_list();
			System.out.println();
			System.out.println(User.Color.PURPLE + "___End testing transaction list add___" + User.Color.RESET);
		}
	}
}
