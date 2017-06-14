package app.lottery.com.lotteryapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 2017/6/14.
 */

public class AdData implements Parcelable {
    private int flag;
    private String appUrl;
    private String picUrl;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.flag);
        dest.writeString(this.appUrl);
        dest.writeString(this.picUrl);
    }

    public AdData() {
    }

    protected AdData(Parcel in) {
        this.flag = in.readInt();
        this.appUrl = in.readString();
        this.picUrl = in.readString();
    }

    public static final Parcelable.Creator<AdData> CREATOR = new Parcelable.Creator<AdData>() {
        @Override
        public AdData createFromParcel(Parcel source) {
            return new AdData(source);
        }

        @Override
        public AdData[] newArray(int size) {
            return new AdData[size];
        }
    };
}
