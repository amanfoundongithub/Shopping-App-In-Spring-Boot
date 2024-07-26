package com.shopping.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.app.controller.CartController;
import com.shopping.app.controller.CustomerController;
import com.shopping.app.controller.ItemController;
import com.shopping.app.controller.OrderController;
import com.shopping.app.controller.RetailorController;
import com.shopping.app.exception.ApplicationException;
import com.shopping.app.exception.CreateException;
import com.shopping.app.model.Cart;
import com.shopping.app.model.Customer;
import com.shopping.app.model.Item;
import com.shopping.app.model.Order;
import com.shopping.app.model.Retailor;
import com.shopping.app.utils.ItemType;
import com.shopping.app.utils.OrderStatus;
import com.shopping.app.utils.model.CartItem;
import com.shopping.app.utils.model.Location;
import com.shopping.app.utils.request.LoginRequest;
import com.shopping.app.utils.request.RetailorLoginRequest;

import java.util.List;

@Controller
public class Application {

    @Autowired
    private RetailorController retailorController;

    @Autowired
    private ItemController itemController;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private CartController cartController;

    private String customerID = "";

    private String retailorID = "";

    private Customer customer;

    @PostMapping("/retailor/create")
    public String getRetailorCreate(@RequestBody Retailor retailor, Model model) throws ApplicationException {
        try {
            if (retailorController.createRetailor(retailor).length() > 0) {
                return "redirect:/retailor/login";
            } else {
                return "redirect:/retailor/login";
            }
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @PostMapping("/retailor/login")
    public String getRetailorLogIn(@RequestBody RetailorLoginRequest retailorLoginRequest) throws ApplicationException {
        try {

            String id = retailorController.verifyRetailor(retailorLoginRequest);

            if (id.length() > 0) {
                retailorID = id;
           
                return "redirect:/retailor/home";
            } else {
                return "redirect:/retailor/login";
            }
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @PostMapping("/retailor/logout")
    public String retsilrlOGOUT(Model model) throws ApplicationException {
        try {
            retailorID = "";
            return "redirect:/retailor/login";
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @PostMapping("/item/create")
    public ResponseEntity<String> create(@RequestBody Item item) throws CreateException {
        try {
            String name = itemController.create(item);

            if (name.length() > 0) {
                return ResponseEntity.ok(name);
            } else {
                return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
            }
        } catch (Exception e) {
            throw new CreateException(e);
        }
    }

    /****************************************************************************************
     * PAGES OF CUSTOMER
     *
     */

    @PostMapping("/customer/login")
    public String getCustomerlogin(@RequestBody LoginRequest loginRequest, Model model) throws ApplicationException {
        try {
            
            if(customerController.logIn(loginRequest.email, loginRequest.password)){
                customerID = customerController.getCustomerByEmail(loginRequest.email).getId();
                customer = customerController.getCustomerByEmail(loginRequest.email);
                return "redirect:/home";
            }
            else{
                return "redirect:/customer/login";
            }
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @PostMapping("/customer/create")
    public String createAccount(@RequestBody Customer customer, Model model) throws ApplicationException {
        try {
            
            customerController.createAccount(customer);

            return "redirect:/customer/login";
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @PostMapping("/customer/logout")
    public String CusomterlOGOUT(Model model) throws ApplicationException {
        try {
            customerID = "";
            return "redirect:/customer/login";
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @PostMapping("/customer/update")
    public ResponseEntity<String> customerUpdate(@RequestBody Customer customer) throws CreateException {
        try {
            customerController.updateAccount(customer);
            this.customer = customerController.getCustomerByID(customerID);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    @PostMapping("/customer/update/address")
    public ResponseEntity<String> customerAddressUpdate(@RequestBody Location location) throws CreateException {
        try {
            if(customerID.length() == 0){
                return ResponseEntity.badRequest().build();
            }
            customerController.updateAddress(customerID, location);
            this.customer = customerController.getCustomerByID(customerID);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    @GetMapping("/customer/login")
    public String getCUSTOMERLoginPage(Model model) throws ApplicationException {
        try {
            return "customer_login";
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/customer/signup")
    public String getcustomerSignUpPage(Model model) throws ApplicationException {
        try {
            return "customer_signup";
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/customer/profile/home")
    public String getHomeCustomer(Model model) throws ApplicationException {
        try {
            if(customerID.equals("")){
                return "customer_login";
            }
            model.addAttribute("customer", customer);
            model.addAttribute("age", customer.getAge());
            model.addAttribute("membersince", customer.getDateOfJoining().toString());
            model.addAttribute("reversedoj", customer.getDateOfBirth().toReverseString());
            model.addAttribute("loginID", customerID);
            return "customer_home";
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/customer/profile/cart")
    public String getCart(Model model) throws ApplicationException {
        try {
            if(customerID.equals("")){
                return "customer_login";
            }
            else{
                model.addAttribute("customer", customer);
                model.addAttribute("loginID", customerID);
                model.addAttribute("cart", cartController.getCart(customerID).getCart().reversed());
                return "customer_cart";
            }
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/customer/profile/order")
    public String getOrder(Model model) throws ApplicationException {
        try {
            if(customerID.equals("")){
                return "customer_login";
            }
            else{
                model.addAttribute("customer", customer);
                model.addAttribute("loginID", customerID);
                model.addAttribute("orders", orderController.getAllOrdersByCustomerID(customerID).reversed());
                return "customer_orders";
            }
        } catch(Exception e) {
            throw new ApplicationException(e);
        }
    }

    /*
     * *****************************************************************************
     * PAGES RENDERING FOR RETAILOR
     * 
     */

    @GetMapping("/retailor/signup")
    public String getSignUpPage(Model model) throws ApplicationException {
        try {
            return "retailor_signup";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/retailor/login")
    public String getLoginPage(Model model) throws ApplicationException {
        try {
            return "retailor_signin";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/retailor/home")
    public String getHomePage(Model model) throws ApplicationException {
        try {
            if (retailorID.length() == 0) {
                return "retailor_signin";
            }
            Retailor retailor = retailorController.getRetailor(retailorID);
            List<Item> listOfItems = itemController.getAllItemsBySellerId(retailorID);
            List<Order> listofOrders = orderController.getAllOrdersBySellerID(retailorID);

            if (retailor == null) {
                return "retailor_signin";
            } else {
                model.addAttribute("statuses", OrderStatus.values());
                model.addAttribute("orders", listofOrders);
                model.addAttribute("retailor", retailor);
                model.addAttribute("items", listOfItems);
                model.addAttribute("types", ItemType.values());
                return "retailor_home";
            }

        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /*******************************************************************************************************
     * ITEMS PAGE AND GENERAL PAGES
     */

    @GetMapping("/home/search")
    public String getSearchPage(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "type", defaultValue = "") String type,
            Model model) throws ApplicationException {
        try {
            List<Item> list;

            if (type.length() == 0) {
                list = itemController.searchItems(query);
                model.addAttribute("selectedType", type);
            } else {
                ItemType itemType = ItemType.valueOf(type);
                list = itemController.searchItems(query, itemType);
                model.addAttribute("selectedType", itemType);
            }
            model.addAttribute("loginID", customerID);
            if(customerID.length() > 0){
                model.addAttribute("customer", customer);
            }
            
            model.addAttribute("query", query);
            model.addAttribute("types", ItemType.values());
            model.addAttribute("items", list);
            return "home";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/home")
    public String getIndexPage(Model model) throws ApplicationException {
        try {
            model.addAttribute("loginID", customerID);
            if(customerID.length() > 0){
                model.addAttribute("customer", customer);
            }
            model.addAttribute("query", "");
            model.addAttribute("selectedType", "");
            model.addAttribute("types", ItemType.values());
            model.addAttribute("items", itemController.getAllObjects());
            return "home";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/view/item/{id}")
    public String getItemPage(
            @PathVariable("id") String id,
            Model model) throws ApplicationException {
        try {
            model.addAttribute("loginID", customerID);
            Item item = itemController.searchByID(id);
            model.addAttribute("item", item);
            if(customerID.length() > 0){
                model.addAttribute("customer", customer);
            }
            return "item_view";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    @GetMapping("/order/confirm/{id}")
    public String placeOrder(
        @PathVariable("id") String id,
        Model model) throws ApplicationException {
            try {
                Cart cart = cartController.getCart(customerID);

                CartItem cartItem = cart.findInCart(id).get();

                Item item = itemController.searchByID(cartItem.itemCode);

                item.setQuantity(cartItem.quantity);

                model.addAttribute("item", item);
                model.addAttribute("loginID", customerID);
                model.addAttribute("customer", customer);

                return "confirm_order";

            } catch(Exception e) {
                throw new ApplicationException(e);
            }
        }


    /**
     * CART
     */
    @PostMapping("/cart/additem")
    public ResponseEntity<String> addToCart(@RequestBody CartItem item) throws CreateException {
        try {
            boolean added =  cartController.addToCart(customerID, item);
            if (added) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
            }
        } catch (Exception e) {
            throw new CreateException(e);
        }
    }

    @PostMapping("/cart/updateitem")
    public ResponseEntity<String> updateToCart(@RequestBody CartItem item) throws CreateException {
        try {
            boolean added = cartController.updateToCart(customerID, item);
            if (added) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
            }
        } catch (Exception e) {
            throw new CreateException(e);
        }
    }

    @PostMapping("/cart/deleteitem")
    public ResponseEntity<String> deleteToCart(@RequestBody CartItem item) throws CreateException {
        try {
            boolean added = cartController.deleteToCart(customerID, item);
            if (added) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
            }
        } catch (Exception e) {
            throw new CreateException(e);
        }
    }

    /**
     * ORDER RELATED STUFF
     */
    @PostMapping("/order/addnew")
    public ResponseEntity<String> addOrder(@RequestBody Order order) throws CreateException {
        try {
            String orderId = orderController.create(order);

            String itemId = order.getItem().getId();
            Item item = itemController.searchByID(itemId);
            int quantity = order.getItem().getQuantity();
            int original = item.getQuantity();

            int diff = original - quantity;
            if(diff < 0){
                diff = 0;
            }
            item.setQuantity(diff); 
            itemController.update(item);

            cartController.deleteToCart(customerID, itemId);
            return ResponseEntity.ok(orderId);
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }

    @PostMapping("/order/status/update")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) throws CreateException {
        try {
            
            Order target = orderController.searchByID(order.getId());
            target.setOrderStatus(order.getOrderStatus());
            orderController.update(target);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            throw new CreateException(e);
        }
    }
    
    

}
