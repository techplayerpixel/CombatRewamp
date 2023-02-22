package me.GodOfDespair.CombatRewamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Click implements Listener{
	
	Main plugin;
	
	public Click(Main plugin) {
		this.plugin = plugin;
	}
	
	public static Map<Player,String> inCombo = new HashMap<Player,String>();
	public static Map<Player,Integer> CounterSlash = new HashMap<Player,Integer>();
	public static Map<Player,Integer> Counterid = new HashMap<Player,Integer>();
	public static Map<ArmorStand,Double> Stands = new HashMap<ArmorStand,Double>();
	public static Map<Player,ArmorStand> CurrentStand = new HashMap<Player,ArmorStand>();
	
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Action a = e.getAction();
		Player p = e.getPlayer();
		if(a == Action.LEFT_CLICK_AIR && isWeapon(e.getPlayer().getInventory().getItemInMainHand()) == true) {
			if(inCombo == null || !(inCombo.containsKey(e.getPlayer()))) {
				
				inCombo.put(p, "left");
				
				Stab(p);
				e.setCancelled(true);
			}
		}
		if(a == Action.RIGHT_CLICK_AIR && isWeapon(e.getPlayer().getInventory().getItemInMainHand()) == true) {
			if(inCombo == null || !(inCombo.containsKey(e.getPlayer()))) {
				inCombo.put(p, "right");
				Slash(p);
				e.setCancelled(true);
			}
		}
	}
	
	public boolean isWeapon(ItemStack i) {
		
		if(i.getType().equals(Material.WOODEN_SWORD)||
				i.getType().equals(Material.STONE_SWORD)||
				i.getType().equals(Material.IRON_SWORD)||
				i.getType().equals(Material.GOLDEN_SWORD)||
				i.getType().equals(Material.DIAMOND_SWORD)||
				i.getType().equals(Material.NETHERITE_SWORD)) {
			return true;
		}else {
			return false;
		}
		
	}
	public void Stab(Player p) {
		Location fa = SwordTypes.getLocalCoord(-0.5,1,1.5,p.getLocation());
		ArmorStand as = SwordTypes.summonArmorStand(true,true,true,true,fa);
		ArrayList<ArmorStand> la = new ArrayList<ArmorStand>();
		la.add(as);
		Stands.put(as, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
		CurrentStand.put(p, as);
		Location tloc = SwordTypes.getLocalCoord(0, 0, 1.5, as.getLocation());
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
					for(ArmorStand a : la) {
						if(Stands.keySet() !=null && Stands.containsKey(a)) {
							Stands.remove(a);
						}
						a.remove();
					}
					CurrentStand.remove(p);
					return;
				}else {
					
					ArmorStand t = SwordTypes.summonArmorStand(true, true, true, true, tloc);
					la.add(t);
					Stands.put(t, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
					CurrentStand.remove(p);
					CurrentStand.put(p, t);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
								for(ArmorStand a : la) {
									if(Stands.keySet() !=null && Stands.containsKey(a)) {
										Stands.remove(a);
									}
									a.remove();
								}
								CurrentStand.remove(p);
								return;
							}else {
								Location thirdloc = SwordTypes.getLocalCoord(0, 0, 1.5, tloc);
								ArmorStand h = SwordTypes.summonArmorStand(true, true, true, true, thirdloc);
								la.add(h);
								Stands.put(h, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
								CurrentStand.put(p, as);
								CurrentStand.put(p, h);
								//four
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

									@Override
									public void run() {
										if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
											for(ArmorStand a : la) {
												if(Stands.keySet() !=null && Stands.containsKey(a)) {
													Stands.remove(a);
												}
												a.remove();
											}
											CurrentStand.remove(p);
											return;
										}else {
											Location FourthLoc = SwordTypes.getLocalCoord(0, 0, 1.5, thirdloc);
											ArmorStand f = SwordTypes.summonArmorStand(true, true, true, true, FourthLoc);
											la.add(f);
											Stands.put(f, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
											CurrentStand.put(p, as);
											CurrentStand.put(p, f);
											Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

												@Override
												public void run() {
													if(inCombo !=null&& inCombo.containsKey(p)) {
														inCombo.remove(p);
													}
													for(ArmorStand at : la) {
														if(Stands !=null && Stands.containsKey(at)) {
															Stands.remove(at);
														}
														at.remove();
													}
													CurrentStand.remove(p);
													
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
	public void Slash(Player p) {
		Location first = SwordTypes.getLocalCoord(-1, 1, 1.5, p.getEyeLocation());
		Location second = SwordTypes.getLocalCoord(-0.5, 0.5, 1.7, p.getEyeLocation());
		Location third = SwordTypes.getLocalCoord(0, 0, 2, p.getEyeLocation());
		Location fifth = SwordTypes.getLocalCoord(1, -1, 1.5, p.getEyeLocation());
		Location fourth = SwordTypes.getLocalCoord(0.5, -0.5, 1.7, p.getEyeLocation());
		List<ArmorStand> ls = new ArrayList<ArmorStand>();
		ArmorStand one = SwordTypes.summonArmorStand(true, true, true, true, first);
		ls.add(one);
		Stands.put(one, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
		CurrentStand.put(p, one);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
					for(ArmorStand a : ls) {
						if(Stands.keySet() !=null && Stands.containsKey(a)) {
							Stands.remove(a);
						}
						a.remove();
					}
					CurrentStand.remove(p);
					return;
				}else {
					ArmorStand two = SwordTypes.summonArmorStand(true, true, true, true, second);
					ls.add(two);
					Stands.put(two, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
					CurrentStand.remove(p);
					CurrentStand.put(p, two);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
								for(ArmorStand a : ls) {
									if(Stands.keySet() !=null && Stands.containsKey(a)) {
										Stands.remove(a);
									}
									a.remove();
								}
								CurrentStand.remove(p);
								return;
							}else {
								ArmorStand three = SwordTypes.summonArmorStand(true, true, true, true, third);
								ls.add(three);
								Stands.put(three, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
								CurrentStand.remove(p);
								CurrentStand.put(p, three);
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
									@Override
									public void run() {
										if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
											for(ArmorStand a : ls) {
												if(Stands.keySet() !=null && Stands.containsKey(a)) {
													Stands.remove(a);
												}
												a.remove();
											}
											CurrentStand.remove(p);
											return;
										}else {
											ArmorStand four = SwordTypes.summonArmorStand(true, true, true, true, fourth);
											ls.add(four);
											Stands.put(four, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
											CurrentStand.remove(p);
											CurrentStand.put(p, four);
											Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
												@Override
												public void run() {
													if(inCombo.keySet() == null || !(inCombo.containsKey(p))) {
														for(ArmorStand a : ls) {
															if(Stands.keySet() !=null && Stands.containsKey(a)) {
																Stands.remove(a);
															}
															a.remove();
														}
														CurrentStand.remove(p);
														return;
													}else {
														ArmorStand five = SwordTypes.summonArmorStand(true, true, true, true, fifth);
														ls.add(five);
														Stands.put(five, p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
														CurrentStand.remove(p);
														CurrentStand.put(p, five);
														Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

															@Override
															public void run() {
																for(ArmorStand a : ls) {
																	if(Stands.keySet() !=null && Stands.containsKey(a)) {
																		Stands.remove(a);
																	}
																	a.remove();
																}
																if(inCombo !=null&& inCombo.containsKey(p)) {
																	inCombo.remove(p);
																}
																CurrentStand.remove(p);
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
