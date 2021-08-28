package com.jamesr.togglesprint;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = ToggleSprint.MODID, version = ToggleSprint.VERSION)
public class ToggleSprint{
	
    public static final String MODID = "togglesprint";
    public static final String VERSION = "1.0";
    public static boolean enabled;
    public static boolean toggled;

    private static Minecraft mc;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new ToggleSprintCommand());
        mc = Minecraft.getMinecraft();
        enabled = false;
        toggled = false;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
    	
        if (!enabled)
        	return;
        
    	if(mc.gameSettings.keyBindSprint.isPressed()) {
    		
        	toggled = !toggled;
    	
	    	if(toggled)
	    		mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "[togglesprint] " + EnumChatFormatting.BLUE + "sprinting"));
	    	else
	    		mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "[togglesprint] " + EnumChatFormatting.RED + "walking"));
	    	
    	}
    	
    	KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), toggled);    	

    	return;
    }
   
}
