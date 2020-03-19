package br.com.zup.payment.event;

public class PaymentEvent {

    private OrderCreatedEvent order;
    private boolean paymentApproved;

    public PaymentEvent() {
    }

    public PaymentEvent(OrderCreatedEvent order, boolean paymentApproved) {
        this.order = order;
        this.paymentApproved = paymentApproved;
    }

    public PaymentEvent(OrderCreatedEvent order) {
        this.order = order;
    }

    public OrderCreatedEvent getOrder() {
        return order;
    }

    public void setOrder(OrderCreatedEvent order) {
        this.order = order;
    }

    public boolean isPaymentApproved() {
        return paymentApproved;
    }

    public void setPaymentApproved(boolean paymentApproved) {
        this.paymentApproved = paymentApproved;
    }
}
