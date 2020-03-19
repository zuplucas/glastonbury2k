package br.com.zup.payment.event;

import java.util.Date;
import java.util.UUID;

public class OrderCreatedStatusEvent {

    private String id;
    private Date date;
    private String status;

    public OrderCreatedStatusEvent() {
    }

    public OrderCreatedStatusEvent(String id, Date date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public OrderCreatedStatusEvent(String pending) {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
