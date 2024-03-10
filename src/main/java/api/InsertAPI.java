package api;

import db.Wifi;
import db.WifiService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InsertAPI {
    /*
        와이파이 개수 가져오기

     */
    public int wifiTotalcount(){
        String key="6e5054506a63796f36327262646654";
        String wifiUrl="http://openapi.seoul.go.kr:8088/" +
                key +
                "/json/TbPublicWifiInfo/1/1";
        String result="";
        int cnt=0;
        try {
            URL url=new URL(wifiUrl);

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bufferedReader.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject TbpublicWifiInfo=(JSONObject)jsonObject.get("TbPublicWifiInfo");
            Object totalCountObj=TbpublicWifiInfo.get("list_total_count");
            if(totalCountObj!=null) {
                cnt = Integer.parseInt(TbpublicWifiInfo.get("list_total_count").toString());
            }
            else{
                cnt=-1;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cnt;

    }
/*
     public int wifiLoad(){
        //list_total_count":23795;

        int count=0;
        int start=1;
        int end=1;
        int listTotalCount=wifiTotalcount();
        System.out.println(listTotalCount);
        String key="6e5054506a63796f36327262646654";
        String wifiUrl="http://openapi.seoul.go.kr:8088/" +
                key +
                "/json/TbPublicWifiInfo/";
        String result="";
        WifiDb wifiDb = new WifiDb();
        for(int i=0;i<=listTotalCount/1000; i++) {
            start=1+(1000*i);
            end=(i+1)*1000;

            try {
                URL url = new URL(wifiUrl+start+"/"+end);

                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                result = bufferedReader.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONObject TbpublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
                JSONArray jsonArray = (JSONArray) TbpublicWifiInfo.get("row");

                SimpleDateFormat cnstcDateFormat = new SimpleDateFormat("yyyy");

                for (Object obj : jsonArray) {
                    count++;

                    Wifi wifi = new Wifi();
                    JSONObject row = (JSONObject) obj;
                    wifi.setX_SWIFI_MGR_NO(row.get("X_SWIFI_MGR_NO").toString());
                    wifi.setX_SWIFI_WRDOFC(row.get("X_SWIFI_WRDOFC").toString());
                    wifi.setX_SWIFI_MAIN_NM(row.get("X_SWIFI_MAIN_NM").toString());
                    wifi.setX_SWIFI_ADRES1(row.get("X_SWIFI_ADRES1").toString());
                    wifi.setX_SWIFI_ADRES2(row.get("X_SWIFI_ADRES2").toString());
                    wifi.setX_SWIFI_INSTL_FLOOR(row.get("X_SWIFI_INSTL_FLOOR").toString());
                    wifi.setX_SWIFI_INSTL_TY(row.get("X_SWIFI_INSTL_TY").toString());
                    wifi.setX_SWIFI_INSTL_MBY(row.get("X_SWIFI_INSTL_MBY").toString());
                    wifi.setX_SWIFI_SVC_SE(row.get("X_SWIFI_SVC_SE").toString());
                    wifi.setX_SWIFI_CMCWR(row.get("X_SWIFI_CMCWR").toString());

                    Date cnstcYear = cnstcDateFormat.parse(row.get("X_SWIFI_CNSTC_YEAR").toString());
                    wifi.setX_SWIFI_CNSTC_YEAR(new java.sql.Date(cnstcYear.getTime()));

                    wifi.setX_SWIFI_INOUT_DOOR(row.get("X_SWIFI_INOUT_DOOR").toString());
                    wifi.setX_SWIFI_REMARS3(row.get("X_SWIFI_REMARS3").toString());

                    String latString = row.get("LAT").toString();
                    float latFloat = Float.parseFloat(latString);
                    wifi.setLAT(latFloat);

                    String lntString = row.get("LNT").toString();
                    float lntFloat = Float.parseFloat(lntString);
                    wifi.setLNT(lntFloat);
                    wifi.setWORK_DTTM();
                    wifiDb.InsertDb(wifi);
                }
                // 일정 개수의 데이터가 쌓이면 한 번에 DB에 삽입


            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return count;
    }

 */
    public int wifiLoadBatch(){
        int count = 0; //수행한 수
        int batchSize = 1000; // 한 번에 처리할 데이터 수
        int listTotalCount = wifiTotalcount();
        System.out.println("Total Count: " + listTotalCount);

        String key="6e5054506a63796f36327262646654";
        String wifiUrl="http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/";
        String result="";
        WifiService wifiService = new WifiService();

        // 일괄 처리할 데이터를 담을 리스트
        List<Wifi> wifiList = new ArrayList<>();

        try {
            for(int i=0; i<=listTotalCount/batchSize; i++) {
                int start = 1 + (batchSize * i);
                int end = (i + 1) * batchSize;
                URL url = new URL(wifiUrl + start + "/" + end);

                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                result = bufferedReader.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONObject TbpublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
                JSONArray jsonArray = (JSONArray) TbpublicWifiInfo.get("row");

                SimpleDateFormat cnstcDateFormat = new SimpleDateFormat("yyyy");

                for (Object obj : jsonArray) {
                    count++;

                    JSONObject row = (JSONObject) obj;
                    Wifi wifi = new Wifi();
                    wifi.setX_SWIFI_MGR_NO(row.get("X_SWIFI_MGR_NO").toString());
                    wifi.setX_SWIFI_WRDOFC(row.get("X_SWIFI_WRDOFC").toString());
                    wifi.setX_SWIFI_MAIN_NM(row.get("X_SWIFI_MAIN_NM").toString());
                    wifi.setX_SWIFI_ADRES1(row.get("X_SWIFI_ADRES1").toString());
                    wifi.setX_SWIFI_ADRES2(row.get("X_SWIFI_ADRES2").toString());
                    wifi.setX_SWIFI_INSTL_FLOOR(row.get("X_SWIFI_INSTL_FLOOR").toString());
                    wifi.setX_SWIFI_INSTL_TY(row.get("X_SWIFI_INSTL_TY").toString());
                    wifi.setX_SWIFI_INSTL_MBY(row.get("X_SWIFI_INSTL_MBY").toString());
                    wifi.setX_SWIFI_SVC_SE(row.get("X_SWIFI_SVC_SE").toString());
                    wifi.setX_SWIFI_CMCWR(row.get("X_SWIFI_CMCWR").toString());

                    Date cnstcYear = cnstcDateFormat.parse(row.get("X_SWIFI_CNSTC_YEAR").toString());
                    wifi.setX_SWIFI_CNSTC_YEAR(new java.sql.Date(cnstcYear.getTime()));

                    wifi.setX_SWIFI_INOUT_DOOR(row.get("X_SWIFI_INOUT_DOOR").toString());
                    wifi.setX_SWIFI_REMARS3(row.get("X_SWIFI_REMARS3").toString());

                    String latString = row.get("LAT").toString();
                    float latFloat = Float.parseFloat(latString);
                    wifi.setLAT(latFloat);

                    String lntString = row.get("LNT").toString();
                    float lntFloat = Float.parseFloat(lntString);
                    wifi.setLNT(lntFloat);
                    wifi.setWORK_DTTM();

                    wifiList.add(wifi);
                }

                // 일정 개수의 데이터가 쌓이면 한 번에 DB에 삽입
                if (wifiList.size() >= batchSize) {
                    wifiService.InsertBatch(wifiList);
                    wifiList.clear();
                }
            }

            // 남은 데이터가 있으면 마지막으로 한 번에 삽입
            if (!wifiList.isEmpty()) {
                wifiService.InsertBatch(wifiList);
                wifiList.clear();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return count;
    }


}

