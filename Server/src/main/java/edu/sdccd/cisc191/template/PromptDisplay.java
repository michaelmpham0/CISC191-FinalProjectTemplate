package edu.sdccd.cisc191.template;

import java.util.HashMap;

public class PromptDisplay
{

    /* Store info of prompts
    */


    static HashMap<String, String[]> promptInfo = new HashMap<String, String[]>()
    {{
        put("Continue", new String[]{"Continue"});
        put("Combat", new String[]{"Attack","Guard","Spells","Items","Run Away"});
        put("Exploration", new String[]{"Continue Forward","Check Status","Spells","Items"});
        put("Check",new String[]{"Go Back"});
        put("Classes", new String[]{"Knight","Wizard","Barbarian","Ranger"});
    }};
    public void display(String menuType)
    {
        /*
        For loop to run through items in promptInfo to print out the prompt
        Count variable "i" starts at 1 to ignore the first value
         */

        for (int i = 0; i < promptInfo.get(menuType).length; i++)
        {
            String printString = "[" + Integer.toString(i+1) + "] " + "- " + promptInfo.get(menuType)[i];
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
