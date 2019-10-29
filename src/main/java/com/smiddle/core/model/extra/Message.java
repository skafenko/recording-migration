//package com.smiddle.core.model.extra;
//
//import com.fasterxml.jackson.annotation.JsonGetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.smiddle.common.model.KeyEntity;
//import com.smiddle.core.model.Call;
//import lombok.*;
//import org.hibernate.annotations.Immutable;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "REC_MESSAGES", indexes = {
//        @Index(name = "IDX_REC_MESSAGES_CALL_ID", columnList = "CALL_ID")
//})
//@Immutable
//@NoArgsConstructor
//@Getter
//@Setter(AccessLevel.PROTECTED)
//@ToString(of = {"payload", "date", "sender"})
//@Builder @AllArgsConstructor
//public class Message extends KeyEntity {
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "CALL_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MESSAGE_CALL"))
//    private Call call;
//    @Column(name = "CREATED_DATE", updatable = false)
//    private Date date;
//    @Column(name = "PAYLOAD", nullable = false, length = 2048)
//    private String payload;
//    @ManyToOne
//    @JoinColumn(name = "SENDER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MESSAGE_CALL_PARTICIPANT"))
//    private CallParticipant sender;
//
//    public Message(Call call, String payload, CallParticipant sender) {
//        this.call = call;
//        this.payload = payload;
//        this.sender = sender;
//    }
//
//    @Override
//    @JsonGetter("messageId")
//    public String getRid() {
//        return super.getRid();
//    }
//
//    @PrePersist
//    protected void onCreate() {
//        date = new Date();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        date = new Date();
//    }
//
//}
//
