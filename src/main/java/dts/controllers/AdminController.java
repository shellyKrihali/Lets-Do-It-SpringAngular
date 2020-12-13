package dts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boundaries.OperationBoundary;
import boundaries.UserBoundary;
import dts.data.UserRole;
import dts.logic.ItemService;
import dts.logic.OperationService;
import dts.logic.UsersService;

@RestController
public class AdminController {

	private ItemService itemService;
	private UsersService usersService;
	private OperationService operationService;
	
	
	@Autowired
	public AdminController(ItemService itemService, UsersService usersService, OperationService operationService) {
		this.itemService = itemService;
		this.usersService = usersService;
		this.operationService = operationService;
	}

	@RequestMapping(
			method = RequestMethod.DELETE, 
			path = "/dts/admin/users/{adminSpace}/{adminEmail}")
	public void deleteAllUsers(
			@PathVariable("adminSpace") String adminSpace,
			@PathVariable("adminEmail") String adminEmail) {
		if (this.isAdmin(adminSpace, adminEmail)) 
			this.usersService.deleteAllUsers(adminSpace, adminEmail);
	}

	@RequestMapping(
			method = RequestMethod.DELETE, 
			path = "/dts/admin/items/{adminSpace}/{adminEmail}")
	public void deleteAllItems(
			@PathVariable("adminSpace") String adminSpace,
			@PathVariable("adminEmail") String adminEmail) {
		if (this.isAdmin(adminSpace, adminEmail)) 
			this.itemService.deleteAll(adminSpace, adminEmail);
	}

	@RequestMapping(
			method = RequestMethod.DELETE, 
			path = "/dts/admin/operations/{adminSpace}/{adminEmail}")
	public void deleteAllOperations(
			@PathVariable("adminSpace") String adminSpace,
			@PathVariable("adminEmail") String adminEmail) {
		if (this.isAdmin(adminSpace, adminEmail)) 
			this.operationService.deleteAllActions(adminSpace, adminEmail);
	}

	@RequestMapping(
			method = RequestMethod.GET, 
			path = "/dts/admin/users/{adminSpace}/{adminEmail}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] exportAllUsers(
			@PathVariable("adminSpace") String adminSpace,
			@PathVariable("adminEmail") String adminEmail) {
		UserBoundary[] array = {};
		if (this.isAdmin(adminSpace, adminEmail)) {
			List<UserBoundary> listUser = this.usersService.getAllUsers(adminSpace, adminEmail);
			array = new UserBoundary[listUser.size()];
			listUser.toArray(array);
		}
		return array;
	}

	@RequestMapping(
			method = RequestMethod.GET, 
			path = "/dts/admin/operations/{adminSpace}/{adminEmail}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationBoundary[] exportAllOperations(
			@PathVariable("adminSpace") String adminSpace,
			@PathVariable("adminEmail") String adminEmail) {
		OperationBoundary[] array = {};
		if (this.isAdmin(adminSpace, adminEmail)) {
			List<OperationBoundary> listBoundary = this.operationService.getAllOperations(adminSpace, adminEmail);
			array = new OperationBoundary[listBoundary.size()];
			listBoundary.toArray(array);
		}
		return array;
	}

	private boolean isAdmin(String adminSpace, String adminEmail) {
		if (this.usersService.login(adminSpace, adminEmail).getRole().equals(UserRole.ADMIN)) 
			return true;
		else {
			System.err.println("User" + adminEmail + "is not an Admin user");
			return false;
		}
	}
}
