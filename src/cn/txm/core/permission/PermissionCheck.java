package cn.txm.core.permission;

import cn.txm.nsfw.user.entity.User;

public interface PermissionCheck {

    //判断用户是否有code对应的权限
    public boolean isAccessible(User user, String string);
}
