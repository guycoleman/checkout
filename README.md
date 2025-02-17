supermarket.com is an online marketplace, here is a sample of
some of the products available on our site:

| Product code | Name | Price |
| ------------ | ---- | ----- |
| 001 | Travel Card Holder | £9.25 |
| 002 | Personalised cufflinks | £45.00 |
| 003 | Kids T-shirt | £19.95 |

Our marketing team want to offer promotions as an incentive for
our customers to purchase these items.

1. If you spend over £60, then you get 10% off your purchase
2. If you buy 2 or more travel card holders then the price drops to
£8.50.

Our check-out can scan items in any order, and because our
promotions will change, it needs to be flexible regarding our
promotional rules.

The interface to our checkout looks like this (shown in Java):

```java
Checkout co = new Checkout(promotionalRules);
co.scan(item);
co.scan(item);
Double price = co.total();
```

Implement a checkout system that fulfils these requirements.

Test data

Basket: 001,002,003
Total price expected: £66.78

Basket: 001,003,001
Total price expected: £36.95

Basket: 001,002,001,003
Total price expected: £73.76
