package com.gy.utils.audio.mpdplayer.event;


import com.gy.utils.audio.mpdplayer.mpd.MPDStatus;

/*
 * Abstract implementation of the StatusChange. Just for convenience.
 */
public abstract class AbstractStatusChangeListener implements StatusChangeListener {

	@Override
	public void volumeChanged(MPDStatus mpdStatus, int oldVolume) {
	}

	@Override
	public void playlistChanged(MPDStatus mpdStatus, int oldPlaylistVersion) {
	}

	@Override
	public void trackChanged(MPDStatus mpdStatus, int oldTrack) {
	}

	@Override
	public void stateChanged(MPDStatus mpdStatus, String oldState) {
	}

	@Override
	public void repeatChanged(boolean repeating) {
	}

	@Override
	public void randomChanged(boolean random) {
	}

	@Override
	public void connectionStateChanged(boolean connected, boolean connectionLost) {
	}

	@Override
	public void libraryStateChanged(boolean updating) {
	}

}
