package cn.txm.nsfw.complain.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "complain_reply", schema = "sshproject", catalog = "")
public class ComplainReplyEntity {
    private String replyId;
    private String replyer;
    private String replyDept;
    private Timestamp replyTime;
    private String replyContent;

    @Id
    @Column(name = "reply_id")
    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    @Basic
    @Column(name = "replyer")
    public String getReplyer() {
        return replyer;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    @Basic
    @Column(name = "reply_dept")
    public String getReplyDept() {
        return replyDept;
    }

    public void setReplyDept(String replyDept) {
        this.replyDept = replyDept;
    }

    @Basic
    @Column(name = "reply_time")
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    @Basic
    @Column(name = "reply_content")
    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplainReplyEntity that = (ComplainReplyEntity) o;
        return Objects.equals(replyId, that.replyId) &&
                Objects.equals(replyer, that.replyer) &&
                Objects.equals(replyDept, that.replyDept) &&
                Objects.equals(replyTime, that.replyTime) &&
                Objects.equals(replyContent, that.replyContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(replyId, replyer, replyDept, replyTime, replyContent);
    }
}