## GFT Inditex Exercise

### Overview

I tried to follow the DDD approach + Hexagonal architecture, separating domain, application and infrastructure layers.

With DDD, we have in the domain layer our business models. In the application layer we have the use cases of the business (what we do with our models).
And last, in the infrastructure layer, we have the frameworks, libraries or tools we decided to use (how we do).
This allows us to change our ORM for a new one without changing a single line of code from other layers.
The same for the application layer: if we decide to change our business use case (the criteria we use for recovering a tariff, for example), we only need to change the application layer, because our model (tariff) remains unchanged.

Perhaps it may seem a bit over-engineered solution for this example, but I wanted to provide the solution I would choose in a real-world exercise, and do it the best way possible (you only get one first impression ;))

I have tried to be as strict as possible about not including third-party libraries in the model and application layers (the only exception has been lombok).

### Some clarifications

#### Validations in Controller

I'm passing the input data directly to the query handler, in raw. I'm aware that SpringBoot offers many validation tools for this, but I prefer to do the validations in the handlers.
Doing this way, we can manage the validations and the errors as we want (without any framework limitation), and if we decide to change the current web framework or our use case initiator (async message instead of api request, for example), we don't need to update our validations.

### Final considerations

I tried to do the challenge applying the best solution or pattern to each situation, despite I know it was just a test example.
I used DDD and SOLID principles, and I tried to write a clean, easy to understand, and maintainable code.
I've really enjoyed doing this challenge. I think starting a project from scratch is something all developers enjoy. At least, that is my case.
I would like to discuss my solution, and I really would appreciate any feedback you can give me.

### Running the app

Command line:
```
mvn spring-boot:run
```

Docker
```
mvn clean package && docker compose up --build
```

Or just use IntelliJ ;)

### Code quality tools

Run tests:
```
mvn test
```
This will generate a coverage report at: `/target/site/jacoco/index.html`

Run checkstyle:
```
mvn checkstyle:check
```

Run spotbugs:
```
mvn compile spotbugs:check
```

Run PMD:
```
mvn compile pmd:check
```

### Extras

A Postman Collection is provided at: `/docs/postman/`

OpenAPI documentation is available at 'http://localhost:8080/swagger-ui/index.html' when the app in running