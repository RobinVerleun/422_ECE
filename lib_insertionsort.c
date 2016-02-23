#include <stdio.h>
#include <jni.h>
#include <stdlib.h>
#include "InsertionSort.h"

/* C source for the native binary search mehtod */
/* Scott Dick, Summer 2004 */

JNIEXPORT jintArray JNICALL Java_InsertionSort_insertionsort
  (JNIEnv *env, jobject object, jintArray data){
  jsize len;
  jint memAccess, *values;
  jintArray result;
  jboolean *is_copy;

  memAccess = 0;
  memAccess += 1;

  len = (*env)->GetArrayLength(env, data);
  values = (jint *) (*env)->GetIntArrayElements(env, data, is_copy);
  memAccess += 9;

  if (values == NULL){
    printf("Cannot obtain array from JVM\n");
    exit(0);
  }
  memAccess += 1;

  int i = 0;
  int j = 0;
  int temp;
  memAccess += 1;

  for(i = 0; i < len; i++){
    j = i;
    while(j>0 && values[j-1] > values[j]){
      temp = values[j];
      values[j] = values[j-1];
      values[j-1] = temp;
      j = j-1;
      memAccess += 17;
    }
    memAccess += 6;
  }
  memAccess += 1;

  memAccess += 14;
  result = (*env)->NewIntArray(env, len+1);
  (*env)->SetIntArrayRegion(env, result, 0, len, values);
  (*env)->SetIntArrayRegion(env, result, len, 1, &memAccess);

  return result;
}