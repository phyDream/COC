package com.cdlixin.coc.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.cdlixin.coc.model.greendaogen.DaoSession;
import com.cdlixin.coc.model.greendaogen.OrgEntityDao;
import com.cdlixin.coc.model.greendaogen.UserEntityDao;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
@Entity
public class UserEntity {

    /**
     * orgs : [{"c_id":"3e43d1d976b9430c8de0975394ff7007","c_type":0,"c_name":"商会之家开发商会（测试）","c_logo_url":"http://mobile.shzj168.cn/mobile//UploadFiles/2017-05/186363069923061573599889672.png","u_type":3}]
     * s_token : 16f0f0590d65496987e97dcd75c4ef9c
     * u_id : f7dc203317fe4103bb2b34565143b2e7
     * u_name : 蒲弘宇
     * u_logo_url : http://mobile.shzj168.cn/UserLogos/f7dc203317fe4103bb2b34565143b2e7.jpeg
     * u_post : 程序员
     * u_male : true
     */
    @Id(autoincrement = true)
    private Long tableId;
    private String s_token;
    private String u_id;
    private String u_name;
    private String u_logo_url;
    private String u_post;
    private boolean u_male;
    @ToMany(referencedJoinProperty = "userId")
    private List<OrgEntity> orgs;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1814575071)
    private transient UserEntityDao myDao;
    @Generated(hash = 1998444173)
    public UserEntity(Long tableId, String s_token, String u_id, String u_name, String u_logo_url, String u_post, boolean u_male) {
        this.tableId = tableId;
        this.s_token = s_token;
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_logo_url = u_logo_url;
        this.u_post = u_post;
        this.u_male = u_male;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public Long getTableId() {
        return this.tableId;
    }
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
    public String getS_token() {
        return this.s_token;
    }
    public void setS_token(String s_token) {
        this.s_token = s_token;
    }
    public String getU_id() {
        return this.u_id;
    }
    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
    public String getU_name() {
        return this.u_name;
    }
    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
    public String getU_logo_url() {
        return this.u_logo_url;
    }
    public void setU_logo_url(String u_logo_url) {
        this.u_logo_url = u_logo_url;
    }
    public String getU_post() {
        return this.u_post;
    }
    public void setU_post(String u_post) {
        this.u_post = u_post;
    }
    public boolean getU_male() {
        return this.u_male;
    }
    public void setU_male(boolean u_male) {
        this.u_male = u_male;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1096745164)
    public List<OrgEntity> getOrgs() {
        if (orgs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OrgEntityDao targetDao = daoSession.getOrgEntityDao();
            List<OrgEntity> orgsNew = targetDao._queryUserEntity_Orgs(tableId);
            synchronized (this) {
                if (orgs == null) {
                    orgs = orgsNew;
                }
            }
        }
        return orgs;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 426929984)
    public synchronized void resetOrgs() {
        orgs = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 287999134)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserEntityDao() : null;
    }

    public void setOrgs(List<OrgEntity> orgs) {
        this.orgs = orgs;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "tableId=" + tableId +
                ", s_token='" + s_token + '\'' +
                ", u_id='" + u_id + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_logo_url='" + u_logo_url + '\'' +
                ", u_post='" + u_post + '\'' +
                ", u_male=" + u_male +
                ", orgs=" + orgs +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                '}';
    }
}
