package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository
{
	private final Connection connection;

	public ProductsRepositoryJdbcImpl(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public List<Product> findAll()
	{
		String SQL_SELECT_ALL = "SELECT * FROM products";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		List<Product> list = new LinkedList<>();

		try
		{
			prepared_statement = connection.prepareStatement(SQL_SELECT_ALL);
			result_set = prepared_statement.executeQuery();
			while (result_set.next())
			{
				list.add(new Product(
						result_set.getLong("id"),
						result_set.getString("name"),
						result_set.getLong("price")
				));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Optional<Product> findById(Long id)
	{
		String SQL_SELECT_ALL = "SELECT * FROM products WHERE id=?";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		Optional<Product> optional_product = Optional.empty();

		try
		{
			prepared_statement = connection.prepareStatement(SQL_SELECT_ALL);
			prepared_statement.setLong(1, id);
			result_set = prepared_statement.executeQuery();
			if (result_set.next())
			{
				Product product = new Product(
						result_set.getLong("id"),
						result_set.getString("name"),
						result_set.getLong("price")
				);
				optional_product = Optional.of(product);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return optional_product;
	}

	@Override
	public void update(Product product)
	{

	}

	@Override
	public void save(Product product)
	{

	}

	@Override
	public void delete(Long id)
	{

	}
}
