package com.epam.library.controller;

import com.epam.library.service.BookServiceImpl;
import com.epam.library.service.ServiceException;

public class Menu {
	public Menu (){  
	}
	
	public void choiseMenu(int number, BookServiceImpl  service) throws ServiceException{
		View newView = new View();
		switch (number) {
			case 1: 
				newView.answerFillTable(service.fillTableEmployeeBook());
				break;
			case 2: 
				newView.printBookArray(service.viewAllBook());
				break;	
			case 3: 
				newView.printBook(service.viewOneBook(newView.inputTitle()));
				break;	
			case 4: 
				newView.answerOperation(service.viewAddBook(newView.inputBook()));
				break;	
			case 5: 
				newView.answerOperation(service.deleteBook(newView.inputTitle()));
				break;
			case 6: 
				newView.answerOperation(service.updateBookTitle(newView.inputTitle(), newView.inputTitle()));
				break;
			case 7: 
				newView.printEmployeeArray(service.viewEmployee(1));
				break;	
			case 8: 
				newView.printEmployeeArray(service.viewEmployee(2));
				break;	
			default:
				System.out.println("Good bye!");
				break;
		}	
	}
	
}
