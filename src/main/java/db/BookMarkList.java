package db;

import java.sql.Timestamp;


/*
    북마크 리스트
 */
public class BookMarkList {
    private int id;
    private int bookmarkId;
    private String X_SWIFI_MGR_NO;
    private Timestamp register_time;
    private String bookmarkName;
    private String wifiName;

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getX_SWIFI_MGR_NO() {
        return X_SWIFI_MGR_NO;
    }

    public void setX_SWIFI_MGR_NO(String x_SWIFI_MGR_NO) {
        X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }
}
