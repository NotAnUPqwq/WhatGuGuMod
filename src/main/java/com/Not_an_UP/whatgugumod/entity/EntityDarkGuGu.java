package com.Not_an_UP.whatgugumod.entity;

import com.Not_an_UP.whatgugumod.init.ModItems;
import com.Not_an_UP.whatgugumod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDarkGuGu extends EntityMob {
	public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;
	
    public EntityDarkGuGu(World worldIn) {
        super(worldIn);
        this.setSize(0.4F, 0.7F);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
    }
    
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityGuGu.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D); // 10颗心生命值
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D); // 移动速度
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D); // 攻击伤害
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D); // 追踪范围
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT; // 使用鸡的环境音效
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return new ResourceLocation(Reference.MOD_ID, "entities/entity_dark_gugu");
    }
    
    @Override
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F)
        {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;
    }
    
    public float getEyeHeight()
    {
        return this.height;
    }
    
    public void fall(float distance, float damageMultiplier)
    {
    }
    
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }
    
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 10;
    }
    
    public static void registerFixesChicken(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityDarkGuGu.class);
    }
    
    public void updatePassenger(Entity passenger)
    {
        super.updatePassenger(passenger);
        float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
        float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
        passenger.setPosition(this.posX + (double)(0.1F * f), this.posY + (double)(this.height * 0.5F) + passenger.getYOffset() + 0.0D, this.posZ - (double)(0.1F * f1));

        if (passenger instanceof EntityLivingBase)
        {
            ((EntityLivingBase)passenger).renderYawOffset = this.renderYawOffset;
        }
    }
    
    @Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if (!world.isRemote) {
			if (player.getHeldItem(hand).isEmpty()) {
				if (this.rand.nextFloat() < 0.02) {
					if (this.rand.nextFloat() < 0.25) {
						this.dropItem(ModItems.GUGU_COIN, 1);
					}else {
						this.dropItem(ModItems.COAL_GUGU, this.rand.nextInt(4)+1);
					}
					world.playSound(null, this.getPosition(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 0.9f + rand.nextFloat()/5);
					Entity particle = new EntityHeartParticle(world);
					particle.setPosition(posX-0.5, posY, posZ-0.5);
					world.spawnEntity(particle);
				}
			}
		}
		return super.processInteract(player, hand);
    }
}
