import java.util.List;
import java.util.ArrayList;

public class DesignPatternsDemo {
    public static void main(String[] args) {
        System.out.println("Exercise 1: Singleton Pattern");
        Logger appLogger1 = Logger.getInstance();
        Logger appLogger2 = Logger.getInstance();
        appLogger1.log("Application started");
        System.out.println(appLogger1 == appLogger2);

        System.out.println("Exercise 2: Factory Method Pattern");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();

        System.out.println("Exercise 3: Builder Pattern");
        Computer gamingRig = new Computer.Builder()
                .setCpu("Intel i9")
                .setRam("32GB")
                .setStorage("2TB SSD")
                .setGraphicsCard("RTX 4090")
                .build();
        System.out.println(gamingRig);

        System.out.println("Exercise 4: Adapter Pattern");
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(2500);
        PaymentProcessor paypalProcessor = new PaypalAdapter(new PaypalGateway());
        paypalProcessor.processPayment(1800);

        System.out.println("Exercise 5: Decorator Pattern");
        Notifier baseNotifier = new EmailNotifier();
        Notifier fullNotifier = new SlackNotifierDecorator(new SMSNotifierDecorator(baseNotifier));
        fullNotifier.send("Server downtime scheduled tonight");

        System.out.println("Exercise 6: Proxy Pattern");
        Image profilePicture = new ProxyImage("profile.png");
        profilePicture.display();
        profilePicture.display();

        System.out.println("Exercise 7: Observer Pattern");
        StockMarket nseMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();
        nseMarket.registerObserver(mobileApp);
        nseMarket.registerObserver(webApp);
        nseMarket.setStockPrice("TCS", 3850.0);

        System.out.println("Exercise 8: Strategy Pattern");
        PaymentContext checkout = new PaymentContext(new CreditCardPayment());
        checkout.pay(4500);
        checkout.setStrategy(new PayPalPayment());
        checkout.pay(1200);

        System.out.println("Exercise 9: Command Pattern");
        Light livingRoomLight = new Light();
        RemoteControl remote = new RemoteControl();
        remote.setCommand(new LightOnCommand(livingRoomLight));
        remote.pressButton();
        remote.setCommand(new LightOffCommand(livingRoomLight));
        remote.pressButton();

        System.out.println("Exercise 10: MVC Pattern");
        Student student = new Student("Ananya Sharma", 101, "A");
        StudentView studentView = new StudentView();
        StudentController studentController = new StudentController(student, studentView);
        studentController.updateStudentGrade("A+");
        studentController.displayStudent();

        System.out.println("Exercise 11: Dependency Injection");
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(repository);
        customerService.findCustomer(7);
    }
}

// Exercise 1: Singleton Pattern
class Logger {
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Exercise 2: Factory Method Pattern
interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word document");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF document");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel document");
    }
}

abstract class DocumentFactory {
    abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    Document createDocument() {
        return new ExcelDocument();
    }
}

// Exercise 3: Builder Pattern
class Computer {
    private final String cpu;
    private final String ram;
    private final String storage;
    private final String graphicsCard;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }

    public String toString() {
        return "Computer [cpu=" + cpu + ", ram=" + ram + ", storage=" + storage + ", graphicsCard=" + graphicsCard + "]";
    }

    static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String graphicsCard;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// Exercise 4: Adapter Pattern
interface PaymentProcessor {
    void processPayment(double amount);
}

class StripeGateway {
    void makeTransaction(double amountInRupees) {
        System.out.println("Stripe processed payment of " + amountInRupees);
    }
}

class PaypalGateway {
    void sendPayment(double amountInRupees) {
        System.out.println("Paypal processed payment of " + amountInRupees);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    public void processPayment(double amount) {
        stripeGateway.makeTransaction(amount);
    }
}

class PaypalAdapter implements PaymentProcessor {
    private PaypalGateway paypalGateway;

    PaypalAdapter(PaypalGateway paypalGateway) {
        this.paypalGateway = paypalGateway;
    }

    public void processPayment(double amount) {
        paypalGateway.sendPayment(amount);
    }
}

// Exercise 5: Decorator Pattern
interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    NotifierDecorator(Notifier wrappedNotifier) {
        this.wrappedNotifier = wrappedNotifier;
    }

    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

class SMSNotifierDecorator extends NotifierDecorator {
    SMSNotifierDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    public void send(String message) {
        super.send(message);
        System.out.println("SMS sent: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    SlackNotifierDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    public void send(String message) {
        super.send(message);
        System.out.println("Slack message sent: " + message);
    }
}

// Exercise 6: Proxy Pattern
interface Image {
    void display();
}

class RealImage implements Image {
    private String fileName;

    RealImage(String fileName) {
        this.fileName = fileName;
        loadFromServer();
    }

    private void loadFromServer() {
        System.out.println("Loading " + fileName + " from remote server");
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;

    ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// Exercise 7: Observer Pattern
interface Observer {
    void update(String stockSymbol, double price);
}

interface Stock {
    void registerObserver(Observer observer);

    void deregisterObserver(Observer observer);

    void notifyObservers(String stockSymbol, double price);
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String stockSymbol, double price) {
        for (Observer observer : observers) {
            observer.update(stockSymbol, price);
        }
    }

    public void setStockPrice(String stockSymbol, double price) {
        notifyObservers(stockSymbol, price);
    }
}

class MobileApp implements Observer {
    public void update(String stockSymbol, double price) {
        System.out.println("Mobile app alert: " + stockSymbol + " is now " + price);
    }
}

class WebApp implements Observer {
    public void update(String stockSymbol, double price) {
        System.out.println("Web app alert: " + stockSymbol + " is now " + price);
    }
}

// Exercise 8: Strategy Pattern
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void pay(double amount) {
        strategy.pay(amount);
    }
}

// Exercise 9: Command Pattern
interface Command {
    void execute();
}

class Light {
    void turnOn() {
        System.out.println("Light is ON");
    }

    void turnOff() {
        System.out.println("Light is OFF");
    }
}

class LightOnCommand implements Command {
    private Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}

// Exercise 10: MVC Pattern
class Student {
    private String name;
    private int id;
    private String grade;

    Student(String name, int id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    String getGrade() {
        return grade;
    }

    void setGrade(String grade) {
        this.grade = grade;
    }
}

class StudentView {
    void displayStudentDetails(String name, int id, String grade) {
        System.out.println("Student: " + name + ", ID: " + id + ", Grade: " + grade);
    }
}

class StudentController {
    private Student student;
    private StudentView studentView;

    StudentController(Student student, StudentView studentView) {
        this.student = student;
        this.studentView = studentView;
    }

    void updateStudentGrade(String grade) {
        student.setGrade(grade);
    }

    void displayStudent() {
        studentView.displayStudentDetails(student.getName(), student.getId(), student.getGrade());
    }
}

// Exercise 11: Dependency Injection
interface CustomerRepository {
    String findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public String findCustomerById(int id) {
        return "Customer-" + id;
    }
}

class CustomerService {
    private CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    void findCustomer(int id) {
        String customer = customerRepository.findCustomerById(id);
        System.out.println("Found: " + customer);
    }
}
