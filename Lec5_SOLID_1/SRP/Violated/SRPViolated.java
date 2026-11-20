import java.util.ArrayList;
import java.util.List;

public class SRPViolated {
    public static void main(String[] args){
        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        cart.printInvoice();
        cart.saveToDB();

    }
}

class Product{
    public double price;
    public String name;

    public Product (String name, double price){
        this.name = name;
        this.price = price;
    }
}

// violating, one class doing many works
class ShoppingCart{

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> getProduct(){
        return products;
    }
    
    // 1. calc total price of product in cart
    public double calcTotalPrice(){
        double sum =0;
        for(Product p: products){
            sum += p.price;
        }

        return sum;
    }

    // 2. print invoice
    public void printInvoice(){
        System.out.println("Shopping cart Invoice");
        for(Product p: products){
            System.out.println(p.name +" -Rs "+ p.price);
        }
        System.out.println("Total: Rs "+ calcTotalPrice());
    }

    public void saveToDB(){
        System.out.println("Saving shopping cart to database...");
    }
}
