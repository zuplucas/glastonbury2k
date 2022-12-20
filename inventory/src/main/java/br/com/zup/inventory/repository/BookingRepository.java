package br.com.zup.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.inventory.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByOrderIdAndActive(String orderId, boolean active);

}
