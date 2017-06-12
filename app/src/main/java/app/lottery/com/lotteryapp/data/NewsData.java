package app.lottery.com.lotteryapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 2017/6/12.
 */

public class NewsData implements Parcelable {
    private String url;
    private String title;
    private String time;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.time);
    }

    public NewsData() {
    }

    protected NewsData(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<NewsData> CREATOR = new Parcelable.Creator<NewsData>() {
        @Override
        public NewsData createFromParcel(Parcel source) {
            return new NewsData(source);
        }

        @Override
        public NewsData[] newArray(int size) {
            return new NewsData[size];
        }
    };
}
