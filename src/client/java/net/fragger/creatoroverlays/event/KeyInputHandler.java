package net.fragger.creatoroverlays.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fragger.creatoroverlays.client.TPHandler;
import net.fragger.creatoroverlays.client.gui.COScreen;
import net.fragger.creatoroverlays.client.gui.RootGUI;
import net.fragger.creatoroverlays.client.gui.overlayguis.*;
import net.fragger.creatoroverlays.client.overlays.CamOverlay;
import net.fragger.creatoroverlays.client.overlays.CustomOverlay;
import net.fragger.creatoroverlays.client.overlays.RO3Overlay;
import net.fragger.creatoroverlays.client.overlays.VVOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public class KeyInputHandler {

    public static final KeyMapping.Category KEY_CATEGORY_CREATOROVERLAYS = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID, "creatoroverlays"));

    public static final String KEY_DISPLAY_RO3OVERLAY = "key.creatoroverlays.display_ro3overlay";
    public static final String KEY_DISPLAY_VVOVERLAY = "key.creatoroverlays.display_vvoverlay";
    public static final String KEY_DISPLAY_TPOVERLAY = "key.creatoroverlays.display_tpoverlay";
    public static final String KEY_DISPLAY_CAMOVERLAY = "key.creatoroverlays.display_camoverlay";
    public static final String KEY_DISPLAY_CUSTOMOVERLAY = "key.creatoroverlays.display_custom";
    public static final String KEY_CO_COLOR = "key.creatoroverlays.co_color";
    public static final String KEY_CO_ROTATEUP = "key.creatoroverlays.co_rotateup";
    public static final String KEY_CO_ROTATEDOWN = "key.creatoroverlays.co_rotatedown";
    public static final String KEY_CO_UP = "key.creatoroverlays.co_up";
    public static final String KEY_CO_DOWN = "key.creatoroverlays.co_down";
    public static final String KEY_CO_RIGHT = "key.creatoroverlays.co_right";
    public static final String KEY_CO_LEFT = "key.creatoroverlays.co_left";
    public static final String KEY_MODIFIER = "key.creatoroverlays.co_modifier";
    public static final String KEY_GUI = "key.creatoroverlays.gui";
    public static final String KEY_SELECT_TP_COLOR = "key.creatoroverlays.select.tp_color";

    public static KeyMapping ro3displayKey;
    public static KeyMapping vvdisplayKey;
    public static KeyMapping tpdisplayKey;
    public static KeyMapping camdisplayKey;
    public static KeyMapping customDisplayKey;
    public static KeyMapping cocolorKey;
    public static KeyMapping corotateupKey;
    public static KeyMapping corotatedownKey;
    public static KeyMapping coupKey;
    public static KeyMapping codownKey;
    public static KeyMapping corightKey;
    public static KeyMapping coleftKey;
    public static KeyMapping modifierKey;
    public static KeyMapping guiKey;
    public static KeyMapping selectAllTPColor;

    public static RO3GUI ro3GUI = new RO3GUI();
    public static RO3Overlay ro3Overlay = ro3GUI.getOverlay();
    public static VVGUI vvGUI = new VVGUI();
    public static VVOverlay vvOverlay = vvGUI.getOverlay();
    public static CAMGUI camGUI = new CAMGUI();
    public static CamOverlay camOverlay = camGUI.getOverlay();
    public static TPHandler tpOverlay = new TPHandler();
    public static TPGUI tpGUI = new TPGUI();
    public static CustomGUI customGUI = new CustomGUI();
    public static CustomOverlay customOverlay = customGUI.getOverlay();
    public static RootGUI coGUI = new RootGUI();
    public static COScreen coScreen = new COScreen(coGUI);

    private static int num = 0;


    //uses Hud Render Callback to check for keypresses, as to fix the issue with not being able to toggle overlays while Replay Mod recordings are paused
    //very unconventional, but it works lol
    public void extractRenderState(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        /*
         *
         * Toggle Keys
         */
        //Handles RO3 Overlay Key Press
        if (ro3displayKey.consumeClick()) {
            if (vvOverlay.isRendered()) {
                ro3Overlay.setisRendered(false);
                vvOverlay.setRO3VV(!vvOverlay.getRO3VV());
            } else {
                ro3GUI.toggle();
            }
        }
        //Handles Vertical Video Key Press
        if (vvdisplayKey.consumeClick()) {
            vvGUI.toggle();
        }
        //Handels Tracking Points Key Press
        if (tpdisplayKey.consumeClick()) {
            tpOverlay.updateRenderStatus();
        }
        //Handles Cam Key Press
        if (camdisplayKey.consumeClick()) {
            camGUI.toggle();
        }
        //Handles Custom Key Press
        if (customDisplayKey.consumeClick()) {
            customGUI.toggle();
        }

        /*
         *
         * Overlay Cycle Keys
         */
        //Cycles Through Colors
        if (cocolorKey.consumeClick()) {
            if (tpOverlay.isRendered()) {
                tpOverlay.cycleColor();
            } else if (camOverlay.isRendered()) {
                camGUI.changeColor();
            } else if (ro3Overlay.isRendered()) {
                ro3GUI.changeColor();
            } else if (vvOverlay.isRendered()){
                vvGUI.changeColor();
            }
        }
        //Cycles Rotations Up
        if (corotateupKey.consumeClick()) {
            if (tpOverlay.isRendered()) {
                tpOverlay.cycleSelectedUp();
            } else if (camOverlay.isRendered()) {
                camGUI.swapSides();
            } else if (ro3Overlay.isRendered()) {
                ro3GUI.changeGrid();
            } else if (customOverlay.isRendered()) {
                customGUI.changeOverlayUp();
            }
        }
        //Cycles Rotations Down
        if (corotatedownKey.consumeClick()) {
            if (tpOverlay.isRendered()) {
                tpOverlay.cycleSelectedDown();
            } else if (camOverlay.isRendered()) {
                camGUI.swapSides();
            } else if (ro3Overlay.isRendered()) {
                ro3GUI.changeGrid();
            } else if (customOverlay.isRendered()) {
                customGUI.changeOverlayDown();
            }
        }

        /*
         *
         * Tracking Point Movement Keys
         */
        //moves tp overlay up
        if (num == 0) {
            if (coupKey.isDown()) {
                if (modifierKey.isDown()) {
                    tpOverlay.moveUp();
                } else {
                    tpOverlay.moveUpFast();
                }
            }
            //moves tp overlay down
            if (codownKey.isDown()) {
                if (modifierKey.isDown()) {
                    tpOverlay.moveDown();
                } else {
                    tpOverlay.moveDownFast();
                }
            }
            //moves tp overlay right
            if (corightKey.isDown()) {
                if (modifierKey.isDown()) {
                    tpOverlay.moveRight();
                } else {
                    tpOverlay.moveRightFast();
                }
            }
            //moves tp overlay left
            if (coleftKey.isDown()) {
                if (modifierKey.isDown()) {
                    tpOverlay.moveLeft();
                } else {
                    tpOverlay.moveLeftFast();
                }
            }
            num = 1;
        } else {
            num = 0;
        }

        //opens GUI
        if (guiKey.isDown()) {
            Minecraft.getInstance().gui.setScreen(coScreen);
            COConfigs.writeConfigs();
        }
        //selects all tracking points of the same color
        if (selectAllTPColor.consumeClick()) {
           tpOverlay.selectSameColor(tpOverlay.getSelected().color());
        }
    }
    public static void register() {
        //set modifier key bind
        modifierKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_MODIFIER,
                InputConstants.KEY_RCONTROL,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets the RO3 display key bind
        ro3displayKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_DISPLAY_RO3OVERLAY,
                InputConstants.KEY_F9,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Vertial Video key bind
        vvdisplayKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_DISPLAY_VVOVERLAY,
                InputConstants.KEY_F8,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //set Tracking Point key bind
        tpdisplayKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_DISPLAY_TPOVERLAY,
                InputConstants.UNKNOWN.getValue(),
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Cam key bind
        camdisplayKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_DISPLAY_CAMOVERLAY,
                InputConstants.KEY_F7,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Custom key bind
        customDisplayKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_DISPLAY_CUSTOMOVERLAY,
                InputConstants.UNKNOWN.getValue(),
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets color cycle key bind
        cocolorKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_COLOR,
                InputConstants.KEY_F4,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets the rotate up key bind
        corotateupKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_ROTATEUP,
                InputConstants.KEY_RBRACKET,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        corotatedownKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_ROTATEDOWN,
                InputConstants.KEY_LBRACKET,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        coupKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_UP,
                InputConstants.KEY_UP,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        codownKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_DOWN,
                InputConstants.KEY_DOWN,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        corightKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_RIGHT,
                InputConstants.KEY_RIGHT,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        coleftKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_CO_LEFT,
                InputConstants.KEY_LEFT,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        guiKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
               KEY_GUI,
               InputConstants.KEY_GRAVE,
               KEY_CATEGORY_CREATOROVERLAYS
        ));
        selectAllTPColor = KeyMappingHelper.registerKeyMapping(new KeyMapping(
           KEY_SELECT_TP_COLOR,
           InputConstants.UNKNOWN.getValue(),
           KEY_CATEGORY_CREATOROVERLAYS
        ));
    }

    private static void attach(String path, HudElement element) {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(creatoroverlays.MOD_ID, path),
                element
        );
    }

    public void initialize()
    {
        attach("ro3_overlay", ro3Overlay::extractRenderState);
        attach("vv_overlay", vvOverlay::extractRenderState);
        attach("cam_overlay", camOverlay::extractRenderState);
        attach("custom_overlay", customOverlay::extractRenderState);
        attach("key_input", this::extractRenderState);
    }
}
