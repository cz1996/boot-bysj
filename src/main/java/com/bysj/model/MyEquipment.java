package com.bysj.model;

import java.util.ArrayList;

public class MyEquipment {
    private int equipmentId;

    private String equipmentName;

    private String equipmentDescribe;

    private int equipmentUserId;

    private ArrayList<EquipmentAttribute> equipmentAttribute;

    public MyEquipment(){

    }

    public MyEquipment(String equipmentName,String equipmentDescribe,
                       ArrayList<EquipmentAttribute> equipmentAttribute){
        this.equipmentName = equipmentName;
        this.equipmentDescribe = equipmentDescribe;
        this.equipmentAttribute = equipmentAttribute;
    }

    public String toString(){
        String strId = "{'equipmentId':'"+this.equipmentId + "',";
        String strName = "'equipmentName':'"+this.equipmentName + "',";
        String strDescribe = "'equipmentDescribe':'"+this.equipmentDescribe + "',";
        String strEquAttr = "'equipmentAttribute':[";
        if(this.equipmentAttribute.size()> 0){
            for(int i = 0;i < this.equipmentAttribute.size();i++){
                if(i < this.equipmentAttribute.size()-1){
                    strEquAttr += this.equipmentAttribute.get(i).toString()+",";
                }else{
                    strEquAttr+=this.equipmentAttribute.get(i).toString();
                }
            }
        }
        strEquAttr += "]}";
        return strId+strName+strDescribe+strEquAttr;
    }

    public int getEquipmentUserId() {
        return equipmentUserId;
    }

    public void setEquipmentUserId(int equipmentUserId) {
        this.equipmentUserId = equipmentUserId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescribe() {
        return equipmentDescribe;
    }

    public void setEquipmentDescribe(String equipmentDescribe) {
        this.equipmentDescribe = equipmentDescribe;
    }

    public ArrayList<EquipmentAttribute> getEquipmentAttribute() {
        return equipmentAttribute;
    }

    public void setEquipmentAttribute(ArrayList<EquipmentAttribute> equipmentAttribute) {
        this.equipmentAttribute = equipmentAttribute;
    }
}
