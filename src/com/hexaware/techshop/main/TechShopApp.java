package com.hexaware.techshop.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.hexaware.techshop.dao.IOrderDetailDAO;
import com.hexaware.techshop.dao.OrderDetailDAOImpl;
import com.hexaware.techshop.entity.Customer;
import com.hexaware.techshop.entity.Inventory;
import com.hexaware.techshop.entity.Order;
import com.hexaware.techshop.entity.OrderDetail;
import com.hexaware.techshop.entity.Payment;
import com.hexaware.techshop.entity.Product;
import com.hexaware.techshop.exception.IncompleteOrderException;
import com.hexaware.techshop.exception.InvalidDataException;
import com.hexaware.techshop.exception.PaymentNotFoundException;
import com.hexaware.techshop.service.CustomerService;
import com.hexaware.techshop.service.InventoryService;
import com.hexaware.techshop.service.OrderService;
import com.hexaware.techshop.service.PaymentService;
import com.hexaware.techshop.service.ProductService;
import com.hexaware.techshop.util.DBConnection;


public class TechShopApp {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		Scanner s=new Scanner(System.in);	
		CustomerService customerService = new CustomerService();
		ProductService productService = new ProductService();
        InventoryService inventoryService = new InventoryService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

		
		while(true) {
			System.out.println("\n--TechShop Main menu-- ");
			System.out.println("1. Register customer");
			System.out.println("2. Manage Product Catalog");
			System.out.println("3. Place order");
			System.out.println("4. Track order status");
			System.out.println("5. Manage Inventory");
			System.out.println("6. View Sales report");
			System.out.println("7. Update Customer Account");
			System.out.println("8. Process payment");
			System.out.println("9. Search Products");
			System.out.println("10. Exit");
			System.out.println("Enter your choice: ");
			int ch=s.nextInt();
			s.nextLine();
			
			switch(ch) {
			case 1: //register customer		
				 
				System.out.println("--Registering new Customer--\n");
                System.out.println("Enter First Name: ");
                String fname = s.nextLine();
                System.out.println("Enter Last Name: ");
                String lname = s.nextLine();
                System.out.println("Enter Email: ");
                String email = s.nextLine();
                System.out.println("Enter Phone: ");
                String phone = s.nextLine();
                System.out.println("Enter Address: ");
                String address = s.nextLine();
				try {
					Customer c = new Customer(fname, lname, email, phone, address);
	                customerService.registerCustomer(c);
	                System.out.println("Customer added successfully\n");
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());
				}                 
				break;
				
			case 2:   //product catalog
				
				System.out.println("1. Add Product \n2. Update Product\n3. Delete Product\n4. View Products\n5. Exit");
				System.out.println("Enter your choice: ");
                int pc = s.nextInt();
                s.nextLine();
                switch (pc) {
                    case 1:
                    	System.out.println("Enter Product Name: ");
                    	String pname=s.nextLine();
                    	System.out.println("Enter Description: ");
                    	String desc=s.nextLine();
                    	System.out.println("Enter price: ");
                    	double price = s.nextDouble();
                    	s.nextLine();
                    	System.out.println("Enter category: ");
                    	String category = s.nextLine();	
                    	Product product;
                    	try {
                    		product = new Product(pname,desc,price,category);
                    		productService.addProduct(product);
                    		System.out.println("Product added successfully\n");
                    	} catch (InvalidDataException e) {
                    		System.out.println(e.getMessage());
                    	}
                    	break;
                    case 2:
                    	System.out.println("Enter productId for update: ");
        				int updatePid=s.nextInt();
        				s.nextLine();
        				System.out.println("Enter new description: ");
        				String newDesc=s.nextLine();
        				System.out.println("Enter new price: ");
        				double newPrice=s.nextDouble();
        				try {
        					productService.updateProduct(updatePid, newDesc, newPrice);
        					System.out.println("Product updated successfully\n");
        				} catch (InvalidDataException e) {
        					System.out.println(e.getMessage());
        				}				
        				break;
                    case 3:
                    	System.out.println("Enter productId to delete: ");
        				int deletePid=s.nextInt();
        				try {
        					productService.removeProduct(deletePid);
        					System.out.println("Product removed successfully\n");
        				} catch (InvalidDataException e) {
        					System.out.println(e.getMessage());
        				}
        				break;
                    case 4:
                    	System.out.println("\n--Viewing all products--\n");
        				List<Product> products;	
        				try {
        					products = productService.getAllProducts();
        					for(Product p:products) {
        						System.out.println("ProductId: "+p.getProductId()+" ProductName: "+p.getProductName()+" Description: "+p.getDescription()+" Price: \n"+p.getPrice());					
        					}
        				} catch (InvalidDataException e) {
        					System.out.println(e.getMessage());
        				}
        				break;
                    case 5:
                    	System.out.println("Exiting product catalog");
                    	System.exit(pc);
                }
                break;
                
			case 3:   // place order  
				
				System.out.println("--Place new order--\n");
				System.out.println("Enter customerId:");
				int custOrderId=s.nextInt();
				try {
					Customer customer = customerService.getCustomerById(custOrderId);
					if(customer == null) {
					    System.out.println("Customer not found.\n");
					    break;
					}
					Order order = new Order(customer,LocalDate.now() , 0.0, "Pending");
					orderService.addOrder(order);
					double totalAmount=0;
					System.out.println("Enter the number of products you want to order: ");
					int n=s.nextInt();
					for(int i=0; i<n; i++) {
						System.out.println("Enter productId: ");
						int prodId=s.nextInt();
						Product prod=productService.getProductById(prodId);
						if(prod==null) {
							System.out.println("Invalid productId");
						}
					System.out.println("Enter quantity: ");
					int qty=s.nextInt();
					OrderDetail item=new OrderDetail(order,prod,qty);
					IOrderDetailDAO orderDetaildao = new OrderDetailDAOImpl();
					orderDetaildao.insertOrderDetail(item);
					totalAmount += prod.getPrice()*qty;
					}
					order.setTotalAmount(totalAmount);
					orderService.updateTotalAmount(order.getOrderId(), totalAmount);
					System.out.println("Order placed successfully \n");
				} catch (InvalidDataException | IncompleteOrderException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 4:  //track order status
				
				System.out.println("Enter OrderId: ");
				int oid=s.nextInt();
				Order order;
				try {
					order = orderService.getOrderById(oid);
					System.out.println("Status: \n"+order.getOrderStatus());	
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());	
				}					
				break;
				
			case 5:    //manage inventory
				
				System.out.println("Enter productId: ");
				int invProductId=s.nextInt();
				try {
				Product invProduct = productService.getProductById(invProductId);
				if(invProduct == null || invProduct.getProductId()==0) {
					throw new InvalidDataException("Invalid productId\n ");					
				}
				System.out.println("Enter quantity: ");
				int invQuantity=s.nextInt();
				inventoryService.addProductToInventory(invProduct, invQuantity);
					System.out.println("Inventory added successfully \n");
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());
				}
				break;										
				
