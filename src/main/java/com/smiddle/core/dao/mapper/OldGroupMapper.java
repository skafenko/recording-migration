package com.smiddle.core.dao.mapper;

import com.smiddle.core.model.old.OldGroup;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OldGroupMapper implements RowMapper<OldGroup> {

    @Override
    public OldGroup mapRow(ResultSet resultSet, int i) throws SQLException {
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        OldGroup oldGroup = new OldGroup(
//                ((BigDecimal)resultSet.getObject(1)).longValue(),
//                (String) resultSet.getObject(3),
//                ((BigDecimal)resultSet.getObject(2)).longValue(),
//                ((BigDecimal)resultSet.getObject(4)).longValue());
        return OldGroup.builder().build();
    }
}
