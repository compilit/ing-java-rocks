# implementation choices

1: I do like to use stuff like Lombok, but since I don't want to force the one watching my code to have the processor
for Lombok enabled, I've chosen for hand-written constructors and getters.

2: I've never heard of Spring Integration DSL, so due tpo lack of time, I won't use it.

3: I've used a local docker instance of rabbit mq as my source and local postgresql as my target

4: I prefer to use immutable objects to deserialize into, but I couldn't get that to work with the XmlMapper. 
I usually use Java 17 records for this. Setters are something I hate to write...
