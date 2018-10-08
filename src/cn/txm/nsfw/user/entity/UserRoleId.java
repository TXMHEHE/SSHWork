package cn.txm.nsfw.user.entity;

import cn.txm.nsfw.role.entity.Role;
import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {

    private Role role;
    private String userId;

    public UserRoleId() {
        super();
    }

    public UserRoleId(Role role, String userId) {
        super();
        this.role = role;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(role, that.role) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(role, userId);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}