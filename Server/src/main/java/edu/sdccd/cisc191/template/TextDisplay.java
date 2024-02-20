package edu.sdccd.cisc191.template;
import java.util.HashMap;


public class TextDisplay
{
    //Hashmap is like an array, but each value is stored with a key, like "Introduction"
    static  HashMap<String, String>  textInfo = new HashMap<String, String>()
    {{
        put("ClassInstructions", "Choose a class.");
        put("KnightIntro","Born into a noble family that has seen its prosperity gradually decline over decades,"
            +" desperation has driven you into leaving your once honorable estate in search for redeeming your family name.");
        put("WizardIntro","Rumors of a dungeon filled with magical artifacts reaches your ears. Naturally gifted with "+
           "magical aptitude, you pursue these treasures.");
        put("BarbarianIntro","Your home village devastated by disaster after disaster, you aimlessly wonder the lands before "+
                "stumbling upon an opening to a cave.");
        put("RangerIntro","Driven by crippling poverty and hunger, you are desperate to make ends meet.");
        put("Introduction", "You stand before the cold stony entrance of a decrepit dungeon.");
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
