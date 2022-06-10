package edu.school21.app;

import edu.school21.classes.Airplane;
import edu.school21.classes.User;

import java.util.ArrayList;
import java.util.List;

public class Program
{
	public static void main(String[] args)
	{
		List<Object> classes = init_classes();
		Menu menu = new Menu(classes);

		menu.menu_start();
	}

	public static List<Object> init_classes()
	{
		List<Object> classes = new ArrayList<>();
		classes.add(new User());
		classes.add(new Airplane());
		return classes;
	}
}
