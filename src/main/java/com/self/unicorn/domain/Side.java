package com.self.unicorn.domain;

public enum Side {
  LEFT("left"), RIGHT("right"), UP("up"), DOWN("down");

  private final String side;

  Side(String side) {
    this.side = side;
  }
}
