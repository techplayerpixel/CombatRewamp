package me.GodOfDespair.CombatRewamp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Combo implements Listener{
    Main plugin;
	
	public Combo(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void doCombo(PlayerInteractEvent e) {
		Action a = e.getAction();
		Player p = e.getPlayer();
		if(a == Action.RIGHT_CLICK_AIR && Click.inCombo!=null && Click.inCombo.containsKey(p) && Click.inCombo.get(p) == "left") {
			Click.inCombo.remove(p);
			
			Location startloc = Click.CurrentStand.get(p).getLocation();
			Location glideloc = SwordTypes.getLocalCoord(0.5, -1, -1.5, startloc);
			glidePlayerToLocation(p, glideloc, 5);
			
			Location first = SwordTypes.getLocalCoord(-0.5, 0, -0.5, startloc);
			Location second = SwordTypes.getLocalCoord(-1, 0, -1, startloc);
			Location third = SwordTypes.getLocalCoord(-1, 0, -2, startloc);
			Location fourth = SwordTypes.getLocalCoord(-1, 0, -3, startloc);
			Location fifth = SwordTypes.getLocalCoord(-0.5, 0, -3.5, startloc);
			Location sixth = SwordTypes.getLocalCoord(0, 0, -4, startloc);
			Location seventh = SwordTypes.getLocalCoord(0.5, 0, -4.5, startloc);
			Click.inCombo.put(p, "comboight");
			ArmorStand one = SwordTypes.summonArmorStand(true, true, true, true, first);
			List<ArmorStand> ls = new ArrayList<ArmorStand>();
			ls.add(one);
			Click.Stands.put(one, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
			if(Click.CurrentStand !=null && Click.CurrentStand.containsKey(p)) {
				Click.CurrentStand.remove(p);
			}
			Click.CurrentStand.put(p, one);
			SwordTypes.turnPlayerToLocation(p, first);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {
					if(Click.inCombo.keySet() == null || !(Click.inCombo.containsKey(p))) {
						for(ArmorStand a : ls) {
							if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
								Click.Stands.remove(a);
							}
							a.remove();
						}
						Click.CurrentStand.remove(p);
						return;
					}else {
						ArmorStand two = SwordTypes.summonArmorStand(true, true, true, true, second);
						ls.add(two);
						Click.Stands.put(two, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
						Click.CurrentStand.remove(p);
						Click.CurrentStand.put(p, two);
						SwordTypes.turnPlayerToLocation(p, second);
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

							@Override
							public void run() {
								if(Click.inCombo.keySet() == null || !(Click.inCombo.containsKey(p))) {
									for(ArmorStand a : ls) {
										if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
											Click.Stands.remove(a);
										}
										a.remove();
									}
									Click.CurrentStand.remove(p);
									return;
								}else {
									ArmorStand three = SwordTypes.summonArmorStand(true, true, true, true, third);
									ls.add(three);
									Click.Stands.put(three, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
									Click.CurrentStand.remove(p);
									Click.CurrentStand.put(p, three);
									SwordTypes.turnPlayerToLocation(p, third);
									Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

										@Override
										public void run() {
											if(Click.inCombo.keySet() == null || !(Click.inCombo.containsKey(p))) {
												for(ArmorStand a : ls) {
													if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
														Click.Stands.remove(a);
													}
													a.remove();
												}
												Click.CurrentStand.remove(p);
												return;
											}else {
												ArmorStand four = SwordTypes.summonArmorStand(true, true, true, true, fourth);
												ls.add(four);
												Click.Stands.put(four, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
												Click.CurrentStand.remove(p);
												Click.CurrentStand.put(p, four);
												SwordTypes.turnPlayerToLocation(p, fourth);
												Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

													@Override
													public void run() {
														if(Click.inCombo.keySet() == null || !(Click.inCombo.containsKey(p))) {
															for(ArmorStand a : ls) {
																if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
																	Click.Stands.remove(a);
																}
																a.remove();
															}
															Click.CurrentStand.remove(p);
															return;
														}else {
															ArmorStand five = SwordTypes.summonArmorStand(true, true, true, true, fifth);
															ls.add(five);
															Click.Stands.put(five, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
															Click.CurrentStand.remove(p);
															Click.CurrentStand.put(p, five);
															SwordTypes.turnPlayerToLocation(p, fifth);
															Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

																@Override
																public void run() {
																	if(Click.inCombo.keySet() == null || !(Click.inCombo.containsKey(p))) {
																		for(ArmorStand a : ls) {
																			if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
																				Click.Stands.remove(a);
																			}
																			a.remove();
																		}
																		Click.CurrentStand.remove(p);
																		return;
																	}else {
																		ArmorStand six = SwordTypes.summonArmorStand(true, true, true, true, sixth);
																		ls.add(six);
																		Click.Stands.put(six, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
																		Click.CurrentStand.remove(p);
																		Click.CurrentStand.put(p, six);
																		SwordTypes.turnPlayerToLocation(p, sixth);
																		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

																			@Override
																			public void run() {
																				if(Click.inCombo.keySet() == null || !(Click.inCombo.containsKey(p))) {
																					for(ArmorStand a : ls) {
																						if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
																							Click.Stands.remove(a);
																						}
																						a.remove();
																					}
																					Click.CurrentStand.remove(p);
																					return;
																				}else {
																					ArmorStand seven = SwordTypes.summonArmorStand(true, true, true, true, seventh);
																					ls.add(seven);
																					Click.Stands.put(seven, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
																					Click.CurrentStand.remove(p);
																					Click.CurrentStand.put(p, seven);
																					SwordTypes.turnPlayerToLocation(p, seventh);
																					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

																						@Override
																						public void run() {
																							for(ArmorStand a : ls) {
																								if(Click.Stands.keySet() !=null && Click.Stands.containsKey(a)) {
																									Click.Stands.remove(a);
																								}
																								a.remove();
																							}
																							if(Click.inCombo !=null&& Click.inCombo.containsKey(p)) {
																								Click.inCombo.remove(p);
																							}
																							Click.CurrentStand.remove(p);
																							
																						}
																						
																					}, 5);
																				}
																				
																			}
																			
																		}, 5);
																	}
																	
																}
																
															}, 5);
														}
														
													}
													
												}, 5);
											}
											
										}
										
									}, 5);
								}
								
							}
							
						}, 5);
					}
					
				}
				
			}, 5);
		}
	}
	public void glidePlayerToLocation(Player player, Location location, double speed) {
	    Location from = player.getLocation();
	    Location to = location.clone().add(0.5, 0, 0.5);
	    Vector direction = to.subtract(from).toVector();
	    double distance = direction.length();
	    direction.normalize();
	    long ticks = (long) (distance / speed * 20);

	    Vector velocity = direction.multiply(speed / 20);
	    player.setVelocity(velocity);

	    new BukkitRunnable() {
	        private final long startTime = System.currentTimeMillis();
	        private final Vector initialVelocity = velocity.clone();

	        @Override
	        public void run() {
	            long elapsed = System.currentTimeMillis() - startTime;
	            if (elapsed > ticks * 50) {
	                player.setVelocity(new Vector());
	                return;
	            }
	            Vector currentVelocity = initialVelocity.clone();
	            currentVelocity.multiply(1 - (double) elapsed / (ticks * 50));
	            player.setVelocity(currentVelocity);
	        }
	    }.runTaskTimer(plugin, 0L, 1L);
	}
}
