package com.xxl.job.core.registry.impl;

import com.xxl.job.core.registry.RegistHelper;
import com.xxl.job.core.util.DBUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Created by xuxueli on 16/9/30.
 */
public class DbRegistHelper implements RegistHelper {

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int registry(String registGroup, String registryKey, String registryValue) {
        String updateSql = "UPDATE XXL_JOB_QRTZ_TRIGGER_REGISTRY SET update_time = sysdate WHERE registry_group = ? AND registry_key = ? AND registry_value = ?";
        int ret = DBUtil.update(dataSource, updateSql, new Object[]{registGroup, registryKey, registryValue});
        if (ret<1) {
            String querySeqSql = " select SEQ_XXL_JOB_QRTZ_TRIGGER_REGIS.nextval as seq from dual ";
            String insertSql = "INSERT INTO XXL_JOB_QRTZ_TRIGGER_REGISTRY(id , registry_group , registry_key , registry_value, update_time) VALUES(? , ? , ? , ?, sysdate)";
            List<Map<String, Object>> seq = DBUtil.query(dataSource, querySeqSql, new Object[]{});
            BigDecimal id = null;
            if(seq!=null&&!seq.isEmpty()){
                id = (BigDecimal) seq.get(0).get("SEQ");
            }
            ret = DBUtil.update(dataSource, insertSql, new Object[]{id,registGroup, registryKey, registryValue});
        }
        return ret;
    }
}
