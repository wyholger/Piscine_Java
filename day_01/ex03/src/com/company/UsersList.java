package com.company;

public interface UsersList
{
	Integer DEFAULT_SIZE = 10;
	Integer DEFAULT_SCALE = 2;

	public void add_user(User user);
	public User get_user_by_id(Integer id);
	public User get_user_by_index(Integer index);
	public Integer length();
}
