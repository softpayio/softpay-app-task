# SoftPOS test project
This project consists of `TransactionManager` interface and its dummy implementation `TransactionManagerImpl`
that mocks making a transaction.
To start a transaction you should first get a new transaction flow through `TransactionManager.newTransactionFlow()`.
Using this flow you can get notified about the state of the `Transaction` as it goes through.
The flow would need some input in order to continue and complete. These inputs can be passed as `Input`s into the flow using `TransactionManager.dispatch()`.

To get a better understanding of how everything works checkout the documentations on `TransactionManager.kt`.

## What the task is?
1. Fork this project.
2. Inside the app module create a `TransactionActivity` with these screens:
3. Create a loading screen that should be shown whenever the state of the `Transaction` is in `PROCESSING`.
4. Create a screen for the user to enter an amount when the state is `AWAITING_AMOUNT` and then dispatch it into the flow.
5. Create a screen to show the `Store` details with the amount and a confirmation button when the state is `AWAITING_CONFIRMATION`.
6. Create a screen to show the final state of the transaction, weather it's a success or a failure, and to show the details of the transaction including its `referenceId`.
7. Make sure you give the user the ability to cancel the transaction when they want before the state is final.

## Things to keep in mind
As you can see there is not much repository or business layer code needed for this task, so our main focus
is going to be on the View layer. We will particularly evaluate these points:
- How polished the code is. It needs to be written in Kotlin.
- Having a smooth UI is really important. Use the UX points you learned to make the screens user friendly.
- The app should be able to rotate. This way we can know you have a good understanding of Android lifecycles.
- Remember that you should use the mocked implementation of `TransactionManager` for this task like so:
```
val transactionManager: TransactionManager = TransactionManagerImpl()
```

Good to haves:
- Having some animations including transitions between the screens.
- Using libraries like Compose and Hilt is a huge plus as well.
- Having an error/exception handling mechanism to prevent the app from crashing.
