package com.company;

public class Program
{

    public static void main(String[] args)
    {
        TransactionsService service = new TransactionsService();
        Menu menu = new Menu(service);
        if (args.length > 0)
            menu.menu_cycle(args[0].equals("--profile=dev"));
        else
            menu.menu_cycle(false);
    }
}
