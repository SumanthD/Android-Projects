// movieInfo.aidl
package com.example.movieclient;

// Declare any non-default types here with import statements

interface movieInfo {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    Map getall();
    Map getone(int number);
    String getUrl(int number);
}