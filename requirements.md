# story 1
[] Case1  
Given a parking lot, and a car  
When park
Then return a parking ticket. 

[] Case2
Given a parking ticket 
When fetch
Then return a car.

[] Case3
Given a wrong parking ticket or no ticket
When fetch
Then don't return car

[] Case4
Given ticket that is used
When fetch
Then don't return car

[] Case5
Given parking lot capacity of 10, and a car
When park
Then do not return parking ticket

# story 2