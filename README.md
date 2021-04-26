# Assumptions:

I create components per each feature(Publishing, Accounting, Image, NewsArticle) and they have their own structure.
And I assume they are services that can be deployed autonomously.

## Architecture
I used the hexagonal and onion architectures, so you can find Application(inbound, outbound), Domain, Infrastructure(Broker, Rest API, and Repository).
When the state is changed within a domain, it raises an event that is listened to by outbound in the Application layer, then it is published by the broker in Infrastructure.

Good luck


