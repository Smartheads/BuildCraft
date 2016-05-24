package buildcraft.lib.gui;

public interface ISimpleDrawable {
    void drawAt(int x, int y);

    default void drawAt(IPositionedElement element) {
        drawAt(element.getX(), element.getY());
    }
}
