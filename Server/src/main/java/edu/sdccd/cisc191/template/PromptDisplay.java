package edu.sdccd.cisc191.template;

public class PromptDisplay
{

    /* Store info of prompts
    */
    static String[][] promptInfo =
            {
                    {"Continue",   "Continue"},
                    {"Combat",   "Attack","Guard","Spells","Items","Run Away"},
                    {"Exploration",   "Continue Forward","Check Status","Spells","Items"},
                    // Catch all for prompts that'll only have Go Back as an option
                    {"Empty Prompt",   "Go Back"},
                    {"Classes",   "Knight","Wizard","Barbarian","Ranger"},
            };

    public void display(String menuType)
    {
        /*
        Menu type will probably be manually indexed with numbers, I don't know how to add dictionaries
        Switch statements will evaluate given menu type to get the index of promptInfo
         */
        int promptNum;
        switch(menuType)
        {
            case "Continue":
                promptNum = 0;
                break;
            case "Combat":
                promptNum = 1;
                break;
            case "Exploration":
                promptNum = 2;
                break;
            case "Classes":
                promptNum = 4;
                break;
            case "Check":
                promptNum = 3;
                break;
            default:
                promptNum = 0;
                break;
        }

        /*
        For loop to run through items in promptInfo to print out the prompt
        Count variable "i" starts at 1 to ignore the first value
         */
        for (int i = 1; i < promptInfo[promptNum].length; i++)
        {
            String printString = "[" + Integer.toString(i) + "]" + "-" + promptInfo[promptNum][i];
            System.out.println(printString);
        }
        System.out.println("----------------");
    }


    @Override
    public String toString()
    {
        return "";
    }
}
