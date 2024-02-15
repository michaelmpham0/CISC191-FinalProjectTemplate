package edu.sdccd.cisc191.template;



public class TextDisplay
{

    static TextStore textObject = new TextStore();

    public void display(String textType)
    {
        System.out.println(textObject.getText(textType));
        System.out.println("----------------");
    }
}
