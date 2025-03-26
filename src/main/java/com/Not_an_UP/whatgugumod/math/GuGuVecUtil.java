package com.Not_an_UP.whatgugumod.math;

import net.minecraft.util.math.Vec3i;

public class GuGuVecUtil {
	public static Vec3i vecAdd(Vec3i a, Vec3i b) {
		return new Vec3i(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
	}
	
	public static Vec3i vecSub(Vec3i a, Vec3i b) {
		return vecAdd(a,vecOpp(b));
	}
	
	public static Vec3i vecOpp(Vec3i a) {
		// return a opposite vector
		return new Vec3i(-a.getX(), -a.getY(), -a.getZ());
	}
	
	public static Vec3i vecMul(Vec3i a, int b) {
		return new Vec3i(a.getX()*b, a.getY()*b, a.getZ()*b);
	}
	
	public static Vec3i vecDiv(Vec3i a, int b) {
		return new Vec3i(a.getX()/b, a.getY()/b, a.getZ()/b);
	}
}
