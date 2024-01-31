package step.learning.oop;

public class Map extends Literature {
    private int scale;

    public Map(String title, String _scale) {
        super(title);
        int scaleValue = Integer.parseInt(_scale.split(":")[1]);
        if (scaleValue % 1000 == 0) {
            this.scale = scaleValue;
        } else {
            throw new IllegalArgumentException("Scale must be a multiple of 1000");
        }
    }

    public int getScale() {
        return scale;
    }

    @Override
    public String getCard() {
        return String.format("Map. Title: '%s'. Scale: '%s'", super.getTitle(), getScale());
    }
}
