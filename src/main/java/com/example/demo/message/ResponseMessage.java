package com.example.demo.message;

public class ResponseMessage {
  private String data;

  public ResponseMessage(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}