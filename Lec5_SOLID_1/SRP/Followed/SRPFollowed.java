
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Main class
public class SRPFollowed {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        // Runtime polymorphism
        InvoicePrinter printer;

        printer = new ConsoleInvoicePrinter();
        printer.printInvoice(cart);

        printer = new PDFInvoicePrinter();
        printer.printInvoice(cart);

        printer = new ExcelInvoicePrinter();
        printer.printInvoice(cart);

        // Database storage
        ShoppingCartStorage db = new ShoppingCartStorage(cart);
        db.saveToDatabase();
    }
}

// Product class
class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// ShoppingCart class
class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public double calculateTotal() {

        double sum = 0;

        for (Product p : products) {
            sum += p.getPrice();
        }

        return sum;
    }
}

// InvoicePrinter interface
interface InvoicePrinter {

    void printInvoice(ShoppingCart cart);
}

// Console invoice printer
class ConsoleInvoicePrinter implements InvoicePrinter {

    @Override
    public void printInvoice(ShoppingCart cart) {

        System.out.println("\nConsole Invoice");
        System.out.println("----------------------");

        for (Product p : cart.getProducts()) {
            System.out.println(
                p.getName() + " - Rs " + p.getPrice()
            );
        }

        System.out.println("Total: Rs " + cart.calculateTotal());
    }
}

// PDF invoice printer
class PDFInvoicePrinter implements InvoicePrinter {

    @Override
    public void printInvoice(ShoppingCart cart) {

        System.out.println("\nGenerating PDF Invoice...");

        for (Product p : cart.getProducts()) {
            System.out.println(
                p.getName() + " - Rs " + p.getPrice()
            );
        }

        System.out.println("Total: Rs " + cart.calculateTotal());
        System.out.println("PDF generated successfully!");
    }
}

// Excel invoice printer
class ExcelInvoicePrinter implements InvoicePrinter {

    @Override
    public void printInvoice(ShoppingCart cart) {

        System.out.println("\nGenerating Excel Invoice...");

        for (Product p : cart.getProducts()) {
            System.out.println(
                p.getName() + " - Rs " + p.getPrice()
            );
        }

        System.out.println("Total: Rs " + cart.calculateTotal());
        System.out.println("Excel generated successfully!");
    }
}

// ShoppingCartStorage class
class ShoppingCartStorage {

    private ShoppingCart cart;

    public ShoppingCartStorage(ShoppingCart cart) {
        this.cart = cart;
    }

    public void saveToDatabase() {

        System.out.println("\nSaving shopping cart to database...");

        System.out.println("Cart saved successfully!");
    }
}