			case 6:   //sales report
				System.out.println("-- Viewing Sales Report--\n");
				List<Order> allOrders = orderService.getAllOrders();
				for(Order o:allOrders) {
					System.out.println(o);
				}
				break;
				
			case 7:    // update customer details
				
				System.out.println("Enter Customer Id to update: ");
				int custId=s.nextInt();
				s.nextLine();
				System.out.println("Enter Email: ");
	            String newEmail = s.nextLine();
	            System.out.println("Enter Phone: ");
	            String newPhone = s.nextLine();
	            System.out.println("Enter Address: ");
	            String newAddress = s.nextLine();	           
	            try {
					customerService.updateCustomer(custId, newEmail, newPhone, newAddress);
		            System.out.println("Customer details updated \n");
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());	
				}
				break;
	            
			case 8:    //process payment
				
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
				Payment payment = new Payment(payOrder, method, amt, payStatus);
				paymentService.recordPayment(payment);
				System.out.println("Payment recorded \n");
				break;
				
			case 9:    // search products	
				
				System.out.println("Enter Product name: ");
				String search=s.nextLine();
				try {
					List<Product> results = productService.searchProductByName(search);
					for(Product pr: results) {
						System.out.println(pr);
					}
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());	
				}
				break;
							
			case 10:				
				System.out.println("Exiting");
				DBConnection.closeConnection();
				System.exit(0);				
				
			default:
				System.out.println("Invalid choice");
				break;					
			}
		}
		
	}

}
