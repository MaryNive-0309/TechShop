package com.hexaware.techshop.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.entity.Payment;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.service.CustomerService;
import com.hexaware.techshop.service.InventoryService;
import com.hexaware.techshop.service.OrderDetailService;
import com.hexaware.techshop.service.OrderService;
import com.hexaware.techshop.service.PaymentService;
import com.hexaware.techshop.service.ProductService;
import com.hexaware.techshop.util.DBConnection;


public class TechShopApp {

	public static void main(String[] args) throws InvalidDataException {
		// TODO Auto-generated method stub
		
		Scanner s=new Scanner(System.in);	
		CustomerService customerService = new CustomerService();
		ProductService productService = new ProductService();
        InventoryService inventoryService = new InventoryService();
        OrderService orderService = new OrderService();
        OrderDetailService orderDetailService = new OrderDetailService();
        PaymentService paymentService = new PaymentService();

		
		while(true) {
			System.out.println("Select the operation you want to perform: ");
			System.out.println("1. Register customer");
			System.out.println("2. Add product");
			System.out.println("3. Update product");
			System.out.println("4. Delete product");
			System.out.println("5. View products");
			System.out.println("6. Add new order");
			System.out.println("7. Update orderStatus");
			System.out.println("8. Cancel order");
			System.out.println("9. Add Inventory");
			System.out.println("10. Make payment");
			System.out.println("11. View payments by orderId");
			System.out.println("12. Low stock products");
			System.out.println("13. Exit");
			System.out.println("Enter your choice: ");
			int ch=s.nextInt();
			s.nextLine();
			
			switch(ch) {
			case 1: //Register customer		
				 
				System.out.println("--Registering new Customer--\n");
                System.out.print("Enter First Name: ");
                String fname = s.nextLine();
                System.out.print("Enter Last Name: ");
                String lname = s.nextLine();
                System.out.print("Enter Email: ");
                String email = s.nextLine();
                System.out.print("Enter Phone: ");
                String phone = s.nextLine();
                System.out.print("Enter Address: ");
                String address = s.nextLine();
                try {
                Customer c = new Customer(fname, lname, email, phone, address);                 
                customerService.registerCustomer(c);
                System.out.println("Customer added successfully");
                }
                catch (InvalidDataException e) {
                    System.out.println("Registration failed: " + e.getMessage());
                }
				break;
				
			case 2:   //Add product
				System.out.println("Enter ProductId: ");
				int pid=s.nextInt();
				s.nextLine();
				System.out.println("Enter Product Name: ");
				String pname=s.nextLine();
				System.out.println("Enter Description: ");
				String desc=s.nextLine();
				System.out.println("Enter price: ");
				double price = s.nextDouble();
				
				Product product=new Product(pid,pname,desc,price);
				productService.addProduct(product);
				System.out.println("Product added successfully");
				break;
				
			case 3:  //Update product
				
				System.out.println("Enter productId for update: ");
				int updatePid=s.nextInt();
				s.nextLine();
				System.out.println("Enter new description: ");
				String newDesc=s.nextLine();
				System.out.println("Enter new price: ");
				double newPrice=s.nextDouble();
				productService.updateProduct(pid, desc, newPrice);
				System.out.println("Product updated successfully");
				break;
				
			case 4:   //Delete product
				
				System.out.println("Enter productId to delete: ");
				int deletePid=s.nextInt();
				productService.removeProduct(pid);
				System.out.println("Product removed successfully");
				break;
				
			case 5:   //View products
				
				System.out.println("\n--Viewing all products--\n");
				List<Product> products = productService.getAllProducts();
				for(Product p:products) {
					p.getProductDetails();
				}
				break;
				
			case 6:   //Add new order
				
				System.out.println("--Placing order--\n");
				System.out.println("Enter OrderId");
				int orderId=s.nextInt();
				s.nextLine();
				System.out.println("Enter customerId:");
				int custOrderId=s.nextInt();
				Customer customer = customerService.getCustomerById(custOrderId);
				Order order = new Order(orderId, customer, new Date(), 0.0, "Placed");
				orderService.addOrder(order);
				System.out.println("Order placed successfully");
				break;
				
			case 7:   //Update orderStatus
				
				System.out.println("Enter OrderId to update status: ");
				int oidToUpdate=s.nextInt();
				s.nextLine();
				System.out.println("Enter new status: ");
				String status=s.nextLine();
				orderService.updateOrderStatus(oidToUpdate, status);
				System.out.println("Order status updated successfully");
				break;
				
			case 8:   //Cancel order
				
				System.out.println("Enter orderId to cancel: ");
				int cancelId= s.nextInt();
				orderService.cancelOrder(cancelId);
				System.out.println("Order cancelled");
				break;
				
			case 9:    //Add Inventory
				
				System.out.println("Enter InventoryId: ");
				int invId=s.nextInt();
				s.nextLine();
				System.out.println("Enter productId: ");
				int invProductId=s.nextInt();
				Product invProduct = productService.getProductById(invProductId);
				if(invProduct == null) {
					throw new InvalidDataException("Product not found");					
				}
				System.out.println("Enter quantity: ");
				int invQuantity=s.nextInt();
				Inventory inv=new Inventory(invId, invProduct, invQuantity, new Date());
				inventoryService.addInventory(inv);
				System.out.println("Inventory added successfully");
				break;
				
			case 10:    //Make payment
				
				System.out.println("Enter PaymentId: ");
				int paymentId=s.nextInt();
				s.nextLine();
				System.out.println("Enter orderId: ");
				int payOrderId=s.nextInt();
				s.nextLine();
				System.out.println("Enter Method of payment(Credit card/UPI/Cash): ");
				String method=s.nextLine();
				System.out.println("Enter Amount: ");
				double amt=s.nextDouble();
				s.nextLine();
				System.out.println("Enter status: ");
				String payStatus=s.nextLine();
				
				Order payOrder = new Order();
				payOrder.setOrderId(payOrderId);
				Payment payment = new Payment(paymentId, payOrder, method, amt, payStatus);
				paymentService.recordPayment(payment);
				System.out.println("Payment recorded");
				break;
				
			case 11:    //View payments by orderId
								
				System.out.println("Enter OrderId to view payment details: ");
				int viewPayId=s.nextInt();
				List<Payment> payments=paymentService.getPaymentsByOrderId(viewPayId);
				System.out.println("--Viewing Payment details for OrderId--\n");
				for(Payment p: payments) {					
					System.out.println(p);
				}
				break;
				
			case 12:    //Low stock products
				System.out.println("Enter threshold: ");
				int threshold= s.nextInt();
				List<Inventory> lowStock = inventoryService.getLowStock(threshold);
				for(Inventory i: lowStock) {
					i.listLowStockProducts(threshold);
				}
				break;
				
			case 13:
				System.out.println("Exiting");
				DBConnection.closeConnection();
				System.exit(1);				
				
			default:
				System.out.println("Invalid choice");
					
			}
		}
	}

}
