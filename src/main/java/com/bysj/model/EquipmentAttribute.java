package com.bysj.model;

public class EquipmentAttribute {
    private int attrId;
    private String attrName;
    private Double attrCommon;
    private Double attrFloat;
    private int attrTiming;
    private Double attrCurrent;
    private int sensorEquId;

    public EquipmentAttribute(){

    }

    public EquipmentAttribute(String attrName,Double attrCommon,
                              Double attrFloat,int attrTiming,Double attrCurrent){
        this.attrName = attrName;
        this.attrCommon = attrCommon;
        this.attrFloat = attrFloat;
        this.attrTiming = attrTiming;
        this.attrCurrent = attrCurrent;
    }

    public EquipmentAttribute(String attrName,Double attrCommon,
                              Double attrFloat,int attrTiming){
        this.attrName = attrName;
        this.attrCommon = attrCommon;
        this.attrFloat = attrFloat;
        this.attrTiming = attrTiming;
        this.attrCurrent = attrCommon;
    }

    public String toString(){
        String strId = "{'attrId':'" + this.attrId + "',";
        String strName = "'attrName':'" + this.attrName + "',";
        String strCommon = "'attrCommon':'" + this.attrCommon + "',";
        String strFloat = "'attrFloat':'" + this.attrFloat + "',";
        String strTiming = "'attrTiming':'" + this.attrTiming + "'}";
        return strId+strName+strCommon+strFloat+strTiming;
    }

    public int getSensorEquId() {
        return sensorEquId;
    }

    public void setSensorEquId(int sensorEquId) {
        this.sensorEquId = sensorEquId;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Double getAttrCommon() {
        return attrCommon;
    }

    public void setAttrCommon(Double attrCommon) {
        this.attrCommon = attrCommon;
    }

    public Double getAttrFloat() {
        return attrFloat;
    }

    public void setAttrFloat(Double attrFloat) {
        this.attrFloat = attrFloat;
    }

    public int getAttrTiming() {
        return attrTiming;
    }

    public void setAttrTiming(int attrTiming) {
        this.attrTiming = attrTiming;
    }

    public Double getAttrCurrent() {
        return attrCurrent;
    }

    public void setAttrCurrent(Double attrCurrent) {
        this.attrCurrent = attrCurrent;
    }
}
