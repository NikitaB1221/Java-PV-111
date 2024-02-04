package step.learning.oop;

@Used
public class OldMap extends Map{

    public OldMap(String title, String _scale) { super(title, _scale);}

    @Override
    public String getCard() {
        return "Old" + super.getCard();
    }

}
