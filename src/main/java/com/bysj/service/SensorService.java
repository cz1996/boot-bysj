package com.bysj.service;

import com.bysj.model.EquipmentAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cccz1996 on 2018/5/2
 */
@Service
public class SensorService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertIntoSensor(String attrName,int attrTiming,
                                    Double attrCommon,Double attrFloat,int attrEquId){
        String sql = "insert into sensor (SENSOR_NAME, SENSOR_TIMING,SENSOR_COMMON," +
                "SENSOR_FLOAT,SENSOR_EQU_ID) values (?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,new Object[]{attrName,attrTiming,attrCommon,attrFloat,attrEquId});

        if(insert > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<EquipmentAttribute> getSensorByEquId(int attrEquId){
        String sql = "select * from sensor where sensor.SENSOR_EQU_ID = ?";
        List<EquipmentAttribute> equipmentAttributes = jdbcTemplate.query(sql,new Object[]{attrEquId},new SensorService.MyRowMapper());
        return  equipmentAttributes;
    }

    public List<EquipmentAttribute> getSensorBySenId(int sensorId){
        String sql = "select * from sensor where sensor.SENSOR_ID = ?";
        List<EquipmentAttribute> equipmentAttributes = jdbcTemplate.query(sql,new Object[]{sensorId},new SensorService.MyRowMapper());
        return  equipmentAttributes;
    }


    class MyRowMapper implements RowMapper<EquipmentAttribute> {
        @Override
        public EquipmentAttribute mapRow(ResultSet rs, int num) throws SQLException {
            EquipmentAttribute equipmentAttribute = new EquipmentAttribute();
            equipmentAttribute.setAttrId(rs.getInt("SENSOR_ID"));
            equipmentAttribute.setAttrName(rs.getString("SENSOR_NAME"));
            equipmentAttribute.setAttrTiming(rs.getInt("SENSOR_TIMING"));
            equipmentAttribute.setAttrCommon(rs.getDouble("SENSOR_COMMON"));
            equipmentAttribute.setAttrFloat(rs.getDouble("SENSOR_FLOAT"));
            equipmentAttribute.setSensorEquId(rs.getInt("SENSOR_EQU_ID"));
            return equipmentAttribute;
        }
    }
}
