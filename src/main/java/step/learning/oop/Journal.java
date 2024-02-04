package step.learning.oop;

public class Journal extends Literature implements Copyable{
    public int getNumber() {
        return number;
    }

    private int number;

    public Journal(String title, String number) {
        super(title);
        if (Integer.parseInt(number) > 0) {
            this.number = Integer.parseInt(number);
        }
        else
            throw new NumberFormatException("Number must be greater than 0");
    }

    @Override
    public String getCard() {
        return String.format("Journal. Title: '%s'. Number: '%s'"
                , super.getTitle(), this.getNumber());
    }
}
