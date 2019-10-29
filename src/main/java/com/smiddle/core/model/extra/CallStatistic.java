package com.smiddle.core.model.extra;

import com.smiddle.common.model.BaseEntity;
import com.smiddle.core.model.Call;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "REC_CALL_STATISTIC")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallStatistic extends BaseEntity {
    @Column(name = "HOLDS_COUNT")
    private int holdsCount;
    @Column(name = "HOLDS_DURATION")
    private int holdsDuration;
    @Column(name = "PARTS_COUNT")
    private int partsCount;
    @Column(name = "AGENT_MESS_COUNT")
    private int agentMessCount;
    @Column(name = "CUSTOMER_MESS_COUNT")
    private int customerMessCount;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "callStatistic")
    private Call call;
}
