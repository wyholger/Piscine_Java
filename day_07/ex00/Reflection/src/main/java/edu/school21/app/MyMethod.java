package edu.school21.app;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MyMethod
{
	private Method	method;
	private Type[]	types;
	private String	string_variation;

	public MyMethod()
	{
	}

	public void setMethod(Method method)
	{
		this.method = method;
	}

	public void setTypes(Type[] types)
	{
		this.types = types;
	}

	public void setString_variation(String string_variation)
	{
		this.string_variation = string_variation;
	}

	public Method getMethod()
	{
		return method;
	}

	public Type[] getTypes()
	{
		return types;
	}

	public String getString_variation()
	{
		return string_variation;
	}
}
