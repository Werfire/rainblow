package com.werfire.rainblow.controllers;

import com.werfire.rainblow.models.Equipment;
import com.werfire.rainblow.models.Item;
import com.werfire.rainblow.models.Orders;
import com.werfire.rainblow.models.User;
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

    @RequestMapping(value = {"/", "/index", "equipment_store"}, method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("Welcome to RainBlow!");
        List<Equipment> equipmentList = DatabaseUtil.getEquipments();
        model.addAttribute("equipments", equipmentList);
        return "equipment_store";
    }

    @RequestMapping({"/login"})
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST, params="tryLogin")
    public String tryLogin(Model model, @RequestParam String login, @RequestParam String password) {
        User user = DatabaseUtil.getUser(login, DigestUtils.sha1Hex(password));
        if(user == null)
            return "login";
        model.addAttribute("user", user);
        DatabaseUtil.getClients();
        Orders cart = DatabaseUtil.getCart(user.getId());
        if(cart != null)
            model.addAttribute("cart", cart);
        return "redirect:equipment_store";
    }

    @RequestMapping({"/register"})
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = {"equipment_store"}, method = RequestMethod.POST, params="addToCart")
    public String addToCart(Model model, @RequestParam int quantity, @RequestParam Equipment equipment,
                            @ModelAttribute("user") User user, @ModelAttribute("cart") Orders cart) {
        if(user == null || DatabaseUtil.getClient(user.getId()) == null)
            return "login";
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setQuantity(quantity);
        item.setOrder(cart);
        item.setEquipmentId(equipment.getId());
        DatabaseUtil.addItem(item);
        return "equipment_store";
    }

    @RequestMapping({"/shopping_cart"})
    public String shoppingCart(Model model) {
        return "shopping_cart";
    }

    @RequestMapping({"/site_reservation"})
    public String siteReservation(Model model) {
        return "site_reservation";
    }

    @RequestMapping({"/errors"})
    public String errorPage(HttpServletRequest httpRequest, Model model) {
        model.addAttribute("errCode", getErrorCode(httpRequest));
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
