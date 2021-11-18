package com.sh1penfire.cri_christmas.item;

import com.sh1penfire.cri_christmas.entity.projectile.HurtSnowballEntity;
import net.minecraft.item.SnowballItem;
import net.minecraft.util.hit.EntityHitResult;

public class HurtSnowballItem extends SnowballItem {
    public boolean HANDLE_HIT = false;

    public HurtSnowballItem(Settings settings) {
        super(settings);
    }

    public void onEntityHit(EntityHitResult entityHitResult, HurtSnowballEntity snowballEntity){
        //left empty for you to interpret what you could do, like fill the Endless Void that is depression and Anxiety, or do something better with your time than looking at this.
        //note that THIS WON'T WORK AAAA
    }
}
