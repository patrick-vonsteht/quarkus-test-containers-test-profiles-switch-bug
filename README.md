# Quarkus: Postgres TestContainer fails when switching test profiles

This is a minimal example to illustrate the following bug:

Setup:
* You have two QuarkusTests
* **The QuarkusTests use different TestProfiles.**
* Both QuarkusTests start a postgres container through a QuarkusTestResourceLifecycleManager.

Result:
* The first test runs successfully.
* The second test fails with "java.sql.SQLException: No suitable driver found for jdbc:postgresql://localhost:55723/vintageStoreDB?loggerLevel=OFF"

Expected Result:
* Both tests run successfully.


Working Scenario:
* If you edit the second test to use the same TestProfile as the first one, everything works.


