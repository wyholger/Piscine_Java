package edu.school21.app;

import edu.school21.util.Scan;

import java.lang.reflect.*;
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
		Object created_obj;
		MyMethod[] my_methods;

		print_classes_list();
		do
			find_class = scan_class_name(scan);
		while (find_class == null);
		my_methods = print_fields_and_methods(find_class);
		created_obj = create_object(find_class);
		fill_object(find_class, created_obj, scan);
		re_fill_object(find_class, created_obj, scan);
		call_method(created_obj, scan, my_methods);
	}

	private void call_method(Object created_obj, Scan scan, MyMethod[] my_methods)
	{
		String		name_method;
		Method		found_method = null;
		Object[]	args;
		Object		arg;
		Parameter[] parameters;
		Object		result;
		MyMethod	met = null;

		scan.scan_line();
		do
		{
			System.out.println(BLUE + "Enter name of the method for call:" + RESET);
			name_method = scan.scan_line();
			for (MyMethod m: my_methods)
			{
				if (m.getString_variation().equals(name_method))
					met = m;
			}
			if (met != null)
				found_method = met.getMethod();
			else
			{
				System.out.println(RED + "Method with name and signature " + YELLOW + name_method + RED + " not found." + RESET);
				System.out.println(RED + "Try again." + RESET);
			}
		}
		while (found_method == null);
		parameters = found_method.getParameters();
		args = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++)
		{
			String type_name = parameters[i].getType().getSimpleName();
			do
			{
				System.out.println(BLUE + "Enter " + YELLOW + type_name + BLUE + " value:");
				String value = scan.scan_word();
				arg = convert_value(type_name, value);
			}
			while (arg == null);
			args[i] = arg;
		}
		try
		{
			found_method.setAccessible(true);
			result = found_method.invoke(created_obj, args);
			found_method.setAccessible(false);
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			e.printStackTrace();
			return;
		}
		if (!found_method.getReturnType().equals(void.class))
		{
			System.out.println(BLUE + "Method returned:" + RESET);
			System.out.println(YELLOW + "" + result + RESET);
		}
	}

	private void re_fill_object(Object find_class, Object created_obj, Scan scan)
	{
		Field[] declared_fields = find_class.getClass().getDeclaredFields();
		Field	find_field;
		Object	obj_arg;
		String	fill_name;
		String	new_value;

		do
		{
			System.out.println(BLUE + "Enter name of the field for changing:" + RESET);
			fill_name = scan.scan_word();
			find_field = validate_fill_name(declared_fields, fill_name);
		}
		while (find_field == null);
		do
		{
			System.out.println(BLUE + "Enter " + YELLOW + find_field.getType().getSimpleName() + BLUE + " value:");
			new_value = scan.scan_word();
			obj_arg = convert_value(find_field.getType().getSimpleName(), new_value);
		}
		while (obj_arg == null);
		try
		{
			find_field.setAccessible(true);
			find_field.set(created_obj, obj_arg);
			find_field.setAccessible(false);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		System.out.println(BLUE + "Object updated: " + YELLOW + created_obj.toString() + RESET);
		print_delimiter();
	}

	private Field validate_fill_name(Field[] fields, String fill_name)
	{
		for (Field f: fields)
		{
			if (f.getName().equals(fill_name))
				return f;
		}
		System.out.println(RED + "Field with name " + YELLOW + fill_name + RED + " not found." + RESET);
		System.out.println(RED + "Try again." + RESET);
		return null;
	}

	private Object create_object(Object find_class)
	{
		Class clazz = find_class.getClass();

		try
		{
			return clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private void fill_object(Object find_class, Object created_obj, Scan scan)
	{
		System.out.println(BLUE + "Let’s create an object." + RESET);
		Field[] declared_fields = find_class.getClass().getDeclaredFields();
		Object obj_arg;

		for (Field f : declared_fields)
		{
			do
			{
				System.out.println(YELLOW + f.getName() + ":" + RESET);
				String value = scan.scan_word();
				obj_arg = convert_value(f.getType().getSimpleName(), value);
			}
			while (obj_arg == null);
			try
			{
				f.setAccessible(true);
				f.set(created_obj, obj_arg);
				f.setAccessible(false);
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println(BLUE + "Object created: " + YELLOW + created_obj.toString() + RESET);
		print_delimiter();
	}

	private Object convert_value(String type, String value)
	{
		try {
			switch (type) {
				case "Integer":
				case "int":
					return (Integer.parseInt(value));
				case "Long":
					return (Long.parseLong(value));
				case "Boolean":
					return (Boolean.parseBoolean(value));
				case "Float":
					return (Float.parseFloat(value));
				case "Double":
					return (Double.parseDouble(value));
				case "Short":
					return (Short.parseShort(value));
				case "String":
					return (value);
				default:
					return null;
			}
		}
		catch (Exception e)
		{
			System.out.println(RED + "Can't cast " + YELLOW + value + RED + " to " + YELLOW + type + RESET);
			System.out.println(RED + "Try again." + RESET);
			return null;
		}
	}

	private MyMethod[] print_fields_and_methods(Object find_class)
	{
		Field[] declared_fields = find_class.getClass().getDeclaredFields();
		Method[] methods = find_class.getClass().getDeclaredMethods();
		MyMethod[] my_methods = new MyMethod[methods.length];
		StringBuilder[] methods_str = new StringBuilder[methods.length];
		int	j = 0;

		System.out.println(BLUE + "fields:" + RESET);
		for (Field f : declared_fields)
			System.out.println("       " + YELLOW + f.getType().getSimpleName() + " " + f.getName() + RESET);
		System.out.println(BLUE + "methods:" + RESET);
		for (Method m: methods)
		{
			my_methods[j] = new MyMethod();
			my_methods[j].setMethod(m);
			methods_str[j] = new StringBuilder(m.getName());
			methods_str[j].append('(');
			System.out.print("       " + YELLOW + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
			my_methods[j].setTypes(m.getParameterTypes());
			Class<?>[] arg_types = m.getParameterTypes();
			int i = 0;
			for (Class<?> c: arg_types)
			{
				if (i > 0 && i < arg_types.length)
				{
					methods_str[j].append(", ");
					System.out.print(", ");
				}
				methods_str[j].append(c.getSimpleName());
				System.out.print(c.getSimpleName());
				i++;
			}
			methods_str[j].append(')');
			my_methods[j].setString_variation(methods_str[j].toString());
			System.out.println(")" + RESET);
			j++;
		}
		print_delimiter();
		return my_methods;
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
