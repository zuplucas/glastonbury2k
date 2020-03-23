package br.com.zup.inventory.exception;

import lombok.Getter;

public class BussinesException extends Exception {

  public BussinesException(String code, String message) {
    super(message);
    this.code = code;
  }

  @Getter private String code = "9999";
}
