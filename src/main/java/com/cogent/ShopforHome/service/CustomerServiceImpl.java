package com.cogent.ShopforHome.service;

import com.cogent.ShopforHome.entity.*;
import com.cogent.ShopforHome.repository.CustomerRepository;
import com.cogent.ShopforHome.repository.OrderRepository;
import com.cogent.ShopforHome.repository.ProductRepository;
import com.cogent.ShopforHome.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final double SALES_TAX=8.875;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public void addToFavorites(long customer_id, long product_id) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(product_id).orElseThrow(()->new RuntimeException("Product not found"));

        customer.getProductWishList().add(product);
        customerRepository.save(customer);
    }

    @Override
    public List<Product> getFavorites(long customer_id) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(() -> new RuntimeException("Customer not found"));

        return customer.getProductWishList();
    }

    @Override
    public void removeFromFavorites(long customer_id, long product_id) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(product_id).orElseThrow(()->new RuntimeException("Product not found"));
        if(!customer.getProductWishList().contains(product)) {
            throw new RuntimeException("Property not found");
        }
        customer.getProductWishList().remove(product);
        customerRepository.save(customer);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(long id) {

        customerRepository.deleteById(id);
    }






//    @Override
//    public void addProductToCart(long customerId, long cartId,  long productId) {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
//        ShoppingCart cart=cartRepository.findById(cartId).orElseThrow(()->new RuntimeException("customer not found"));
//        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
//
//
//        product.setNumberInStock(product.getNumberInStock()-1);
//        cart.getProducts().add(product);
//        customer.setCart(cart);
//
//        productRepository.save(product);
//        cartRepository.save(cart);
//        customerRepository.save(customer);
//
////        customer.getCart().getProducts().add(product);
////       // cart.getProducts().add(product);
////        product.setNumberInStock(product.getNumberInStock()-1);
////       // cartRepository.save(cart);
////        customerRepository.save(customer);
////        productRepository.save(product);
//    }
//
//    @Override
//    public void removeProductFromCart(long customerId, long cartId, long productId) {
//        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
//        ShoppingCart cart=cartRepository.findById(cartId).orElseThrow(()->new RuntimeException("customer not found"));
//        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
//
////        customer.getCart().getProducts().remove(product);
////        product.setNumberInStock(product.getNumberInStock()-1);
////        customerRepository.save(customer);
////        productRepository.save(product);
//
//        if(!customer.getCart().getProducts().contains(product)) {
//            throw new RuntimeException("Property not found");
//        }
//        product.setNumberInStock(product.getNumberInStock()+1);
//        cart.getProducts().remove(product);
//        customer.setCart(cart);
//
//
//        productRepository.save(product);
//        cartRepository.save(cart);
//        customerRepository.save(customer);
//
//    }

    @Override
    public Order proceedToCheckout(long customerId, ShoppingCart shoppingCart) {
        double subTotal=0;
         double discount=0;
         double totalBeforeTax=0;
          double estimatedTaxToBeCollected=0;
         double orderTotal=0;
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        shoppingCart.setCustomer(customer);

        List<CartItem> cartItems = shoppingCart.getCartItems();
        Order order= new Order();

        for (CartItem cartItem : cartItems) {
            int requestedQuantity=cartItem.getQuantity();
            long productId=cartItem.getProductId();
            Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));

            if (product.getNumberInStock() < requestedQuantity) {
                throw new RuntimeException("Product out of stock: " + product.getName()+" ProductId "+ product.getProductId());
            }

        }
        for(CartItem cartItem: cartItems){
            int requestedQuantity=cartItem.getQuantity();
            long productId=cartItem.getProductId();
            Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));

            product.setNumberInStock(product.getNumberInStock() - requestedQuantity);
            for(int i=0; i<requestedQuantity ;i++){
                subTotal+= product.getPrice();
            }

            productRepository.save(product);
        }

        shoppingCart.setCartItems(new ArrayList<>());
        // Save the updated shopping cart
        cartRepository.save(shoppingCart);
        discount=(customer.getDiscountPercentage()/100)*subTotal;
        totalBeforeTax = subTotal - discount;
        estimatedTaxToBeCollected= totalBeforeTax *(SALES_TAX/100);
        orderTotal=totalBeforeTax + estimatedTaxToBeCollected;

        order.setSubTotal(subTotal);
        order.setDiscount(discount);
        order.setTotalBeforeTax(totalBeforeTax);
        order.setEstimatedTaxToBeCollected(estimatedTaxToBeCollected);
        order.setOrderTotal(orderTotal);

        orderRepository.save(order);
        customer.getOrderList().add(order);





        // Optionally, update the customer's reference to the shopping cart
        customer.setCart(shoppingCart);
        customerRepository.save(customer);
        return order;
    }


//    @Override
//    public void updateCartAndStock(long customerId, ShoppingCart shoppingCart) {
//        // Assuming the customer_id is provided
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        // Set the customer for the provided shopping cart
//        shoppingCart.setCustomer(customer);
//
//        List<CartItem> cartItems = shoppingCart.getCartItems();
//
//        for (CartItem cartItem : cartItems) {
//            int requestedQuantity=cartItem.getQuantity();
//            long productId=cartItem.getProductId();
//            Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("product not found"));
//
//            if (product.getNumberInStock() < requestedQuantity) {
//                throw new RuntimeException("Product out of stock: " + product.getName());
//            }
//
//            // Update the product stock
//            product.setNumberInStock(product.getNumberInStock() - requestedQuantity);
//
//            // Save the product
//            productRepository.save(product);
//        }
//
//        // Save the updated shopping cart
//        cartRepository.save(shoppingCart);
//
//        // Optionally, update the customer's reference to the shopping cart
//        customer.setCart(shoppingCart);
//        customerRepository.save(customer);
//    }

    @Override
    public ShoppingCart getCart(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        return customer.getCart();
    }

    @Override
    public List<Product> filterProductByCategory(String category) {

        return productRepository.findProductByCategory(category);
    }
}
