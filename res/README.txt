Stocks (Part 1) 
This program simulates stock purchases and portfolio, allowing users to learn how to invest in stocks without staking real money. 
Stock data is real data from AlphaVantage. This data is collected through the API on program start, allowing the user to choose which stocks they wish to load by entering ticker symbols.



The following features have been implemented in the Stocks program:
- The ability to create a portfolio of multiple stocks, and tracks the shares owned by the user.
- The ability to add stocks to an existing portfolio. 
- The ability to get the value of a specific stock.
- To see the gain/loss of a particular stock between a specified span of time. The user is asked for a ticker symbol to select a stock to analyze, then two dates. The gain/loss is based on closing prices of stocks on the day of.
- To calculate the moving average of a stock on a particular date. Users can choose how many days (x) to base their moving average on, such that they can obtain the x-day moving average. Users are asked for a ticker symbol, then a number of days for (x), and finally the date the moving average should be calculated for.
- To see how many days in a specified time period are crossover days. Crossover days occur when the closing price on a particular day is higher than the x-day moving average of that day. The user is asked for a ticker symbol, then asked for the number of days for (x), and then the starting and ending dates of the specified time period the program should analyze.

Currently, due to the program's reliance on a free API (AlphaVantage), the program is limited to 25 requests per day. Therefore in the case a user wishes to request more than 25 times, a message will notify the user that they can use archived stocks from the start of the program.

Stocks (Part 2)
Added functionalities:
- Added ability to save and load portfolios.
- Added ability to buy/sell shares of an existing stock in a portfolio.
- IModel2 allows for rebalance and getting the graph of a portfolio or stock's performance.
- Created IPerformance and APerformance. There is a stock and portfolio class that extends APerformance and implements IPerformance.
  These classes are meant to generate a graph that shows the stock/portfolio's performance over time.
- Created IWeight to couple together the percentage and stock for a better user experience for rebalance.
- Created ITransaction to track transactions. This helps with buying/selling stocks.
  Users cannot buy/sell stocks in the "past" if they did so in the "future".
- Created command for getting the information of a portfolio.
- Created IParseXml interface for parsing xml files. The XMLToPortfolio class implements it and is dedicated to parsing
  xml files to create portfolios.

  Stocks (Part 3)
  Structure:
  - Added IView, IViewListener, and IGuiController for developing the new view

  Added functionalities:
  - Created a GUI on top of the text-based view

