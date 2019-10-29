package com.smiddle.core.dao.old;

import com.smiddle.core.model.old.OldCall;

import java.util.Date;
import java.util.List;

public interface OldCallDAO {

    List<OldCall> getAllOldCalls(Date startDate, Date finishDate, int maxResult, int startPosition);

    long getCount(Date startDate, Date finishDate);
}
