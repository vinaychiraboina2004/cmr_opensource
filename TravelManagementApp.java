import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// User class definition
class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

// TravelPackage class definition
class TravelPackage {
    private String destination;
    private int duration; // duration in days
    private double price;

    public TravelPackage(String destination, int duration, double price) {
        this.destination = destination;
        this.duration = duration;
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TravelPackage{" +
                "destination='" + destination + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}

// Booking class definition
class Booking {
    private User user;
    private TravelPackage travelPackage;
    private Date bookingDate;

    public Booking(User user, TravelPackage travelPackage, Date bookingDate) {
        this.user = user;
        this.travelPackage = travelPackage;
        this.bookingDate = bookingDate;
    }

    public User getUser() {
        return user;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "user=" + user +
                ", travelPackage=" + travelPackage +
                ", bookingDate=" + bookingDate +
                '}';
    }
}

// Payment class placeholder
class Payment {
    public static boolean processPayment(User user, TravelPackage travelPackage, double amount) {
        // Here you would integrate with a real payment gateway
        System.out.println("Processing payment for " + user.getName() + " for package " + travelPackage.getDestination() + " amount: " + amount);
        return true; // Assuming payment is successful
    }
}

// Main application class
public class TravelManagementApp {
    private List<User> users;
    private List<TravelPackage> travelPackages;
    private List<Booking> bookings;

    public TravelManagementApp() {
        users = new ArrayList<>();
        travelPackages = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addTravelPackage(TravelPackage travelPackage) {
        travelPackages.add(travelPackage);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public User authenticate(String email, String password) {
        for (User user : users) {
            if (user.authenticate(email, password)) {
                return user;
            }
        }
        return null;
    }

    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void displayTravelPackages() {
        for (TravelPackage travelPackage : travelPackages) {
            System.out.println(travelPackage);
        }
    }

    public void displayBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public static void main(String[] args) {
        TravelManagementApp app = new TravelManagementApp();
        new TravelManagementGUI(app);
    }
}

// GUI class
class TravelManagementGUI {
    private TravelManagementApp app;
    private JFrame frame;
    private JTextField nameField, emailField, passwordField, destinationField, durationField, priceField, authEmailField, authPasswordField;

    public TravelManagementGUI(TravelManagementApp app) {
        this.app = app;
        frame = new JFrame("Travel Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        initializeComponents();
        frame.setVisible(true);
    }

    private void initializeComponents() {
        // User Registration
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addUser(new User(nameField.getText(), emailField.getText(), passwordField.getText()));
                JOptionPane.showMessageDialog(frame, "User Registered Successfully!");
            }
        });

        // Travel Package
        JLabel destinationLabel = new JLabel("Destination:");
        destinationField = new JTextField();
        JLabel durationLabel = new JLabel("Duration (days):");
        durationField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JButton addPackageButton = new JButton("Add Travel Package");
        addPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addTravelPackage(new TravelPackage(destinationField.getText(), Integer.parseInt(durationField.getText()), Double.parseDouble(priceField.getText())));
                JOptionPane.showMessageDialog(frame, "Travel Package Added Successfully!");
            }
        });

        // Authentication
        JLabel authEmailLabel = new JLabel("Email:");
        authEmailField = new JTextField();
        JLabel authPasswordLabel = new JLabel("Password:");
        authPasswordField = new JPasswordField();
        JButton authButton = new JButton("Login");
        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = app.authenticate(authEmailField.getText(), authPasswordField.getText());
                if (user != null) {
                    JOptionPane.showMessageDialog(frame, "Login Successful! Welcome " + user.getName());
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!");
                }
            }
        });

        // Layout components
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(registerButton);

        frame.add(destinationLabel);
        frame.add(destinationField);
        frame.add(durationLabel);
        frame.add(durationField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(addPackageButton);

        frame.add(authEmailLabel);
        frame.add(authEmailField);
        frame.add(authPasswordLabel);
        frame.add(authPasswordField);
        frame.add(authButton);
    }
}
