package enums;

public class FigureColor {

    public Figure figure;
    public Color color;

    public FigureColor(Figure figure, Color color) {
        this.figure = figure;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public String toString() {
        return String.valueOf(figure.name().substring(0, 1) + '.' + color.name().substring(0, 1));
    }

}
