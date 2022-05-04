package com.company;

import static com.company.User.Color.*;

public class Program
{

    public static void main(String[] args) {
        User bob = new User("Bob", 500);
        bob.setId(1);
        User carl = new User("Carl", 300);
        carl.setId(2);

        System.out.println(bob);
        System.out.println(carl);

        Transaction tr_1 = Transaction.create_transaction(bob, carl, Transaction.Transfer_category.CREDIT, -50);
        Transaction tr_2 = Transaction.create_transaction(carl, bob, Transaction.Transfer_category.DEBIT, 50);

        if (tr_1 != null && tr_2 != null)
        {
            System.out.println();
            System.out.println(tr_1);
            System.out.println(tr_2);
            System.out.println();
            bob.setBalance(bob.getBalance() - 50);
            carl.setBalance(carl.getBalance() + 50);
            System.out.println();
            System.out.println(bob);
            System.out.println(carl);
        }
        else
            System.out.println(RED + "Invalid transaction!" + RESET);
    }
}
