package edu.sdccd.cisc191.template;

import  java.util.Scanner;

public class PromptController
{

    static Scanner keyboard = new Scanner(System.in);

    public int answerPrompt(int numOfAnswers,boolean hasZero)
    {
        boolean hasAnswer = false;
        int promptChoice = 0;
        if (hasZero)
        {
            promptChoice = -1;
        }

        //While pit to get user's prompt choice
        do
        {
            System.out.print("Enter Number: ");
            String answer = keyboard.next();
            /*
            try catch, checks if user input is a number within the range
             */
            try
            {
                int promptNumber = Integer.parseInt(answer);
                if (promptNumber <= numOfAnswers && promptNumber > promptChoice)
                {
                    hasAnswer = true;
                    promptChoice = promptNumber;
                    System.out.println("----------------");
                }
                else
                {
                    //Number outside of prompt range, ex: [4] is out of [1] - [3]
                    System.out.println("Invalid Answer");
                }
            }
            catch (NumberFormatException Exception)
            {
                // Not Number
                System.out.println("Invalid Answer");
            }

        }
        while (hasAnswer == false);
        return promptChoice;
    }
}
