package view;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import controller.MockController;
import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

public class GUITest {
  GUI gui;
  StringBuilder received;
  IViewListener controller;
  IModel2 model;

  @Before
  public void setUp() {
    gui = new GUI();
    received = new StringBuilder();
    model = new ModelImpl2();

    controller = new MockController(gui, model, received);
    gui.addViewListener(controller);
  }

  @Test
  public void testFireCreatePortfolio() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleCreatePortfolio("AMZN", 1, "A", date, true);
    assertEquals("handleCreatePortfolio", received.toString());
  }

  @Test
  public void testFireSavePortfolio() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleSavePortfolio("A");
    assertEquals("handleSavePortfolio", received.toString());
  }

  @Test
  public void testFireLoadPortfolio() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleLoadPortfolio("A");
    assertEquals("handleLoadPortfolio", received.toString());
  }

  @Test
  public void testFireQueryPortfolio() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleGetPortfolioValue("A", "A", date);
    assertEquals("handleGetPortfolioValue", received.toString());
  }

  @Test
  public void testFireAddStockEvent() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleBuyStock("AMZN", "A", 1, date);
    assertEquals("handleBuyStock", received.toString());
  }

  @Test
  public void testFireRemoveStockEvent() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleSellStock("AMZN", "A", 1, date);
    assertEquals("handleSellStock", received.toString());
  }

  @Test
  public void testFirePopulateEvent() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandlePopulateStock("AMZN");
    assertEquals("handlePopulateStock", received.toString());
  }

  @Test
  public void testFireCSVStockEvent() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleCSVStock("AMZN");
    assertEquals("handleCSVStock", received.toString());
  }

  @Test
  public void testFireBuyStockEvent() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleBuyStock("AMZN", "A", 1, date);
    assertEquals("handleBuyStock", received.toString());
  }

  @Test
  public void testFireSellStockEvent() {
    LocalDate date = LocalDate.of(2023, 1, 1);
    gui.fireHandleSellStock("A", "AMZN", 0, date);
    assertEquals("handleSellStock", received.toString());
  }
}