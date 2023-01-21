# implementation choices and thoughts

0: in order to run the integration test:
  - start rabbit: docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
  - start postgres: docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres --p 5432:5432
    (or define your own local db of course)

1: I do like to use stuff like Lombok, but since I don't want to force the one watching my code to have the processor
for Lombok enabled, I've chosen for handwritten constructors and getters.

2: I've never heard of Spring Integration DSL, so due to lack of time, I won't use it. I will definitely look into it, however.

3: I prefer to use immutable objects to deserialize into, but I couldn't get that to work with the XmlMapper.
I usually use Java 17 records for this. Setters are something I hate to write...

4: due to lack of time I did not create unit tests purely for the sake of code coverage. Only "business-critcal" is
covered by tests

