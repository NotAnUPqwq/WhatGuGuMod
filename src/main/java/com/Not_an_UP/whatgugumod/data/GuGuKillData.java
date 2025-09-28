package com.Not_an_UP.whatgugumod.data;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

public class GuGuKillData extends WorldSavedData{

private static final String DATA_NAME = "whatgugumod_gugu_kills";
    
    private NBTTagCompound playerKills = new NBTTagCompound();
    
    public GuGuKillData() {
        super(DATA_NAME);
    }
    
    public GuGuKillData(String name) {
        super(name);
    }
    
    // 增加玩家击杀数
    public void addKill(UUID playerId) {
        String key = playerId.toString();
        int current = playerKills.getInteger(key);
        playerKills.setInteger(key, current + 1);
        this.markDirty(); // 标记需要保存
    }
    
    // 获取玩家击杀数
    public int getKills(UUID playerId) {
        return playerKills.getInteger(playerId.toString());
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        playerKills = nbt.getCompoundTag("playerKills");
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("playerKills", playerKills);
        return compound;
    }
    
    // 获取世界数据实例
    public static GuGuKillData get(World world) {
        GuGuKillData instance = (GuGuKillData) world.getMapStorage().getOrLoadData(GuGuKillData.class, DATA_NAME);
        
        if (instance == null) {
            instance = new GuGuKillData();
            world.getMapStorage().setData(DATA_NAME, instance);
        }
        
        return instance;
    }
	
}
