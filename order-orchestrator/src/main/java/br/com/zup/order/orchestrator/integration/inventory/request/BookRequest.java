package br.com.zup.order.orchestrator.integration.inventory.request;

import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest implements Serializable {
  private static final long serialVersionUID = -6964824150585870049L;
  private Map<String, Integer> orderEntries;
}
