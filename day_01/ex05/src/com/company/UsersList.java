package com.company;

public interface UsersList
{
	Integer DEFAULT_SIZE = 10;
	Integer DEFAULT_SCALE = 2;

	void add_user(User user);
	User get_user_by_id(Integer id);
	User get_user_by_index(int index);
	Integer length();
}
