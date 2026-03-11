// public enum OrderStatus{
//     PLACED,
//     CONFIRMED,
//     SHIPPED,
//     DELIVERED,
//     CANCELLED
// }

// public enum PaymentMethod{
//     CREDIT_CARD("Credit Card", 2.5),
//     DEBIT_CARD("Debit Card", 1.0),
//     UPI("UPI", 0.0),
//     NET_BANKING("Net Banking", 1.5);


//     private final String displayName;
//     private final double feePercent;

//     PaymentMethod(String displayName, double feePercent){
//         this.displayName = displayName;
//         this.feePercent = feePercent;
//     }


//     public String getDisplayName(){
//         return displayName;
//     }

//     public double getFeePercent(){
//         return feePercent;
//     }
// }



// public class Order {
//     private final String orderId;
//     private OrderStatus status;
//     private final PaymentMethod paymentMethod;
//     private final double amount;


//     public Order(String orderId, PaymentMethod paymentMethod, double amount){
//         this.orderId = orderId;
//         this.paymentMethod = paymentMethod;
//         this.amount = amount;
//         this.status = OrderStatus.PLACED;
//     }

//     public boolean advanceStatus(){
//         switch (status){
//             case PLACED:
//                 status = OrderStatus.CONFIRMED;
//                 return true;
//             case CONFIRMED:
//                 status = OrderStatis.SHIPPED;
//                 return true;
//             case SHIPPED:
//                 status = OrderStatus.DELIVERED;
//                 return true;
//             default:
//                 return false;
//         }
//     }


//     public boolean cancel(){
//         if (status == OrderStatus.PLACED || status == OrderStatus.CONFIRMED){
//             status = OrderStatus.CANCELLED;
//             return true;
//         }
//         return false;
//     }

//     public double getTotalWithFees() {
//         return amount + (amount * paymentMethod.getFeePercent() / 100);
//     }

//     public void displayInfo() {
//          System.out.printf("Order %s | Status: %s | Payment: %s | Amount: $%.2f (with fees: $%.2f)%n",
//             orderId, status, paymentMethod.getDisplayName(), amount, getTotalWithFees());
//     }
// }

// public class Main {
//     public static void main(String[] args) {
//         Order order = new Order("ORD-001", PaymentMethod.CREDIT_CARD, 99.99);
//         order.displayInfo();


//         order.advanceStatus(); // PLACED -> CONFIRMED
//         order.advanceStatus(); // CONFIRMED -> SHIPPED
//         order.displayInfo();

//         System.out.println("Cancel after shipping: " + order.cancel()); // false
//     }
// }

// // EXERCISE:
// // 1. Create a TrafficLight enum where each light has a color (RED, YELLOW, GREEN), a duration in seconds, and a next() method that returns the next light in the cycle (RED -> GREEN -> YELLOW -> RED).

// // Requirements:

// //     Each light has a duration property: RED = 30s, YELLOW = 5s, GREEN = 25s
// //     next() method returns the next TrafficLight in the cycle

// enum TrafficLight {
//     RED(30),
//     YELLOW(5),
//     GREEN(25);

//     private final int duration;

//     TrafficLight(int duration) {
//         this.duration = duration;
//     }

//     public int getDuration() {
//         return duration;
//     }

//     public TrafficLight next() {
//         switch (this) {
//             case RED: return GREEN;
//             case GREEN: return YELLOW;
//             case YELLOW: return RED;
//             default: return this;
//         }
//     }

//     public void display(){
//         System.out.println(this.name() + " ("+ duration+"s)");
//     }
// }

// public class Main {
//     public static void main(String[] args){
//         TrafficLight light = TrafficLight.RED;
//         for (int i = 0; i < 6; i++){
//             light.display();
//             light = light.next();
//         }
//     }
// }


// Exercise 2: HTTP Status Code
// Problem: Create an HttpStatus enum where each status has a numeric code and a message string.

// Requirements:

//     Values: OK(200, "OK"), BAD_REQUEST(400, "Bad Request"), NOT_FOUND(404, "Not Found"), INTERNAL_SERVER_ERROR(500, "Internal Server Error")
//     isSuccess() method that returns true if the code is less than 400
//     display() method that prints "CODE MESSAGE" (e.g. "200 OK")
//     A static fromCode(int) method that returns the HttpStatus for a given code, or null/None if not found

enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    HttpStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public int getMessage() {
            return message;
    }

    public boolean isSuccess() {
        if(this.code < 400) return true;
        return false;
    }

    public void display(){
        System.out.printf("%d %s%n",this.code,this.message);
    }

    public static HttpStatus fromCode(int code){
        for (HttpStatus status : values()){
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }


}