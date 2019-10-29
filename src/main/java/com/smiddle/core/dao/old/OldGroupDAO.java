package com.smiddle.core.dao.old;

import com.smiddle.core.model.old.OldGroup;

import java.util.List;

public interface OldGroupDAO {

    List<OldGroup> getAllOldGroupsWithParentNull();
}
