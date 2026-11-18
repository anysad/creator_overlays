package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fragger.creatoroverlays.client.gui.RootGUI;
import net.fragger.creatoroverlays.client.overlays.TPOverlay;
import net.minecraft.network.chat.Component;

import static net.fragger.creatoroverlays.event.KeyInputHandler.tpGUI;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpOverlay;


public class TPGUIRow {

    private static final Component black = Component.translatable("key.creatoroverlays.gui.color.black");
    private static final Component white = Component.translatable("key.creatoroverlays.gui.color.white");
    private static final Component red = Component.translatable("key.creatoroverlays.gui.color.red");
    private static final Component blue = Component.translatable("key.creatoroverlays.gui.color.blue");

    private TPOverlay overlay = new TPOverlay();
    private WLabel section;
    private WToggleButton selectTP = new WToggleButton(RootGUI.SELECT_ON, RootGUI.SELECT_OFF);
    private WToggleButton toggleTP = new WToggleButton(RootGUI.TOGGLE_ON, RootGUI.TOGGLE_OFF);
    private WButton colorTP = new WButton(getColor());;
    private WButton reset = new WButton(new TextureIcon(RootGUI.RESET));;
    private WButton remove = new WButton(Component.nullToEmpty("X"));;
    private WSprite texture;
    private WPlainPanel tpRow = new WPlainPanel();


    int y = 5;
    public TPGUIRow(Component label) {
        tpGUI.updateMultiSelection(overlay.color());

        tpRow.setSize(220, 45);

        //section label
        section = new WLabel(label);
        section.setColor(0xFFFFFF);
        tpRow.add(section, 10, y - 10);

        //Visibility Toggle
        tpRow.add(toggleTP, 10, y, 20, 20);
        toggleTP.setOnToggle(on -> {
            toggle();
            if (!tpOverlay.isRendered()) {
                tpOverlay.toggleAllOn();
            }
            tpOverlay.updateSelected(this);
            if (!overlay.isRendered()) {
                tpOverlay.cycleSelectedUp();
            }
        });

        //Change Color
        tpRow.add(colorTP, 38, y, 70, 20);
        colorTP.setOnClick(() -> {
            overlay.updateColor();
            colorTP.setLabel(getColor());
            updateSprite();
            tpOverlay.updateSelected(this);
        });

        //Reset Overlay Position
        tpRow.add(reset, 118, y, 20, 20);
        reset.setOnClick(() -> {
           overlay.reset();
            tpOverlay.updateSelected(this);
        });

        //remove Tracking Point
        tpRow.add(remove, 176, y, 20, 20);
        remove.setOnClick(() -> {
            tpOverlay.updateSelected(this);
            tpOverlay.cycleSelectedUp();
            tpGUI.removeRow(this);
        });

        //Selection Toggle
        tpRow.add(selectTP, 148, y, 20, 20);
        selectTP.setOnToggle(on -> {
            tpOverlay.updateSelected(this);
        });

        this.updateSprite();
    }

    public void initilize() {
        toggleOn();
        overlay.initialize();
        tpOverlay.updateSelected(this);
        selectTP.setToggle(true);
    }

    private void updateSprite()  {
        tpRow.remove(texture);
        texture = new WSprite(overlay.getTexture());
        tpRow.add(texture, 200, y, 20, 20);
    }

    public void updateLabel(Component newLable) {
        tpRow.remove(section);
        section = new WLabel(newLable);
        section.setColor(0xFFFFFF);
        tpRow.add(section, 10, y - 10);
    }

    private Component getColor() {
        if (overlay.color() == 0) {
            return black;
        } else if (overlay.color() == 1) {
            return white;
        } else if (overlay.color() == 2) {
            return red;
        } else {
            return blue;
        }
    }

    public void select() {
        overlay.setIsSelected(true);
        selectTP.setToggle(true);
        tpGUI.updateMultiSelection(overlay.color());
    }
    public void deselect() {
        overlay.setIsSelected(false);
        selectTP.setToggle(false);
    }
    public void toggle() {
        overlay.updateRenderStatus();
        toggleTP.setToggle(overlay.isRendered());
    }
    public void toggleOn() {
        toggleTP.setToggle(true);
        overlay.setisRendered(true);
    }
    public void toggleOff() {
        toggleTP.setToggle(false);
        overlay.setisRendered(false);
    }
    public void updateColor() {
        colorTP.setLabel(getColor());
        overlay.updateColor();
        updateSprite();
        tpGUI.updateMultiSelection(overlay.color());
    }
    public WPlainPanel rowPanel()  {
        return tpRow;
    }
    public TPOverlay getTP() {
        return overlay;
    }
}
