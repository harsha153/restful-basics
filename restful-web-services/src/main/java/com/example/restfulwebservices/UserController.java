package com.example.restfulwebservices;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import javax.validation.Valid;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restfulwebservices.domain.User;
import com.example.restfulwebservices.exceptions.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retrieveAllusers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUsrById(@PathVariable Integer id) {
		User user = userDaoService.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("id+" + id);
		}

		Resource<User> resource = new Resource<User>(user);
		// a library of APIs that you can use to create links that point to
		// Spring MVC controllers, build up resource representations

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllusers());

		resource.add(linkTo.withRel("all-Users"));
		return resource;

	}

	@DeleteMapping("/users/{id}")
	public void deleteUsrById(@PathVariable Integer id) {
		User user = userDaoService.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("id+" + id);
		}

	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUsers(@Valid @RequestBody User user) {
		User us = userDaoService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(us.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
