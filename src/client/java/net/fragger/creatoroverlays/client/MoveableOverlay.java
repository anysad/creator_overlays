package net.fragger.creatoroverlays.client;

import net.minecraft.client.Minecraft;

public abstract class MoveableOverlay extends AbstractOverlay {

    public int mColor = 0;

    public Minecraft client = Minecraft.getInstance();

    public boolean isSelected = false;
    public boolean isRendered = false;
    public boolean invisible = true;

    public int x = 0;
    public int y = 0;

    public static int height = 0;
    public static int width = 0;

    //0 is black, 1 is white, 2 is red, 3 is blue
    public void updateColor() {
        if (mColor == 0) {
            mColor = 1;
        } else if (mColor == 1) {
            mColor = 2;
        } else if (mColor == 2) {
            mColor = 3;
        } else if (mColor == 3) {
            mColor = 0;
        }
    }

    public void reset() {
        if (client != null) {
            x = client.getWindow().getGuiScaledWidth() / 2 - (width / 2) - 1;
            y = client.getWindow().getGuiScaledHeight() / 2 - (height / 2) + 1;
        }
    }

    public void moveUp() {
        if(isRendered & isSelected) {
            if (y > 0) {
                y--;
            }
        }
    }
    public void moveUpFast() {
        if(isRendered & isSelected) {
            if(y >= 5) {
                y = y - 5;
            }
        }
    }
    public void moveDown() {
        if (isRendered & isSelected) {
            if (y < client.getWindow().getGuiScaledHeight() - height) {
                y++;
            }
        }
    }
    public void moveDownFast() {
        if (isRendered & isSelected) {
            if (y <= client.getWindow().getGuiScaledHeight() - height - 5) {
                y = y + 5;
            }
        }
    }
    public void moveRight() {
        if (isRendered & isSelected) {
            if (x < client.getWindow().getGuiScaledWidth() - width) {
                    x++;
            }
        }
    }
    public void moveRightFast() {
        x = x + 5;
    }
    public void moveLeft() {
        x--;
    }
    public void moveLeftFast() {
        x = x - 5;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}
