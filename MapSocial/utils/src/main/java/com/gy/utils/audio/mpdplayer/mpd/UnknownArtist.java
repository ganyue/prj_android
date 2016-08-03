package com.gy.utils.audio.mpdplayer.mpd;

import android.os.Parcel;
import android.os.Parcelable;

public class UnknownArtist extends Artist {

    public static final UnknownArtist instance = new UnknownArtist();

    private UnknownArtist() {
        super("jmpdcomm_unknown_artist", 0);
    }

    protected UnknownArtist(Parcel in) {
        super(in);
    }

    @Override
    public String subText() {
        return "";
    }

    public static final Parcelable.Creator<UnknownArtist> CREATOR =
            new Parcelable.Creator<UnknownArtist>() {
                public UnknownArtist createFromParcel(Parcel in) {
                    return new UnknownArtist(in);
                }

                public UnknownArtist[] newArray(int size) {
                    return new UnknownArtist[size];
                }
            };

}
