[![Build Status](https://travis-ci.org/MadTribe/jwechat-parent.svg?branch=master)](https://travis-ci.org/MadTribe/jwechat-parent)
# Description

This project intends to provide a flexible and simple Java API for interaction with Wechat Services

The project goals are that it should be:

Modular - A user should not need to depend on features that they don't use.
Widely compatible, although there will be opinionated decisions about some frameworks, by default it will use Guice for dependency injection and an event bus.
Fail fast design.
Should largely avoid breaking as new Wechat features are added.

A major intention is to keep the API up to date with the latest Wechat features.


## Building the Project
The project uses maven so Java8 and Maven3 should  be installed.

In the project folder you can build the jar file with:

    mvn clean package

You can then run the example service with:

    java -jar jwechat-service-example/target/jwechat-service-example-1.0-SNAPSHOT.jar server jwechat-service-example/dev-config.yml


In the /jwechat-service/docker folder there is a script: **compile-and-run.sh**
Which will build the project and deploy to a a local docker service. 

To run this script you need to set the following environment variables:
SAMPLE_WECHAT_APP_ID, 
SAMPLE_WECHAT_APP_SECRET, 
SAMPLE_WECHAT_APP_TOKEN

The script is developed to run in bash on OS-X or Linux but a Windows version should be easy to adapt. 

## Kicking the tires.
Since the project is still in its early days there are a few more manual steps needed than is desirable. Will fix this as we go.
The Jwechat service project is provided as an example wechat service you would normally just use the jwechat-api-core in your own projects.

Developing in Wechat requires a server that the Wechat service can connect to. For kicking the tires, this is a bit inconvenient.

Docker is a convenient way to get things installed and some basic scripts are available to do this.
Localtunnel is a useful tool for exposing your local machine to wechat.

You can easily set up a Sandbox account on Wechat [here](http://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login).

TODO - Improve Notes on configuring Wechat Sandbox

### You will need these common tools:
 - Java8
 - Maven
 - NodeJS
 - NPM
 - LocalTunnel (Node JS Module)
 - DockerMachine


Set the following environment variables on your computer.

SAMPLE_WECHAT_APP_ID to your test app id (match what you set in the sandbox account)

SAMPLE_WECHAT_APP_SECRET to your app secret (match what you set in the sandbox account)

SAMPLE_WECHAT_APP_TOKEN to you app secret (match what you set in the sandbox account)

WECHAT_REQUESTED_SDOMAIN to a subdomain to be used by localtunnel 

Go to jwechat-service-example/docker folder

run compile-and-run.sh  this will deploy the service to your local docker.

run tunnel-to-docker.sh  this will 

configure AppID, AppSecret AppToken in Wechat sandbox backend and set callback url to what tunnel-to-docker.sh tell you.

Scan the QR code for the sandbox account and send yourself a message, you should see it in the docker logs and see a text response to your phone. 


## Example Code

See jwechat-service/src/main/java/org/madtribe/wechat/service/WechatExampleApplication.java

Current version handles text messages and image messages. 

For a simple illustration of how messages from WeChat can be handled.


	@Override
	public void run(WechatExampleConfig configuration, Environment environment) throws Exception {
	
	    WeChatEntryPoint entryPoint = configureEntryPoint(configuration, environment);
	
	    // This is where you can register your WeChat message handlers.
	    // These can obviously be put in other classes as required.
        entryPoint.handle("text", (InboundRequest message) -> {        	
        	
        	  return Response.ok(new TextMessageResponse(message, "An Text Message was received")))).build();
        });
    
        entryPoint.handle("image", (InboundRequest message) -> {
        	
      	  return Response.ok(new TextMessageResponse(message, "An Image Message was received")))).build();
        });
	
	
	}

For a simple illustration of how to use the WeChat active apis.

	// Upload an image and get its media ID
	InputStream is = this.getClass().getResourceAsStream("/ball.jpg");
	Optional<MediaUploadResponse> mediaResponse = wechatAPI.uploadTemporaryMedia(is, MediaType.image);

	// Send an image 
	wechatAPI
		.sendCustomerServiceMessage(new CustomerServiceImageMessage(message.getSender(),																		            mediaResponse.get().getMediaId()));





## Development Methodology
The project uses a Test Driven Development approach. This is highly useful for projects that may see intermittent development.

In Phase one  most development will be done on the master branch.
Before the project is useful to anyone there will normally be one test present in each commit.

The purposes of checking in a failing test are as follows:
1. It prevents someone from wasting time using the when it is not usable. Most casual users will run mvn package and see a test failure and then move on.
2. It is a clear indication to developers on where to begin work.
