package com.epam.library;

import java.util.Scanner;

import com.epam.library.controller.Menu;
import com.epam.library.service.BookServiceImpl;
import com.epam.library.service.ServiceException;



public class Main {
	private static final int EXIT = 9;

	public static void main(String[] args) throws ServiceException {
		welcomScreen();
		BookServiceImpl service = new BookServiceImpl();
		try {
			inputNumberMenu(service);	
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}	
	}
		
	public static void welcomScreen(){
		System.out.println("Your can do it: ");
		System.out.println("1 - To fill table 'employee_book' randomly generated values (user_id and book_id)");
		System.out.println("2 - To view all books");
		System.out.println("3 - To view one of the book (input title)");
		System.out.println("4 - To add the book (input title, athor, year publishing: for example, 'Sbornik', 'Lermontov M.U.', 2013)");
		System.out.println("5 - To delete the book (input title)");
		System.out.println("6 - To rename the book (input old title, new title and press Enter)");	
		System.out.println("7 - To view employees who have read more than 1 book");
		System.out.println("8 - To view employees who have read less than or equal to 2 books");
		System.out.println("9 - Exit");
		System.out.print("Yours choise: ");
			
	}
		
		public static void inputNumberMenu(BookServiceImpl service) throws ServiceException{
			Scanner sc = new Scanner(System.in);
			int numberOperation = sc.nextInt();
			if (numberOperation == EXIT) {
				System.out.println("Good bye!");
			} else {
				Menu menu = new Menu();
				menu.choiseMenu(numberOperation, service);
				welcomScreen();
				inputNumberMenu(service);
			}
			if (sc != null) {
			sc.close();
			}
		}
		
}
		
	

//		b.updateBookTitle("Sb", "Sbornik");

