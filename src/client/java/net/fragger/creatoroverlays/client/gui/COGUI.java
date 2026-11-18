package net.fragger.creatoroverlays.client.gui;

import com.mojang.datafixers.util.Pair;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fabricmc.fabric.api.util.TriState;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.coScreen;

public class COGUI extends AbstractGUI {

    private static final Identifier NotesOutline = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/widgets/config_outline.png");

    public WPlainPanel coPanel = new WPlainPanel();
    private WPlainPanel notes = new WPlainPanel();
    public String color = "";
    public String colorM = "";
    public String mode = "";

    public COGUI() {
        updateColor();
        updateColorMode();
        updateDisplayMode();

        coPanel.setSize(RootGUI.guiWidth, RootGUI.guiHeight);

        //header section
        WLabel head = new WLabel(Component.translatable("key.creatoroverlays.gui.creatoroverlays"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        coPanel.add(head, 120, 10);

        WLabel version = new WLabel(Component.literal("Version: 3.1.0"));
        coPanel.add(version, 10, 30);
        WLabel release = new WLabel(Component.literal("Released: " + "July 2024"));
        coPanel.add(release, 10, 40);

        WLabel config = new WLabel(Component.translatable("key.creatoroverlays.gui.default"));
        coPanel.add(config, 10, 60);



        WSprite outline = new WSprite(NotesOutline);
        coPanel.add(outline, 10, 75, 230, 115);


        //notes panel
        notes.setSize(230, 110);

        WButton defaultColor = new WButton(Component.translatable(color));
        if (COConfigs.getValue("color.mode").equals(1)) {
            defaultColor.setEnabled(false);
        }
        notes.add(defaultColor, 10, 10, 210, 20);
        defaultColor.setOnClick(() -> {
            if (COConfigs.getValue("overlay.color").equals(1)){
                    COConfigs.updateConfig(new Pair<>("overlay.color", 2));
                    updateColor();
                    defaultColor.setLabel(Component.translatable(color));
            } else if (COConfigs.getValue("overlay.color").equals(2)) {
                    COConfigs.updateConfig(new Pair<>("overlay.color", 0));
                    updateColor();
                    defaultColor.setLabel(Component.translatable(color));
            } else {
                    COConfigs.updateConfig(new Pair<>("overlay.color", 1));
                    updateColor();
                    defaultColor.setLabel(Component.translatable(color));
            }
        });

        WButton colorMode = new WButton(Component.translatable(colorM));
        notes.add(colorMode, 10, 40, 210, 20);
        colorMode.setOnClick(() -> {
           if (COConfigs.getValue("color.mode").equals(1)) {
               COConfigs.updateConfig(new Pair<>("color.mode", 0));
               updateColorMode();
               colorMode.setLabel(Component.translatable(colorM));
               defaultColor.setEnabled(true);
           } else {
               COConfigs.updateConfig(new Pair<>("color.mode", 1));
               updateColorMode();
               colorMode.setLabel(Component.translatable(colorM));
               defaultColor.setEnabled(false);
           }
        });

        WButton displayMode = new WButton(Component.translatable(mode));
        notes.add(displayMode, 10, 70, 210, 20);
        displayMode.setOnClick(() -> {
           if (COConfigs.getValue("display.mode").equals(1)) {
                   COConfigs.updateConfig(new Pair<>("display.mode", 2));
                   updateDisplayMode();
                   displayMode.setLabel(Component.translatable(mode));
           } else if (COConfigs.getValue("display.mode").equals(2)) {
                   COConfigs.updateConfig(new Pair<>("display.mode", 0));
                   updateDisplayMode();
                   displayMode.setLabel(Component.translatable(mode));
           } else {
                   COConfigs.updateConfig(new Pair<>("display.mode", 1));
                   updateDisplayMode();
                   displayMode.setLabel(Component.translatable(mode));
           }
        });


        //scroll panel
        WScrollPanel scroll = new WScrollPanel(notes);
        scroll.setScrollingHorizontally(TriState.FALSE);
        scroll.setScrollingVertically(TriState.FALSE);
        coPanel.add(scroll, 10, 77, 230, 111);

        WButton close = new WButton(Component.translatable("key.creatoroverlays.gui.close"));
        coPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.onClose();
            COConfigs.writeConfigs();
        });
    }

    private void addLine(String note, int y) {
        WText line = new WText(Component.literal(note));
        line.setColor(0x000000);
        notes.add(line, 8, y, 210, 10);
    }

    public void updateColor(){
        if (COConfigs.getValue("overlay.color").equals(1)) {
            color = "key.creatoroverlays.gui.default.white";
        } else if (COConfigs.getValue("overlay.color").equals(2)) {
            color = "key.creatoroverlays.gui.default.red";
        } else {
            color = "key.creatoroverlays.gui.default.black";
        }
    }
    public void updateColorMode() {
        if (COConfigs.getValue("color.mode").equals(1)) {
            colorM = "key.creatoroverlays.gui.default.previous";
        } else {
            colorM = "key.creatoroverlays.gui.default.default";
        }
    }
    public void updateDisplayMode() {
        if (COConfigs.getValue("display.mode").equals(1)) {
            mode = "key.creatoroverlays.gui.default.on";
        } else if (COConfigs.getValue("display.mode").equals(2)) {
            mode = "key.creatoroverlays.gui.default.replay";
        } else {
            mode = "key.creatoroverlays.gui.default.off";
        }
    }
}
