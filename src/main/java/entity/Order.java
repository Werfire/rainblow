package entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
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

    //@Column(name = "client_id")
    //private UUID clientId;

    public Order() { }

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
}
