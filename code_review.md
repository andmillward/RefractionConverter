EddiesHotDogOrderProcessor.java

 - Should replace token matchers (like "JUMBO") with constants that have meaningful names. (e.x.: PRETZEL_BREAD_TOKEN)
 - String equals check should be inverted. This is null safe. e.x.: "P".equals(eddiesTokens[1])
 - Bread type could end up null if a new unhandled type is added. Do a null check and throw a friendly exception to make things easier down the road.
 - I spy commented out code! Line 52
 - Log exceptions


NutritionCalculator.java

 - Condiments should have an individual calorie count. Otherwise the if statement checking exceptions to the rule is going to get out of hand in the future