Before I jump into classes, I want to clarify the scope.

First, I’ll focus on the core flow of the system. Could you confirm the main operations we need to support?

I also want to clarify the main actors, the important entities we need to track, and any state transitions or failure cases.

For the first version, I’ll keep the design simple and avoid database, UI, authentication, and concurrency unless you want me to include them.

After that, I’ll identify the main classes, their responsibilities, relationships, and then discuss extensibility points like adding new types, new rules, or new strategies.

##### Use these in almost every problem:
1. What are the core features we need for version one?

2. Who are the main actors/users?

3. What is the main happy-path flow?

4. What data should the system track?

5. Are there any important states?

6. What failure cases should I handle?

7. What should be extensible in the future?

8. Should I consider concurrency?

9. Should I include persistence/database or keep it in memory?

10. What should I explicitly keep out of scope?


##### Mental Model 
Scope
Actors
Flow
Entities
States
Failures
Extensibility
Out of scope

For interviews, don’t ask 20 questions randomly. Ask 5–7 strong questions, make assumptions, and move forward.
