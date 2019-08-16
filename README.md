# Android Notifications

## Introduction

This project will have you create a page of buttons to display different types of notifications when pressed.

## Instructions
### Part 1 - Basic Notification
1. Create a new empty activity
2. Add a button called "Get Notification"
3. Use `context.getSystemService(Context.NOTIFICATION_SERVICE)` to get a handle on the `NotificationManager`
4. Create a listener for the button.
5. Check the `Build.VERSION.SDK_INT` if it is greater than or equal to `Build.VERSION_CODES.O`, build a channel using `new NotificationChannel(channelId, name, importance)`
6. Add a description using `channel.setDescription`
7. Register the channel using `notificationManager.createNotificationChannel(channel)`
8. Create a `NotificationCompat.Builder`using the `Activity`'s context and the channel id you just used.
9. Set the `Priority`, `ContentTitle`, `ContentText`, `SmallIcon`, `Color`, and `Defaults`
10. Use the `notificationManager.notify` method with `NOTIFICATION_ID_INSTANT` and `builder.build()` to execute the notification
11. Compile and test the app to make sure everything works.

### Part 2 - Importance
1. Change the notification pritory to see the difference it makes, explore changes in both the notification builder and notification channel
2. Build and test with each of these with various combinations

### Part 3 - Content Interaction
1. Create a new Fullscreen Activity
2. Remove the bottom frame and its contents
> Be sure to remove all references to these views in the FulscreenActivity java file as well

3. In the `onCreate` method of the new activity, get the intent and pull a string extra from it. We will be sending that string extra to this activity from our notification
4. Use the string you just got to set the text of the activity's `mContentView`
> You'll have to change its data type from `View` to `TextView` before doing so

5. Go back to your button's onclick listener and create an intent to launch your new activity and add a string extra to it.
> Your string should let you know how the intent was launched in this case it could be "Noticiation Tapped" or "Content Intent"

6. Instead of calling `startActivity` with this intent, we're going to wrap it with a `PendingIntent` object
	a. Call the static method `PendingIntent.getActivity`
	b. pass it your `context`, a request code of 0, the intent you created, and `PendingIntent.FLAG_ONE_SHOT`
	> Thte ONE_SHOT means the intent can only be used once, but since you have AutoCancel set to true, the notification will be destroyed once you execute the intent anyway

7. In your notification builder, set the `ContentIntent` with your new `PendingIntent`
8. Build and test your app to make sure it launches the new activity and displays your string

### Part 4 - Action Buttons
1. Create another `PendingIntent` with a different string extra.
> The string extra in this intent must use the same key as the one for your other intent as your FullscreenActivity won't differentiate.
> Also be sure that this `PendingIntent` has a different `Request Code` than your other intent

2. Add an `Action` to your notification builder. Pass it a drawable id for an icon, a title, and your pending intent
3. Build and test your app to make sure it launches the new activity and displays your string.

## Challenge
In your testing, find bugs and small feature improvements that can improve the polish of your app..
