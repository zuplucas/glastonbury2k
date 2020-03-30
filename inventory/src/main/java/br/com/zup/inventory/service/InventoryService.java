package br.com.zup.inventory.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.BookRequestItem;
import br.com.zup.inventory.entity.Booking;
import br.com.zup.inventory.entity.Inventory;
import br.com.zup.inventory.enumeration.WeekDay;
import br.com.zup.inventory.event.OrderUpdateEvent;
import br.com.zup.inventory.repository.BookingRepository;
import br.com.zup.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

    private BookingRepository bookingRepository;
    private InventoryRepository repository;
    private KafkaTemplate<String, OrderUpdateEvent> template;

    @Autowired
    public InventoryService(InventoryRepository repository, KafkaTemplate<String, OrderUpdateEvent> template,
            BookingRepository bookingRepository) {
        this.repository = repository;
        this.template = template;
        this.bookingRepository = bookingRepository;
    }

    public void processBooking(BookRequest request) {
        bookTicket(request);

        OrderUpdateEvent event = new OrderUpdateEvent(request.getOrderId(), "BOOKED");
        this.template.send("update-order-events", event);
    }

    @Transactional
    private void bookTicket(BookRequest request) {
        for (BookRequestItem item : request.getOrderEntries()) {
            Inventory inventory = repository.findByDay(WeekDay.valueOf(item.getName()));

            if (null != inventory && inventory.getAvailable() >= item.getQuantity()) {
                inventory.setAvailable(inventory.getAvailable() - item.getQuantity());
                repository.save(inventory);

                Booking booking = new Booking();
                booking.setOrderId(request.getOrderId());
                booking.setTicketAmount(item.getQuantity());
                booking.setDay(WeekDay.valueOf(item.getName()));
                booking.setActive(true);
                bookingRepository.save(booking);
            } else {
                throw new RuntimeException();
            }
        }

    }

    @Transactional
    public void unbook(String orderId) {
        List<Booking> bookings = bookingRepository.findByOrderIdAndActive(orderId, true);

        for(Booking booking : bookings) {
            Inventory inventory = repository.findByDay(WeekDay.valueOf(booking.getDay().name()));
            inventory.setAvailable(inventory.getAvailable() + booking.getTicketAmount());
            repository.save(inventory);

            booking.setActive(false);
            bookingRepository.save(booking);
        }
    }
}
