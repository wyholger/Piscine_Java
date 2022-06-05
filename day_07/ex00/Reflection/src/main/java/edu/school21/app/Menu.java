package edu.school21.app;

import edu.school21.util.Scan;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static edu.school21.util.Color.*;

public class Menu
{
	private final List<Object> classes;

	public Menu(List<Object> classes)
	{
		this.classes = classes;
	}

	public void menu_start()
	{
		Scan scan = new Scan();
		Object find_class;

		print_classes_list();
		do
			find_class = scan_class_name(scan);
		while (find_class == null);
		print_fields_and_methods(find_class);
	}

	private void print_fields_and_methods(Object find_class)
	{
		Field[] declared_fields = find_class.getClass().getDeclaredFields();
		Method[] methods = find_class.getClass().getDeclaredMethods();
		System.out.println(BLUE + "fields:" + RESET);
		for (Field f : declared_fields)
			System.out.println("       " + YELLOW + f.getType().getSimpleName() + " " + f.getName());
		System.out.println(BLUE + "methods:" + RESET);
		for (Method m: methods)
			System.out.println("       " + YELLOW + m. + " " + f.getName());

	}

	private Object scan_class_name(Scan scan)
	{
		Object find_class;
		String class_name;

		System.out.println(BLUE + "Enter class name:" + RESET);
		class_name = scan.scan_word();
		find_class = search_class_in_list_classes(class_name);
		if (find_class == null)
		{
			System.out.println(RED + "Error. Class with name " + YELLOW + class_name + RED + " not found." + RESET);
			System.out.println(RED + "Try again." + RESET);
		}
		else
			print_delimiter();
		return find_class;
	}

	private Object search_class_in_list_classes(String class_name)
	{
		for (Object o : classes)
		{
			if (o.getClass().getSimpleName().equals(class_name))
				return o;
		}
		return null;
	}

	private void print_classes_list()
	{
		System.out.println(BLUE + "Classes:");
		for (Object o : classes)
		{
			System.out.println(YELLOW + "  - " + o.getClass().getSimpleName() + RESET);
		}
		print_delimiter();
	}

	private void print_delimiter()
	{
		System.out.println(PURPLE + "---------------------" + RESET);
	}
}
