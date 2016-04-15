# Development Guidelines

The project uses a TDD approach.

1. In the pre-alpha stage at least 1 test should fail until the project is usable.

There is no point in people wasting their time trying to use a project that is not ready/
It is also hard for contributors to know where to start.
So we will take an approach of leaving tests failing to mark core functionality that needs to be implemented.

Before the project is stable, it is perfectly acceptable and even desirable to check in failing tests.
These tell any visitors to the project the current state of the code and what needs to be done next.

Developers are encouraged to check in often and leave a failing test for the next piece of functionality they need to implement.

After the project is stable, short lived feature branches can also contain failed tests to show which edge cases are left to complete.
This way new comers can contribute easily by implementing features to fix tests.

Obviously failed tests will not be merged into master.

