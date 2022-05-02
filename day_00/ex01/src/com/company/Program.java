package com.company;

import java.util.Scanner;

public class Program {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        final int   num = scan.nextInt();
        boolean     status = false;
        int         step = 0;

        if (num <= 1 )
        {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        if (num % 2 == 0)
        {
            step++;
            System.out.println(status + " " + step);
            System.exit(0);
        }
        for (int i = 2; i * i <= num; i++)
        {
            step++;
            if (num % i == 0)
            {
                step--;
                status = true;
                break;
            }
        }
        step++;
        System.out.println(!status + " " + step);
        System.exit(0);
    }

}
