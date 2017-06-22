# AlertDialog Interface
Simple AlertDialog interface abstraction for easy unit testing

### Background
Sometimes, you just need to unit test the logic after user interaction (button click, adapter item pick...)
is performed.

This can be some dialog displayed during common `Command` execution. `Command` can be called from
multiple places ("class owners"), so it doesn't make sense to inject the alert dialog in the `Command`
each time it is used, because it would be generally the same every time.

If you want to **UNIT** test (not UI test, because you just want to check the business logic that happens
in different scenarios), this is a useful abstraction layer where you can inject Dialog builder and use it
the same way you are used to. But with a great simple advantage - **JUnit won't crash because of dialog initialization**
inside your Specs.
