## Synopsis

This program is used to interact with the TestExecutor, which in turn interacts with the Test Container.
It offers the test result pretty printed in JSON, and by choice persisting the result to a local CockroachDB. 
The test container used in the thesis tests can be found under https://hub.docker.com/r/rkurz/test-container/.
The source files can be found under https://gitlab.nttdata-labs.com/robin.kurz/TestContainer.
The TestExecutor can be found under https://gitlab.nttdata-labs.com/robin.kurz/TestExecutor.

## Code Example

**Prerequisite**: TestExecutor is running and TestContainer is deployed to a platform.

Start the suite:
```
java -jar target/TestSuite-1.0-SNAPSHOT.jar
```
1. (Optional) Adjust the IP where the TestExecutor is reachable.
2. Insert IP where the testContainer is reachable.
**Note**: http:// needs to be the prefix of the serviceIp parameter.
3. (Optional) Check persistence
	1. Choose tested platform name
	2. Choose tested platform version
4. Choose test to run
5. Press Go

The test will run.
When it is finished it will output the data to the result window, and if persistence was chosen will write it to the database.

## Persistence
If persistence is chosen, a CockroachDB must run on the same machine the TestSuite is running from.
The database must listen on port 26257.
The database must be called test2.
The user root must have the privileges.

Of course this is all changeable from the source code.



