package net.fragger.creatoroverlays.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public abstract class AbstractGUI extends LightweightGuiDescription {

    public static final Identifier OUTLINE = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/widgets/overlay_outline.png");

    private static final Component black = Component.translatable("key.creatoroverlays.gui.color.black");
    private static final Component white = Component.translatable("key.creatoroverlays.gui.color.white");
    private static final Component red = Component.translatable("key.creatoroverlays.gui.color.red");
    private static final Component blue = Component.translatable("key.creatoroverlays.gui.color.blue");
    private static final Component green = Component.translatable("key.creatoroverlays.gui.color.green");
    private static final Component yellow = Component.translatable("key.creatoroverlays.gui.color.yellow");
    private static final Component cyan = Component.translatable("key.creatoroverlays.gui.color.cyan");
    private static final Component magenta = Component.translatable("key.creatoroverlays.gui.color.magenta");
    private static final Component orange = Component.translatable("key.creatoroverlays.gui.color.orange");

    public int y = 30;

    public Component getColor(StaticOverlay overlay) {
        return switch (overlay.color()) {
            case 1 -> white;
            case 2 -> red;
            case 3 -> blue;
            case 4 -> green;
            case 5 -> yellow;
            case 6 -> cyan;
            case 7 -> magenta;
            case 8 -> orange;
            default -> black;
        };
    }
}
