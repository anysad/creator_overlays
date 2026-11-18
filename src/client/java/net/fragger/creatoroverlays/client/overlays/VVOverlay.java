package net.fragger.creatoroverlays.client.overlays;

import com.mojang.datafixers.util.Pair;
import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class VVOverlay extends StaticOverlay implements Overlay {
    //blank vertical video
    private static final Identifier VV_Overlay = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/vv_overlay.png");
    private static final Identifier VV_Overlay_White = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/vv_overlay_white.png");
    private static final Identifier VV_Overlay_Red = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/vv_overlay_red.png");

    //ro3 vertical video
    private static final Identifier VV_OVERLAY_RO3 = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/rule_of_thirds/vv_overlay_ro3.png");
    private static final Identifier VV_Overlay_White_RO3 = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/rule_of_thirds/vv_overlay_white_ro3.png");
    private static final Identifier VV_Overlay_Red_RO3 = Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/rule_of_thirds/vv_overlay_red_ro3.png");

    private static boolean isRendered = false;
    public static boolean isRO3VV = COConfigs.DISPLAY_RO3VV;

    private static int x = 0;
    private static int y = 0;
    private static int width = 0;
    private static int height = 0;

    public void extractRenderState(GuiGraphicsExtractor guiGraphicsExtractor, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        height = client.getWindow().getGuiScaledHeight();
        width = height / 16 * 9 + 10;
        x = client.getWindow().getGuiScaledWidth() / 2 - (width / 2) - 1;
        if (isRendered) {
            if (color >= 3) {
                Identifier tintedTexture = isRO3VV ? VV_Overlay_White_RO3 : VV_Overlay_White;
                renderTinted(guiGraphicsExtractor, tintedTexture, x, y, width, height, getColorTint(color));
            } else {
                render(guiGraphicsExtractor, getTexture(), x, y, width, height);
            }
        }
    }
    public void updateRenderStatus() {
        isRendered = !isRendered;
        setisRendered(isRendered);
    }

    public Identifier getTexture() {
        Identifier texture = VV_Overlay;
        //normal overlay
        if (!ro3Overlay.isRendered()) {
            if (color == 1) {
                texture = VV_Overlay_White;
            } else if (color == 2){
                texture = VV_Overlay_Red;
            } else if (color >= 3) {
                texture = VV_Overlay_White;
            } else {
                texture = VV_Overlay;
            }
        }

        //ro3 overlay
        if (isRO3VV) {
            if (color == 1) {
                texture = VV_Overlay_White_RO3;
            } else if (color == 2) {
                texture = VV_Overlay_Red_RO3;
            } else if (color >= 3) {
                texture = VV_Overlay_White_RO3;
            } else {
                texture = VV_OVERLAY_RO3;
            }
        }
        return texture;
    }

    public boolean isRendered() {
        return isRendered;
    }
    public void setisRendered(boolean Rendered) {
        isRendered = Rendered;
        if (isRendered) {
            COConfigs.updateConfig(new Pair<>("display.vv", true));
            if (COConfigs.DISPLAY_RO3) {
                COConfigs.updateConfig(new Pair<>("display.ro3", false));
            }
            if (COConfigs.DISPLAY_CAM) {
                COConfigs.updateConfig(new Pair<>("display.cam", false));
            }
            if (ro3Overlay.isRendered()) {
                ro3GUI.toggle();
                if (camOverlay.isRendered()) {
                    camGUI.toggle();
                }
            }
        }
    }
    public boolean getRO3VV(){
        return isRO3VV;
    }
    public void setRO3VV(boolean state) {
        isRO3VV = state;
        COConfigs.updateConfig(new Pair<>("display.ro3vv", isRO3VV));
    }
    public void setColor(int c) {
        this.color = c;
    }
}
