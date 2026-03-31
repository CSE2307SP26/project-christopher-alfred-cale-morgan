# project26

#Iteration 2 User Stories/Tasks

## 1. A user should be able to log in to their account
Login w/ a pin
Subtask - An app context should be able to manage several users
## 2.) A user should be able to freeze their account
Add a Boolean to the bank account, isFrozen, and check that Boolean before withdrawal/deposit
Add a menu option for account freeze
## 3.) A user should be able to log in as different account types
Have admin and customer accounts. Prevent customer accounts from gaining admin privileges
## 4.) An administrator should be able to view all account histories
## 5.) An administrator should be able to view all account balances
## 6.) A customer should be able to manage several account types (e.g., checking & savings)
Create subclasses of the bankAccount class. Let the subclasses inherit much of the functionality of the bankAccount class
The easiest difference would be changing the default interest rate
Important design choice: relying on inheritance and abstraction, or creating an interface (like IMenuOption) that guarantees each account implements certain methods
## 7.) A customer should be able to request a withdrawal limit on their account(s)
## 8.) An administrator should be able to review limit requests
