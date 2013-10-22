package fr.yamakaky.KillThemAll;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) {
		if (e.getEntityType() != EntityType.PLAYER || e.getDamager().getType() != EntityType.PLAYER)
			return;
		
		Player dead = (Player) e.getEntity();
		
		if (dead.getHealth() < e.getDamage())
			return;
		
		Player killer = (Player) e.getDamager();
		
		transferXP(dead, killer);
	}

	private void transferXP(Player dead, Player killer) {
		killer.giveExp(3);
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		e.setDroppedExp(0);
	}
}
