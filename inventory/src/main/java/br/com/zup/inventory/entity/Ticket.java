package br.com.zup.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "order_items")
public class Ticket {

  @Id private String id;

  private Integer quantity;

  private Boolean active = true;
}
