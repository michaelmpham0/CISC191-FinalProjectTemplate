package edu.sdccd.cisc191.template;
import java.util.HashMap;


public class TextDisplay
{
    //Hashmap is like an array, but each value is stored with a key, like "Introduction"
    static  HashMap<String, String>  textInfo = new HashMap<String, String>()
    {{
        put("ClassInstructions", "Choose a class.");
        put("KnightIntro","Born into a noble family that has seen its prosperity gradually decline over decades,"
            +" desperation has driven you into leaving your once honorable estate in search for redeeming your family name");
        put("WizardIntro","Rumors of a dungeon filled with magical artifacts reached your ears. Naturally gifted with "+
           "magical aptitude, you seek out these treasures.");
        put("BarbarianIntro","Driven out of your land,");
        put("RangerIntro","");
        put("Introduction", "You stand before the dungeons of Andrew Huang.");
    }};

    //Get text info from HashMap textInfo and return it
    public String getText(String textType)
    {
        if (textInfo.containsKey(textType))
        {
            return textInfo.get(textType);
        }
        else
        {
            return "Missing Text";
        }
    }

    public void display(String textType)
    {
        System.out.println(getText(textType));
        System.out.println("----------------");
    }
}
