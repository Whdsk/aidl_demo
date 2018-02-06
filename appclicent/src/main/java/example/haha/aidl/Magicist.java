package example.haha.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovocabulary on 2018/2/5.
 */

public class Magicist implements Parcelable{
    private String name;
    private int age;
    private String part;

    public Magicist(){

    }

    protected Magicist(Parcel in) {
        name = in.readString();
        age = in.readInt();
        part = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(part);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Magicist> CREATOR = new Creator<Magicist>() {
        @Override
        public Magicist createFromParcel(Parcel in) {
            return new Magicist(in);
        }

        @Override
        public Magicist[] newArray(int size) {
            return new Magicist[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return "Magicist{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", part='" + part + '\'' +
                '}';
    }
}
