package com.sots.util;

import java.util.ArrayList;

import com.sots.routing.interfaces.IPipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ConnectionHelper {
	public static boolean canConnectTile(IBlockAccess world, BlockPos pos) {
		return world.getTileEntity(pos)!=null;
	}
	
	public static boolean isPipe(IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		return(te instanceof IPipe);
	}
	
	public static boolean isInventory(IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		return(te instanceof IInventory || te instanceof ISidedInventory);
	}
	
	public static boolean isSidedInventory(IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		return(te instanceof ISidedInventory);
	}
	
	public static ArrayList<String> checkForPipes(IBlockAccess world, BlockPos pos) {
		ArrayList<String> hidden = new ArrayList<String>();
		//The Center Block of the Pipe allways has to be shown, thus its never added here
		//We are never using Connectors if we are checking for Pipes
		hidden.add("CUP");
		hidden.add("CDOWN");
		hidden.add("CNORTH");
		hidden.add("CSOUTH");
		hidden.add("CEAST");
		hidden.add("CWEST");
		//Pipe Glass is currently not implemented
		hidden.add("GUP");
		hidden.add("GDOWN");
		hidden.add("GNORTH");
		hidden.add("GWEST");
		hidden.add("GSOUTH");
		hidden.add("GEAST");
		hidden.add("GCENTER");
		
		
		//North Connection
		if(!isPipe(world, pos.north()))
			hidden.add(Connections.NORTH.toString());
		
		//South Connection
		if(!isPipe(world, pos.south()))
			hidden.add(Connections.SOUTH.toString());
		
		//East Connection
		if(!isPipe(world, pos.east()))
			hidden.add(Connections.EAST.toString());
		
		//West Connection
		if(!isPipe(world, pos.west()))
			hidden.add(Connections.WEST.toString());
		
		//Up Connection
		if(!isPipe(world, pos.up()))
			hidden.add(Connections.UP.toString());
		
		//Down Connection
		if(!isPipe(world, pos.down()))
			hidden.add(Connections.DOWN.toString());
		
		return hidden;
	}
	
	public static ArrayList<String> checkInventoriesAndPipes(IBlockAccess world, BlockPos pos){
		ArrayList<String> hidden = new ArrayList<String>();
		//The Center Block of the Pipe allways has to be shown, thus its never added here
		//Pipe Glass is currently not implemented
		hidden.add("GUP");
		hidden.add("GDOWN");
		hidden.add("GNORTH");
		hidden.add("GWEST");
		hidden.add("GSOUTH");
		hidden.add("GEAST");
		hidden.add("GCENTER");
		
		//North Side Check
		if(!isPipe(world, pos.north())  && !isInventory(world, pos.north()))
		
		
		if(!isPipe(world, pos.north()) || !isInventory(world, pos.north())) {
			hidden.add(Connections.NORTH.toString());
			if(!isInventory(world, pos.north())) 
				hidden.add(Connections.C_NORTH.toString());
		}
		
		//South Side Check
		if(!isPipe(world, pos.south()) || !isInventory(world, pos.south())) {
			hidden.add(Connections.SOUTH.toString());
			if(!isInventory(world, pos.south())) 
				hidden.add(Connections.C_SOUTH.toString());
		}
		
		//East Side Check
		if(!isPipe(world, pos.east()) || !isInventory(world, pos.east())) {
			hidden.add(Connections.EAST.toString());
			if(!isInventory(world, pos.east())) 
				hidden.add(Connections.C_EAST.toString());
		}
		
		//West Side Check
		if(!isPipe(world, pos.west()) || !isInventory(world, pos.west())) {
			hidden.add(Connections.WEST.toString());
			if(!isInventory(world, pos.west())) 
				hidden.add(Connections.C_WEST.toString());
		}
		
		//Up Side Check
		if(!isPipe(world, pos.up()) || !isInventory(world, pos.up())) {
			hidden.add(Connections.UP.toString());
			if(!isInventory(world, pos.up())) 
				hidden.add(Connections.C_UP.toString());
		}
		
		//Down Side Check
		if(!isPipe(world, pos.down()) || !isInventory(world, pos.down())) {
			hidden.add(Connections.DOWN.toString());
			if(!isInventory(world, pos.down())) 
				hidden.add(Connections.C_DOWN.toString());
		}
		
		return hidden;
	}
}