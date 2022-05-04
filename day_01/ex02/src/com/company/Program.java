package com.company;

import static com.company.User.Color.*;

public class Program {

    public static void main(String[] args)
    {
        UsersArrayList users = new UsersArrayList();

        int size = (int)(Math.random() * 100 + 20);
        for (int i = 0; i < size; i++) {
            User tmp;
            if((int)(Math.random() * 2) == 0)
                tmp = new User("Bob", (int)(Math.random() * 1000));
            else
                tmp = new User("Karl", (int)(Math.random() * 1000));
            users.add_user(tmp);
        }

        System.out.println("User in midl of arr:\n" + users.get_user_by_index(size / 2));
        System.out.println("User by id 12:\n" + users.get_user_by_id(12));
        System.out.println("Total users in the array and number users been add:\n" + YELLOW + users.length() + RESET + " == " + YELLOW + size + RESET);
    }
}
