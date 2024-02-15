package edu.sdccd.cisc191.template;
import java.util.HashMap;
public class TextStore
{

    //Hashmap is like an array, but each value is stored with a key, like "Introduction"
    static  HashMap<String, String>  textInfo = new HashMap<String, String>()
    {{
        put("ClassInstructions", "Choose a class.");
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
}
