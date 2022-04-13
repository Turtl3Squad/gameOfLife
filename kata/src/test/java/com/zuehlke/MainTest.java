package com.zuehlke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

  @Test
  void test(){
    var main = new Main();

    main.blub();

    assertTrue(true);
  }
}