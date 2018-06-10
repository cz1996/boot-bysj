package com.bysj.controller;

import com.bysj.model.EquipmentAttribute;
import com.bysj.model.MyEquipment;
import com.bysj.service.EquipmentService;
import com.bysj.service.SensorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cccz1996 on 2018/5/2
 */
@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private SensorService sensorService;

//    添加设备
    @GetMapping("/addNewEquipment")
    public String addNewEqu(HttpServletRequest request, String currAddNewEquip){
        Gson gson = new Gson();
        MyEquipment myEquipment = gson.fromJson(currAddNewEquip,MyEquipment.class);

        HttpSession httpSession = request.getSession();
        int currId = (int) httpSession.getAttribute("id");

        boolean insertEqu = equipmentService.insertIntoEqu(myEquipment.getEquipmentName(),myEquipment.getEquipmentDescribe(),currId);
        if(insertEqu){
            List<MyEquipment> myEquipments = equipmentService.searchEquByInfo(myEquipment.getEquipmentName(),myEquipment.getEquipmentDescribe(),currId);
            if(myEquipments.size() == 1){
                int currEquId = myEquipments.get(0).getEquipmentId();
                ArrayList<EquipmentAttribute> equipmentAttributes = myEquipment.getEquipmentAttribute();
                for(int i = 0;i < equipmentAttributes.size();i++){
                    boolean insertSensor = sensorService.insertIntoSensor(equipmentAttributes.get(i).getAttrName(),equipmentAttributes.get(i).getAttrTiming(),
                            equipmentAttributes.get(i).getAttrCommon(),equipmentAttributes.get(i).getAttrFloat(),currEquId);
                    if(!insertSensor){
                        return "插入传感器失败";
                    }
                }
            }
            return "成功";
        }else{
            return "插入设备失败";
        }
    }

//    查找用户设备
    @GetMapping("/deviceList")
    public String getDeviceList(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        int currId = (int) httpSession.getAttribute("id");

        List<MyEquipment> myEquipments = equipmentService.searchEquByUserId(currId);
        if(myEquipments.size() == 0){
            return "-1";
        }else{
            for(int i = 0;i < myEquipments.size();i++){
                int currEquId = myEquipments.get(i).getEquipmentId();
                List<EquipmentAttribute> equipmentAttributes = sensorService.getSensorByEquId(currEquId);
                myEquipments.get(i).setEquipmentAttribute((ArrayList<EquipmentAttribute>) equipmentAttributes);
            }
            return myEquipments.toString();
        }
    }

    @GetMapping("/editEquINfoById")
    public String saveCurrChangeEquId(HttpServletRequest request, String currEquId){
        HttpSession httpSession = request.getSession();
        if(currEquId != "" && currEquId != null){
            int currId = Integer.parseInt(currEquId);
            httpSession.setAttribute("currChangeEquId",currId);
            return "1";
        }else{
            return "0";
        }
    }

    @GetMapping("/viewDeviceInfoById")
    public String saveCurrChangeDeviceId(HttpServletRequest request, String currDeviceId){
        HttpSession httpSession = request.getSession();
        if(currDeviceId != "" && currDeviceId != null){
            int currId = Integer.parseInt(currDeviceId);
            httpSession.setAttribute("currChangeDeviceId",currId);
            return "1";
        }else{
            return "0";
        }
    }

    @GetMapping("/getCurrDeviceInfoById")
    public String getCurrentDeviceInfoById(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        int currId = (int) httpSession.getAttribute("currChangeDeviceId");
        List<EquipmentAttribute> equipmentAttributes = sensorService.getSensorBySenId(currId);
        if(equipmentAttributes.size() > 0){
            String currEquStr = equipmentAttributes.get(0).toString();
            return currEquStr;
        }else{
            return "0";
        }
    }

//    获取当前修改的大设备-数据
    @GetMapping("/currEquipmentInfo")
    public String getCurrEquipmentInfo(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        int currId = (int) httpSession.getAttribute("currChangeEquId");
        List<MyEquipment> myEquipments = equipmentService.searchEquByEquipmentId(currId);
        if(myEquipments.size() > 0){
            int i = 0;
            int currEquId = myEquipments.get(i).getEquipmentId();
            List<EquipmentAttribute> equipmentAttributes = sensorService.getSensorByEquId(currEquId);
            myEquipments.get(i).setEquipmentAttribute((ArrayList<EquipmentAttribute>) equipmentAttributes);

            return myEquipments.toString();
        }else{
            return "0";
        }
    }

    @GetMapping("/changeEquipment")
    public String getCurrChangeEquipment(HttpServletRequest request,String currChangeEquId,String currChangeEquip){
        HttpSession httpSession = request.getSession();
        int currEquId = Integer.parseInt(currChangeEquId);
        boolean deleteCurrEqu = equipmentService.deleteEquipmentById(currEquId);
        if(deleteCurrEqu){
            Gson gson = new Gson();
            MyEquipment myEquipment = gson.fromJson(currChangeEquip,MyEquipment.class);

            int currId = (int) httpSession.getAttribute("id");

            boolean insertEqu = equipmentService.insertIntoEqu(myEquipment.getEquipmentName(),myEquipment.getEquipmentDescribe(),currId);
            if(insertEqu){
                List<MyEquipment> myEquipments = equipmentService.searchEquByInfo(myEquipment.getEquipmentName(),myEquipment.getEquipmentDescribe(),currId);
                if(myEquipments.size() == 1){
                    int currEquipmentId = myEquipments.get(0).getEquipmentId();
                    ArrayList<EquipmentAttribute> equipmentAttributes = myEquipment.getEquipmentAttribute();
                    for(int i = 0;i < equipmentAttributes.size();i++){
                        boolean insertSensor = sensorService.insertIntoSensor(equipmentAttributes.get(i).getAttrName(),equipmentAttributes.get(i).getAttrTiming(),
                                equipmentAttributes.get(i).getAttrCommon(),equipmentAttributes.get(i).getAttrFloat(),currEquipmentId);
                        if(!insertSensor){
                            return "修改失败";
                        }
                    }
                }
                return "修改成功";
            }else{
                return "修改设备失败";
            }
        }else{
            return "意外的错误，修改失败。请重新操作";
        }
    }

    @GetMapping("/warningSensorId")
    public String actionWarningSensorId(HttpServletRequest request,String currWarningSensorId){
        HttpSession httpSession = request.getSession();
        Date date = new Date();
        String currStr = "在"+date+",设备ID为"+currWarningSensorId+"请求重置操作";
        System.out.println(currStr);
        return "1";
    }

    @GetMapping("/deleteEquINfoById")
    public String deleteEquINfoInServerById(HttpServletRequest request,String currEquId){
        HttpSession httpSession = request.getSession();
        int currId = Integer.parseInt(currEquId);
        boolean del = equipmentService.deleteEquipmentById(currId);
        if(del){
            return "1";
        }else{
            return "0";
        }
    }
}
