package net.fragger.creatoroverlays.client;

import net.fragger.creatoroverlays.util.config.COConfigs;

public abstract class StaticOverlay extends AbstractOverlay {

   public int color = COConfigs.OVERLAY_COLOR;
   public int rotation = 0;

   public boolean state = true;

   public static final int COLOR_COUNT = 9;

   public void colorCycle() {
       color = (color + 1) % COLOR_COUNT;
   }

   public static int getColorTint(int color) {
       return switch (color) {
           case 3 -> 0xFF0000FF;
           case 4 -> 0xFF00FF00;
           case 5 -> 0xFFFFFF00;
           case 6 -> 0xFF00FFFF;
           case 7 -> 0xFFFF00FF;
           case 8 -> 0xFFFF8000;
           default -> 0xFFFFFFFF;
       };
   }

   public void rotateUp() {
       if (rotation == 1) {
           rotation = 2;
       } else if (rotation == 2) {
           rotation = 3;
       } else if (rotation == 3) {
           rotation = 0;
       } else {
           rotation = 1;
       }
   }
   public void rotateDown() {
       if (rotation == 180) {
           rotation = 90;
       } else if (rotation == 270) {
           rotation = 180;
       } else if (rotation == 0) {
           rotation = 270;
       } else {
           rotation = 0;
       }
   }

   public void swap() {
       state = !state;
   }

   public int color(){
        return color;
    }
}
