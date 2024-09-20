// IMyAidlInterface.aidl
package com.example.myserviceaidl;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int calculateData(int firstValue,int secondValue,int operationToPerform);
}