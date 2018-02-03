package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/demo")
public class UserResourceService  {

	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;  
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String username, @RequestParam String password)
	{
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request	
		User n = new User();
		n.setUserName(username);
		n.setPassword(password);
		userRepository.save(n);
		return "Saved";
	}

	 @RequestMapping("/update")
	 public @ResponseBody String updateUser(@RequestParam Long id , @RequestParam String username , @RequestParam String password)
	 {
		 try
		 {
			 User user = userRepository.findOne(id);
			 if(user == null)
			 {
				 return "User dosent exist";
			 }
			 else
			 {
				 user.setUserName(username);
				 user.setPassword(password);
				 userRepository.save(user);				 
			 }			
		 }
		 catch (Exception e) { 
			return "Error in  Updating User : "+ e.toString();
		 }
		 return "User Updated!";		 
	 }
	 
	@RequestMapping("/delete") 
	 public @ResponseBody String delete(@RequestParam Long id) 
	 {
		 try
		 {
			 User user = userRepository.findOne(id);
			 if( user == null)
			 {
				 return "User dosent exist";
			 }
			 else
				 userRepository.delete(user);
	     }
	     catch (Exception ex) 
	     {
	    	 return "Error deleting the user: " + ex.toString();
	     }
	     return "User successfully deleted!";
	  }	
	 
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers()
	{
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}	

	@GetMapping(path="/profile/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUserProfile (@RequestParam Long userid, @RequestParam String name, @RequestParam String phone , @RequestParam String address)
	{
		User user = userRepository.findOne(userid);
		Profile profile = new Profile( name, phone, address);		       
        user.setProfile(profile);// Set child reference(userProfile) in parent entity(user)       
        profile.setUser(user);// Set parent reference(user) in child entity(userProfile)       
        userRepository.save(user);// Save Parent Reference (which will save the child as well)
		return "Saved";
	}
	
	@GetMapping(path="/profile/update") // Map ONLY GET Requests
	public @ResponseBody String updateProfile (@RequestParam Long profileid, @RequestParam String name, @RequestParam String phone , @RequestParam String address)
	{
		try 
		{			
			Profile profile = profileRepository.findOne(profileid);
			if(profile == null)
			{
				return "Your profile disnt exist";			
			}
			else
			{
				profile.setName(name);
				profile.setPhone(phone);
				profile.setAddress(address);
		        profileRepository.save(profile);				
			}

		} catch (Exception e) {
			return "error in update profile " + e.toString();
		}
		return "profile updated";
	}
	
	 @RequestMapping("/profile/delete") 
	 public @ResponseBody String deleteProfile(@RequestParam Long id) 
	 {
		 try
		 {
			 Profile profile = profileRepository.findOne(id);
			 if(profile == null)
			 {
				    return "Profile dosent exist";				 				 
			 }
			 else
			 {
				 profileRepository.delete(profile); 
			 }
	     }
	     catch (Exception ex) {
	    	 return "Error deleting the profile: " + ex.toString();
	     }
	    return "Profile successfully deleted!";
	  }	
	
	@GetMapping(path="/profile/all") // Map ONLY GET Requests
	public @ResponseBody Iterable<Profile> getAllProfile ()
	{
		return profileRepository.findAll();
	}
		
}
