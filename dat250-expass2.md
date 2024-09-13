DAT250 Experiment Assignment 2: Poll App REST API


Technical Problems Encountered
1. Understanding the Domain Model
   Problem: The domain model required implementing associations between Poll, User, Vote, and VoteOption classes. I initially struggled to manage these relationships, particularly when handling recursive references and cyclic dependencies in JSON serialization.
   Resolution: After reviewing the assignment requirements and researching Spring's annotations like @JsonManagedReference and @JsonBackReference, I was able to break the cyclic dependencies between the entities. This allowed me to serialize and deserialize JSON without encountering infinite recursion.
2. Automated Testing Setup
   Problem: Writing automated tests using JUnit and MockMvc was challenging, particularly for simulating HTTP requests and validating API responses.
   Resolution: I iplemented automated tests that simulated creating users, polls, casting votes, and deleting polls. Using MockMvc helped me mock HTTP requests and verify the behavior of the API in a controlled environment. The test scenarios outlined in the assignment were successfully automated.

   Password Field: The domain model didn’t explicitly require the password field for User. I initially included it but removed it when I realized it wasn’t necessary for this assignment. If password management becomes necessary in future iterations, this will need to be reconsidered.

Conclusion
Despite facing multiple technical challenges, the development of the Poll App REST API was a success. I was able to implement all required functionality and automate the test scenarios as described in the assignment. The project provided a valuable learning experience, especially in areas such as REST API development, automated testing, and handling cyclic dependencies in object serialization.