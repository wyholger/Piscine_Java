package com.company;

public class Program {

    public static void main(String[] args) {
        int num = 479598;
        int len = 0;
        int dec = 10;
        int sum = 0;
        int tmp_len;

        while (num / dec > 0)
        {
            dec *= 10;
//            sum += num - num / (dec * len);
            len++;
        }
        while (len > 0)
        {
            tmp_len = len - 1;
            dec = 10;
            while (tmp_len-- > 0)
            {
                dec *= 10;
            }
            sum += num - (num / dec);
            len--;
        }
        System.out.println(len);
        System.out.println(sum);
    }
}
