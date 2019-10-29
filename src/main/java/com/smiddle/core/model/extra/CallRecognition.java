//package com.smiddle.core.model.extra;
//
//import com.fasterxml.jackson.annotation.JsonGetter;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonSetter;
//import com.fasterxml.jackson.annotation.JsonView;
//import com.smiddle.common.model.KeyEntity;
//import com.smiddle.core.model.Call;
//import com.smiddle.rec.model.calls.json.Views;
//import lombok.Generated;
//import org.hibernate.annotations.Immutable;
//
//import javax.persistence.*;
//
//@Entity
//@Table(
//        name = "REC_CALL_RECOGNITIONS",
//        indexes = {@Index(
//                name = "IDX_CALL_RECOGNITIONS_CALL_ID",
//                columnList = "CALL_ID"
//        ), @Index(
//                name = "IDX_RECOGNITIONS_CALL_PART",
//                columnList = "CALL_PART_ID"
//        )}
//)
//@JsonIgnoreProperties({"id", "call", "callParts"})
//@Immutable
//public class CallRecognition extends KeyEntity {
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "CALL_ID",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "FK_CALL_RECOGNITIONS_REC_CALLS"
//            )
//    )
//    private Call call;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "CALL_PART_ID",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "FK_CALL_RECOGNITION_CALL_PART"
//            )
//    )
//    private CallParts callParts;
//    @JsonIgnoreProperties({"duration", "codec", "finishState", "callParticipantId"})
//    @ManyToOne
//    @JoinColumn(
//            name = "PARTICIPANT_ID",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "FK_RECOGNITIONS_PARTICIPANTS"
//            )
//    )
//    private CallParticipant participant;
//    @Column(
//            name = "RECOGNITION_TEXT",
//            nullable = false
//    )
//    private String text;
//    @Column(
//            name = "START_TIME"
//    )
//    private double startTime;
//    @Column(
//            name = "DURATION"
//    )
//    private double duration;
//    @Column(
//            name = "CONFIDENCE"
//    )
//    private Double confidence;
//
//    @JsonView({Views.API.class})
//    @JsonGetter("recognitionId")
//    public String getRid() {
//        return this.rid;
//    }
//
//    @JsonSetter("recognitionId")
//    public void setRid(String rid) {
//        this.rid = rid;
//    }
//
//    @Generated
//    public static CallRecognition.CallRecognitionBuilder builder() {
//        return new CallRecognition.CallRecognitionBuilder();
//    }
//
//    @Generated
//    public CallRecognition() {
//    }
//
//    @Generated
//    public CallRecognition(Call call, CallParts callParts, CallParticipant participant, String text, double startTime, double duration, Double confidence) {
//        this.call = call;
//        this.callParts = callParts;
//        this.participant = participant;
//        this.text = text;
//        this.startTime = startTime;
//        this.duration = duration;
//        this.confidence = confidence;
//    }
//
//    @Generated
//    public Call getCall() {
//        return this.call;
//    }
//
//    @Generated
//    public CallParts getCallParts() {
//        return this.callParts;
//    }
//
//    @Generated
//    public CallParticipant getParticipant() {
//        return this.participant;
//    }
//
//    @Generated
//    public String getText() {
//        return this.text;
//    }
//
//    @Generated
//    public double getStartTime() {
//        return this.startTime;
//    }
//
//    @Generated
//    public double getDuration() {
//        return this.duration;
//    }
//
//    @Generated
//    public Double getConfidence() {
//        return this.confidence;
//    }
//
//    @Generated
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        } else if (!(o instanceof CallRecognition)) {
//            return false;
//        } else {
//            CallRecognition other = (CallRecognition)o;
//            if (!other.canEqual(this)) {
//                return false;
//            } else {
//                label79: {
//                    Object this$call = this.getCall();
//                    Object other$call = other.getCall();
//                    if (this$call == null) {
//                        if (other$call == null) {
//                            break label79;
//                        }
//                    } else if (this$call.equals(other$call)) {
//                        break label79;
//                    }
//
//                    return false;
//                }
//
//                Object this$callParts = this.getCallParts();
//                Object other$callParts = other.getCallParts();
//                if (this$callParts == null) {
//                    if (other$callParts != null) {
//                        return false;
//                    }
//                } else if (!this$callParts.equals(other$callParts)) {
//                    return false;
//                }
//
//                Object this$participant = this.getParticipant();
//                Object other$participant = other.getParticipant();
//                if (this$participant == null) {
//                    if (other$participant != null) {
//                        return false;
//                    }
//                } else if (!this$participant.equals(other$participant)) {
//                    return false;
//                }
//
//                label58: {
//                    Object this$text = this.getText();
//                    Object other$text = other.getText();
//                    if (this$text == null) {
//                        if (other$text == null) {
//                            break label58;
//                        }
//                    } else if (this$text.equals(other$text)) {
//                        break label58;
//                    }
//
//                    return false;
//                }
//
//                if (Double.compare(this.getStartTime(), other.getStartTime()) != 0) {
//                    return false;
//                } else if (Double.compare(this.getDuration(), other.getDuration()) != 0) {
//                    return false;
//                } else {
//                    Object this$confidence = this.getConfidence();
//                    Object other$confidence = other.getConfidence();
//                    if (this$confidence == null) {
//                        if (other$confidence != null) {
//                            return false;
//                        }
//                    } else if (!this$confidence.equals(other$confidence)) {
//                        return false;
//                    }
//
//                    return true;
//                }
//            }
//        }
//    }
//
//    @Generated
//    protected boolean canEqual(Object other) {
//        return other instanceof CallRecognition;
//    }
//
//    @Generated
//    public int hashCode() {
//        int result = 1;
//        Object $call = this.getCall();
//        result = result * 59 + ($call == null ? 43 : $call.hashCode());
//        Object $callParts = this.getCallParts();
//        result = result * 59 + ($callParts == null ? 43 : $callParts.hashCode());
//        Object $participant = this.getParticipant();
//        result = result * 59 + ($participant == null ? 43 : $participant.hashCode());
//        Object $text = this.getText();
//        result = result * 59 + ($text == null ? 43 : $text.hashCode());
//        long $startTime = Double.doubleToLongBits(this.getStartTime());
//        result = result * 59 + (int)($startTime >>> 32 ^ $startTime);
//        long $duration = Double.doubleToLongBits(this.getDuration());
//        result = result * 59 + (int)($duration >>> 32 ^ $duration);
//        Object $confidence = this.getConfidence();
//        result = result * 59 + ($confidence == null ? 43 : $confidence.hashCode());
//        return result;
//    }
//
//    @Generated
//    public static class CallRecognitionBuilder {
//        @Generated
//        private Call call;
//        @Generated
//        private CallParts callParts;
//        @Generated
//        private CallParticipant participant;
//        @Generated
//        private String text;
//        @Generated
//        private double startTime;
//        @Generated
//        private double duration;
//        @Generated
//        private Double confidence;
//
//        @Generated
//        CallRecognitionBuilder() {
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder call(Call call) {
//            this.call = call;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder callParts(CallParts callParts) {
//            this.callParts = callParts;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder participant(CallParticipant participant) {
//            this.participant = participant;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder text(String text) {
//            this.text = text;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder startTime(double startTime) {
//            this.startTime = startTime;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder duration(double duration) {
//            this.duration = duration;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition.CallRecognitionBuilder confidence(Double confidence) {
//            this.confidence = confidence;
//            return this;
//        }
//
//        @Generated
//        public CallRecognition build() {
//            return new CallRecognition(this.call, this.callParts, this.participant, this.text, this.startTime, this.duration, this.confidence);
//        }
//
//        @Generated
//        public String toString() {
//            return "CallRecognition.CallRecognitionBuilder(call=" + this.call + ", callParts=" + this.callParts + ", participant=" + this.participant + ", text=" + this.text + ", startTime=" + this.startTime + ", duration=" + this.duration + ", confidence=" + this.confidence + ")";
//        }
//    }
//}
