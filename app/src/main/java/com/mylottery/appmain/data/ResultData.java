package com.mylottery.appmain.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 2017/6/13.
 */

public class ResultData implements Parcelable {
    private String balls;
    private String date;
    private String num;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.balls);
        dest.writeString(this.date);
        dest.writeString(this.num);
    }

    public ResultData() {
    }

    protected ResultData(Parcel in) {
        this.balls = in.readString();
        this.date = in.readString();
        this.num = in.readString();
    }

    public static final Parcelable.Creator<ResultData> CREATOR = new Parcelable.Creator<ResultData>() {
        @Override
        public ResultData createFromParcel(Parcel source) {
            return new ResultData(source);
        }

        @Override
        public ResultData[] newArray(int size) {
            return new ResultData[size];
        }
    };
}
