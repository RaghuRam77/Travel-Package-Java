package TravelAgenices;

import java.util.ArrayList;
import java.util.List;

class TravelPackage {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public List<Destination> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Destination> itinerary) {
		this.itinerary = itinerary;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		if (passengers != null) {
			this.passengers = new ArrayList<>(passengers);
		}
	}

	int passengerCapacity;
	List<Destination> itinerary;
	List<Passenger> passengers;

	public TravelPackage(String name, int passengerCapacity) {
		this.name = name;
		this.passengerCapacity = passengerCapacity;
		this.itinerary = new ArrayList<>();
		this.passengers = new ArrayList<>();
	}

	public void addDestination(Destination destination) {
		itinerary.add(destination);
	}

	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}

	public void printItinerary() {
		System.out.println("********** 1. Printing the itinerary of the Travel Package **********");
		System.out.println("Travel Package: " + name);
		System.out.println("Destinations:");
		System.out.println("------------------");

		for (Destination destination : itinerary) {
			System.out.println("Destination: " + destination.getName());

			System.out.println("Activities:");
			for (Activity activity : destination.getActivities()) {
				System.out.println("Name: " + activity.getName());
				System.out.println("Cost: " + activity.getCost());
				System.out.println("Available Capacity: " + activity.getCapacity());
				System.out.println("Description: " + activity.getDescription());
				System.out.println("-----------------");
			}

			System.out.println("-----------------");
		}
	}

	public void printPassengerList() {
		System.out.println("********** 2. Printing the Details of Travel Package and Passenger List **********");
		System.out.println("Travel Package: " + name);
		System.out.println("Passenger Capacity: " + passengerCapacity);
		System.out.println("Number of Passengers Enrolled: " + getNumberOfPassengersEnrolled());
		System.out.println("-----------------");
		System.out.println("-----------------");

		for (Passenger passenger : passengers) {
			System.out.println("Name: " + passenger.getName());
			System.out.println("Number: " + passenger.getPassengerNumber());
			System.out.println("-----------------");
		}
	}

	public void printAvailableActivities() {
		System.out.println(
				"********** 4. Printing the details of all the activities that still have spaces available **********");

		for (Destination destination : itinerary) {
			for (Activity activity : destination.getActivities()) {
				if (activity.getCapacity() > 0) {
					System.out.println("Activity: " + activity.getName());
					System.out.println("Destination: " + destination.getName());
					System.out.println("Spaces Available: " + activity.getCapacity());
					System.out.println("-----------------");
				}
			}
		}
	}

	public int getNumberOfPassengersEnrolled() {
		return passengers.size();
	}

	public void printPassengerDetails() {
		System.out.println("********** 3. Printing the details of Individual Passenger List **********:");
		for (Passenger passenger1 : passengers) {
			System.out.println("Name: " + passenger1.getName());
			System.out.println("Passenger Number: " + passenger1.getPassengerNumber());

			PassengerType passengerType = passenger1.getPassengerType(passenger1);
			if (passengerType == PassengerType.STANDARD || passengerType == PassengerType.GOLD
					|| passengerType == PassengerType.PREMIUM) {
				System.out.println("Balance: Rs " + passenger1.getBalance());
				System.out.println("Membership Type: " + passenger1.type);
				for (Destination destination : itinerary) {
					for (Activity activity : destination.getActivities()) {
						if (activity.isPassengerSignedUp(passenger1)) {
							double pricePaid = activity.getPriceForPassenger(passenger1);
							System.out.println("Destination: " + destination.getName());
							System.out.println("Activity: " + activity.getName());
							System.out.println("Price Paid: $" + pricePaid);
							System.out.println("-----------------");
						}
					}
				}
				System.out.println("-----------------");
			}

		}

	}

}

class Destination {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	List<Destination> itinerary;

	private List<Activity> activities;

	public Destination(String name) {
		this.name = name;
		this.activities = new ArrayList<>();
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}
}

class Activity {
	private String name;
	private String description;
	List<Destination> itinerary;
	List<Passenger> passengers;

	public String getName() {
		return name;
	}

	public void printPassengersByActivity(Activity activity) {
		System.out.println("Passengers signed up for activity: " + activity.getName());

		for (Passenger passenger : passengers) {
			if (activity.isPassengerSignedUp(passenger)) {
				System.out.println("Passenger: " + passenger.getName());
				System.out.println("Passenger Number: " + passenger.getPassengerNumber());
				System.out.println("-----------------");
			}
		}
	}

