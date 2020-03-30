package br.com.zup.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.zup.inventory.enumeration.WeekDay;

@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "ticket_amount")
    private Integer ticketAmount;
    @Column(name = "active")
    private boolean active;
    @Enumerated(EnumType.STRING)
    private WeekDay day;

    public Booking() {
    }

    public Booking(Long id, String orderId, Integer ticketAmount, boolean active,
            WeekDay day) {
        this.id = id;
        this.orderId = orderId;
        this.ticketAmount = ticketAmount;
        this.active = active;
        this.day = day;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public WeekDay getDay() {
        return day;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

}
