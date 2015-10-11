package com.notas.cristhian.notas;

import android.os.Parcel;
import android.os.Parcelable;

public class ListFilesToStorage implements Parcelable {

    private String tituloPista;
    private Boolean marcado;

    public ListFilesToStorage(String pista, Boolean marcado) {
        this.tituloPista = pista;
        this.marcado = marcado;
    }

    public ListFilesToStorage(Parcel in){
        this.tituloPista= in.readString();
        this.marcado = in.readInt() == 1 ? true:false;
    }

    public Boolean getMarcado() {
        return marcado;
    }

    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }

    public String getTituloPista() {
        return tituloPista;
    }

    public void setTituloPista(String tituloPista) {
        this.tituloPista = tituloPista;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTituloPista());
        dest.writeInt(getMarcado() ? 1 : 0);
    }

    public static final Parcelable.Creator<ListFilesToStorage> CREATOR = new Parcelable.Creator<ListFilesToStorage>() {
        public ListFilesToStorage createFromParcel(Parcel in) {
            return new ListFilesToStorage(in);
        }

        public ListFilesToStorage[] newArray(int size) {
            return new ListFilesToStorage[size];
        }
    };
}
