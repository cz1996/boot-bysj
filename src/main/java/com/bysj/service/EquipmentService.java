package com.bysj.service;

import com.bysj.model.MyEquipment;
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
public class EquipmentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertIntoEqu(String equName,String equInfo,int UserId){
        String sql = "insert into equipment (EQU_NAME,EQU_INFO,EQU_USER_ID) values (?,?,?)";
        int insert = jdbcTemplate.update(sql,new Object[]{equName,equInfo,UserId});

        if(insert > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<MyEquipment> searchEquByInfo(String equName,String equInfo,int UserId){
        String sql = "select * from equipment where equipment.EQU_NAME = ? and equipment.EQU_INFO = ? and equipment.EQU_USER_ID = ?";
        List<MyEquipment> myEquipments= jdbcTemplate.query(sql,new Object[]{equName,equInfo,UserId},new EquipmentService.MyRowMapper());
        return myEquipments;
    }

    public List<MyEquipment> searchEquByUserId(int UserId){
        String sql = "select * from equipment where equipment.EQU_USER_ID = ?";
        List<MyEquipment> myEquipments= jdbcTemplate.query(sql,new Object[]{UserId},new EquipmentService.MyRowMapper());
        return myEquipments;
    }

    public List<MyEquipment> searchEquByEquipmentId(int equipmentId){
        String sql = "select * from equipment where equipment.EQU_ID = ?";
        List<MyEquipment> myEquipments= jdbcTemplate.query(sql,new Object[]{equipmentId},new EquipmentService.MyRowMapper());
        return myEquipments;
    }

    public Boolean deleteEquipmentById(int equId){
        String sql = "delete from equipment where equipment.EQU_ID = ?";
        int deleteEqu = jdbcTemplate.update(sql,new Object[]{equId});
        if(deleteEqu > 0){
            return true;
        }else{
            return false;
        }
    }


    class MyRowMapper implements RowMapper<MyEquipment> {
        @Override
        public MyEquipment mapRow(ResultSet rs, int num) throws SQLException {
            MyEquipment myEquipment = new MyEquipment();
            myEquipment.setEquipmentId(rs.getInt("EQU_ID"));
            myEquipment.setEquipmentName(rs.getString("EQU_NAME"));
            myEquipment.setEquipmentDescribe(rs.getString("EQU_INFO"));
            myEquipment.setEquipmentUserId(rs.getInt("EQU_USER_ID"));
            return myEquipment;
        }
    }
}
