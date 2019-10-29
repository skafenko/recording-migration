//package com.smiddle.core.model.extra;
//
//import com.fasterxml.jackson.annotation.JsonGetter;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonSetter;
//import com.fasterxml.jackson.annotation.JsonView;
//import com.smiddle.common.model.KeyEntity;
//import com.smiddle.core.model.Call;
//import com.smiddle.core.model.User;
//import com.smiddle.rec.model.calls.json.Views;
//import lombok.Generated;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@JsonIgnoreProperties({"id", "call"})
//@Table(
//        name = "REC_CALL_COMMENTS",
//        indexes = {@Index(
//                name = "IDX_REC_CALL_COMMENTS_RID",
//                columnList = "RID"
//        )}
//)
//public class CallComment extends KeyEntity {
//    @Column(
//            name = "COMMENT_TEXT",
//            nullable = false
//    )
//    private String text;
//    @Column(
//            name = "CREATED_DATE",
//            nullable = false
//    )
//    private Date createdDate;
//    @JsonIgnoreProperties({"agentId", "login", "loginAD", "password", "email", "phone", "fax", "locale", "dateCreate", "scMode", "userGroupRoles", "unmappedCalls", "enabled", "deleted", "groups", "roles", "domain", "hibernateLazyInitializer", "handler"})
//    @ManyToOne
//    @JoinColumn(
//            name = "USER_ID",
//            foreignKey = @ForeignKey(
//                    name = "FK_CALL_COMMENTS_ADM_USERS"
//            )
//    )
//    private User user;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "CALL_ID",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "FK_CALL_COMMENT_CALL"
//            )
//    )
//    private Call call;
//
//    @JsonView({Views.API.class})
//    @JsonGetter("commentId")
//    public String getRid() {
//        return this.rid;
//    }
//
//    @JsonSetter("commentId")
//    public void setRid(String rid) {
//        this.rid = rid;
//    }
//
//    @Generated
//    public String getText() {
//        return this.text;
//    }
//
//    @Generated
//    public Date getCreatedDate() {
//        return this.createdDate;
//    }
//
//    @Generated
//    public User getUser() {
//        return this.user;
//    }
//
//    @Generated
//    public Call getCall() {
//        return this.call;
//    }
//
//    @Generated
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    @Generated
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    @Generated
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @Generated
//    public void setCall(Call call) {
//        this.call = call;
//    }
//
//    @Generated
//    public String toString() {
//        return "CallComment(text=" + this.getText() + ", createdDate=" + this.getCreatedDate() + ", user=" + this.getUser() + ", call=" + this.getCall() + ")";
//    }
//
//    @Generated
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof CallComment)) {
//            return false;
//        } else {
//            CallComment other = (CallComment)o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else if (!super.equals(o)) {
//                return false;
//            } else {
//                label61: {
//                    Object this$text = this.getText();
//                    Object other$text = other.getText();
//                    if (this$text == null) {
//                        if (other$text == null) {
//                            break label61;
//                        }
//                    } else if (this$text.equals(other$text)) {
//                        break label61;
//                    }
//
//                    return false;
//                }
//
//                label54: {
//                    Object this$createdDate = this.getCreatedDate();
//                    Object other$createdDate = other.getCreatedDate();
//                    if (this$createdDate == null) {
//                        if (other$createdDate == null) {
//                            break label54;
//                        }
//                    } else if (this$createdDate.equals(other$createdDate)) {
//                        break label54;
//                    }
//
//                    return false;
//                }
//
//                Object this$user = this.getUser();
//                Object other$user = other.getUser();
//                if (this$user == null) {
//                    if (other$user != null) {
//                        return false;
//                    }
//                } else if (!this$user.equals(other$user)) {
//                    return false;
//                }
//
//                Object this$call = this.getCall();
//                Object other$call = other.getCall();
//                if (this$call == null) {
//                    if (other$call != null) {
//                        return false;
//                    }
//                } else if (!this$call.equals(other$call)) {
//                    return false;
//                }
//
//                return true;
//            }
//        }
//    }
//
//    @Generated
//    protected boolean canEqual(Object other) {
//        return other instanceof CallComment;
//    }
//
//    @Generated
//    public int hashCode() {
//        int result = super.hashCode();
//        Object $text = this.getText();
//        result = result * 59 + ($text == null ? 43 : $text.hashCode());
//        Object $createdDate = this.getCreatedDate();
//        result = result * 59 + ($createdDate == null ? 43 : $createdDate.hashCode());
//        Object $user = this.getUser();
//        result = result * 59 + ($user == null ? 43 : $user.hashCode());
//        Object $call = this.getCall();
//        result = result * 59 + ($call == null ? 43 : $call.hashCode());
//        return result;
//    }
//
//    @Generated
//    public CallComment() {
//    }
//}
