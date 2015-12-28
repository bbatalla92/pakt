package btech.pakt;

public class SingleMessage{
    public String mTimeStamp;
    public String sender;
    public String message;

    public SingleMessage(){}

    public String getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(String mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMesage() {
        return message;
    }

    public void setMesage(String mesage) {
        this.message = mesage;
    }
}
