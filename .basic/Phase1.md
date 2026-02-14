# Java Phase 1 
---

## Table of Contents
1. [How Java Works (JVM, JDK, JRE)](#1-how-java-works)
2. [Data Types & Variables](#2-data-types--variables)
3. [Operators](#3-operators)
4. [Control Flow](#4-control-flow)
5. [Methods](#5-methods)
6. [Arrays](#6-arrays)
7. [Strings (Deep Dive)](#7-strings-deep-dive)
8. [OOP — Classes & Objects](#8-oop--classes--objects)
9. [OOP — Four Pillars](#9-oop--four-pillars)
10. [OOP — Interfaces vs Abstract Classes](#10-interfaces-vs-abstract-classes)
11. [OOP — Important Keywords](#11-important-keywords)
12. [OOP — Object Class Methods](#12-object-class-methods)
13. [Exception Handling (Deep Dive)](#13-exception-handling-deep-dive)
14. [Collections Framework (Deep Dive)](#14-collections-framework-deep-dive)
15. [Wrapper Classes & Autoboxing](#15-wrapper-classes--autoboxing)
16. [Enums](#16-enums)
17. [Inner Classes](#17-inner-classes)
18. [Java Memory Model Basics](#18-java-memory-model-basics)
19. [Common Interview Questions & Answers](#19-common-interview-questions--answers)

---

## 1. How Java Works

### JDK vs JRE vs JVM
```
JDK (Java Development Kit)
├── JRE (Java Runtime Environment)
│   ├── JVM (Java Virtual Machine)
│   │   ├── Class Loader
│   │   ├── Bytecode Verifier
│   │   ├── Execution Engine (Interpreter + JIT Compiler)
│   │   └── Garbage Collector
│   └── Core Libraries (java.lang, java.util, java.io, etc.)
├── javac (Compiler)
├── jar (Archive tool)
├── javadoc (Documentation tool)
└── jdb (Debugger)
```


- **JVM**: Excutes bytecode. Platform-specific (different JVM for Windows/Mac/Linux).
- **JRE**: JVM + libraries. Enough to Run Java programs.
- **JDK**: JRE + development tools. Needed to COMPILE Java programs.

### Compilation & Execution Flow

```
Source Code (.java)
       │
       ▼  javac (compiler)
Bytecode (.class)
       │
       ▼  JVM
Machine Code (platform-specific)
```

```java
//HelloWorld.java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

```bash
javac HelloWorld.java  # Produces HelloWorld.class (bytecode)
java HelloWorld        # JVM executes the bytecode
```

### Why "Write Once, Run Anywhere"?
The `.class` file (bytecode) is platform-independent. Each OS has its own JVM that translates bytecode to native machine code. This is why Java is NOT purely compiled or purely interpreted - it's both.

### Is Java pass-by-value or pass-by-reference?
**Java is ALWAYS pass-by-value.** But for objects, the "value" being passed is the reference (memory address). So:  

```java
// Primitive: copy of value
void change(int x) { x = 10; } //original NOT affected 

//Objects: copy of reference
void change(Dog d) { d.setName("Rex"); } //original IS affected (same object)
void change(Dog d) { d = new Dog("Rex"); } // original NOT affected (new reference)
```

---

## 2. Data Types & Variables

Java Learning
/

Java RoadMap, basic to advanced
Feb 9
Java Roadmap: Basic to Advanced
Phase 1 — Core Fundamentals

    Setup & Tooling: JDK installation, IDE (IntelliJ IDEA), build tools (Maven/Gradle)
    Syntax & Basics: Variables, data types, operators, control flow (if, switch, loops)
    OOP Concepts: Classes, objects, inheritance, polymorphism, abstraction, encapsulation, interfaces
    Core APIs: Strings, arrays, wrapper classes, Math, Scanner
    Exception Handling: try-catch-finally, checked vs unchecked exceptions, custom exceptions
    Collections Framework: List, Set, Map, Queue, Iterator, Comparable/Comparator

Phase 2 — Intermediate Java

    Generics: Type parameters, bounded types, wildcards
    Functional Programming: Lambdas, Stream API, Optional, method references
    I/O & NIO: File handling, buffered streams, java.nio (channels, buffers, Path)
    Concurrency Basics: Threads, Runnable, synchronized, volatile, wait/notify
    Java Memory Model: Stack vs heap, garbage collection basics, reference types (weak, soft, phantom)
    Date/Time API: java.time package (LocalDate, Instant, Duration, ZonedDateTime)
    Annotations & Reflection: Built-in annotations, custom annotations, Class object, dynamic invocation
    Unit Testing: JUnit 5, Mockito, AssertJ

Phase 3 — Advanced Core

    Advanced Concurrency: ExecutorService, CompletableFuture, ForkJoinPool, CountDownLatch, Semaphore, ConcurrentHashMap, ReadWriteLock
    JVM Internals: Class loading, bytecode basics, JIT compilation, GC algorithms (G1, ZGC, Shenandoah), JVM tuning flags (-Xmx, -XX:+UseG1GC)
    Design Patterns: Singleton, Factory, Builder, Strategy, Observer, Decorator, Proxy, Circuit Breaker
    Modules (JPMS): module-info.java, modular JARs, encapsulation
    Serialization: Java serialization, JSON (Jackson/Gson), Protocol Buffers

Phase 4 — Backend & Frameworks

    Spring Ecosystem:
        Spring Core (IoC, DI, AOP)
        Spring Boot (auto-configuration, starters, profiles, actuator)
        Spring MVC / REST APIs
        Spring Data JPA (Hibernate, repositories, query methods)
        Spring Security (authentication, authorization, JWT, OAuth2)
        Spring WebFlux (reactive programming with Project Reactor)
    Databases: JDBC, connection pooling (HikariCP), JPA/Hibernate deep dive (N+1, caching, lazy vs eager)
    Messaging: Kafka, RabbitMQ (Spring Kafka / Spring AMQP)
    API Design: REST best practices, OpenAPI/Swagger, gRPC with Java

Phase 5 — Distributed Systems & DevOps (plays to your interests)

    Microservices Patterns: Service discovery (Eureka/Consul), API gateway, circuit breaker (Resilience4j), saga pattern, event sourcing, CQRS
    Distributed Concerns: CAP theorem, distributed caching (Redis), distributed locking, idempotency, eventual consistency
    Containerization: Docker (multi-stage builds for Java), Docker Compose
    Orchestration: Kubernetes (deployments, services, config maps, health probes for Spring Boot apps)
    CI/CD: GitHub Actions / Jenkins pipelines for Java projects, artifact publishing
    Observability: Structured logging (SLF4J/Logback), Micrometer metrics, distributed tracing (OpenTelemetry, Jaeger/Zipkin), Grafana/Prometheus
    Cloud-Native Java: GraalVM native images, Spring Boot 3 + native compilation, Quarkus/Micronaut (lightweight alternatives)

Phase 6 — Performance & Mastery

    Profiling & Tuning: JFR (Java Flight Recorder), async-profiler, heap dump analysis, thread dump analysis
    Advanced Patterns: Virtual Threads (Project Loom), structured concurrency, SequencedCollection (Java 21+)
    System Design with Java: Rate limiters, consistent hashing, distributed queues, leader election — implementing these in Java
    Open Source Contribution: Read source code of Spring, Kafka clients, Netty to deepen understanding

Suggested practice: Build progressively — a CLI app → REST API → microservices system with Kafka + Redis + Docker + K8s. That arc will tie together all phases naturally.

Phase 1
Feb 9
Phase 1 — Java Core Fundamentals

This is your foundation. Take it seriously — everything else builds on this.
1. Setup & Tooling

Install & configure:

    JDK 21+ (LTS) — use Adoptium or Oracle
    IntelliJ IDEA (Community is fine)
    Understand JAVA_HOME, PATH, javac, java commands

Build tools (intro level):

    Maven — pom.xml, dependencies, lifecycle (compile, test, package)
    Know that Gradle exists (you'll go deeper later)

Practice: Create a project from scratch via CLI (javac + java), then recreate it in IntelliJ with Maven.
2. Syntax & Basics
java

// Variables & Data Types
int age = 25;
double salary = 50000.50;
boolean isActive = true;
char grade = 'A';
String name = "Claude"; // reference type, not primitive

// Type casting
double d = age;          // implicit (widening)
int i = (int) salary;    // explicit (narrowing)

// Operators
// arithmetic: + - * / %
// comparison: == != > < >= <=
// logical: && || !

Control Flow:
java

// if-else
if (age >= 18) {
    System.out.println("Adult");
} else if (age >= 13) {
    System.out.println("Teen");
} else {
    System.out.println("Child");
}

// switch (modern style, Java 14+)
String result = switch (grade) {
    case 'A' -> "Excellent";
    case 'B' -> "Good";
    default  -> "Average";
};

// loops
for (int j = 0; j < 10; j++) { }
while (condition) { }
do { } while (condition);

// enhanced for
int[] nums = {1, 2, 3};
for (int n : nums) {
    System.out.println(n);
}

Practice: Solve 15–20 basic problems — FizzBuzz, palindrome check, factorial, Fibonacci, prime numbers.
3. OOP Concepts (the heart of Java)
Classes & Objects
java

public class Dog {
    // fields (state)
    private String name;
    private int age;

    // constructor
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // method (behavior)
    public void bark() {
        System.out.println(name + " says Woof!");
    }

    // getter/setter (encapsulation)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

Four Pillars

Encapsulation — hide internals, expose via methods
java

private int balance; // hidden
public void deposit(int amount) { // controlled access
    if (amount > 0) balance += amount;
}

Inheritance — reuse via extends
java

public class Animal {
    public void eat() { System.out.println("Eating..."); }
}

public class Cat extends Animal {
    public void purr() { System.out.println("Purring..."); }
}
// Cat inherits eat() and adds purr()

Polymorphism — one interface, many forms
java

Animal animal = new Cat(); // upcasting
animal.eat(); // works
// animal.purr(); // compile error — reference type is Animal

// Method overriding
public class Cat extends Animal {
    @Override
    public void eat() { System.out.println("Cat eating fish"); }
}

Abstraction — hide complexity
java

// Abstract class — partial implementation
public abstract class Shape {
    abstract double area(); // subclasses MUST implement
    public void describe() { System.out.println("I'm a shape"); }
}

// Interface — pure contract
public interface Drawable {
    void draw();
    default void resize() { System.out.println("Resizing..."); }
}

public class Circle extends Shape implements Drawable {
    private double radius;
    @Override double area() { return Math.PI * radius * radius; }
    @Override public void draw() { System.out.println("Drawing circle"); }
}

Key OOP concepts to also understand:

    static vs instance members
    final keyword (variables, methods, classes)
    this and super
    Abstract class vs Interface — when to use which
    Composition over inheritance

Practice: Model a real system — a library (books, members, borrowing), a bank (accounts, transactions), or a vehicle hierarchy.
4. Core APIs
java

// Strings (immutable!)
String s = "Hello";
s.length();          // 5
s.charAt(0);         // 'H'
s.substring(0, 3);   // "Hel"
s.equals("Hello");   // true (NEVER use == for strings)
s.contains("ell");   // true
s.split(",");        // splits into array
String.format("Hi %s, age %d", name, age);

// StringBuilder (mutable, use in loops)
StringBuilder sb = new StringBuilder();
sb.append("Hello").append(" World");

// Arrays
int[] arr = new int[5];
int[] arr2 = {1, 2, 3, 4, 5};
Arrays.sort(arr2);
Arrays.toString(arr2); // "[1, 2, 3, 4, 5]"

// Math
Math.max(10, 20);   // 20
Math.random();       // 0.0 to 1.0
Math.pow(2, 10);     // 1024.0

// Scanner (user input)
Scanner sc = new Scanner(System.in);
String input = sc.nextLine();
int num = sc.nextInt();

5. Exception Handling
java

// Basic structure
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Can't divide by zero: " + e.getMessage());
} finally {
    System.out.println("Always runs");
}

// Multiple catch blocks
try {
    // risky code
} catch (FileNotFoundException e) {
    // specific first
} catch (IOException e) {
    // broader second
} catch (Exception e) {
    // most general last
}

// Checked vs Unchecked
// Checked: compiler forces you to handle (IOException, SQLException)
// Unchecked: runtime errors (NullPointerException, ArrayIndexOutOfBoundsException)

// Custom exception
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Throwing
public void withdraw(double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Balance too low");
    }
    balance -= amount;
}

// Try-with-resources (auto-closes resources)
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
} // br is automatically closed

6. Collections Framework
java

// LIST — ordered, allows duplicates
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
names.get(0);        // "Alice"
names.remove("Bob");
// Also: LinkedList (better for frequent insert/remove)

// SET — no duplicates
Set<Integer> ids = new HashSet<>();     // unordered
Set<Integer> sorted = new TreeSet<>();  // sorted
ids.add(1); ids.add(1);                 // still size 1

// MAP — key-value pairs
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.get("Alice");           // 95
scores.getOrDefault("Bob", 0); // 0
scores.containsKey("Alice");   // true

// Iterating
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// QUEUE
Queue<String> queue = new LinkedList<>();
queue.offer("first");  // add
queue.poll();           // remove & return head
queue.peek();           // look at head without removing

// Sorting with Comparable
public class Student implements Comparable<Student> {
    private String name;
    private int grade;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }
}
Collections.sort(studentList); // uses compareTo

// Sorting with Comparator (external, flexible)
studentList.sort(Comparator.comparing(Student::getName));
studentList.sort(Comparator.comparingInt(Student::getGrade).reversed());

Collections cheat sheet:
Need	Use
Ordered list, fast random access	ArrayList
Frequent insert/delete	LinkedList
Unique elements	HashSet
Unique + sorted	TreeSet
Key-value lookup	HashMap
Key-value sorted by key	TreeMap
FIFO queue	LinkedList or ArrayDeque
Phase 1 Project Ideas

    CLI Bank System — accounts, deposit/withdraw, transfer, exception handling
    Student Grade Manager — store students in a Map, sort by grade, calculate averages
    Library Management — OOP hierarchy (Book, Member, Borrow), collections, file I/O for persistence

How to know you're ready for Phase 2

You should be able to:

    Write a class hierarchy from scratch without looking anything up
    Choose the right collection for a given problem
    Handle exceptions properly
    Read and understand Java error stack traces
    Solve medium-level problems on LeetCode/HackerRank in Java

Want me to expand on Phase 2 or dive deeper into any specific topic here?

I want you to cover all major topic because I'm also preparing for the interview so get in details
Feb 9

That's a solid plan — combining learning with interview prep. Let me break Phase 1 into detailed, interview-ready deep dives. Given how much ground there is to cover, let me create a comprehensive document for you.
Done

No network access — I'll create this as a comprehensive Markdown document instead, which will render nicely for you.
Done

Here's your complete Phase 1 guide covering 19 major topics in depth, all interview-ready. It includes:

    JVM internals (how Java works, pass-by-value explanation)
    All 8 primitives with ranges, casting rules, and traps
    OOP deep dive — all four pillars with code, overloading vs overriding table, diamond problem
    HashMap internals — how buckets, hashing, chaining, and treeification work
    Collections comparison tables — ArrayList vs LinkedList, HashSet vs TreeSet, etc.
    Exception hierarchy — checked vs unchecked, custom exceptions, try-with-resources
    String pool mechanics and Integer cache trick
    Memory model — stack vs heap, GC generations
    14 common interview Q&As with code examples
    15 practice problems (easy → challenging)
    Modern Java features: Records, Sealed Classes, pattern matching, enhanced switch

Let me know when you're ready for Phase 2!
Java phase1 complete guide
Document · MD 


Claude is AI and can make mistakes. Please double-check responses.
Java phase1 complete guide · MD
# Java Phase 1 — Complete Deep Dive (Interview Ready)

---

## Table of Contents

1. [How Java Works (JVM, JDK, JRE)](#1-how-java-works)
2. [Data Types & Variables](#2-data-types--variables)
3. [Operators](#3-operators)
4. [Control Flow](#4-control-flow)
5. [Methods](#5-methods)
6. [Arrays](#6-arrays)
7. [Strings (Deep Dive)](#7-strings-deep-dive)
8. [OOP — Classes & Objects](#8-oop--classes--objects)
9. [OOP — Four Pillars](#9-oop--four-pillars)
10. [OOP — Interfaces vs Abstract Classes](#10-interfaces-vs-abstract-classes)
11. [OOP — Important Keywords](#11-important-keywords)
12. [OOP — Object Class Methods](#12-object-class-methods)
13. [Exception Handling (Deep Dive)](#13-exception-handling-deep-dive)
14. [Collections Framework (Deep Dive)](#14-collections-framework-deep-dive)
15. [Wrapper Classes & Autoboxing](#15-wrapper-classes--autoboxing)
16. [Enums](#16-enums)
17. [Inner Classes](#17-inner-classes)
18. [Java Memory Model Basics](#18-java-memory-model-basics)
19. [Common Interview Questions & Answers](#19-common-interview-questions--answers)

---

## 1. How Java Works

### JDK vs JRE vs JVM

```
JDK (Java Development Kit)
├── JRE (Java Runtime Environment)
│   ├── JVM (Java Virtual Machine)
│   │   ├── Class Loader
│   │   ├── Bytecode Verifier
│   │   ├── Execution Engine (Interpreter + JIT Compiler)
│   │   └── Garbage Collector
│   └── Core Libraries (java.lang, java.util, java.io, etc.)
├── javac (Compiler)
├── jar (Archive tool)
├── javadoc (Documentation tool)
└── jdb (Debugger)
```

- **JVM**: Executes bytecode. Platform-specific (different JVM for Windows/Mac/Linux).
- **JRE**: JVM + libraries. Enough to RUN Java programs.
- **JDK**: JRE + development tools. Needed to COMPILE Java programs.

### Compilation & Execution Flow

```
Source Code (.java)
       │
       ▼  javac (compiler)
Bytecode (.class)
       │
       ▼  JVM
Machine Code (platform-specific)
```

```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

```bash
javac HelloWorld.java   # Produces HelloWorld.class (bytecode)
java HelloWorld         # JVM executes the bytecode
```

### Why "Write Once, Run Anywhere"?

The `.class` file (bytecode) is platform-independent. Each OS has its own JVM that translates bytecode to native machine code. This is why Java is NOT purely compiled or purely interpreted — it's both.

### Interview Q: Is Java pass-by-value or pass-by-reference?

**Java is ALWAYS pass-by-value.** But for objects, the "value" being passed is the reference (memory address). So:

```java
// Primitives: copy of value
void change(int x) { x = 10; }  // original NOT affected

// Objects: copy of reference
void change(Dog d) { d.setName("Rex"); }  // original IS affected (same object)
void change(Dog d) { d = new Dog("Rex"); } // original NOT affected (new reference)
```

---

## 2. Data Types & Variables

### Primitive Types (8 total)

| Type    | Size    | Default | Range                                          | Example               |
|---------|---------|---------|------------------------------------------------|------------------------|
| byte    | 1 byte  | 0       | -128 to 127                                    | `byte b = 100;`       |
| short   | 2 bytes | 0       | -32,768 to 32,767                              | `short s = 10000;`    |
| int     | 4 bytes | 0       | -2^31 to 2^31 - 1 (~2.1 billion)              | `int i = 100000;`     |
| long    | 8 bytes | 0L      | -2^63 to 2^63 - 1                              | `long l = 100000L;`   |
| float   | 4 bytes | 0.0f    | ~6-7 decimal digits precision                   | `float f = 3.14f;`    |
| double  | 8 bytes | 0.0d    | ~15 decimal digits precision                    | `double d = 3.14159;` |
| char    | 2 bytes | '\u0000'| 0 to 65,535 (Unicode)                          | `char c = 'A';`       |
| boolean | ~1 bit  | false   | true / false                                    | `boolean b = true;`   |

### Reference Types
 Everything that is NOT a primitive is a reference type: Strings, arrays, objects, interfaces.

```java
String name = "Alice";   // reference to String object in heap
int[] arr = {1, 2, 3};   // reference to array object in heap
Dog dog = new Dog();     // reference to Dog object in heap
```

### Type Casting 

```java
// Widening (implicit) - smaller to larger, no data loss
byte → short → int → long → float → double 
int i = 100;
double d = i;    // 100.0 - automatic 


// Narrowing (explicit) - larger to smaller, possible data loss 
double d = 9.78; 
int i = (int) d; // 9 - truncated, NOT rounded 

//char to int (implicit)
char c = 'A';
int ascii = c; // 65 

// int to char (explicit)
int num = 66;
char ch = (char) num; // 'B'
```

### Interview: Why is `float f = 3.14;` a compile error?
`3.14` is a `double` literal by default. Assigning a `double` to a `float` is narrowing conversion. Fix: float f = 3.14f;


### Variable Types
```java
public class Example {

    // Instance variable - belongs to object, stored in heap
    private int intstanceVar = 10;

    // Static variable (class variable) - belongs to class, shared across all instances
    private static int staticVar = 20;

    public void method() {
        // Local variable - lives on stack, MUST be initialzed before use 
        int localVar = 30;
        
        // No Default value for local variables!
        // int x; System.out.println(x); // COMPILE ERROR 
    }
}
```

### var (Local Variable Type Inference - Java 10+)
```java
var name = "Alice";         // inferred as String
var list = new ArrayList(); // inferred as ArrayList
car count = 10;             // inferred as int 

// Restrictions:
// var x;                   // ERROR  - can't infer without initializer
// var x = null;            // ERROR   - can't infer null type// var x = {1, 2, 3};       // ERROR  - can't infer array initializer
// Can ONLY be used local variables, NOT fields or method params
```

---

## 3. Operators 

### Arithmetic 
```java
int a = 10, b = 3;
a + b   // 13
a - b   // 7
a * b   // 30
a / b   // 3 (integer division — truncates)
a % b   // 1 (modulo — remainder)

// Integer division trap:
double result = 10 / 3;      // 3.0 (NOT 3.333)
double result = 10.0 / 3;    // 3.333... (at least one operand must be double)
double result = (double) 10 / 3; // 3.333...
```


### Comparison & Logical

```java 
// Comparison (returns boolean)
==  !=  >  <  >=  <=

// Logical
&&  // AND (short-circuit: if left is false, right is NOT evaluated)
||  // OR  (short-circuit: if left is true, right is NOT evaluated)
!   // NOT

// Interview Q: Difference between & and &&?
// & always evaluates both sides; && short-circuits
if (obj != null && obj.getValue() > 0) { }  // safe — short-circuits if null
if (obj != null & obj.getValue() > 0) { }   // UNSAFE — NullPointerException if null
```

### Increment/Decrement 
```java
int x = 5;
int a = x++;  // a = 5, x = 6 (post-increment: use THEN increment)
int b = ++x;  // b = 7, x = 7 (pre-increment: increment THEN use)

// Classic interview trick:
int i = 0;
i = i++;  // i is STILL 0! (stores old value, then increments, then assigns old value)
```

### Bitwise (common in interviews)
```java
&   // AND:  5 & 3  = 0101 & 0011 = 0001 = 1
|   // OR:   5 | 3  = 0101 | 0011 = 0111 = 7
^   // XOR:  5 ^ 3  = 0101 ^ 0011 = 0110 = 6
~   // NOT:  ~5     = ...11111010 (inverts all bits)
<<  // Left shift:  1 << 3 = 8  (multiply by 2^n)
>>  // Right shift: 8 >> 2 = 2  (divide by 2^n, preserves sign)
>>> // Unsigned right shift (fills with 0)

// Interview trick: Check if number is even/odd
(n & 1) == 0  // even
(n & 1) == 1  // odd

// Swap without temp variable
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

### Ternary Operator 

```java 
int max = (a > b) ? a : b;
String status = (age >= 18) ? "Adult" : "Minor";
```

### instanceof
```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal; //safe cast and old way 
} 

// Pattern matching (Java 16+)
if (animal instanceof Dog dog) {
    dog.bark(); // no explicit case needed
}
```


---


## 4. Control Flow 

### if-else

```java
if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";
} else if (score >= 70) {
    grade = "C";
} else {
    grade = "F";
}
```

### switch

```java
// Traditional switch
switch (day) {
    case "MONDAY":
    case "TUESDAY":
        System.out.println("Weekday");
        break; //MUST have break, or falls through!
    case "SATURDAY":
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Unknown");
}

//Enhanced switch (Java 14+) - NO break needed, NO fall-through
switch (day) {
    case "MONDAY", "TUESDAY" -> System.out.println("Weekday");
    case "SATURDAY" -> System.out.println("Weekend");
    default         -> System.out.println("Unknown");
}

//Switch expression - returns a value
String type = switch (day) {
    case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
    case "SATURDAY", "SUNDAY" -> "Weekend";
    default -> throw new IllegalArgumentException("Invalid day");
};

// yield (for multi-line blocks in switch expressions)
String type = switch (day) {
    case "MONDAY" -> {
        System.out.println("Start of week");
        yield "Weekday";
    }
    default -> "Other";
};
```

### Loops

```java
// for loop
for (int i = 0; i < 10; i++) { }

// while loop
while (condition) { }

// do-while (executes at least once)
do {
    // body
} while (condition);

// enhanced for-each
for (String name : names) { }

// break & continue
for (int i = 0; i < 10; i++) {
    if (i == 5) break;     // exits loop entirely
    if (i % 2 == 0) continue;  // skips to next iteration
}

// labeled break (for nested loops)
outer:
for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
        if (i * j > 6) break outer;  // breaks out of BOTH loops
    }
}
```

### Interview Q: What's the difference between `while` and `do-while`?

`do-while` always executes the body at least once, even if the condition is false from the start. `while` checks the condition first and may never execute.

---

## 5. Methods 


