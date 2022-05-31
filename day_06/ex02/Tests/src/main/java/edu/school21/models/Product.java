package edu.school21.models;

import java.util.Objects;

public class Product
{
	private Long id;
	private String name;
	private Long price;

	public Product(String name, Long price)
	{
		this.name = name;
		this.price = price;
	}

	public Product(Long id, String name, Long price)
	{
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, price);
	}

	@Override
	public String toString()
	{
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}
