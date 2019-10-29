package com.smiddle.core.dao.old;

import com.smiddle.core.model.old.OldCall;

import java.util.List;

public interface OldCallDAO {

    List<OldCall> getAllOldCalls(int maxResult, int startPosition);

    long getCount();
}
