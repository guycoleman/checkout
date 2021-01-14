# Solution
The solution is split into 4 main parts
 1. Basket and BasketItem which are model classes for storing items scanned at the checkout
 2. Checkout which is responsible for storing and manipulating the basket
 3. The PromotionRule interface and its implementations which calculate the results of individual promotions
 4. PromotionRuleParser for loading rules from json
 
There are two rule types implemented
 1. Spend over X and get Y% off the total. X and Y can be configured.
 2. Buy more than X of item Y and the price changes to Z. X, Y and Z can be configured.

The rules themselves are implemented in Java; adding another type of rule would involve writing
and deploying new code.

New rules of the existing types can be configured in code or loaded from a json file using PromotionRuleParser.

# Rules Engine
I did consider using a rules engine (e.g. Drools or Easy Rules) but I decided to follow the YAGNI principle and decided
it would be over engineering for the task as described.

Rules engine pros:
 1. Powerful and flexible (e.g. promotion priority, exclusive promotions)
 2. Rules can often be added and configured at runtime.
 3. A well know, well tested rule engine may well be more reliable than a home grown one.

Rule engine cons
 1. More complex code and runtime cost
 2. New rule types should still be properly unit tested so may need new code regardless.

# Did not do
For the sake of the task I am ignoring:
 - Concurrency (e.g. multiple threads updating the basket at the same time)
 - Persistence
 - Currencies (e.g. ones with different rounding policies)
 - Payments
