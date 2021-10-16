## ICS0024 2021 Project 1

This is a repository of team 5 (Zadrots) of TalTech Course ICS0024 (Automated testing)

### Project description

We decided to use the following API: [Alphavantage](`https://www.alphavantage.co/documentation/#crypto-exchange`)

We are going to display weekly, monthly, and annual MIN, MAX and DIFFERENCE values.

[Example API request for monthly rates.](https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_MONTHLY&symbol=BTC&market=CNY&apikey=demo)

Plan for making API:

1. Fakhri -> weekly
1. Alikhan -> monthly
1. Almaz -> annual

For tests, we will change the responsibilities and test each other's endpoints.

## Example Requests

### All the possible GET requests examples:

1. http://localhost:8080/api/weekly
1. http://localhost:8080/api/monthly
1. http://localhost:8080/api/annual

### Sample response for annual:

```json
[
  {
    "year": 2019,
    "low": 3373.1,
    "high": 13970,
    "absoluteDifference": 10596.9
  },
  {
    "year": 2020,
    "low": 3782.13,
    "high": 29300,
    "absoluteDifference": 25517.87
  },
  {
    "year": 2021,
    "low": 28130,
    "high": 64854,
    "absoluteDifference": 36724
  }
}
```

### Setup

Start Application.java with your favorite IDE

### Swagger

http://localhost:8080/api/swagger-ui/
http://localhost:8080/api/v2/api-docs

