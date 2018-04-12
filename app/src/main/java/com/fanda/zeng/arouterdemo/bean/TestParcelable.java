package com.fanda.zeng.arouterdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des 测试Parcelable对象
 */

public class TestParcelable implements Parcelable {
    public String name ;
    public String sex ;

    public TestParcelable(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.sex);
    }

    public TestParcelable() {
    }

    protected TestParcelable(Parcel in) {
        this.name = in.readString();
        this.sex = in.readString();
    }

    public static final Parcelable.Creator<TestParcelable> CREATOR = new Parcelable.Creator<TestParcelable>() {
        public TestParcelable createFromParcel(Parcel source) {
            return new TestParcelable(source);
        }

        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };
}
