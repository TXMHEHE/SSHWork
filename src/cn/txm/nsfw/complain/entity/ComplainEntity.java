package cn.txm.nsfw.complain.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "complain", schema = "sshproject")
public class ComplainEntity {
    private String compId;
    private String compCompany;
    private String compName;
    private String compMobile;
    private Byte isNm;
    private Timestamp compTime;
    private String compTitle;
    private String toCompName;
    private String toCompDept;
    private String compContent;
    private String state;
    private Set complainReplies=new HashSet(0);

    //状态
    public static String COMPLAIN_STATE_UNDONE="0";
    public static String COMPLAIN_STATE_DONE="1";
    public static String COMPLAIN_STATE_INVALID="2";
    public static Map<String,String> COMPLAIN_STATE_MAP;
    static {
        COMPLAIN_STATE_MAP=new HashMap<String,String>();
        COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_UNDONE,"代受理");
        COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_DONE,"已受理");
        COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_INVALID,"已失效");
    }

    public ComplainEntity() {
        super();
    }

    public ComplainEntity(String compId, String compCompany, String compName, String compMobile,
                          Byte isNm, Timestamp compTime, String compTitle, String toCompName,
                          String toCompDept, String compContent, String state, Set complainReplies) {
        this.compId = compId;
        this.compCompany = compCompany;
        this.compName = compName;
        this.compMobile = compMobile;
        this.isNm = isNm;
        this.compTime = compTime;
        this.compTitle = compTitle;
        this.toCompName = toCompName;
        this.toCompDept = toCompDept;
        this.compContent = compContent;
        this.state = state;
        this.complainReplies = complainReplies;
    }

    @Id
    @Column(name = "comp_id")
    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    @Basic
    @Column(name = "comp_company")
    public String getCompCompany() {
        return compCompany;
    }

    public void setCompCompany(String compCompany) {
        this.compCompany = compCompany;
    }

    @Basic
    @Column(name = "comp_name")
    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    @Basic
    @Column(name = "comp_mobile")
    public String getCompMobile() {
        return compMobile;
    }

    public void setCompMobile(String compMobile) {
        this.compMobile = compMobile;
    }

    @Basic
    @Column(name = "is_NM")
    public Byte getIsNm() {
        return isNm;
    }

    public void setIsNm(Byte isNm) {
        this.isNm = isNm;
    }

    @Basic
    @Column(name = "comp_time")
    public Timestamp getCompTime() {
        return compTime;
    }

    public void setCompTime(Timestamp compTime) {
        this.compTime = compTime;
    }

    @Basic
    @Column(name = "comp_title")
    public String getCompTitle() {
        return compTitle;
    }

    public void setCompTitle(String compTitle) {
        this.compTitle = compTitle;
    }

    @Basic
    @Column(name = "to_comp_name")
    public String getToCompName() {
        return toCompName;
    }

    public void setToCompName(String toCompName) {
        this.toCompName = toCompName;
    }

    @Basic
    @Column(name = "to_comp_dept")
    public String getToCompDept() {
        return toCompDept;
    }

    public void setToCompDept(String toCompDept) {
        this.toCompDept = toCompDept;
    }

    @Basic
    @Column(name = "comp_content")
    public String getCompContent() {
        return compContent;
    }

    public void setCompContent(String compContent) {
        this.compContent = compContent;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplainEntity that = (ComplainEntity) o;
        return Objects.equals(compId, that.compId) &&
                Objects.equals(compCompany, that.compCompany) &&
                Objects.equals(compName, that.compName) &&
                Objects.equals(compMobile, that.compMobile) &&
                Objects.equals(isNm, that.isNm) &&
                Objects.equals(compTime, that.compTime) &&
                Objects.equals(compTitle, that.compTitle) &&
                Objects.equals(toCompName, that.toCompName) &&
                Objects.equals(toCompDept, that.toCompDept) &&
                Objects.equals(compContent, that.compContent) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(compId, compCompany, compName, compMobile, isNm, compTime, compTitle, toCompName, toCompDept, compContent, state);
    }
}