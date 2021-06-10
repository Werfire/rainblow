package com.werfire.rainblow.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(columnDefinition = "set")
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY, targetEntity = Item.class, orphanRemoval = true)
    private Set<Item> items = new HashSet<>();

    public Orders() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<String> getStatus() {
        return Set.of(status.split(","));
    }

    public void setStatus(String status) {
        this.status = String.join(",", status);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Item> getItems() {return items;}

    public void setItems(Set<Item> items) {this.items = items;}
}
