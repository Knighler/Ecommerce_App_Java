import java.util.Scanner;
public abstract class Users {
    protected String username;
    protected String password;
    protected String dateOfBirth;

    public enum Gender {
        Male, Female
    }

    protected Gender gender;


    public Users() throws ItemFoundException {
        System.out.println("User not initialized, please try again");
        login();
    }

    public Users(String username, String password, String dateOfBirth, Gender gender) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static Users login() throws ItemFoundException {
        Scanner scanner = new Scanner(System.in);
        Users us = null;
        boolean flag = false;

        while (!flag) {
            String username;
            String password;
            // Username validation
            System.out.println("Log in: ");
            do {
                System.out.print("Enter Username : ");
                username = scanner.nextLine();
                if (username.isEmpty() || username.length() > 16) {
                    System.out.println("not define or too long(shouldn't exceed 16), Please try again.");
                }
            } while (username.isEmpty() || username.length() > 16);

            // Password validation
            do {
                System.out.print("Enter Password (must be at least 8 characters long and contain at least one number): ");
                password = scanner.nextLine();
                if (password.length() < 8 || !password.matches(".*\\d.*")) {
                    System.out.println("Password must be at least 8 characters long and contain at least one number. Please try again.");
                }
            } while (password.length() < 8 || !password.matches(".*\\d.*"));

            us = Database.getUser(username,password);

            if (us == null) {
                System.out.println("Incorrect Username or Password");

                // Prompt user to try again or redirect to signup
                while (true) {
                    System.out.print("Do you want to try logging in again? (Y/N): ");
                    String retry = scanner.nextLine().trim();
                    if (retry.equalsIgnoreCase("N")) {
                        System.out.println("Redirecting to signup...");
                        Customer.signup();
                        return null; // Exit the method after signup
                    } else if (retry.equalsIgnoreCase("Y")) {
                        System.out.println("Please try logging in again...");
                        break; // Exit this loop to retry login
                    } else {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                }
            } else {
                System.out.println("Login successful! Welcome, " + username);
                flag = true;
            }
        }
        return us;
    }

}
