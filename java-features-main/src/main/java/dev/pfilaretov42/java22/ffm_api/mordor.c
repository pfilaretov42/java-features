#include <jni.h>
#include <string.h>
#include "dev_pfilaretov42_java22_ffm_api_ElvenScrollBefore.h"

JNIEXPORT jint JNICALL Java_dev_pfilaretov42_java22_ffm_1api_ElvenScrollBefore_countRunes(JNIEnv *env, jobject obj, jstring ancientText) {
    // Convert Elvish runes to C-compatible form
    const char *runes = (*env)->GetStringUTFChars(env, ancientText, NULL);
    if (runes == NULL) return 0; // The magic faltered

    // Count the runes with ancient wisdom
    int length = (int)strlen(runes);

    // Release the spellbound memory
    (*env)->ReleaseStringUTFChars(env, ancientText, runes);
    return length;
}
