# JetPackDataStore

Introduction

Today's applications use local data storage in one or another way. In almost every application there would be instances where we need to store data locally, for instance storing the user's settings. Traditionally we were using the SharedPreferences API to store the data in key-value pairs.
SharedPreferences was good to have back in the day, the main drawback of using the SharedPreferences was its synchronous API to read/write operations that are being performed on the UI thread. But now we have a far better solution to store data locally using Jetpack DataStore.

Read more about it on
https://medium.com/@jaydeeppipaliya27/jetpack-datastore-storing-data-asynchronously-c8ae8337fef3
