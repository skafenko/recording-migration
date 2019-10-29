//package com.smiddle.core.model.extra;
//
//import com.fasterxml.jackson.annotation.JsonGetter;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonSetter;
//import com.fasterxml.jackson.annotation.JsonView;
//import com.smiddle.common.model.KeyEntity;
//import com.smiddle.core.model.Call;
//import com.smiddle.rec.model.calls.json.Views;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties({"id", "call"})
//@Table(name = "REC_CALL_KEYWORDS", indexes = {
//        @Index(name = "IDX_REC_CALL_KEYWORDS_RID", columnList = "RID")
//})
//public class Keyword extends KeyEntity {
//    @Column(name = "KEYWORD_VALUE", nullable = false)
//    private String value;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CALL_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_KEYWORD_CALL"))
//    private Call call;
//
//    @Override
//    @JsonView(Views.API.class)
//    @JsonGetter("keywordId")
//    public String getRid() {
//        return this.rid;
//    }
//
//    @Override
//    @JsonSetter("keywordId")
//    public void setRid(String rid) {
//        this.rid = rid;
//    }
//}
