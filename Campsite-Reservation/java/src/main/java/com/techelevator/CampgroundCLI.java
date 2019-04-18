package com.techelevator;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import com.techelevator.view.Menu;

import com.techelevator.campground.*;
import com.techelevator.park.*;
import com.techelevator.reservation.*;
import com.techelevator.site.*;

public class CampgroundCLI {
	
	private static final String MAIN_MENU_OPTION_VIEW_PARKS = "View Parks";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] {MAIN_MENU_OPTION_VIEW_PARKS, MAIN_MENU_OPTION_EXIT};
	
	private static final String COMMAND_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String COMMAND_MENU_OPTION_SEARCH_RESERVATION = "Search for Reservation";
	private static final String COMMAND_MENU_OPTION_EXIT = "Return to Previous Screen";
	private static final String[] COMMAND_MENU_OPTIONS = new String[] {COMMAND_MENU_OPTION_VIEW_CAMPGROUNDS, COMMAND_MENU_OPTION_SEARCH_RESERVATION, COMMAND_MENU_OPTION_EXIT};
	
	private static final String CAMPGROUND_MENU_OPTION_SEARCH_AVAILABLE_RESERVATION = "Search for Available Reservation";
	private static final String CAMPGROUND_MENU_OPTION_EXIT = "Return to Previous Screen";
	private static final String[] CAMPGROUND_MENU_OPTIONS = new String[] {CAMPGROUND_MENU_OPTION_SEARCH_AVAILABLE_RESERVATION, CAMPGROUND_MENU_OPTION_EXIT};
	
	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private ParkDAO parkDAO;
	private ReservationDAO reservationDAO;
	private SiteDAO siteDAO;
	private Park selectedPark = new Park();
	DecimalFormat df = new DecimalFormat("#,###.00");
	DecimalFormat confirmation = new DecimalFormat("###");

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource dataSource) {
		// create your DAOs here
		this.menu = new Menu(System.in, System.out);
		
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		
	}
	
	//generate park menu based off of size of park table
	public void viewParksMenu() {
		int menuNumber = 0;
		List<Park> parkList = parkDAO.getAllParks();
		
		printHeading("Select a Park for Further Details");
		
		for (int i = 0; i < parkList.size(); i++) {
			menuNumber++;
			System.out.println(menuNumber + ") " + parkList.get(i).getName());	
		}
		
		System.out.println("Q) Quit");
	
		//print park details if the selection was valid, the catch occurs when a user intentionally quits
		try { Park selectedPark = parkDAO.getParkByName(getParkSelection());
		this.selectedPark = selectedPark;                                      //saves our park object to data member so we can access the park_id in another method
		parkDAO.printPark(selectedPark);
		runCommandMenu();
		
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Returning to Main Menu...");
			run();
		}
	
	}
	
	public void viewCampgroundsMenu() { // i created this  method so that I could reuse viewCampgrounds()  and just added the runCampgroundMenu to it
		viewCampgrounds();
		runCampgroundMenu(); 
	}
	
	public void viewCampgrounds() {  // I eliminated redundant code later on and call this method more than once
									 // I moved runCampgroundMenu out of here so that it could be reused
		int menuNumber = 0;
		List<Campground> campgroundList = campgroundDAO.getCampgroundsByParkId(this.selectedPark.getPark_id());
		
		//These are the column titles for Campground display
	
		System.out.println("\n" + this.selectedPark.getName() + " National Park Campgrounds\n");
		System.out.printf("     %-4s", "Name");
		System.out.printf("                 %-4s", "Open");
		System.out.printf("         %-5s", "Close");  
		System.out.printf("        %-9s", "Daily Fee\n");
		System.out.printf("--------------------------------------------------------------\n");
						
		for(int i = 0; i < campgroundList.size(); i++) {
			menuNumber++;
			
			System.out.printf("#" + menuNumber);
			System.out.printf("   %-15s", campgroundList.get(i).getName());
			System.out.printf("      %-10s", campgroundList.get(i).getMonth(campgroundList.get(i).getOpen_from_mm()));
			System.out.printf("   %-10s", campgroundList.get(i).getMonth(campgroundList.get(i).getOpen_to_mm()));
			System.out.printf("   $%-10s\n", df.format(campgroundList.get(i).getDaily_fee()));
		}
	}
	
	
	
	public void selectCampground() {
		List<Campground> campgroundList = campgroundDAO.getCampgroundsByParkId(this.selectedPark.getPark_id());

//I replaced a large block of redundant code with an updated viewCampgrounds();

		viewCampgrounds();  //<--- 
		
		Scanner input = new Scanner(System.in);
		System.out.println("\nWhich campground (enter 0 to cancel)?");
		String userCampgroundSelection = input.nextLine();
		int userCampgroundInt = Integer.parseInt(userCampgroundSelection);
		
		if(userCampgroundInt == 0) {      						//this works properly now
			System.out.println("Returning to Main Menu ...\n");   //moved the conditional below when 0 is entered that way it immediately 
			run();												//removes you from the menu rather than prompting you for dates first
																//and then removing you -- 
		} else {
																	   //			   ***************************************************
		System.out.println("What is the arrival date? __/__/____: ");  //<-------------** Check range is within campground's open dates **
		String userArrivalDate = input.nextLine();					   //              ***************************************************
		userArrivalDate = makeDateFromStringArray(splitString(userArrivalDate));
																		//			    ***************************************************
		System.out.println("What is the departure date? __/__/____: "); //<-------------** Check range is within campground's open dates **
		String userDepartureDate = input.nextLine();                    //              ***************************************************
		userDepartureDate = makeDateFromStringArray(splitString(userDepartureDate));
				
		Long selectedCampgroundId = (long)0;													 //        ************************************************
		try { selectedCampgroundId = campgroundList.get(userCampgroundInt-1).getCampground_id(); //<-------** campground_id here can be used to get cost **
		} catch (IndexOutOfBoundsException e) {											 //		   ************************************************
			System.out.println("Invalid selection!  Returning...");
			run();
		}
																										
		//this calls a method to both store the values in a list and to print the values to the console
		try {
		Campground selectedCampground = campgroundDAO.getCampgroundById(selectedCampgroundId);
		siteDAO.getAvailableSites(userArrivalDate, userDepartureDate, selectedCampgroundId, selectedCampground);
		} catch (Exception e) {
			System.out.println("Invalid date format or selection! Returning...");
			run();
		}
		
		System.out.println("\nWhich site should be reserved(enter 0 to cancel?");
		String selectedSiteNum = input.nextLine();
		Long userSiteNum = Long.parseLong(selectedSiteNum);
		if(userSiteNum == 0) {
			System.out.println("Returning to Main Menu...\n");
			run();
		} else {
							
		Site selectedSite = siteDAO.getSiteById(userSiteNum);
		Long selectedSiteId = selectedSite.getSite_id();
		
		System.out.println("What name should the reservation be made under?");
		String reservationName = input.nextLine();
		
		//This method is in JDBCReservationDAO	
		reservationDAO.createReservation(selectedSiteId, reservationName, userArrivalDate, userDepartureDate); 

		//I have tested this if you run it it will update your sql table and when you search for the same parameters the site is no longer available		
		System.out.println("\nReservation made for " + reservationName + " at site "+ userSiteNum + " from: " + userArrivalDate + " until: "
						  + userDepartureDate + "\n\nThank you, and have a nice day!\n");
		
		System.out.println("Your confirmation number is: " + confirmation.format(Math.random() * 10000) + "\n");
		run(); // right now after you make a reservation it returns you to main menu... we could just System.exit(0);
		}
		
	}
		
	}
	
	public String[] splitString (String userString) {
		String[] userStringArray = userString.split("/");
		
		return userStringArray;
	}
	
	public String makeDateFromStringArray (String[] userStringArray) {
		
		String formattedDate = new String();
		
		formattedDate = userStringArray[2] + "-" + userStringArray[0] + "-" + userStringArray[1];
		
		return formattedDate;
		
	}
	
	
	public String getParkSelection() {
		List<Park> parkList = parkDAO.getAllParks();
		String parkName = null;
		Scanner input = new Scanner(System.in);
		boolean shouldLoop = true;
		
		while (shouldLoop) {
			
			String userChoice = input.nextLine();
			
			if(userChoice.equalsIgnoreCase("Q")) {
				shouldLoop = false;
			}
			
			else {
			
				try { int userChoiceInt = Integer.parseInt(userChoice) - 1;
			
				if (userChoiceInt >= 0 && userChoiceInt < parkList.size()) {
					parkName = parkList.get(userChoiceInt).getName();
					shouldLoop = false;
				}
			
				} catch (NumberFormatException e) {
				
				}
			
				if (parkName == null) {
					System.out.println("Invalid Selection! Try again.");
					viewParksMenu();
				}
			
			}
			
		}
		
		return parkName;
	}
	
	public void run() {
		displayBanner();
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(choice.equals(MAIN_MENU_OPTION_VIEW_PARKS)) {
				viewParksMenu();
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
		
	}
	
	public void runCommandMenu() {
		System.out.println("Select a Command");
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(COMMAND_MENU_OPTIONS);
			if(choice.equals(COMMAND_MENU_OPTION_VIEW_CAMPGROUNDS)) {
				viewCampgroundsMenu();
			} else if(choice.equals(COMMAND_MENU_OPTION_SEARCH_RESERVATION)) {
				selectCampground();
			} else if(choice.equals(COMMAND_MENU_OPTION_EXIT)) {
				run();
			}
		}
		
	}
	
	public void runCampgroundMenu() {
		System.out.println("\nSelect a Command");
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(CAMPGROUND_MENU_OPTIONS);
			if(choice.equals(CAMPGROUND_MENU_OPTION_SEARCH_AVAILABLE_RESERVATION)) {
				selectCampground(); //<------------------------- Create method to view available reservations and put it here
			} else if(choice.equals(COMMAND_MENU_OPTION_EXIT)) {
				runCommandMenu();
			}
		}
		
	}

	public void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public void displayBanner() {
		System.out.println("Greetings! Welcome to the...");
		printHeading("National Park Campsite Reservation System");
	}
}
