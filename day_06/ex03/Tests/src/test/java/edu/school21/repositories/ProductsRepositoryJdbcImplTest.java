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
	final Product EXPECTED_UPDATED_PRODUCT = new Product(0L, "Update_milk", 12345678L);
	final Product EXPECTED_SAVE_PRODUCT = new Product( "Apple", 999L);
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

	@Test
	public void update_test()
	{
		ProductsRepositoryJdbcImpl products_repo = new ProductsRepositoryJdbcImpl(connection);
		Optional<Product> optional_product;
		Product product;

		optional_product = products_repo.findById(0L);
		product = optional_product.orElse(null);
		Assertions.assertNotEquals(EXPECTED_UPDATED_PRODUCT, product);
		if (product != null)
		{
			product.setName(EXPECTED_UPDATED_PRODUCT.getName());
			product.setPrice(EXPECTED_UPDATED_PRODUCT.getPrice());
			products_repo.update(product);
		}
		optional_product = products_repo.findById(0L);
		product = optional_product.orElse(null);
		Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, product);
	}

	@Test
	public void save_test()
	{
		ProductsRepositoryJdbcImpl products_repo = new ProductsRepositoryJdbcImpl(connection);
		Product product = new Product(EXPECTED_SAVE_PRODUCT.getName(), EXPECTED_SAVE_PRODUCT.getPrice());

		products_repo.save(product);
		EXPECTED_SAVE_PRODUCT.setId(product.getId());
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, products_repo.findById(product.getId()).orElse(null));
		Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, product);
	}

	@Test
	public void delete_test()
	{
		ProductsRepositoryJdbcImpl products_repo = new ProductsRepositoryJdbcImpl(connection);
		Product product = products_repo.findById(0L).orElse(null);

		Assertions.assertNotNull(product);
		products_repo.delete(product.getId());
		product = products_repo.findById(0L).orElse(null);
		Assertions.assertNull(product);
	}
}
