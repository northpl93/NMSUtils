package pl.north93.nmsutils.tracker;

interface IEntityTrackerCallback<PLAYER>
{
    void entityTrackedByPlayer(PLAYER player);

    void entityUnTrackedByPlayer(PLAYER player);
}
