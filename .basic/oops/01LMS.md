# Design Library Management System

---
## Problem Statement
> Build a simple library Management System where:
>> - A library has books
>> - A Book has a title, author, ISBN, and availibility status
>> - A Member can borrow and return books
>> - The library can add books, search books by title, and show all available books
```java
class Book {
  private String title;
  private String author;
  private String isbn;
  private boolean isAvailable;

  public Book(String title, String author, String isbn){
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.isAvailable = true;
  }

  //Getters - controlled read access
  public String getTitle() {return title;}
  public String getAuthor() {return author;}
  public String getIsbn() {return isbn;}
  public boolean isAvailable() {return isAvailable;}

  // Controlled behavior - not just a setter!
  public void borrowBook() {
        isAvailable = false;
  }

  public void returnBook() {
    isAvailable = true;
  }
}
```

### Concept: **Encapsulation**
> Hide your data (`private`), expose behavior through methods. The outside world doesn't need to know how you store things - they just call your methods.

```java
class Member{
    private String name;
    private long memberId;
    private List<Book> borrowedBooks;
    
    public Member(String name, long memberId){
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    //Getters
    public String getName() {return name;}
    public long getMemberId() {return memberId;}
//    public List<Book> getBorrowedBook() {return borrowedBooks;} // Leaking Issue. .clear(), .add(someRandomBook);
    public List<Book> getBorrowedBook() { return Collections.unmodifiableList(borrowedBooks);}

    public void borrowBook(Book book){
        if (book.isAvailable()){
            borrowedBooks.add(book);
            book.borrowBook();
        }else{
            System.out.println("Book is already borrowed!");
        }
    }

    public void returnBook(Book book){
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
        }else {
            System.out.println("This book wasn't borrowed by "+ name);
        }
    }
}
```
```java
class Library {
    private List<Book> listOfBooks;
    private List<Member> listOfMembers;

    public Library(){
        listOfBooks = new ArrayList<>();
        listOfMembers = new ArrayList<>();
    }

    public void addBook(Book book){
        for(Book bk : listOfBooks){
            if(bk.getIsbn().equals(book.getIsbn())){
                System.out.println("Book already exists!");
                return;
            }
        }
                
        listOfBooks.add(book);
    }

    public List<Book> searchByTitle(String title){
        List<Book> newList = new ArrayList<>();
        for(Book book : listOfBooks){
            if(book.getTitle().contains(title)){
                newList.add(book);
            }
        }
        return newList;
        
    }
    
    public List<Book> showAvailableBooks(){
        List<Book> available = new ArrayList<>();
    
        for(Book book :listOfBooks){
            if(book.isAvailable()) available.add(book);
        }
        return available;
    }


    public void addMember(Member member){
        listOfMembers.add(member);
    }
}
```

#### Create main class
```java
public class Main{
    public static void main(String[] args){
       Library library = new Library();
        
        Book bk1 = new Book("a1","John","13133");
        Book bk2 = new Book("ss","Nikhil","45453");
        Book bk3 = new Book("kjsg","Ron","44552");

        Member m1 = new Member("Batman",1);
        Member m2 = new Member("SuperMan",2);
        
        library.addBook(bk1);
        library.addBook(bk2);
        library.addBook(bk3);
        
        library.addMember(m1);
        library.addMember(m2);

        m1.borrowBook(bk1);
        List<Book> available = library.showAvailableBooks();
        for(Book bk : available){
            System.out.println(bk.getTitle() +" by "+bk.getAuthor() + " is available!!");
        }
        
        List<Book> books = library.searchByTitle("ss");
        for(Book bk : books){
            System.out.println(bk.getTitle()+" here is the similar book!!!");
        }

        m2.borrowBook(bk1); 
    }
}
```


## Summary
| Concept | Where you used it |
|---|---|
| **Class & Object** | `Book`, `Member`, `Library` are blueprints; `bk1`, `m1` are objects |
| **Constructor** | `new Book("a1", "John", "13133")` calls the constructor |
| **`this` keyword** | `this.title = title` inside constructors |
| **Encapsulation** | `private` fields + public methods to control access |
| **Defensive copying** | `Collections.unmodifiableList()` to protect internal state |
| **Early return** | Cleaner than boolean flags in `addBook` |

---

## UML Class Diagram — Visualizing What We Built

Now let's draw what you built. In interviews, you'll sketch this *before* coding. Today we're doing it backwards so you understand what the boxes and arrows actually mean.
```
┌──────────────────────┐
│        Book          │
├──────────────────────┤
│ - title: String      │
│ - author: String     │
│ - isbn: String       │
│ - isAvailable: bool  │
├──────────────────────┤
│ + getTitle(): String │
│ + borrowBook(): void │
│ + returnBook(): void │
└──────────────────────┘

┌─────────────────────────────┐
│          Member             │
├─────────────────────────────┤
│ - name: String              │
│ - memberId: long            │
│ - borrowedBooks: List<Book> │
├─────────────────────────────┤
│ + borrowBook(Book): void    │
│ + returnBook(Book): void    │
└─────────────────────────────┘

┌──────────────────────────────────┐
│           Library                │
├──────────────────────────────────┤
│ - listOfBooks: List<Book>        │
│ - listOfMembers: List<Member>    │
├──────────────────────────────────┤
│ + addBook(Book): void            │
│ + searchByTitle(String): List    │
│ + getAvailableBooks(): List      │
└──────────────────────────────────┘
```

**UML notation:**
- `-` means `private`, `+` means `public`
- Top section: class name
- Middle: fields
- Bottom: methods
```java
// Books are created OUTSIDE and passed IN
Book book = new Book("Clean Code", "Robert Martin", "123");
Library library = new Library();
library.addBook(book);

// If library is destroyed, 'book' still exists here
// because YOU created it, not the library
```
**Relationships:**
- `Library` **HAS** many `Book`s → this is called **Aggregation** (books can exist without the library)
- `Member` **HAS** many `Book`s (borrowed) → also **Aggregation**
- `Library` **HAS** many `Member`s → **Aggregation**



##### Aggregation
- *if the parent dies, do the children die too?*
    - Aggregation (weak "HAS-A" - Children survive)
    - Books are created outside, passed in. Books can exist without the Library.

##### Composition
- strong **HAS-A** 
    - A Composition example would be if our Book had Chapters that were created inside the Book constructor — destroy the book, chapters are gone.
```java
public class House {
    private List<Room> rooms;

    public House() {
        // Rooms are created INSIDE — House owns them
        rooms = new ArrayList<>();
        rooms.add(new Room("Bedroom"));
        rooms.add(new Room("Kitchen"));
    }
}
// If House object is garbage collected, the Rooms go with it
// No one else has a reference to those rooms
```



