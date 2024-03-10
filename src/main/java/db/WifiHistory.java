package db;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
    검색 기록
 */
public class WifiHistory {
    private int id;
    private float LAT; //	Y좌표
    private float LNT; //	X좌표
    private Timestamp WORK_DTTM; //	작업일자

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WifiHistory(){}
    public WifiHistory(String LAT, String LNT) {
        this.LAT = Float.parseFloat(LAT);
        this.LNT = Float.parseFloat(LNT);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        String formattedDateTime = currentDateTime.format(formatter);
        this.WORK_DTTM = Timestamp.valueOf(formattedDateTime);
    }

    public float getLAT() {
        return LAT;
    }

    public void setLAT(float LAT) {
        this.LAT = LAT;
    }

    public float getLNT() {
        return LNT;
    }

    public void setLNT(float LNT) {
        this.LNT = LNT;
    }

    public Timestamp getWORK_DTTM() {
        return WORK_DTTM;
    }

    public void setWORK_DTTM(Timestamp WORK_DTTM) {
        this.WORK_DTTM = WORK_DTTM;
    }
}
