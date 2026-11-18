package net.fragger.creatoroverlays.client;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public abstract class AbstractOverlay implements HudElement
{
    public static void render(GuiGraphicsExtractor graphics, Identifier texture)
    {
        Minecraft client = Minecraft.getInstance();
        int width = client.getWindow().getGuiScaledWidth();
        int height = client.getWindow().getGuiScaledHeight();
        render(graphics, texture, 0, 0, width, height);
    }

    public static void render(GuiGraphicsExtractor graphics, Identifier texture, int x, int y, int width, int height)
    {
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0, 0, width, height, width, height);
    }
}
