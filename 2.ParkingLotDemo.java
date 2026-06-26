// before proceding further I need to layout the system 
// Parking lot has floors. Floors have spots. Vehicle enter. Ticket is generated. Vehicle exits and pays.
// ///////////////////////////////////
// 1. ASK: Let me clarify the requirements first 
// 2. ASK questions to simplify the problem statement 
// 3. Find Enties from nouns 
// 4. Think in hierarchy (parking lot is a has-a relationship problem)
// 5. Think about types (enum)
// 6. Think about behavior (What does each class do?)
// //////////////////////////////////
// ParkingLot 
//    has many ParkingFloor 
//
// ParkingFloor 
//    has many ParkingSpot 
//
//
// ParkingSpot 
//    can hold one Vehicle 
//
// Vehicle 
//   has VehicleType
//
//  ParkingTicket 
//    has Vehicle 
//    has ParkingSpot 
//    has entryTime 
//
////////////
import java.util.*;
import java.time.Instant;


public class 2.ParkingLotDemo {
    public static void main(String[] args){
        ParkingLot parkingLot = new ParkingLot("My Parking Lot");

        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot(101, SpotType.BIKE));
        floor1.addSpot(new ParkingSpot(102, SpotType.CAR));
        floor1.addSpot(new ParkingSpot(103, SpotType.TRUCK));

        Vehicle bike = new Bike("MH15DF1221");
        Vehicle car  = new Bike("MH15EF1221");

        parkingLot.addFloor(floo1);

        Optional<Ticket> carTicket = parkingLot.parkVehicle(car);
        Optional<Ticket> bikeTicket = parkingLot.parkVehicle(bike);

        System.out.println();

        parkingLot.displayAvailability();

        System.out.println();

    }
} 

enum VehicleType {
  BIKE,
  CAR,
  TRUCK 
}

enum SpotType {
  BIKE,
  CAR,
  TRUCK 
}

enum TicketStatus {
  ACTIVE,
  CLOSED


class ParkingFloor {
  private List<Vehicle> 
}

abstract class Vehicle {
  private String licensePlate;
  private VehicleType type;

  public Vehicle(String licensePlate, VehicleType type){
    this.licensePlate = licensePlate;
    this.type = type;
  }

  public String getLicensePlate(){
    return licensePlate;
  }

  public VehicleType getType(){
    return type;
  }
}

class Bike extends Vehicle{
  public Bike(String licensePlate){
    super(licensePlate,VehicleType.BIKE);
  }
}

class Car extends Vehicle{
  public Car(String licensePlate){
    super(licensePlate,VehicleType.CAR);
  }
}

class Truck extends Vehicle{
  public Truck(String licensePlate){
    super(licensePlate, VehicleType.TRUCK);
  }
}


record Ticket(String ticketId,int floorNumber, int spotNumber,String licensePlate, Instant entryTime, TicketStatus status) {
  public static Ticket create(int floorNumber,int spotNumber,String licensePlate){
    return new Ticket(
          UUID.randomUUID().toString(),
          floorNumber,
          spotNumber,
          licensePlate,
          Instant.now(),
          TicketStatus.ACTIVE
        );
  }

  public Ticket close() {
    return new Ticket(
        ticketId,
        floorNumber,
        spotNumber,
        licensePlate,
        entryTime,
        TicketStatus.CLOSED
    );
  }
}


// Parking Spot 
class ParkingSpot {
  private final int spotNumber;
  private final SpotType spotType;
  private Vehicle parkedVehicle;

  public ParkingSpot(int spotNumber, SpotType spotType){
    this.spotNumber = spotNumber;
    this.spotType = spotType;
    this.parkedVehicle = null;
  }

  public int getSpotNumber(){
    return spotNumber;
  }

  public SpotType getSpotType() {
    return spotType;
  }

  public boolean isAvailable(){
    retunr parkedVehicle == null;
  }

  public boolean canFitVehicle(Vehicle vehicle){
    if (!isAvailable){
      return false;
    }

    if (!canFitVehicle(vehicle)){
      return false;
    }

    this.parkedVehicle = vehicle;
    return true;
  }

  public void removeVehicle() {
    this.parkedVehicle = null;
  }

  public String getParkedVehicleInfo() {
    if (parkedVehicle == null) {
      return "EMPTY";
    }  

    return parkedVehicle.getType() + " - " + parkedVehicle.getLicensePlate();
  }
}


// Parking Floor 

class ParkingFloor {
  private final int floorNumber;
  private final List<ParkingSpot> spots;

  public ParkingFloor(int floorNumber) {
    this.floorNumber = floorNumber;
    this.spots = new ArrayList<>();
  }

  public int getFloorNumber() {
    return floorNumber;
  }

  public void addSpot(ParkingSpot spot){
    spots.add(spot);
  }

  public ParkingSpot findAvailableSpot(Vehicle vehicle){
    for (ParkingSpot spot : spots){
      if (spot.isAvailable() && spot.canFitVehicle(vehicle)){
        return spot;
      }
    }
    return null;
  }

  public void displayAvailability() {
    System.out.println("Floor " + floorNumber + " availability:");
    for (ParkingSpot spot : spots){
      System.out.println(
            "Spot " + spot.getSpotNumber()
                    + " | Type: " + spot.getSpotType() 
                    + " | Status: " + spot.getParkedVehicleInfo()
      );
    }
  }
}


// Parking Lot 
class ParkingLot {
  private final String name;
  private final List<ParkingFloor> floors;
  private final Map<String, Ticket> activeTickets;

  public ParkingLot(String name){
    this.name = name;
    this.floors = new ArrayList<>();
    this.activeTickets = new HashMap<>();
  }

  public void addFloor(ParkingFloor floor){
    floors.add(floor);
  }

  public Optional<Ticket> parkVehicle(Vehicle vehicle){
    for (ParkingFloor floor : floors){
      ParkingSpot spot = floor.findAvailableSpot(vehicle);

      if (spot != null){
        boolean parked = spot.parkVehicle(vehicle);

        if (parked) {
          Ticket ticket = Ticket.create(floor.getFloorNumber(), spot.getSpotNumber(),vehicle.getLicensePlate());

          activeTickets.put(ticket.ticketId(), ticket);
          retunr Optional.of(ticket);
        }
      }
    }

    return Optional.empty();
  }


  public boolean unparkVehicle(String ticketId){
    Ticket ticket = activeTickets.get(ticketId);

    if (ticket == null){
      System.out.println("Invalid ticket.");
      return false;
    }

    ParkingFloor floor = getFloorNumber(ticket.floorNumber());

    if (floor == null) {
      System.out.println("Floor not found");
      return false;
    }

    ParkingSpot spot = floor.getSpotNumber(ticket.spotNumber());
    
    if (spot == null) {
      System.out.println("Spot not found. ");
      return false;
    }

    spot.removeVehicle();
    Ticket closeTicket = ticket.close();
    activeTickets.remove(ticketId);
    System.out.println("Ticket closed:");
    System.out.println(closedTicket);

    return true; 

  }

  private ParkingFloor getFloorNumber(int floorNumber){
    for (ParkingFloor floor : floors) {
      if (floor.getFloorNumber() == floorNumber){
        return floor;
      }
    }

    return null;
  }

  public void displayAvailability() {
    System.out.println("Parking Lot: " + name);
    for( Parkingfloor floor : floors){
      floor.displayAvailability();
      System.out.println();
    }
  }
}
























}
