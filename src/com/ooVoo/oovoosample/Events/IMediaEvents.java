//
// IMediaEvents.java
// 
// Created by ooVoo on July 22, 2013
//
// © 2013 ooVoo, LLC.  Used under license. 
//
package com.ooVoo.oovoosample.Events;

import com.oovoo.core.ConferenceCore.MediaDevice;
import com.oovoo.core.ConferenceCore.MediaDeviceInfo;
import com.oovoo.core.IConferenceCore.ConferenceCoreError;
import com.oovoo.core.IConferenceCore.ConferenceCoreError;


public interface IMediaEvents 
{
	// Media
	void OnBestMediaDevicesConfigurationDetected(ConferenceCoreError eErrorCode, MediaDeviceInfo[] aDeviceArray);
	void OnGetMediaDeviceList(MediaDevice[] aDeviceArray);
	void OnCameraSelected(ConferenceCoreError eErrorCode, String sPreviousDeviceId, String sNewDeviceId);
	void OnMicrophoneSelected(ConferenceCoreError eErrorCode, String sPreviousDeviceId, String sNewDeviceId);
	void OnSpeakerSelected(ConferenceCoreError eErrorCode, String sPreviousDeviceId, String sNewDeviceId);
	void OnCameraMuted(ConferenceCoreError eErrorCode, String sMediaDeviceId);
	void OnMicrophoneMuted(ConferenceCoreError eErrorCode, String sMediaDeviceId);
	void OnSpeakerMuted(ConferenceCoreError eErrorCode, String sMediaDeviceId);
	void OnCameraUnmuted(ConferenceCoreError eErrorCode, String sMediaDeviceId);
	void OnMicrophoneUnmuted(ConferenceCoreError eErrorCode, String sMediaDeviceId);
	void OnSpeakerUnmuted(ConferenceCoreError eErrorCode, String sMediaDeviceId);
	void OnBestMediaDevicesConfigurationDetected(MediaDeviceInfo[] aDeviceArray);		
	void OnMicrophoneVolumneChanged(double dNewVolume);
	void OnSpeakerVolumneChanged(double dNewVolume);
}
