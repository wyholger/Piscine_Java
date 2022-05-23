package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;
import edu.school21.printer.logic.ArgParser;

import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static edu.school21.printer.logic.Colors.*;
import static edu.school21.printer.logic.ImageToChar.image_bmp_to_char;
import static edu.school21.printer.logic.ValidatorArgs.colors_back;


public class Program
{
	public static void main(String[] args)
	{
		int[][] map;
		ArgParser arguments = new ArgParser();

		try
		{
			JCommander.newBuilder()
					.addObject(arguments)
					.build()
					.parse(args);
		}
		catch (ParameterException ex)
		{
			exception_handler(ex);
		}

		if ((map = image_bmp_to_char('b', 'w')) == null)
			return;
		print_img(map, arguments);
	}

	private static void color_case()
	{
		System.out.println(colorize("This text will be yellow on magenta", YELLOW_TEXT(), MAGENTA_BACK()));
		System.out.println("\n");

// Use Case 2: compose Attributes to create your desired format
		Attribute[] myFormat = new Attribute[]{RED_TEXT(), YELLOW_BACK(), BOLD()};
		System.out.println(colorize("This text will be red on yellow", myFormat));
		System.out.println("\n");

// Use Case 3: AnsiFormat is syntactic sugar for an array of Attributes
		AnsiFormat fWarning = new AnsiFormat(GREEN_TEXT(), BLUE_BACK(), BOLD());
		System.out.println(colorize("AnsiFormat is just a pretty way to declare formats", fWarning));
		System.out.println(fWarning.format("...and use those formats without calling colorize() directly"));
		System.out.println("\n");

// Use Case 4: you can define your formats and use them throughout your code
		AnsiFormat fInfo = new AnsiFormat(CYAN_TEXT());
		AnsiFormat fError = new AnsiFormat(YELLOW_TEXT(), RED_BACK());
		System.out.println(fInfo.format("This info message will be cyan"));
		System.out.println("This normal message will not be formatted");
		System.out.println(fError.format("This error message will be yellow on red"));
		System.out.println("\n");

// Use Case 5: we support bright colors
		AnsiFormat fNormal = new AnsiFormat(MAGENTA_BACK(), YELLOW_TEXT());
		AnsiFormat fBright = new AnsiFormat(BRIGHT_MAGENTA_BACK(), BRIGHT_YELLOW_TEXT());
		System.out.println(fNormal.format("You can use normal colors ") + fBright.format(" and bright colors too"));

// Use Case 6: we support 8-bit colors
		System.out.println("Any 8-bit color (0-255), as long as your terminal supports it:");
		for (int i = 0; i <= 255; i++) {
			Attribute txtColor = TEXT_COLOR(i);
			System.out.print(colorize(String.format("%4d", i), txtColor));
		}
		System.out.println("\n");

// Use Case 7: we support true colors (RGB)
//		System.out.println("Any TrueColor (RGB), as long as your terminal supports it:");
//		for (int i = 0; i <= 300; i++) {
//			Attribute bkgColor = BACK_COLOR(randomInt(255), randomInt(255), randomInt(255));
//			System.out.print(colorize("   ", bkgColor));
//		}
//		System.out.println("\n");

// Credits
		System.out.print("This example used JColor 5.0.0   ");
		System.out.print(colorize("\tMADE ", BOLD(), BRIGHT_YELLOW_TEXT(), GREEN_BACK()));
		System.out.println(colorize("IN PORTUGAL\t", BOLD(), BRIGHT_YELLOW_TEXT(), RED_BACK()));
		System.out.println("I hope you find it useful ;)");

	}

	private static void print_img(int[][] map, ArgParser arguments)
	{
		AnsiFormat white_color = new AnsiFormat(colors_back.get(arguments.getWhite()));
		AnsiFormat black_color = new AnsiFormat(colors_back.get(arguments.getBlack()));

		for (int[] ints : map) {
			for (int anInt : ints) {
				if ((char) anInt == 'b')
					System.out.print(black_color.format(" "));
				else
					System.out.print(white_color.format(" "));
			}
			System.out.println();
		}
	}

	private static void exception_handler(ParameterException ex)
	{
		AnsiFormat f_red = new AnsiFormat(RED_TEXT());
		AnsiFormat f_yellow = new AnsiFormat(YELLOW_TEXT());
		AnsiFormat f_magenta = new AnsiFormat((MAGENTA_TEXT()));
		if (ex.getMessage().startsWith("Error. Color "))
		{
			System.out.println(RED.format(ex.getMessage()));
			System.out.println(YELLOW.format("Example walid colors:"));
			for (Map.Entry<String, Attribute> entry: colors_back.entrySet())
				System.out.println("    " + entry.getKey());
		}
		else
		{
			System.out.println(RED.format("Error. Format arguments error."));
			System.out.print(YELLOW.format("Example: "));
			System.out.println(MAGENTA.format("java Program --white=RED --black=GREEN"));
		}
	}
}
