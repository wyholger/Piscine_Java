package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Objects;

import static edu.school21.printer.logic.ColorMy.*;

public class ImageToChar
{
	public static int[][] image_bmp_to_char(char black, char white)
	{
//		InputStream is = open_file_img();
//		if (is == null)
//			return null;

		BufferedImage img = init_buffered_img();
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

	private static BufferedImage init_buffered_img()
	{
		BufferedImage buffer;
		try
		{
			URL url = ImageToChar.class.getResource("/resources/it.bmp");
			if (url == null)
			{
				System.out.println(RED + "Error. Read file error." + RESET);
				return null;
			}
			buffer = ImageIO.read(url);
		}
		catch (IOException e)
		{
			System.out.println(RED + "Error. Read file error." + RESET);
			return null;
		}
		return buffer;
	}


//	private static InputStream open_file_img()
//	{
//		InputStream is;
//		ImageToChar.class.getResourceAsStream("/resources/it.bmp");
//		if (is == null)
//			System.out.println(RED + "Error. Open file error." + RESET);
//		return is;
//	}
}
