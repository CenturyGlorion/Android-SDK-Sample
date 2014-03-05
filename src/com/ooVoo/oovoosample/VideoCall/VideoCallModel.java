//
// VideoCallModel.java
// 
// Created by ooVoo on July 22, 2013
//
// © 2013 ooVoo, LLC.  Used under license. 
//

//package com.ooVoo.oovoosample.VideoCall;
//
//import java.util.HashMap;
//
//import com.oovoo.core.Utils.LogSdk;
//import android.view.SurfaceView;
//
//import com.ooVoo.oovoosample.Base.BaseModel;
//import com.ooVoo.oovoosample.Common.AlertsManager;
//import com.ooVoo.oovoosample.Common.Participant;
//import com.ooVoo.oovoosample.Common.ParticipantVideoSurface;
//import com.ooVoo.oovoosample.Common.ParticipantsManager;
//import com.ooVoo.oovoosample.Common.Utils;
//import com.oovoo.core.ConferenceCore;
//import com.oovoo.core.ConferenceCore.ConferenceCoreError;
//import com.oovoo.core.ConferenceCore.FrameSize;
//import com.oovoo.core.ConferenceCore.MediaDevice;
//import com.oovoo.core.ConferenceCore.MediaDeviceChangeType;
//import com.oovoo.core.ConferenceCore.MediaDeviceInfo;
//import com.oovoo.core.ConferenceCore.ConferenceCoreError;
//import com.oovoo.core.ConferenceCore.SessionStatus;
//import com.oovoo.core.ClientCore.VideoChannelPtr;
//
//// Video model entity
//public class VideoCallModel extends BaseModel {
//
//	private VideoCallActivity mPresenter;
//	private HashMap<Integer, ParticipantVideoSurface> _surfaces = new HashMap<Integer, ParticipantVideoSurface>();
//	private boolean mIsCameraMute = true;
//	private boolean mIsMicrophoneMute = true;
//	private boolean mAreSpeakersMute = true;
//
//	boolean _isMicMutingOrUnmuting = false;
//	boolean _isSpeakerMutingOrUnmuting = false;
//	boolean _isCamMutingOrUnmuting = false;
//
////	@Override
////	public void OnParticipantVideoPaused(String sParticipantId) {
////		AlertsManager.getInstance().addAlert(sParticipantId + " video Paused");
////		int participantViewId = ParticipantsManager.getInstance().getHolder()
////				.getViewIdByParticipant(sParticipantId);
////		if (participantViewId != -1) {
////			this._surfaces.get(participantViewId).showAvatar();
////			this._surfaces.get(participantViewId).setName("");
////		}
////		super.OnParticipantVideoPaused(sParticipantId);
////	}
////
////	@Override
////	public void OnParticipantVideoResumed(String sParticipantId) {
////		AlertsManager.getInstance().addAlert(sParticipantId + " video Resumed");
////		int participantViewId = ParticipantsManager.getInstance().getHolder()
////				.getViewIdByParticipant(sParticipantId);
////		if (participantViewId != -1) {
////			this._surfaces.get(participantViewId).showVideo();
////			this._surfaces.get(participantViewId).setName(sParticipantId);
////		}
////		super.OnParticipantVideoResumed(sParticipantId);
////	}
//
//	public VideoCallModel(VideoCallActivity presenter) {
//		super(presenter);
//		mPresenter = presenter;
//		registerForEvents();
//	}
//
////	// Start End of Call logic
////	public void endOfCall() {
////		// ConferenceCore.instance().turnMyVideoOff(); ???
////		ConferenceCore.instance().leaveSession(ConferenceCoreError.OK);
////		unregisterFromEvents();
////		mPresenter.onSessionLeft(ConferenceCoreError.OK);
////	}
////
////	// Start toggle camera mute logic
////	public void toggleCameraMute() {
////		setCameraMute(!mIsCameraMute);
////	}
////
////	// Start toggle microphone mute logic
////	public void toggleMicrophoneMute() {
////		setMicrophoneMute(!mIsMicrophoneMute);
////
////	}
////
////	// Start toggle speakers mute logic
////	public void toggleSpeakersMute() {
////		setSpeakersMuted(!mAreSpeakersMute);
////	}
////
////	// Start set camera mute logic
////	public void setCameraMute(boolean shouldMute) {
////		if (_isCamMutingOrUnmuting) {
////			return;
////		} // preventing multi DoS attack on mute/unmute
////		_isCamMutingOrUnmuting = true;
////		LogSdk.d(Utils.getOoVooTag(), "Setting camera mute to: " + shouldMute);
////		if (!shouldMute) {
////			try {
////				ConferenceCore.instance().turnMyVideoOn();
////			} catch (Exception e) {
////				LogSdk.e(Utils.getOoVooTag(),
////						"An Exception thrown while calling turnMyVideoOn", e);
////				AlertsManager.getInstance().addAlert(
////						"An error occured while calling turnMyVideoOn");
////				return;
////			}
////		} else {
////			ConferenceCore.instance().turnMyVideoOff();
////		}
////
////	}
////
////	@Override
////	public void OnMyVideoTurnedOff(ConferenceCoreError eErrorCode) {
////		LogSdk.d(Utils.getOoVooTag(), "received OnCameraUnmuted (JAVA");
////		mIsCameraMute = true;
////		mPresenter.onSetCameraMuted(mIsCameraMute);
////		_isCamMutingOrUnmuting = false;
////
////	}
////
////	@Override
////	public void OnMyVideoTurnedOn(ConferenceCoreError eErrorCode,
////			FrameSize frameSize) {
////		LogSdk.d(Utils.getOoVooTag(), "received OnCameraUnmuted (JAVA");
////		mIsCameraMute = false;
////		mPresenter.onSetCameraMuted(mIsCameraMute);
////		_isCamMutingOrUnmuting = false;
////	}
////
////	// Start toggle microphone mute logic
////	public void setMicrophoneMute(boolean shouldMute) {
////		if (_isMicMutingOrUnmuting)
////			return; // preventing multi DoS attack on mute/unmute
////		_isMicMutingOrUnmuting = true;
////		LogSdk.d(Utils.getOoVooTag(), "Setting microphone mute to: " + shouldMute);
////		ConferenceCoreError error = (shouldMute) ? ConferenceCore.instance()
////				.turnMicrophoneOff() : ConferenceCore.instance()
////				.turnMicrophoneOn();
////		LogSdk.d(Utils.getOoVooTag(), "Setting microphone mute to: " + shouldMute
////				+ " rc = " + error);
////
////	}
////
////	@Override
////	public void OnMicrophoneTurnedOff(ConferenceCoreError eErrorCode,
////			String sMediaDeviceId) {
////		mIsMicrophoneMute = true;
////		mPresenter.onSetMicrophoneMuted(mIsMicrophoneMute);
////		_isMicMutingOrUnmuting = false;
////	}
////
////	@Override
////	public void OnMicrophoneTurnedOn(ConferenceCoreError eErrorCode,
////			String sMediaDeviceId) {
////		mIsMicrophoneMute = false;
////		mPresenter.onSetMicrophoneMuted(mIsMicrophoneMute);
////		_isMicMutingOrUnmuting = false;
////	}
//
////	// Start toggle speakers mute logic
////	public void setSpeakersMute(boolean shouldMute) {
////		if (_isSpeakerMutingOrUnmuting) {
////			return;
////		} // preventing multi DoS attack on mute/unmute
////		_isSpeakerMutingOrUnmuting = true;
////		LogSdk.d(Utils.getOoVooTag(), "Setting speaker mute to: " + shouldMute);
////		ConferenceCoreError error = (shouldMute) ? ConferenceCore.instance()
////				.turnSpeakerOff() : ConferenceCore.instance().turnSpeakerOn();
////		LogSdk.d(Utils.getOoVooTag(), "Setting speaker mute to: " + shouldMute
////				+ " rc = " + error);
////	}
////
////	@Override
////	public void OnSpeakerTurnedOff(ConferenceCoreError eErrorCode,
////			String sMediaDeviceId) {
////		mAreSpeakersMute = true;
////		mPresenter.onSetSpeakersMuted(mAreSpeakersMute);
////		_isSpeakerMutingOrUnmuting = false;
////
////	}
////
////	@Override
////	public void OnSpeakerTurnedOn(ConferenceCoreError eErrorCode,
////			String sMediaDeviceId) {
////		mAreSpeakersMute = false;
////		mPresenter.onSetSpeakersMuted(mAreSpeakersMute);
////		_isSpeakerMutingOrUnmuting = false;
////
////	}
////
////	// SDK events
////
////	@Override
////	public void OnLeftSession(ConferenceCoreError eErrorCode) {
////		mPresenter.onSessionLeft(eErrorCode);
////
////	}
////
////	@Override
////	public void OnGetSessionStatus(SessionStatus oSessionStatus) {
////		// TODO Auto-generated method stub
////
////	}
//
////	@Override
////	public synchronized void OnParticipantJoinedSession(String sParticipantId,
////			String sOpaqueString) {
////		try {
////			LogSdk.d(Utils.getOoVooTag(),
////					"VideoCallModel.OnParticipantJoinedSession - adding participant to holder");
////			ParticipantsManager.getInstance().getHolder()
////					.addParticipant(sParticipantId, sOpaqueString);
////
////			if (ParticipantsManager.getInstance().getHolder()
////					.getNumOfVideosOn() < ParticipantsManager.getInstance().MAX_ACTIVE_PARTICIPANTS_IN_CALL) {
////				LogSdk.d(Utils.getOoVooTag(),
////						"VideoCallModel.OnParticipantJoinedSession - turning video on for "
////								+ sParticipantId);
////				if (ConferenceCoreError.OK == ConferenceCore.instance()
////						.turnParticipantVideoOn(sParticipantId)) {
////					ParticipantsManager.getInstance().getHolder()
////							.setVideoStateOn(sParticipantId, true);
////					int participantViewId = ParticipantsManager.getInstance()
////							.getHolder().getViewIdByParticipant(sParticipantId);
////					LogSdk.d(Utils.getOoVooTag(),
////							"VideoCallModel.OnParticipantJoinedSession - participantViewId = "
////									+ participantViewId);
////					if (participantViewId != -1) {
////						this._surfaces.get(participantViewId).showAvatar();
////						this._surfaces.get(participantViewId).setName(
////								sOpaqueString);
////					}
////
////				}
////
////			}
////		} catch (Exception ex) {
////			LogSdk.e(Utils.getOoVooTag(),
////					"VideoCallModel.OnParticipantJoinedSession - An Exception:",
////					ex);
////		}
////	}
////
////	@Override
////	public void OnParticipantLeftSession(String sParticipantId) {
////		int participantViewId = ParticipantsManager.getInstance().getHolder()
////				.getViewIdByParticipant(sParticipantId);
////		if (participantViewId != -1) {
////			this._surfaces.get(participantViewId).showEmptyCell();
////			this._surfaces.get(participantViewId).setName("");
////		}
////	}
////
////	@Override
////	public void OnParticipantVideoTurnedOff(ConferenceCoreError eErrorCode, String sParticipantId) {
////		AlertsManager.getInstance().addAlert(sParticipantId + " turned video Off");
////		int participantViewId = ParticipantsManager.getInstance().getHolder().getViewIdByParticipant(sParticipantId);
////		if (participantViewId != -1) {
////			this._surfaces.get(participantViewId).showAvatar();
////			this._surfaces.get(participantViewId).setName("");
////		}
////		ParticipantsManager.getInstance().getHolder()
////				.turnVideoOff(sParticipantId);
////
////	}
////
////	public void OnParticipantVideoTurnedOn(ConferenceCoreError eErrorCode,
////			String sParticipantId, FrameSize frameSize) {
////
////		try {
////			VideoChannelPtr in = ConferenceCore.instance().getVideoChannelForUser(sParticipantId);
////			if (ParticipantsManager.getInstance().getHolder().turnVideoOn(sParticipantId, in)) {
////				AlertsManager.getInstance().addAlert(sParticipantId + " turned video On");
////				int participantViewId = ParticipantsManager.getInstance()
////						.getHolder().getViewIdByParticipant(sParticipantId);
////				if (participantViewId != -1) {
////					Participant participant = ParticipantsManager.getInstance()
////							.getParticiapnt(sParticipantId);
////					this._surfaces.get(participantViewId).showVideo();
////					this._surfaces.get(participantViewId).setName(
////							participant.getDisplayName());
////				}
////			}
////
////			else
////				AlertsManager.getInstance().addAlert(
////						sParticipantId + " turned video Off");
////		} catch (Exception ex) {
////			LogSdk.e(Utils.getOoVooTag(),
////					"VideoCallModel.OnParticipantVideoTurnedOn - an Exception occured:"
////							+ ex.getMessage());
////		}
////	}
////
////	@Override
////	public void OnSessionError(ConferenceCoreError eErrorCode) {
////		LogSdk.d(Utils.getOoVooTag(), "OnSessionError - recieved error:" + eErrorCode);
////
////	}
////
////	@Override
////	public void OnBestMediaDevicesConfigurationDetected(
////			ConferenceCoreError eErrorCode, MediaDeviceInfo[] aDeviceArray) {}
////
////	@Override
////	public void OnGetMediaDeviceList(MediaDevice[] aDeviceArray) {}
////
////	@Override
////	public void OnCameraSelected(ConferenceCoreError eErrorCode,
////			String sPreviousDeviceId, String sNewDeviceId) {}
////
////	@Override
////	public void OnMicrophoneSelected(ConferenceCoreError eErrorCode,
////			String sPreviousDeviceId, String sNewDeviceId) {}
////
////	@Override
////	public void OnSpeakerSelected(ConferenceCoreError eErrorCode,
////			String sPreviousDeviceId, String sNewDeviceId) {}
////
////	@Override
////	public void OnBestMediaDevicesConfigurationDetected(
////			MediaDeviceInfo[] aDeviceArray) {}
////
////	@Override
////	public void OnMediaDeviceListChanged(MediaDeviceChangeType eChangeType,
////			MediaDeviceInfo oMediaDeviceInfo) {}
////
////	@Override
////	public void OnMicrophoneVolumneChanged(double dNewVolume) {}
////
////	@Override
////	public void OnSpeakerVolumneChanged(double dNewVolume) {}
////
////	public void onPause() {
////		LogSdk.d(Utils.getOoVooTag(), "VideoCallModel - onPause");
////		ParticipantsManager.getInstance().getHolder().Pause();
////	}
////
////	public synchronized void onResume() {
////		LogSdk.d(Utils.getOoVooTag(), "VideoCallModel - onResume");
////		try {
////			LogSdk.d(Utils.getOoVooTag(), "setting preview video surface");
////			SurfaceView view = mPresenter.getPreviewSurface();
////			LogSdk.e(Utils.getOoVooTag(), (view == null) ? "Surface is null" : "Surface is not null");
////			ConferenceCore.instance().setPreviewSurface(view);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////
////		ParticipantsManager.getInstance().getHolder().updateGLViews(mPresenter);
////
////		ParticipantsManager.getInstance().getHolder().Resume();
////		for (ParticipantVideoSurface surf : _surfaces.values()) {
////			surf.showEmptyCell();
////			surf.setName("");
////		}
////		LogSdk.d(Utils.getOoVooTag(),
////				"ParticipantsManager.getInstance().getParticipants().size() = "
////						+ ParticipantsManager.getInstance().getParticipants()
////								.size());
////		for (Participant participant : ParticipantsManager.getInstance()
////				.getParticipants()) {
////			int participantViewId = ParticipantsManager.getInstance()
////					.getHolder().getViewIdByParticipant(participant.getId());
////			LogSdk.d(Utils.getOoVooTag(), "participantViewId ="
////					+ participantViewId);
////			if (participantViewId != -1) {
////
////				this._surfaces.get(participantViewId).setName(
////						participant.getDisplayName());
////				this._surfaces.get(participantViewId).showAvatar();
////				if (participant.getIsVideoOn()) {
////					this._surfaces.get(participantViewId).showVideo();
////				}
////			}
////		}
////	}
////
////	public synchronized void Init(ParticipantVideoSurface[] mParticipantsVideoSurfaces) {
////		// Select devices
////		try {
////			_surfaces.clear();
////			for (int i = 0; i < mParticipantsVideoSurfaces.length; i++) {
////				ParticipantsManager.getInstance().getHolder().addGLView(
////								mParticipantsVideoSurfaces[i].surface.getId());
////				_surfaces.put(mParticipantsVideoSurfaces[i].surface.getId(),
////						mParticipantsVideoSurfaces[i]);
////
////			}
////
////			int numOfVidOn = 0;
////			for (Participant participant : ParticipantsManager.getInstance().getParticipants()) {
////				if (numOfVidOn < ParticipantsManager.MAX_ACTIVE_PARTICIPANTS_IN_CALL) {
////					LogSdk.d(Utils.getOoVooTag(),
////							"turning ParticipantVideoOn for "
////									+ participant.getDisplayName());
////					if (ConferenceCoreError.OK == ConferenceCore.instance()
////							.turnParticipantVideoOn(participant.getId())) {
////						LogSdk.d(Utils.getOoVooTag(), "setting VideoStateOn for "
////								+ participant.getDisplayName());
////						ParticipantsManager.getInstance().getHolder()
////								.setVideoStateOn(participant.getId(), true);
////					}
////				}
////			}
////
////			setCameraMute(false);
////			setMicrophoneMute(false);
////			setSpeakersMute(false);
////
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//
//	public void updateActivity(VideoCallActivity videoCallActivity) {
//		mPresenter = videoCallActivity;
//	}
//}
