package com.example.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulwebservices.domain.HelloWorldDomain;

@RestController
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	public String helloWorld()
	{
		return "hello harry";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldDomain helloworldbean()
	{
		return new HelloWorldDomain("hi harry ,how are you");
	}
	
	@GetMapping(path="/hello-world-pathvariable/{str}/pv2/{str2}")
	public HelloWorldDomain helloworldpathvariable(@PathVariable String str,@PathVariable String str2)
	{
		return new HelloWorldDomain("hi harry ,how are you"+" "+str+" "+str2);
	}
	
	
	
}
