# Shinra
An http networking library for Android & iOS

# Sample Usages

### Android (Kotlin)
```kotlin
val url = "https://endpoint-url-here"

Shinra.withURL(url)
    .get(
        onSuccess = { response: HttpCallResponse ->
            // Handle response here...
        },
        onError = { error: Throwable ->
            // Handle error here...
        }
    )
``` 

### iOS (Swift)
```swift
let url = "https://endpoint-url-here"
		
Shinra.Companion.init()
    .withURL(url: url)
    .get(
        onSuccess: { (response: HttpCallResponse) in
            // Handle response here...
        },
        onError: { (error: KotlinThrowable) in
            // Handle error here...
        }
    )
``` 

### Available methods
- `get(onSuccess: (response: HttpCallResponse) -> Unit, onError: (error: Throwable) -> Unit)`
- `post(onSuccess: (response: HttpCallResponse) -> Unit, onError: (error: Throwable) -> Unit)`
- `put(onSuccess: (response: HttpCallResponse) -> Unit, onError: (error: Throwable) -> Unit)`
- `patch(onSuccess: (response: HttpCallResponse) -> Unit, onError: (error: Throwable) -> Unit)`
- `update(onSuccess: (response: HttpCallResponse) -> Unit, onError: (error: Throwable) -> Unit)`
- `delete(onSuccess: (response: HttpCallResponse) -> Unit, onError: (error: Throwable) -> Unit)`


# How to Contribute
TBC
