LOCAL_PATH := $(call my-dir)

#KCAL
include $(CLEAR_VARS)
LOCAL_MODULE := kcal
LOCAL_SRC_FILES := $(LOCAL_MODULE).apk
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_SUFFIX := .apk
LOCAL_MODULE_CLASS := APPS
LOCAL_CERTIFICATE := PRESIGNED
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
