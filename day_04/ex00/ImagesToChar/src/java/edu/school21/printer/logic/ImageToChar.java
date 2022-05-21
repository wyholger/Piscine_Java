package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static edu.school21.printer.logic.ColorMy.*;

public class ImageToChar
{
	public static int[][] image_bmp_to_char(String path_to_img, char black, char white)
	{
		FileInputStream fis = open_file_img(path_to_img);
		if (fis == null)
			return null;

		BufferedImage img = init_buffered_img(fis);
		if (img == null)
			return null;

		int[][] map = new int[img.getHeight()][img.getWidth()];

		for (int i = 0; i < img.getHeight(); i++)
		{
			for (int j = 0; j < img.getWidth(); j++)
			{
				int color = img.getRGB(j, i);
				if (color == Color.BLACK.getRGB())
					map[i][j] = black;
				else
					map[i][j] = white;
			}
		}
		return map;
	}

	private static BufferedImage init_buffered_img(FileInputStream fis)
	{
		BufferedImage buffer;
		try
		{
			buffer = ImageIO.read(fis);
		}
		catch (IOException e)
		{
			System.out.println(RED + "Error. Read file error." + RESET);
			return null;
		}
		return buffer;
	}


	private static FileInputStream open_file_img(String path_to_img)
	{
		FileInputStream fis;
		try
		{
			fis = new FileInputStream(path_to_img);
		}
		catch (FileNotFoundException e)
		{
			System.out.println(RED + "Error. " + YELLOW + path_to_img + RED + "\nOpen file error." + RESET);
			return null;
		}
		return fis;
	}
}
