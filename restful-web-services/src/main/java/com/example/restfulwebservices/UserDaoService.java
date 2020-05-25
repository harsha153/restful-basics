package com.example.restfulwebservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.restfulwebservices.domain.User;

@Component
public class UserDaoService {

	private static List<User>users= new ArrayList<>();
	
	

	private static Integer userCount=3;
	static{
		users.add(new User(1,"harsha",new Date()));
		users.add(new User(2,"rakhee",new Date()));
		users.add(new User(3,"ragi",new Date()));	
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User findOne(Integer id)
	{
		User user=users.stream().filter(var->var.getId().equals(id)).findAny().orElse(null);
		return user;
	}
	
	public User saveUser(User user)
	{
		if(!ObjectUtils.isEmpty(user)&&user.getId()==null)
		{
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User deleteById(Integer id)
	{
		
        Iterator<User>iterator=users.iterator();
        
        while(iterator.hasNext())
        {
         User user=iterator.next();
         if(user.getId()==id)
         {
        	 iterator.remove();
        	 return user;
         }
        }
        return null;
	}
	
	
}
