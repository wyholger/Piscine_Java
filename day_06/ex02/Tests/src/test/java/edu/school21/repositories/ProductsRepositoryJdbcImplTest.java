package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ProductsRepositoryJdbcImplTest
{
	private final int EXPECTED_SIZE = 5;
	private final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
			new Product(0L, "Milk", 68L),
			new Product(1L, "Cheese", 184L),
			new Product(2L, "Juice", 99L),
			new Product(3L, "Potatoes", 38L),
			new Product(4L, "Melon", 200L)
			);
	final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "Milk", 68L);
//	final Product EXPECTED_UPDATED_PRODUCT = ...;
	private Connection connection;

	@BeforeEach
	public void reset_db_and_get_new_connection() throws SQLException
	{
		this.connection = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("schema.sql")
				.addScript("data.sql")
				.build().getConnection();
	}

	@Test
	public void find_all_test()
	{
		ProductsRepositoryJdbcImpl products_repo = new ProductsRepositoryJdbcImpl(connection);

		List<Product> actual_list = products_repo.findAll();
		int actual_size = actual_list.size();
		Assertions.assertEquals(EXPECTED_SIZE, actual_size);
		Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, actual_list);
	}

	@Test
	public void find_by_id_test()
	{
		ProductsRepositoryJdbcImpl products_repo = new ProductsRepositoryJdbcImpl(connection);
		Optional<Product> optional_product;
		Product product;

		optional_product = products_repo.findById(0L);
		product = optional_product.orElse(null);

		Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, product);
	}
}
