package pl.north93.nmsutils.tracker;

import javax.annotation.Nonnull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

class EntityTrackerSet<PLAYER> implements Set<PLAYER>
{
    private final IEntityTrackerCallback<PLAYER> listener;
    private final Set<PLAYER> wrappedSet;

    public EntityTrackerSet(final IEntityTrackerCallback<PLAYER> listener, final Set<PLAYER> wrappedSet)
    {
        this.listener = listener;
        this.wrappedSet = wrappedSet;
    }

    @Override
    public int size()
    {
        return this.wrappedSet.size();
    }

    @Override
    public boolean isEmpty()
    {
        return this.wrappedSet.isEmpty();
    }

    @Override
    public boolean contains(final Object o)
    {
        return this.wrappedSet.contains(o);
    }

    @Nonnull
    @Override
    public Iterator<PLAYER> iterator()
    {
        return this.wrappedSet.iterator();
    }

    @Nonnull
    @Override
    public Object[] toArray()
    {
        return this.wrappedSet.toArray();
    }

    @Nonnull
    @Override
    public <T> T[] toArray(final @Nonnull T[] a)
    {
        return this.wrappedSet.toArray(a);
    }

    @Override
    public boolean add(final PLAYER player)
    {
        return this.wrappedSet.add(player);
    }

    @Override
    public boolean remove(final Object o)
    {
        if (this.wrappedSet.remove(o))
        {
            this.listener.entityUnTrackedByPlayer((PLAYER) o);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(final @Nonnull Collection<?> c)
    {
        return this.wrappedSet.containsAll(c);
    }

    @Override
    public boolean addAll(final @Nonnull Collection<? extends PLAYER> c)
    {
        return this.wrappedSet.addAll(c);
    }

    @Override
    public boolean retainAll(final @Nonnull Collection<?> c)
    {
        return this.wrappedSet.retainAll(c);
    }

    @Override
    public boolean removeAll(final @Nonnull Collection<?> c)
    {
        return this.wrappedSet.removeAll(c);
    }

    @Override
    public void clear()
    {
        this.wrappedSet.clear();
    }

    @Override
    public String toString()
    {
        return this.wrappedSet.toString();
    }
}
