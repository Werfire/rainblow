package com.werfire.rainblow.controllers;

import com.werfire.rainblow.models.*;
import com.werfire.rainblow.util.DatabaseUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes({"user", "cart", "isAdmin"})
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @ModelAttribute("user")
    public User initUser() {
        return null;
    }

    @ModelAttribute("cart")
    public Orders initShoppingCart() {
        return null;
    }

    @ModelAttribute("isAdmin")
    public boolean initIsAdmin() {
        return false;
    }

    @RequestMapping(value = {"", "index", "equipment_store"}, method = RequestMethod.GET)
    public String index(Model model, @ModelAttribute("cart") Orders cart) {
        logger.debug("Welcome to RainBlow!");
        List<Equipment> equipmentList = DatabaseUtil.getEquipments();
        //model.addAttribute("cartItems", cart == null ? 0 : cart.getItems().size());
        model.addAttribute("equipments", equipmentList);
        return "equipment_store";
    }

    @RequestMapping("login")
    public String login(Model model) {
        model.addAttribute("user", null);
        model.addAttribute("cart", null);
        model.addAttribute("isAdmin", false);
        return "login";
    }

    @RequestMapping(value="tryLogin", method = RequestMethod.POST)
    public String tryLogin(Model model, @RequestParam String login, @RequestParam String password) {
        User user = DatabaseUtil.getUser(login, DigestUtils.sha1Hex(password));
        if(user == null)
            return "redirect:login";
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", DatabaseUtil.getClient(user.getId()) == null);
        Orders cart = DatabaseUtil.getCart(user.getId());
        if(cart != null)
            model.addAttribute("cart", cart);
        return "redirect:equipment_store";
    }

    @RequestMapping({"register"})
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "addToCart", method = RequestMethod.POST)
    public String addToCart(Model model, @RequestParam int quantity, @RequestParam UUID equipmentId,
                            @ModelAttribute("user") User user, @ModelAttribute("cart") Orders cart,
                            @ModelAttribute("isAdmin") boolean isAdmin) {
        if(user == null || isAdmin)
            return "redirect:login";

        if(cart == null) {
            Client client = DatabaseUtil.getClient(user.getId());
            Orders order = new Orders();
            order.setId(UUID.randomUUID());
            if(client.getAddress() != null)
                order.setDeliveryAddress(client.getAddress());
            order.setStatus("shopping_cart");
            order.setClient(client);
            DatabaseUtil.addOrder(order);
        }

        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setQuantity(quantity);
        item.setOrder_(cart);
        item.setEquipmentId(equipmentId);
        DatabaseUtil.addItem(item, user.getId());
        DatabaseUtil.subtractEquipmentQuantity(equipmentId, quantity);
        return "redirect:equipment_store";
    }

    @RequestMapping("shopping_cart")
    public String shoppingCart(Model model) {
        return "shopping_cart";
    }

    @RequestMapping("site_reservation")
    public String siteReservation(Model model) {
        return "site_reservation";
    }

    @RequestMapping("errors")
    public String errorPage(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("errCode", getErrorCode(httpRequest));
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
