package pl.north93.nmsutils.tracker;

import net.minecraft.server.v1_16_R3.EntityPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import pl.north93.nmsutils.tracker.event.EntityTrackedPlayerEvent;
import pl.north93.nmsutils.tracker.event.EntityUnTrackedPlayerEvent;

class EntityTrackerCallback1_16R3 implements IEntityTrackerCallback<EntityPlayer>
{
    private final Entity entity;

    public EntityTrackerCallback1_16R3(final Entity entity)
    {
        this.entity = entity;
    }

    @Override
    public void entityTrackedByPlayer(final EntityPlayer entityPlayer)
    {
        final Player player = entityPlayer.getBukkitEntity();
        Bukkit.getPluginManager().callEvent(new EntityTrackedPlayerEvent(player, this.entity));
    }

    @Override
    public void entityUnTrackedByPlayer(final EntityPlayer entityPlayer)
    {
        final Player player = entityPlayer.getBukkitEntity();
        Bukkit.getPluginManager().callEvent(new EntityUnTrackedPlayerEvent(player, this.entity));
    }
}
