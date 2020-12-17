# Title: Programming Project #3
# Author: Jared Duncan ([redacted])
# Date: 4/12/2020
# Description: This program is designed to help train the user with basic math
# and to satisfy the programming project #3 assignment for CS115.

# Even though I won't be changing this program, I liked doing it this way
# because it allows the programmer to add more choices without modifying
# the main program loop, plus it was an excuse to have lambda/anonymous
# functions which I haven't had a reason to use much in Python.

# Define our choices that get displayed and setup a lambda function for each.
choices = [
    [
        "Addition",
        lambda firstNumber, secondNumber: firstNumber + secondNumber
    ],
    [
        "Subtraction",
        lambda firstNumber, secondNumber: firstNumber - secondNumber
    ],
    [
        "Multiplication",
        lambda firstNumber, secondNumber: firstNumber * secondNumber
    ],
    [
        "Division",
        lambda firstNumber, secondNumber: firstNumber / secondNumber
    ]
]

# This is our interactive portion of the code that loops.
while(True):
    # I did the following lines the way I did because in order to
    # alter the choices, we can just edit the choices array above.
    
    # Show all of our choices using formatted strings.
    for index in range(len(choices)):
        print(f"{index + 1} -> {choices[index][0]}")
        
    # Show the quit choice which doesn't exist in the choices array.
    print(f"{len(choices) + 1} -> Quit")

    # Get the users choice selection and change it to match
    # the index number of the choice in choices array.
    choice = int(input("Enter a selection number: ")) - 1

    # If choice is equal to the last index number of
    # choices array + 1, the user wants to quit.
    if choice == len(choices):
        print("Thank you for using the math tutor, good bye.")
        
        # quit() is outside the while loop, so go there using break.
        break
    
    # If choice is a valid index number of choices array, the
    # user gave us an arithmetical selection (valid choice).
    elif choice >= 0 and choice < len(choices):
        
        # Gather the two numbers to be used in calculations.
        firstNumber = float(input("Enter the first number to use: "))
        secondNumber = float(input("Enter the second number to use: "))
        
        # Get the users guess to the answer.
        guessedAnswer = float(input("Enter your educated guess for the answer: "))
        
        # Get the actual answer.
        solvedAnswer = choices[choice][1](firstNumber,secondNumber)
        
        # Compare guess to actual answer and show the corresponding message.
        if solvedAnswer == guessedAnswer:
            print("Congratulations, you guessed the correct answer.")
        else:
            print(f"Sorry, you guessed an incorrect answer. The correct answer was: {solvedAnswer}")
            
    # If we got here, the user gave us a choice that is not
    # a valid index number and is also not quit's number.
    else:
        print("You entered an incorrect selection, please try again.")

# Good bye.
quit()