package controller.command;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import model.MockModel;
import model.ModelImpl;
import model.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GainOrLossTest {

  MockModel testModel;
  ModelImpl realModel;
  StringBuilder sb;
  StringBuilder expected;
  GainOrLoss test;
  StringBuilder log;
  @Before
  public void setup() {
    log = new StringBuilder();
    testModel = new MockModel(log);
    realModel = new ModelImpl();
    sb = new StringBuilder();
    expected = new StringBuilder();
  }

  //tests isValidTicke
  @Test
  public void testIncorrectTicker() {
    try {
      expected.append("Which stock do you want to analyze? " + System.lineSeparator());
      sb.append("invalidTicker" + System.lineSeparator());
      Scanner sc = new Scanner(String.valueOf(sb));
      test.run(sc, realModel);
      fail("An exception should have been thrown");
    }
    catch (IllegalArgumentException e){
      assertEquals("Make sure to spell the ticker correctly and populate first.", e.getMessage());
    }
  }

  @Test
  public void testGainOrLoss(){
    test = new GainOrLoss(expected);
    sb.append("GOOG"+ System.lineSeparator());
    sb.append("2024-11-04"+ System.lineSeparator());
    sb.append("2024-12-04"+ System.lineSeparator());
    Scanner sc = new Scanner(String.valueOf(sb));
    test.run(sc, testModel);
    expected.append("start: 2024-11-04 end: 2024-12-04 ticker: GOOG");
    assertEquals(expected.toString(), log.toString());
  }
}
