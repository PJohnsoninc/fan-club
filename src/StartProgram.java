import java.util.List;
import java.util.Scanner;

import controller.PersonHelper;
import model.Person;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static PersonHelper pHelper = new PersonHelper();

		private static void addPerson() {
			// TODO Auto-generated method stub
			System.out.print("Enter first name: ");
			String firstName = in.nextLine();
			System.out.print("Enter last name: ");
			String lastName = in.nextLine();
			System.out.print("Enter email: ");
			String email = in.nextLine();
			
			Person toAdd = new	Person(firstName, lastName, email);
			pHelper.insertPerson(toAdd);
			
		}

		private static void deletePerson() {
			System.out.print("Enter the first name: ");
			String firstName = in.nextLine();
			System.out.print("Enter the last name: ");
			String lastName = in.nextLine();
			System.out.print("Enter the email: ");
			String email = in.nextLine();
			
			Person	toDelete	=	new	Person(firstName, lastName, email);
			pHelper.deletePerson(toDelete);
		}

		private static void editPerson() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by name");
			System.out.println("2 : Search by email");
			int searchBy = in.nextInt();
			in.nextLine();
			
			List<Person> foundPeople;
			
			if (searchBy == 1) {
				System.out.print("Enter first name: ");
				String firstName = in.nextLine();
				System.out.print("Enter last name: ");
				String lastName = in.nextLine();
				
				foundPeople	=	pHelper.searchPersonByName(firstName, lastName);
				
			} else {
				System.out.print("Enter email: ");
				String email = in.nextLine();
				
				foundPeople	=	pHelper.searchForPersonByEmail(email);
			}

			if (!foundPeople.isEmpty()) {
				System.out.println("Found Results.");
				for (Person p : foundPeople) {
					System.out.println(p.getId() + " : " + p.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Person toEdit = pHelper.searchForPersonById(idToEdit);
				System.out.println("Retrieved " + toEdit.getFirstName() + " " +
						toEdit.getLastName() + " [" + toEdit.getEmail() + "]");
				System.out.println("1 : Update first name");
				System.out.println("2 : Update last name");
				System.out.println("3 : Update email");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New first name: ");
					String firstName = in.nextLine();
					toEdit.setFirstName(firstName);
				} else if (update == 2) {
					System.out.print("New last name: ");
					String lastName = in.nextLine();
					toEdit.setLastName(lastName);
				}else if (update == 3) {
					System.out.print("New email: ");
					String email = in.nextLine();
					toEdit.setEmail(email);
				}

				pHelper.updatePerson(toEdit);

			} else {
				System.out.println("---- No results found");
			}
			
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addPerson();
				}else if (selection == 2) {
					editPerson();
				}else if (selection == 3) {
					deletePerson();
				}else if (selection == 4) {
					viewPeople();
				} else {
					pHelper.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewPeople() {
			List<Person>	allPeople	=	pHelper.showAllPeople();
			
			for(Person person :	allPeople){
				System.out.println(person.toString());
			}
		}

	}