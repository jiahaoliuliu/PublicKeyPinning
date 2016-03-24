# PublicKeyPinning
A sample of how to implement public key pinning in Android using the Random.org services

The slides could be found here:
http://www.jiahaoliuliu.com/2016/03/public-key-pinning-in-android.html

To use this app do the follow:

I. Go to Random.org and request your API Key

https://api.random.org/api-keys/beta

It could be something like: 998b69a7-f93d-4edf-8fc0-f5aa29ca04e0

II. Create a new class called APIKeys under the package ```com.jiahaoliuliu.publickeypinning```

III. Create a new variable named RANDOM_ORG_API_KEY which the value is the API key. i.e.

```public static final String RANDOM_ORG_API_KEY = "998b69a7-f93d-4edf-8fc0-f5aa29ca04e0";```

IV. Run!

The Application contains seveal models and two main classes:

1. ```MainActivity``` which contains a simple TextView as layout and as button. The user can click on the text to request to random.org a number between 1 and 100000.

2. ```PubKeyManager``` which is used by Volley to check the SSL connection. It contains the public key.

Under the hood, the app uses Volley to connect to the Random.org server and check the server's public key once connected. Only if the public key is the one embedded inside the app, the connection will be established.

For any question, send a mail to jiahaoliuliu@gmail.com
