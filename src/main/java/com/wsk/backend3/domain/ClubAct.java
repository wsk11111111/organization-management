package com.wsk.backend3.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 社团活动表
 * @TableName club_act
 */
@TableName(value ="club_act")
public class ClubAct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private String act;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     */
    public Integer getClubId() {
        return clubId;
    }

    /**
     * 
     */
    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    /**
     * 
     */
    public String getAct() {
        return act;
    }

    /**
     * 
     */
    public void setAct(String act) {
        this.act = act;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClubAct other = (ClubAct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getAct() == null ? other.getAct() == null : this.getAct().equals(other.getAct()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getAct() == null) ? 0 : getAct().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clubId=").append(clubId);
        sb.append(", act=").append(act);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}