package com.epam.library.controller;

import java.util.Scanner;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

public class View {
	
	public View() {
		
	}
	
	public void printBookArray(Book[] arrayBook) {
		if (arrayBook != null) {
			for (int i = 0; i < arrayBook.length; i++){
				System.out.println(arrayBook[i]);
			}
		}
	}
	
	public void answerFillTable (boolean result) {
		if (result == false) {
			System.out.println("Table 'employee_book' don't fill!");
		} else {
			System.out.println("Table 'employee_book' filled!");
		}
	}
	
	public String inputTitle(){
		String title = "";
		String inputTitle = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("Input title of the book and press Enter: ");
		inputTitle = sc.nextLine();
		for (int i = 0; i < inputTitle.length(); i++){
			char ch = inputTitle.charAt(i);
			if (ch != '*') {
				title = title + ch;	
			} else {
				break;
			}
		}
		return title;
	}
	
	public void printBook(Book book){
		System.out.println(book);
	}
	
	public Book inputBook(){
		Book book = new Book();
		Scanner sc = new Scanner(System.in);
		System.out.print("Input title of the book and press Enter: ");
		book.setTitle(sc.nextLine());
		System.out.print("Input author of the book and press Enter: ");
		book.setAuthor(sc.nextLine());
		System.out.print("Input year publishing of the book and press Enter: ");
		book.setYearPublishing(sc.nextInt());
		System.out.println("");
		return book;
	}
	
	public void answerOperation (boolean result) {
		if (result == false) {
			System.out.println("Operation isn't successful!");
		} else {
			System.out.println("Operation is successful!");
		}
	}
	
	public void printEmployeeArray(String[] arrayEmployee) {
		
		if (arrayEmployee != null) {
			if(arrayEmployee.length == 0){
				System.out.println("There are not employees!");
			}
			for (int i = 0; i < arrayEmployee.length; i++){
				System.out.println(arrayEmployee[i]);
			}

		} 
	}
	
	
}
