package pl.north93.nmsutils.tracker;

import javax.annotation.Nonnull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

class EntityTrackerMap<PLAYER> implements Map<PLAYER, Boolean>
{
    private final IEntityTrackerCallback<PLAYER> listener;
    private final Map<PLAYER, Boolean> wrappedMap;

    public EntityTrackerMap(final IEntityTrackerCallback<PLAYER> listener, final Map<PLAYER, Boolean> wrappedMap)
    {
        this.listener = listener;
        this.wrappedMap = wrappedMap;
    }

    @Override
    public int size()
    {
        return this.wrappedMap.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.wrappedMap.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key)
    {
        return this.wrappedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value)
    {
        return this.wrappedMap.containsValue(value);
    }

    @Override
    public Boolean get(final Object key)
    {
        return this.wrappedMap.get(key);
    }

    @Override
    public Boolean put(final PLAYER key, final Boolean value)
    {
        this.listener.entityTrackedByPlayer(key);
        return this.wrappedMap.put(key, value);
    }

    @Override
    public Boolean remove(final Object key)
    {
        return this.wrappedMap.remove(key);
    }

    @Override
    public void putAll(final Map<? extends PLAYER, ? extends Boolean> m)
    {
        this.wrappedMap.putAll(m);
    }

    @Override
    public void clear()
    {
        this.wrappedMap.clear();
    }

    @Nonnull
    @Override
    public Set<PLAYER> keySet()
    {
        return new EntityTrackerSet<>(this.listener, this.wrappedMap.keySet());
    }

    @Nonnull
    @Override
    public Collection<Boolean> values()
    {
        return this.wrappedMap.values();
    }

    @Nonnull
    @Override
    public Set<Entry<PLAYER, Boolean>> entrySet()
    {
        return this.wrappedMap.entrySet();
    }

    @Override
    public String toString()
    {
        return this.wrappedMap.toString();
    }
}
