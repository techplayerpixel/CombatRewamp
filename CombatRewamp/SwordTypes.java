package me.GodOfDespair.CombatRewamp;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SwordTypes{
	
	public static Location getLocalCoord(double x, double y, double z, Location origin) {
	    Location arrival = origin.clone();

	    org.bukkit.util.Vector dirX = new Location(arrival.getWorld(), 0, 0, 0, Location.normalizeYaw(arrival.getYaw()-90),
	            arrival.getPitch()).getDirection().normalize();
	    org.bukkit.util.Vector dirY = new Location(arrival.getWorld(), 0, 0, 0, arrival.getYaw(),
	            arrival.getPitch()-90).getDirection().normalize();
	    Vector dirZ = arrival.getDirection().normalize();

	    return arrival.add(dirX.multiply(x))
	            .add(dirY.multiply(y))
	            .add(dirZ.multiply(z));
	}
	
	public static ArmorStand summonArmorStand(Boolean Invisible,Boolean notGravity,Boolean Small,Boolean invurnable,Location loc) {
		ArmorStand ar = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		
		if(Invisible == true) {
			ar.setInvisible(true);
		}
		if(Small == true) {
			ar.setSmall(true);
		}
		if(notGravity == true) {
			ar.setGravity(false);
		}
		if(invurnable == true) {
			ar.setInvulnerable(true);
		}
		
		return ar;
	}
	public static void turnPlayerToLocation(Player player, Location location) {
		  // Get the player's current location
		  Location playerLocation = player.getLocation();

		  // Calculate the vector from the player's current location to the target location
		  Vector targetDirection = location.toVector().subtract(playerLocation.toVector());

		  // Convert the vector to yaw and pitch values
		  float yaw = (float) Math.toDegrees(Math.atan2(-targetDirection.getX(), targetDirection.getZ()));
		  float pitch = (float) Math.toDegrees(Math.asin(targetDirection.getY() / targetDirection.length()));

		  // Set the player's facing direction
		  playerLocation.setYaw(yaw);
		  playerLocation.setPitch(pitch);
		  player.teleport(playerLocation);
		}
}
