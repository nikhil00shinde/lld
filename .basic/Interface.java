// one interface, multiple implementations, and a service that depends only on the interface.


// Exercise 1: Log Formatter
// Problem: Build a logging system where the format of log messages is configurable. A Logger class writes log messages, but the format (plain text vs. JSON) is determined by an injected Formatter interface.

// Requirements:

//     Formatter interface with a format(message) method that takes a string and returns a formatted string
//     PlainFormatter: returns the message as-is (e.g., "Server started on port 8080")
//     JsonFormatter: returns the message wrapped in JSON (e.g., {"log": "Server started on port 8080"})
//     Logger class takes a Formatter in its constructor and has a log(message) method that formats the message, then prints it

interface Formatter {
    Stirng format(String message);
}

class PlainFormatter implements Formatter {
    public String format(String message){
        return message;
    }
}

class JsonFormatter implements Formatter {
    public String format(String message){
        return "{\"log\":"+ message + "\"}";
    }
}

class Logger {
    private Formatter formatter;

    public Logger(Formatter formatter){
        this.formatter = formatter;
    }

    public void log(String message){
        System.out.println(formatter.format(message));
    }
}

public class Main{
    public static void main(String[] args){
        Logger plainLogger = new Logger(new PlainFormatter());
        plainLogger.log("Server started on port 8080");

        Logger jsonLogger = new Logger(new JsonFormatter());
        jsonLogger.log("Server started on port 8080");
    }
}
// Exercise 2: Input Validator

// Problem: Build a registration system where multiple validation rules are applied to user input. Each rule is a separate implementation of a Validator interface, and the RegistrationService runs all validators before accepting the registration.

// Requirements:

//     Validator interface with a validate(input) method that returns true if valid, false otherwise
//     EmailValidator: returns true if the input contains @
//     PasswordValidator: returns true if the input has 8 or more characters
//     RegistrationService: takes a list of validators in its constructor. Its register(input) method runs all validators and prints whether the input passed or failed

import java.util.List;

interface Validator {
    boolean validate(String input);
}

class EmailValidator implements Validator {
    public boolean validate(String input){
        if(input.contains("@")) return true;
        return false;
    }
}

class PasswordValidator implements Validator {
    public boolean validate(String input){
        if(input.length() >= 8) return true;
        return false;
    }
}


class RegistrationService {
    private List<Validator> validators;

    public RegistrationService(List<Validator> validators){
        this.validators = validators;
    }

    public boolean register(String input){
           boolean allPassed = true;
        for (Validator validator : validators) {
            if (!validator.validate(input)) {
                allPassed = false;
                break;
            }
        }
        System.out.println("\"" + input + "\" - " + (allPassed ? "PASSED" : "FAILED"));
    }
}

public class Main {
    public static void main(String[] args){
        List<Validator> emailValidators = List.of(new EmailValidator());
        RegistrationService emailReg = new RegistrationService(emailValidators);
        System.out.println(emailReg.register("user@example.com"));
        System.out.println(emailReg.register("invalid-email"));

        List<Validator> passwordValidators = List.of(new PasswordValidator());
        RegistrationService passReg = new RegistrationService(passwordValidators);
        System.out.println(passReg.register("strongpassword"));
        System.out.println(passReg.register("short"));
    }
}