	public boolean isPassengerSignedUp(Passenger passenger) {
		return passengers != null && passengers.contains(passenger);
	}

	public double getPriceForPassenger(Passenger passenger) {
		double price = 0.0;

		// Retrieve the base price of the activity
		double basePrice = getCost();

		// Apply discounts based on passenger type
		PassengerType passengerType = passenger.getPassengerType(passenger);
		if (passengerType == PassengerType.GOLD) {
			basePrice *= 0.9; // Apply a 10% discount for gold passengers
		}

		// Assign the final calculated price to the 'price' variable
		price = basePrice;

		return price;
	}

	public Activity() {
		passengers = new ArrayList<>(); // Initialize the passengers list
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	private double cost;
	private int capacity;

	public Activity(String name, String description, double cost, int capacity) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.capacity = capacity;
	}

	public int getCapacity() {

		return capacity;
	}

	public double getCost() {
		return cost;
	}

	public void setCapacity(int i) {
		this.capacity = i;

	}

	public void addPassenger(Passenger passenger) {
		if (passengers == null) {
			passengers = new ArrayList<>();
		}
		passengers.add(passenger);
	}

}

class Passenger {
	private String name;
	private int passengerNumber;

	public String getName() {
		return name;
	}

	public PassengerType getPassengerType(Passenger passenger) {
		return passenger.type;

	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	public void setType(PassengerType type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public PassengerType type;
	private double balance;

	public Passenger(String name, int passengerNumber, PassengerType type) {
		this.name = name;
		this.passengerNumber = passengerNumber;
		this.type = type;
		this.balance = 0.0;
	}

	private double applyDiscount(double cost, PassengerType passengerType) {
		if (passengerType == PassengerType.GOLD) {
			return cost * 0.9; // Apply 10% discount for gold passengers
		}
		return cost;
	}

	public void signUpForActivity(Activity activity, Passenger passenger) {
		if (activity.getCapacity() <= 0) {
			System.out.println("Activity has reached its capacity. Cannot sign up for it.");
			return;
		}

		double cost = activity.getCost();

		if (type == PassengerType.GOLD || (type == PassengerType.STANDARD && balance >= cost)) {
			cost = applyDiscount(cost, type); // Apply discount for gold passengers
			balance -= cost;
			activity.setCapacity(activity.getCapacity() - 1);
			activity.addPassenger(passenger); // Add the passenger to the activity
		} else if (type == PassengerType.PREMIUM) {
			activity.setCapacity(activity.getCapacity() - 1);
			activity.addPassenger(passenger); // Add the passenger to the activity
		} else {
			System.out.println("Insufficient balance to sign up for the activity.");
		}
	}

}

enum PassengerType {
	STANDARD, GOLD, PREMIUM
}

public class Main {
	public static void main(String[] args) {
		TravelPackage package1 = new TravelPackage("India", 10);

		Destination Delhi = new Destination("Delhi");
		Destination Karnataka = new Destination("Karnataka");
		Activity TajMahal = new Activity("TajMahal", "Visiting and Exploring TajMahal", 10.0, 50);
		Activity ChikMangaluru = new Activity("ChikMagaluru", "Kudremuk Trek", 50.0, 30);
		Delhi.addActivity(TajMahal);
		Karnataka.addActivity(ChikMangaluru);

		package1.addDestination(Delhi);
		package1.addDestination(Karnataka);

		Passenger passenger1 = new Passenger("John", 1, PassengerType.STANDARD);
		passenger1.setBalance(50.0);
		passenger1.signUpForActivity(TajMahal, passenger1);
		package1.addPassenger(passenger1);

		Passenger passenger2 = new Passenger("Alice", 2, PassengerType.GOLD);
		passenger2.setBalance(150.0);
		passenger2.signUpForActivity(ChikMangaluru, passenger2);
		package1.addPassenger(passenger2);

		Passenger passenger3 = new Passenger("Bob", 3, PassengerType.PREMIUM);
		passenger3.signUpForActivity(ChikMangaluru, passenger3);
		package1.addPassenger(passenger3);

		package1.printItinerary();
		package1.printPassengerList();
		package1.printPassengerDetails();
		package1.printAvailableActivities();

	}
}
