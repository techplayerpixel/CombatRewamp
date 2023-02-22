package me.GodOfDespair.CombatRewamp;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents((Listener)new Click(this), (Plugin)this);
		Bukkit.getPluginManager().registerEvents((Listener)new Combo(this), (Plugin)this);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(Click.Stands !=null) {
					for(ArmorStand as : Click.Stands.keySet()) {
						if(!(as.isDead())) {
							as.getWorld().spawnParticle(Particle.CRIT, as.getLocation(),0, 0, 0, 0, 2);
							as.getWorld().playSound(as.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 2, 2);
							List<Entity> ens= as.getNearbyEntities(1, 1, 1);
							for(Entity en : ens) {
								if(en instanceof LivingEntity) {
									((LivingEntity) en).damage(Click.Stands.get(as));
									
									
								}
							}
						}else {
							Click.Stands.remove(as);
						}
					}
				}
			}
			
		}, 0, 1);
		
	}
	
	
	public void onDisable() {
		
	}
}
