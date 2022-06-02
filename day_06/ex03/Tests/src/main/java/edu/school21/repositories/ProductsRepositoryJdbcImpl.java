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
		String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id=?";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		Optional<Product> optional_product = Optional.empty();

		try
		{
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_ID);
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
		String SQL_UPDATE = "UPDATE products SET name = ?, price = ? WHERE id = ?";
		PreparedStatement prepared_statement;
		try
		{
			prepared_statement = connection.prepareStatement(SQL_UPDATE);
			prepared_statement.setString(1, product.getName());
			prepared_statement.setLong(2, product.getPrice());
			prepared_statement.setLong(3, product.getId());
			prepared_statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void save(Product product)
	{
		String SQL_INSERT = "INSERT INTO products(name, price) VALUES (?, ?)";
		PreparedStatement prepared_statement;
		ResultSet result_set;

		try
		{
			prepared_statement = connection.prepareStatement(SQL_INSERT);
			prepared_statement.setString(1, product.getName());
			prepared_statement.setLong(2, product.getPrice());
			prepared_statement.execute();
			result_set = connection.createStatement().executeQuery("CALL IDENTITY()");
			if (result_set.next()) {
				product.setId(result_set.getLong(1));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id)
	{
		String SQL_DELETE = "DELETE FROM products WHERE id = ?";
		PreparedStatement prepared_statement;

		try
		{
			prepared_statement = connection.prepareStatement(SQL_DELETE);
			prepared_statement.setLong(1, id);
			prepared_statement.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}
