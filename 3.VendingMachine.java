// The vending machine lld question 
// Payment 
//    has cash  
//    card 
//
// Item 
//  can be various edible types 
//
// Slot  
//   has a item 
//
// Rack  
//  has Slots 
//
// Pin checker 
//    has items price 
//
//
//    Better version:
//    VendingMachine 
//    Inventory 
//    Slot 
//    Item 
//    Transaction 
//    Payment 

// 1. Scope Question : What core features should support?
// 2. Core Flow question (select item -> insert money -> validate payment -> dispense item -> return change)
// 3. What important data should the system track?
// 4. State Question : (Does the system have states that changes behavior?) (Vending machine : IDLE, ITEM_SELECTED, WAITING_FOR_PAYMENT, DISPENSING, OUT_OF_SERIVCE)
//  5. Failure/Edge-case question (What should happen when something fails?) 
//  6. Scale/Concurrency question (Should I handle concurrency or assume single-threaded behavior for now?)
//  7. Out of Scope question (What should I ignore for this version?)


public class 3.VendingMachine {
  public static void main(String[] args){

  }
}


//Should the user select items by slot code?

//Can each slot hold multiple units of the same item?

//Should we support cash only or cash and card?

// If inserted money is insufficient, should the transaction stay pending?

// If extra money is inserted, should we return change?

// Can the user cancel and get a refund?

// Should I include admin refill flow or keep it out of scope?




