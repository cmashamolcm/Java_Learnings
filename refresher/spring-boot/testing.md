### Testing
In the order of high to low effort, fragility, confidence in test pyramid:
1. Unit test - test most granular level. Single function test in isolation. Not tests non-functional, performance aspects.
2. Contract test - ensures that two systems communicates as expected. Based on API contracts etc. Non-functional. Using tools like `PACT`.
                 - Done in build time itself.
                 - white-box
3. Integration test - functional testing. How two systems connected and works. Eg: testing database integration with our application.
                    - It can be with multiple systems, classes, function, modules etc.
                    - Done at build time. Blackbox type
4. End to end test - done after deploying to test environment. Full flows are tested.
                    - Uses tools like TestComplete
5. UI test - Test from application UI for the entire flow from frontend to backend.
6. https://pactflow.io/blog/contract-testing-vs-integration-testing/