package org.jsp.userapp.controller;

import java.util.Scanner;

import org.jsp.userapp.dto.User;
import org.jsp.userapp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	static ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
	static UserService service = context.getBean(UserService.class);
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome To User App");

		while (true) {
			System.out.println();
			System.out.println("Please Select Option");
			System.out.println();
			System.out.println("1.Register\n2.Login\n3.View Profile\n4.Update Profile\n5.Delete Account\n6.Exit");
			int choice = sc.nextInt();
			switch (choice) {

			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				viewProfile();
				break;
			case 4:
				updateProfile();
				break;
			case 5:
				deleteAccount();
				break;
			case 6:
				System.out.println("Thank You\nVisit Again");
				return;
			default:
				System.err.println("You Have Chosen Incorrect Option");
				break;
			}

		}

	}

	public static void register() {
		System.out.println("Welcome To User Registration Form");
		System.out.println("Enter Your Name");
		String name = sc.nextLine();
		name = sc.nextLine();
		System.out.println("Enter The Phone Number");
		long phone = sc.nextLong();
		System.out.println("Enter The Password");
		String Password = sc.next();

		User user = new User();
		user.setName(name);
		user.setPhone(phone);
		user.setPassword(Password);
		user = service.saveUser(user);

		System.out.println("Your Account Created Successfully With Id : " + user.getId());
		System.out.println();
	}

	public static void login() {
		System.out.println("Welcome To User Login Page");
		System.out.println("Enter Your Phone Number");
		long phone = sc.nextLong();
		System.out.println("Enter Your Password");
		String password = sc.next();

		User recUser = service.verifyUser(phone, password);
		if (recUser != null) {
			System.out.println("-----------------------------------");
			System.out.println("Login Successfull");
			System.out.println("Welcome Mr." + recUser.getName());
			System.out.println("-----------------------------------");
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void viewProfile() {
		System.out.println("Welcome To User Profile Page\nPlease Enter Your ID to View Your Profile");
		int id = sc.nextInt();
		User user = service.findUserById(id);
		if (user != null) {
			System.out.println();
			System.out.println("----------------------------------------");
			System.out.println("ID : " + user.getId());
			System.out.println("Name : " + user.getName());
			System.out.println("Phone : " + user.getPhone());
			System.out.println("Password : " + user.getPassword());
			System.out.println("----------------------------------------");
			System.out.println();
		} else {
			System.err.println("Invalid Id " + id);
		}
	}

	public static void updateProfile() {
		System.out.println("Welcome To Update Details Page");
		System.out.println("Please Enter The ID ");
		int id = sc.nextInt();
		User recUser = service.findUserById(id);
		if (recUser != null) {

			System.out.println("Enter Your New Name");
			String name = sc.nextLine();
			name = sc.nextLine();
			System.out.println("Enter Your New Phone Number");
			long phone = sc.nextLong();
			System.out.println("Enter Your New Password");
			String Password = sc.next();

			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setPhone(phone);
			user.setPassword(Password);
			user = service.updateUser(user);

			System.out.println("Your Details Updated Successfully");

		} else {
			System.out.println("Invalid ID " + id);
		}

	}

	public static void deleteAccount() {
		System.out.println("Please Enter The Id To Delete Your Account");
		int id = sc.nextInt();
		User recUser = service.findUserById(id);
		if (recUser != null) {
			service.deleteUser(id);
			System.out.println("Your Account Deleted Successfully");

		} else {
			System.err.println("Invalid ID " + id);
		}
	}
}
