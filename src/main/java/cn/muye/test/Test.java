//package cn.muye.test;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Ray.Fu on 2017/6/9.
// */
//public class Test {
//
//    public static void main(String[] args) {
//        List<AgvMenu> list = Lists.newArrayList();
//        list = initMenus(list);
//        List<String> listRights = Lists.newArrayList();
//        listRights.add("dashboard:i");
//        listRights.add("dashboard:u");
//        listRights.add("dashboard:r");
//        listRights.add("dashboard:d");
//
//        listRights.add("dispatch:i");
//        listRights.add("dispatch:u");
//        listRights.add("dispatch:r");
//        listRights.add("dispatch:d");
//        list = initRights(listRights, list);
//        String listJson = JSON.toJSONString(list);
//        System.out.println(listJson);
//    }
//
//    private static List<AgvMenu> initMenus(List<AgvMenu> list) {
//        AgvMenu agvMenu1 = new AgvMenu();
//        agvMenu1.setKey("dashboard");
//        agvMenu1.setName("控制台");
//        AgvMenu agvMenu2 = new AgvMenu();
//        agvMenu2.setKey("dispatch");
//        agvMenu2.setName("调度任务");
//        AgvMenu agvMenu3 = new AgvMenu();
//        agvMenu3.setKey("assets");
//        agvMenu3.setName("资产管理");
//        list.add(agvMenu1);
//        list.add(agvMenu2);
//        list.add(agvMenu3);
//        return list;
//    }
//
//    private static List<AgvMenu> initRights(List<String> list, List<AgvMenu> listObj) {
//        if (listObj != null) {
//            for (AgvMenu agvMenu : listObj) {
//                for (String str : list) {
//                    String menu = str.substring(0, str.indexOf(":"));
//                    String option = str.substring(str.indexOf(":")+1, str.length());
//                    if (agvMenu.getKey().equals(menu)) {
//                        agvMenu.setRight(agvMenu.getRight() != null ? agvMenu.getRight() + "," + option : "" + "," + option );
//                    }
//                }
//
//            }
//        }
//        return listObj;
//    }
//}
